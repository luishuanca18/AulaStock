# AulaStock

Sistema de escritorio para ventas e inventario desarrollado con Java 17, Swing, Maven y SQL Server.

## Requisitos

- JDK 17 o superior.
- Apache Maven (IntelliJ IDEA o NetBeans pueden usar Maven integrado).
- SQL Server y SQL Server Management Studio.

## Preparar la base de datos

1. Abre `database/AulaStock.sql` en SQL Server Management Studio.
2. Ejecuta todo el archivo. Creará la base, sus tablas, relaciones, datos iniciales y procedimientos almacenados.
3. Abre `database/crear_usuario_local.example.sql`.
4. Cambia el texto de contraseña de ejemplo por una contraseña local y ejecuta el archivo.

## Configurar la conexión local

1. Copia `src/main/resources/database.properties.example`.
2. Nombra la copia `database.properties`.
3. Escribe en esa copia tu usuario y contraseña locales de SQL Server.

`database.properties` está ignorado por Git para impedir que una contraseña llegue a GitHub.

## Ejecutar el proyecto

Abre la carpeta como proyecto Maven en IntelliJ IDEA o NetBeans. Maven descargará automáticamente el controlador JDBC de SQL Server definido en `pom.xml`.

## Trabajo del equipo

Cada integrante debe crear una rama para su módulo:

```bash
git pull
git checkout -b modulo/nombre-del-modulo
git add .
git commit -m "Agrega modulo de productos"
git push -u origin modulo/nombre-del-modulo
```

Después debe crear un Pull Request en GitHub. La rama principal se actualiza únicamente al revisar y aceptar ese Pull Request.
