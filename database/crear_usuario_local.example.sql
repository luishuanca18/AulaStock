/*
    Copia este archivo con otro nombre local y cambia la contrasena.
    No escribas una contrasena real en un archivo que subirás a GitHub.
*/

USE master;
GO

IF SUSER_ID(N'aulaStock_user') IS NULL
BEGIN
    CREATE LOGIN aulaStock_user
    WITH PASSWORD = 'CAMBIAR_POR_UNA_CONTRASENA_LOCAL';
END;
GO

USE AulaStock;
GO

IF USER_ID(N'aulaStock_user') IS NULL
BEGIN
    CREATE USER aulaStock_user FOR LOGIN aulaStock_user;
END;
GO

IF IS_ROLEMEMBER(N'db_datareader', N'aulaStock_user') <> 1
    ALTER ROLE db_datareader ADD MEMBER aulaStock_user;
GO

IF IS_ROLEMEMBER(N'db_datawriter', N'aulaStock_user') <> 1
    ALTER ROLE db_datawriter ADD MEMBER aulaStock_user;
GO

GRANT EXECUTE ON SCHEMA::dbo TO aulaStock_user;
GO

PRINT 'Usuario local de AulaStock configurado correctamente.';
GO
