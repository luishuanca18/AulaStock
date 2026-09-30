# Guía de trabajo para AulaStock

## Paquetes

- `com.aulastock.modelo`: clases que representan los datos.
- `com.aulastock.dao`: consultas y procedimientos de SQL Server.
- `com.aulastock.vista`: ventanas Swing.
- `com.aulastock.util`: conexión y utilidades compartidas.
- `com.aulastock.app`: clase principal.

## Nombres acordados

- Clases: `Producto`, `ProductoDAO`, `FrmProductos`.
- Variables y métodos: empiezan en minúscula, por ejemplo `productoNuevo` y `listarProductos()`.
- Controles Swing: `txtNombre`, `btnGuardar`, `tblProductos`, `chkActivo`.
- Atributos de modelos: deben coincidir con el concepto de la columna, por ejemplo `idProducto`, `nombre`, `precio`.

Antes de crear una clase, atributo o método, busca si ya existe. Reutiliza los modelos y la clase `ConexionSQL` compartidos.

## Reglas para formularios NetBeans

- Modifica el diseño desde la pestaña Design de NetBeans.
- No edites manualmente el contenido protegido de `initComponents()`.
- Sube juntos los archivos `.java` y `.form` de cada ventana.

## Git

- Una rama por módulo o cambio.
- Antes de comenzar: `git pull`.
- No subas contraseñas, archivos `database.properties`, carpetas `target` ni configuración personal del IDE.
- Haz commits pequeños con mensajes claros.
- Crea un Pull Request y pide revisión antes de unirlo con la rama principal.
