# AulaStock

Sistema de escritorio para ventas e inventario desarrollado con Java 17, Swing, Maven y SQL Server.

## Requisitos

- JDK 17 o superior.
- Apache Maven (IntelliJ IDEA o NetBeans pueden usar Maven integrado).
- SQL Server y SQL Server Management Studio.

## Preparar la base de datos

1. Abre `database/AulaStock.sql` en SQL Server Management Studio.
2. Ejecuta todo el archivo. Creará la base, sus tablas, relaciones, datos iniciales y procedimientos almacenados.
3. Abre el script de creación del usuario SQL incluido en la carpeta `database`.
4. Ejecuta el archivo para crear el usuario `aulaStock_user`.

## Configurar la conexión

Abre `src/main/java/com/aulastock/util/ConexionSQL.java` y localiza:

## Ejecutar el proyecto

Abre la carpeta como proyecto Maven en IntelliJ IDEA o NetBeans. Maven descargará automáticamente el controlador JDBC de SQL Server definido en `pom.xml`.

## Trabajo del equipo

Cada integrante debe crear una rama para su módulo:

```bash
git pull
git checkout -b modulo/nombre-del-modulo
git add .
git commit -m "Agrega modulo de productos"
git push  origin modulo/nombre-del-modulo
```

Después debe crear un Pull Request en GitHub. La rama principal se actualiza únicamente al revisar y aceptar ese Pull Request.
