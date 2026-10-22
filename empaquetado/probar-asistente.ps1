$ErrorActionPreference = 'Stop'
$tokensPrueba = $null
$erroresPrueba = $null
$arbolPrueba = [Management.Automation.Language.Parser]::ParseFile(
    (Join-Path $PSScriptRoot 'PrepararAulaStock.ps1'), [ref]$tokensPrueba, [ref]$erroresPrueba)
if ($erroresPrueba.Count) { throw 'El asistente contiene errores de sintaxis.' }

# Carga solo las funciones. No muestra ventanas ni accede a SQL Server o servicios.
$funcionesPrueba = $arbolPrueba.FindAll({ param($nodo) $nodo -is [Management.Automation.Language.FunctionDefinitionAst] }, $true)
foreach ($funcionPrueba in $funcionesPrueba) { Invoke-Expression $funcionPrueba.Extent.Text }

function Comprobar($condicion, [string]$mensaje) {
    if (-not $condicion) { throw $mensaje }
}

function Reiniciar-Escenario {
    $script:existe = $true
    $script:columnaFaltante = $false
    $script:procedimientoAntiguo = $false
    $script:aceptarActualizacion = $false
    $script:archivosEjecutados = New-Object Collections.Generic.List[string]
    $script:comandosEjecutados = New-Object Collections.Generic.List[string]
    $script:confirmaciones = 0
}

function Consultar-Valor($conexion, [string]$consulta) {
    if ($consulta -match 'DB_ID') { if ($script:existe) { return 7 } else { return $null } }
    if ($consulta -match 'COL_LENGTH') {
        if ($script:columnaFaltante -and $consulta -match 'numeroDocumento') { return $null }
        return 20
    }
    if ($consulta -match "dbo\.(usp_\w+)") {
        $nombrePrueba = $matches[1]
        if ($consulta -match 'is_output') {
            if ($script:procedimientoAntiguo -and $nombrePrueba -eq 'usp_registrar_venta') { return 1 }
            return 0
        }
        if ($consulta -match 'COUNT') {
            $parametrosPrueba = @{
                usp_registrar_categoria=3; usp_actualizar_categoria=4; usp_eliminar_categoria=1
                usp_listar_productos=0; usp_guardar_productos=7; usp_actualizar_productos=7; usp_eliminar_productos=1
                usp_listar_inventario=3; usp_registrar_movimiento=4; usp_registrar_venta=5; usp_registrar_detalle_venta=4
            }
            if ($script:procedimientoAntiguo -and $nombrePrueba -eq 'usp_registrar_venta') { return 6 }
            return $parametrosPrueba[$nombrePrueba]
        }
        return 100
    }
    throw ('Consulta inesperada: ' + $consulta)
}

function Ejecutar-Sql($conexion, [string]$consulta) { $script:comandosEjecutados.Add($consulta) }
function Ejecutar-Archivo($conexion, [string]$archivo) {
    $script:archivosEjecutados.Add((Split-Path $archivo -Leaf))
    if ((Split-Path $archivo -Leaf) -eq 'AulaStock.sql') { $script:existe = $true }
    if ((Split-Path $archivo -Leaf) -eq 'procedimientos_completos.sql') { $script:procedimientoAntiguo = $false }
}
function Confirmar-ActualizacionProcedimientos([string[]]$pendientes) {
    $script:confirmaciones++
    return $script:aceptarActualizacion
}
function Esperar-Fallo([scriptblock]$accion, [string]$mensaje) {
    try { & $accion } catch {
        Comprobar ($_.Exception.Message -like ('*' + $mensaje + '*')) ('Error inesperado: ' + $_.Exception.Message)
        return
    }
    throw 'Se esperaba que la operacion se detuviera.'
}

Reiniciar-Escenario
Preparar-BaseAulaStock $null 'existente' $PSScriptRoot
Comprobar ($script:archivosEjecutados.Count -eq 0) 'Reutilizar una base compatible no debe ejecutar archivos SQL.'
Comprobar ($script:comandosEjecutados.Count -eq 1 -and $script:comandosEjecutados[0] -eq 'USE AulaStock;') 'No debe modificar filas ni tablas existentes.'

Reiniciar-Escenario
Esperar-Fallo { Preparar-BaseAulaStock $null 'crear' $PSScriptRoot } 'Ya existe AulaStock'
Comprobar ($script:archivosEjecutados.Count -eq 0) 'No debe recrear una base existente.'

Reiniciar-Escenario
$script:existe = $false
Preparar-BaseAulaStock $null 'crear' $PSScriptRoot
Comprobar (($script:archivosEjecutados -join ',') -eq 'AulaStock.sql,procedimientos_completos.sql') 'Crear una base nueva requiere los dos scripts en orden.'

Reiniciar-Escenario
$script:existe = $false
Esperar-Fallo { Preparar-BaseAulaStock $null 'existente' $PSScriptRoot } 'No existe una base'
Comprobar ($script:archivosEjecutados.Count -eq 0) 'No debe crear una base al seleccionar existente.'

Reiniciar-Escenario
$script:columnaFaltante = $true
Esperar-Fallo { Preparar-BaseAulaStock $null 'existente' $PSScriptRoot } 'numeroDocumento'
Comprobar ($script:archivosEjecutados.Count -eq 0) 'Una columna faltante no autoriza recrear tablas.'

Reiniciar-Escenario
$script:procedimientoAntiguo = $true
Esperar-Fallo { Preparar-BaseAulaStock $null 'existente' $PSScriptRoot } 'Se cancelo'
Comprobar ($script:archivosEjecutados.Count -eq 0 -and $script:confirmaciones -eq 1) 'Sin confirmacion no se actualizan procedimientos.'

Reiniciar-Escenario
$script:procedimientoAntiguo = $true
$script:aceptarActualizacion = $true
Preparar-BaseAulaStock $null 'existente' $PSScriptRoot
Comprobar (($script:archivosEjecutados -join ',') -eq 'procedimientos_completos.sql') 'Solo deben actualizarse los procedimientos autorizados.'
Comprobar ($script:confirmaciones -eq 1) 'Debe pedir confirmacion para actualizar procedimientos.'

Write-Output 'ASISTENTE_OK: siete escenarios simulados; reutilizacion, creacion, proteccion de datos, estructura y actualizacion autorizada de procedimientos.'
