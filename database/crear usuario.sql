USE master;
GO

IF SUSER_ID(N'aulaStock_user') IS NULL
BEGIN
    CREATE LOGIN aulaStock_user
    WITH PASSWORD = 'proyecto2026#sql';
END;
GO

USE AulaStock;
GO

IF USER_ID(N'aulaStock_user') IS NULL
BEGIN
    CREATE USER aulaStock_user
    FOR LOGIN aulaStock_user;
END;
GO

ALTER ROLE db_datareader
ADD MEMBER aulaStock_user;
GO

ALTER ROLE db_datawriter
ADD MEMBER aulaStock_user;
GO

GRANT EXECUTE ON SCHEMA::dbo
TO aulaStock_user;
GO