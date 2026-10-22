param([string]$ConfigDirectory = (Join-Path $env:LOCALAPPDATA 'AulaStock'))

$ErrorActionPreference = 'Stop'
Add-Type -AssemblyName System.Windows.Forms
Add-Type -AssemblyName System.Drawing

function Mostrar-Error([string]$texto) {
    [void][System.Windows.Forms.MessageBox]::Show($texto, 'Preparacion de AulaStock', 'OK', 'Error')
}

function Abrir-Conexion([string]$servidor, [string]$base, [string]$usuario = '', [string]$clave = '') {
    $cadena = New-Object System.Data.SqlClient.SqlConnectionStringBuilder
    $cadena['Data Source'] = $servidor
    $cadena['Initial Catalog'] = $base
    $cadena['Connect Timeout'] = 15
    $cadena['TrustServerCertificate'] = $true
    if ($usuario) {
        $cadena['User ID'] = $usuario
        $cadena['Password'] = $clave
    } else {
        $cadena['Integrated Security'] = $true
    }
    $conexion = New-Object System.Data.SqlClient.SqlConnection $cadena.ConnectionString
    $conexion.Open()
    return $conexion
}

function Consultar-Valor($conexion, [string]$consulta) {
    $comando = $conexion.CreateCommand()
    try {
        $comando.CommandText = $consulta
        $valor = $comando.ExecuteScalar()
        if ($valor -is [DBNull]) { return $null }
        return $valor
    }
    finally { $comando.Dispose() }
}

function Ejecutar-Sql($conexion, [string]$consulta) {
    $comando = $conexion.CreateCommand()
    try {
        $comando.CommandText = $consulta
        $comando.CommandTimeout = 120
        [void]$comando.ExecuteNonQuery()
    } finally { $comando.Dispose() }
}

function Ejecutar-Archivo($conexion, [string]$archivo) {
    $contenido = [IO.File]::ReadAllText($archivo)
    foreach ($lote in [regex]::Split($contenido, '(?im)^\s*GO\s*$')) {
        if ($lote.Trim()) { Ejecutar-Sql $conexion $lote }
    }
}

function Comprobar-TablasAulaStock($conexion) {
    $tablas = [ordered]@{
        CATEGORIA = @('idCategoria','nombre','descripcion','estado')
        cliente = @('idCliente','dni','nombres','apellidos','telefono','correo','direccion','estado')
        producto = @('idProducto','codigo','nombre','idCategoria','precio','stock','stockMinimo','estado')
        venta = @('idVenta','fecha','idCliente','total','tipoDocumento','numeroDocumento')
        detalleVenta = @('idDetalle','idVenta','idProducto','cantidad','precioUnitario','subtotal')
        movimientoInventario = @('idMovimiento','idProducto','tipo','cantidad','motivo','fecha')
    }
    $faltantes = @()
    foreach ($tabla in $tablas.Keys) {
        foreach ($columna in $tablas[$tabla]) {
            if ($null -eq (Consultar-Valor $conexion "SELECT COL_LENGTH(N'dbo.$tabla', N'$columna')")) {
                $faltantes += "$tabla.$columna"
            }
        }
    }
    if ($faltantes.Count -gt 0) {
        throw ('La base existente no tiene la estructura necesaria. No se modificaron sus tablas ni datos. Faltan: ' + ($faltantes -join ', '))
    }
}

function Obtener-ProcedimientosPendientes($conexion) {
    $procedimientos = [ordered]@{
        usp_registrar_categoria = 3
        usp_actualizar_categoria = 4
        usp_eliminar_categoria = 1
        usp_listar_productos = 0
        usp_guardar_productos = 7
        usp_actualizar_productos = 7
        usp_eliminar_productos = 1
        usp_listar_inventario = 3
        usp_registrar_movimiento = 4
        usp_registrar_venta = 5
        usp_registrar_detalle_venta = 4
    }
    foreach ($nombre in $procedimientos.Keys) {
        $idProcedimiento = Consultar-Valor $conexion "SELECT OBJECT_ID(N'dbo.$nombre', N'P')"
        if ($null -eq $idProcedimiento) {
            $nombre
        } else {
            $cantidad = [int](Consultar-Valor $conexion "SELECT COUNT(*) FROM sys.parameters WHERE object_id = OBJECT_ID(N'dbo.$nombre', N'P')")
            $salidas = [int](Consultar-Valor $conexion "SELECT COUNT(*) FROM sys.parameters WHERE object_id = OBJECT_ID(N'dbo.$nombre', N'P') AND is_output = 1")
            if ($cantidad -ne $procedimientos[$nombre] -or $salidas -ne 0) { $nombre }
        }
    }
}

function Confirmar-ActualizacionProcedimientos([string[]]$pendientes) {
    $respuesta = [Windows.Forms.MessageBox]::Show(
        "Se encontraron procedimientos faltantes o con parametros distintos:`r`n$($pendientes -join ', ')`r`n`r`nDesea instalar la version incluida de los 11 procedimientos? No se recrearan tablas ni se cargaran ejemplos. Las definiciones de esos procedimientos se actualizaran.",
        'Actualizar procedimientos de AulaStock', 'YesNo', 'Question')
    return $respuesta -eq 'Yes'
}

function Preparar-BaseAulaStock($conexion, [string]$modo, [string]$carpetaSql) {
    $existe = $null -ne (Consultar-Valor $conexion "SELECT DB_ID(N'AulaStock')")
    if ($modo -eq 'crear') {
        if ($existe) { throw 'Ya existe AulaStock. No se borro ni se modifico. Seleccione Usar la base existente.' }
        Ejecutar-Archivo $conexion (Join-Path $carpetaSql 'AulaStock.sql')
        Ejecutar-Archivo $conexion (Join-Path $carpetaSql 'procedimientos_completos.sql')
    } elseif ($modo -eq 'existente') {
        if (-not $existe) { throw 'No existe una base AulaStock en esta instancia. Seleccione Crear una base nueva.' }
        Ejecutar-Sql $conexion 'USE AulaStock;'
        Comprobar-TablasAulaStock $conexion
        $pendientes = @(Obtener-ProcedimientosPendientes $conexion)
        if ($pendientes.Count -gt 0) {
            if (-not (Confirmar-ActualizacionProcedimientos $pendientes)) {
                throw 'Se cancelo la preparacion. No se actualizaron los procedimientos.'
            }
            Ejecutar-Archivo $conexion (Join-Path $carpetaSql 'procedimientos_completos.sql')
        }
    } else {
        throw 'Seleccione un modo de preparacion valido.'
    }
    Comprobar-TablasAulaStock $conexion
    $pendientes = @(Obtener-ProcedimientosPendientes $conexion)
    if ($pendientes.Count -gt 0) { throw ('Faltan procedimientos compatibles: ' + ($pendientes -join ', ')) }
}

try {
    $identidad = [Security.Principal.WindowsIdentity]::GetCurrent()
    $principal = New-Object Security.Principal.WindowsPrincipal $identidad
    if (-not $principal.IsInRole([Security.Principal.WindowsBuiltInRole]::Administrator)) {
        $powershellExe = Join-Path $env:WINDIR 'System32\WindowsPowerShell\v1.0\powershell.exe'
        $argumentos = '-NoProfile -STA -ExecutionPolicy Bypass -WindowStyle Hidden -File "' + $PSCommandPath + '" -ConfigDirectory "' + $ConfigDirectory + '"'
        $elevado = Start-Process -FilePath $powershellExe -ArgumentList $argumentos -Verb RunAs -WindowStyle Hidden -Wait -PassThru
        exit $elevado.ExitCode
    }

    $servicios = @(Get-Service | Where-Object { $_.Name -eq 'MSSQLSERVER' -or $_.Name -like 'MSSQL$*' })
    if ($servicios.Count -eq 0) { throw 'No se encontro SQL Server instalado en este equipo.' }

    $formulario = New-Object Windows.Forms.Form
    $formulario.Text = 'Preparar AulaStock'
    $formulario.ClientSize = New-Object Drawing.Size(570, 390)
    $formulario.StartPosition = 'CenterScreen'
    $formulario.FormBorderStyle = 'FixedDialog'
    $formulario.MaximizeBox = $false
    $formulario.MinimizeBox = $false
    $formulario.Font = New-Object Drawing.Font('Segoe UI', 10)
    $formulario.BackColor = [Drawing.Color]::White
    $titulo = New-Object Windows.Forms.Label
    $titulo.Text = 'Conectar AulaStock'
    $titulo.SetBounds(25, 20, 520, 35)
    $titulo.Font = New-Object Drawing.Font('Segoe UI', 17, [Drawing.FontStyle]::Bold)
    $formulario.Controls.Add($titulo)
    $texto = New-Object Windows.Forms.Label
    $texto.Text = "Seleccione la instancia local y como desea comenzar. Usar una base existente conserva sus tablas, registros y stock.`r`n`r`nSe necesita una cuenta de Windows administradora de SQL Server."
    $texto.SetBounds(25, 65, 520, 95)
    $formulario.Controls.Add($texto)
    $selector = New-Object Windows.Forms.ComboBox
    $selector.DropDownStyle = 'DropDownList'
    $selector.SetBounds(25, 167, 520, 30)
    foreach ($servicio in $servicios) {
        $nombre = if ($servicio.Name -eq 'MSSQLSERVER') { 'localhost' } else { 'localhost\' + $servicio.Name.Substring(6) }
        [void]$selector.Items.Add($nombre)
    }
    $selector.SelectedIndex = 0
    $formulario.Controls.Add($selector)
    $usarExistente = New-Object Windows.Forms.RadioButton
    $usarExistente.Text = 'Usar la base AulaStock existente'
    $usarExistente.Checked = $true
    $usarExistente.SetBounds(25, 222, 520, 28)
    $formulario.Controls.Add($usarExistente)
    $crearNueva = New-Object Windows.Forms.RadioButton
    $crearNueva.Text = 'Crear una base nueva con datos de ejemplo'
    $crearNueva.SetBounds(25, 258, 520, 28)
    $formulario.Controls.Add($crearNueva)
    $boton = New-Object Windows.Forms.Button
    $boton.Text = 'Continuar'
    $boton.SetBounds(405, 330, 140, 36)
    $boton.DialogResult = 'OK'
    $boton.BackColor = [Drawing.Color]::FromArgb(18,124,104)
    $boton.ForeColor = [Drawing.Color]::White
    $boton.FlatStyle = 'Flat'
    $formulario.Controls.Add($boton)
    $formulario.AcceptButton = $boton
    if ($formulario.ShowDialog() -ne 'OK') { exit 1 }
    $servidor = [string]$selector.SelectedItem
    $modoBase = if ($usarExistente.Checked) { 'existente' } else { 'crear' }
    $instancia = if ($servidor -eq 'localhost') { 'MSSQLSERVER' } else { $servidor.Substring(10) }
    $nombreServicio = if ($instancia -eq 'MSSQLSERVER') { 'MSSQLSERVER' } else { 'MSSQL$' + $instancia }
    $formulario.Dispose()

    $servicio = Get-Service -Name $nombreServicio
    if ($servicio.Status -ne 'Running') { Start-Service -Name $nombreServicio }
    $admin = Abrir-Conexion $servidor 'master'
    try {
        if ([int](Consultar-Valor $admin "SELECT IS_SRVROLEMEMBER('sysadmin')") -ne 1) {
            throw 'La cuenta de Windows no es administradora de SQL Server. Ejecute la preparacion con una cuenta con permisos sysadmin.'
        }
        $baseExistente = $null -ne (Consultar-Valor $admin "SELECT DB_ID(N'AulaStock')")
        if ($modoBase -eq 'crear' -and $baseExistente) {
            throw 'Ya existe AulaStock. No se borro ni se modifico. Seleccione Usar la base existente.'
        }
        if ($modoBase -eq 'existente' -and -not $baseExistente) {
            throw 'No existe una base AulaStock en esta instancia. Seleccione Crear una base nueva.'
        }
        if ($modoBase -eq 'existente') {
            Ejecutar-Sql $admin 'USE AulaStock;'
            Comprobar-TablasAulaStock $admin
        }
        $modoWindows = [int](Consultar-Valor $admin "SELECT SERVERPROPERTY('IsIntegratedSecurityOnly')")
        $mapa = Get-ItemProperty -LiteralPath 'HKLM:\SOFTWARE\Microsoft\Microsoft SQL Server\Instance Names\SQL'
        $idInstancia = $mapa.PSObject.Properties[$instancia].Value
        if (-not $idInstancia) { throw 'No se pudo localizar la configuracion de la instancia SQL Server seleccionada.' }
        $rutaInstancia = 'HKLM:\SOFTWARE\Microsoft\Microsoft SQL Server\' + $idInstancia
        $rutaTcp = $rutaInstancia + '\MSSQLServer\SuperSocketNetLib\Tcp'
        $tcp = Get-ItemProperty -LiteralPath $rutaTcp
        $ipAll = Get-ItemProperty -LiteralPath ($rutaTcp + '\IPAll')
        $requiereReinicio = $modoWindows -eq 1 -or $tcp.Enabled -ne 1 -or $tcp.ListenOnAllIPs -ne 1
        $puerto = [string]$ipAll.TcpPort
        if (-not $puerto) { $puerto = [string]$ipAll.TcpDynamicPorts }
        if ($puerto -notmatch '^\d+$' -or [int]$puerto -eq 0) {
            $puerto = '14333'
            $ocupado = Get-NetTCPConnection -LocalPort 14333 -State Listen -ErrorAction SilentlyContinue
            if ($ocupado) { throw 'El puerto local 14333 esta ocupado. Configure otro puerto para la instancia seleccionada.' }
            $requiereReinicio = $true
        }
        if ($requiereReinicio) {
            $respuesta = [Windows.Forms.MessageBox]::Show(
                "Para conectar AulaStock se habilitara TCP y autenticacion mixta en $servidor. SQL Server se reiniciara y sus conexiones activas se interrumpiran brevemente. No se abriran reglas del firewall.`r`n`r`nDesea continuar?",
                'Configurar SQL Server', 'YesNo', 'Warning')
            if ($respuesta -ne 'Yes') { exit 1 }
            $admin.Close()
            Set-ItemProperty -LiteralPath ($rutaInstancia + '\MSSQLServer') -Name LoginMode -Value 2
            Set-ItemProperty -LiteralPath $rutaTcp -Name Enabled -Value 1
            Set-ItemProperty -LiteralPath $rutaTcp -Name ListenOnAllIPs -Value 1
            Set-ItemProperty -LiteralPath ($rutaTcp + '\IPAll') -Name TcpDynamicPorts -Value ''
            Set-ItemProperty -LiteralPath ($rutaTcp + '\IPAll') -Name TcpPort -Value $puerto
            Restart-Service -Name $nombreServicio -Force
            $admin = $null
            for ($intento=0; $intento -lt 15; $intento++) {
                try { $admin = Abrir-Conexion $servidor 'master'; break } catch { Start-Sleep -Seconds 1 }
            }
            if (-not $admin) { throw 'SQL Server no respondio despues del reinicio.' }
        }

        $carpetaSql = Join-Path $PSScriptRoot 'database'
        Preparar-BaseAulaStock $admin $modoBase $carpetaSql
        $usuario = 'aulaStockDemo_' + [Guid]::NewGuid().ToString('N').Substring(0,8)
        $clave = 'Aa1!' + [Guid]::NewGuid().ToString('N')
        Ejecutar-Sql $admin ("USE master; CREATE LOGIN [$usuario] WITH PASSWORD = '$clave', CHECK_POLICY = ON; USE AulaStock; CREATE USER [$usuario] FOR LOGIN [$usuario]; ALTER ROLE db_datareader ADD MEMBER [$usuario]; ALTER ROLE db_datawriter ADD MEMBER [$usuario]; GRANT EXECUTE ON SCHEMA::dbo TO [$usuario];")
        $verificar = Abrir-Conexion ('tcp:localhost,' + $puerto) 'AulaStock' $usuario $clave
        try {
            Comprobar-TablasAulaStock $verificar
            $pendientes = @(Obtener-ProcedimientosPendientes $verificar)
            if ($pendientes.Count -gt 0) { throw 'No se puede acceder a todos los procedimientos necesarios.' }
            if ($modoBase -eq 'crear' -and [int](Consultar-Valor $verificar 'SELECT COUNT(*) FROM dbo.detalleVenta') -ne 20) {
                throw 'No se cargaron todos los detalles de ejemplo.'
            }
        } finally { $verificar.Close() }
        [void][IO.Directory]::CreateDirectory($ConfigDirectory)
        $archivo = Join-Path $ConfigDirectory 'database.properties'
        $config = "url=jdbc:sqlserver://localhost:$puerto;databaseName=AulaStock;encrypt=true;trustServerCertificate=true;`r`nusuario=$usuario`r`nclave=$clave`r`n"
        [IO.File]::WriteAllText($archivo, $config, [Text.Encoding]::ASCII)
        $mensajeFinal = if ($modoBase -eq 'existente') { 'Se configuro la conexion a la base existente. Sus registros y stock se conservaron. Se abrira AulaStock.' } else { 'AulaStock esta preparado con datos de ejemplo. Se abrira la pantalla principal.' }
        [void][Windows.Forms.MessageBox]::Show($mensajeFinal, 'AulaStock', 'OK', 'Information')
    } finally {
        if ($admin) { $admin.Dispose() }
    }
    exit 0
} catch {
    Mostrar-Error ($_.Exception.Message + "`r`n`r`nNo se guardo la configuracion de inicio. Si la creacion de la base ya habia comenzado, revise su estado antes de reintentar.")
    exit 1
}
