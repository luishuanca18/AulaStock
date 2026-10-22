$ErrorActionPreference = 'Stop'
$raiz = Split-Path $PSScriptRoot -Parent
$trabajo = Join-Path $raiz ('target\empaquetado\build-' + (Get-Date -Format 'yyyyMMdd-HHmmss'))
$clases = Join-Path $trabajo 'clases'
$entrada = Join-Path $trabajo 'entrada'
$distribucion = Join-Path $raiz ('dist\AulaStock-' + (Get-Date -Format 'yyyyMMdd-HHmmss'))
$jdkBin = Split-Path (Get-Command javac.exe).Source
$wix = Join-Path $raiz 'target\empaquetado\herramientas\wix'
$layout = Join-Path $raiz 'lib\unknown\binary\AbsoluteLayout\SNAPSHOT\AbsoluteLayout-SNAPSHOT.jar'
$jdbc = Join-Path $env:USERPROFILE '.m2\repository\com\microsoft\sqlserver\mssql-jdbc\13.6.0.jre11\mssql-jdbc-13.6.0.jre11.jar'

foreach ($necesario in @($layout, $jdbc, (Join-Path $jdkBin 'jpackage.exe'))) {
    if (-not (Test-Path -LiteralPath $necesario)) { throw "No se encontro: $necesario" }
}
if (-not (Test-Path -LiteralPath (Join-Path $wix 'candle.exe'))) {
    $herramientas = Join-Path $raiz 'target\empaquetado\herramientas'
    New-Item -ItemType Directory -Force -Path $herramientas | Out-Null
    $archivoWix = Join-Path $herramientas 'wix314-binaries.zip'
    Invoke-WebRequest -Uri 'https://github.com/wixtoolset/wix3/releases/download/wix3141rtm/wix314-binaries.zip' -OutFile $archivoWix
    Expand-Archive -LiteralPath $archivoWix -DestinationPath $wix -Force
}
$env:PATH = $wix + ';' + $env:PATH
New-Item -ItemType Directory -Force -Path $clases,$entrada,$distribucion | Out-Null

# Compila una copia para Windows. Los archivos Java del alumno no se modifican.
$conexionInstalada = Join-Path $trabajo 'ConexionSQL.java'
Copy-Item -LiteralPath (Join-Path $PSScriptRoot 'ConexionSQLInstalada.java.template') -Destination $conexionInstalada
$fuentes = @(Get-ChildItem (Join-Path $raiz 'src\main\java') -Recurse -Filter *.java |
    Where-Object { $_.Name -ne 'ConexionSQL.java' } | Select-Object -ExpandProperty FullName)
$fuentes += $conexionInstalada
$fuentes += (Join-Path $PSScriptRoot 'src\com\aulastock\instalacion\InicioInstalado.java')
& (Join-Path $jdkBin 'javac.exe') --release 17 -encoding UTF-8 -cp "$layout;$jdbc" -d $clases $fuentes
if ($LASTEXITCODE -ne 0) { throw 'Fallo la compilacion de AulaStock.' }
Copy-Item -Path (Join-Path $raiz 'src\main\resources\*') -Destination $clases -Recurse -Force
# El paquete usa la configuracion creada en el equipo destino, no archivos locales de desarrollo.
$configLocal = Join-Path $clases 'database.properties'
if (Test-Path -LiteralPath $configLocal) { Remove-Item -LiteralPath $configLocal }
$manifiesto = Join-Path $trabajo 'MANIFEST.MF'
[IO.File]::WriteAllText($manifiesto, "Manifest-Version: 1.0`r`nMain-Class: com.aulastock.instalacion.InicioInstalado`r`nClass-Path: mssql-jdbc-13.6.0.jre11.jar AbsoluteLayout-SNAPSHOT.jar`r`n`r`n", [Text.Encoding]::ASCII)
& (Join-Path $jdkBin 'jar.exe') --create --file (Join-Path $entrada 'AulaStock.jar') --manifest $manifiesto -C $clases .
if ($LASTEXITCODE -ne 0) { throw 'Fallo la creacion del JAR.' }
Copy-Item -LiteralPath $jdbc,$layout -Destination $entrada
Copy-Item -LiteralPath (Join-Path $PSScriptRoot 'PrepararAulaStock.ps1') -Destination $entrada
Copy-Item -LiteralPath (Join-Path $PSScriptRoot 'LEEME.txt') -Destination $entrada
$sqlDestino = Join-Path $entrada 'database'
New-Item -ItemType Directory -Force -Path $sqlDestino | Out-Null
Copy-Item -LiteralPath (Join-Path $raiz 'database\AulaStock.sql'),(Join-Path $raiz 'database\procedimientos_completos.sql') -Destination $sqlDestino

& (Join-Path $jdkBin 'jpackage.exe') --type app-image --name AulaStock --app-version 1.0.1 --vendor 'Grupo AulaStock - Cibertec' --description 'Sistema academico de ventas e inventarios' --input $entrada --main-jar AulaStock.jar --main-class com.aulastock.instalacion.InicioInstalado --icon (Join-Path $PSScriptRoot 'AulaStock.ico') --java-options '--enable-native-access=ALL-UNNAMED' --dest $distribucion
if ($LASTEXITCODE -ne 0) { throw 'Fallo la creacion de la aplicacion portable.' }
& (Join-Path $jdkBin 'jpackage.exe') --type exe --name AulaStock --app-version 1.0.1 --vendor 'Grupo AulaStock - Cibertec' --description 'Sistema academico de ventas e inventarios' --app-image (Join-Path $distribucion 'AulaStock') --win-per-user-install --win-menu --win-shortcut --win-dir-chooser --win-upgrade-uuid '187edcc7-253b-413c-aab0-fd341a766397' --dest $distribucion
if ($LASTEXITCODE -ne 0) { throw 'Fallo la creacion del instalador.' }
Copy-Item -LiteralPath (Join-Path $PSScriptRoot 'LEEME.txt') -Destination $distribucion
Get-ChildItem -LiteralPath $distribucion -Filter *.exe | Select-Object FullName,Length
Write-Output ('DISTRIBUCION=' + $distribucion)
