DROP DATABASE IF EXISTS cajeroAutomatico;
CREATE DATABASE cajeroAutomatico;
USE cajeroAutomatico;

DROP TABLE IF EXISTS cuentaUsuario;
CREATE TABLE cuentaUsuario(
id INT auto_increment PRIMARY KEY,
rut int NOT NULL,
dv char(1) NOT NULL,
nombre varchar(15) NOT NULL,
apellido varchar(15) NOT NULL,
clave int NOT NULL,
saldo float DEFAULT 0,
UNIQUE(rut,dv));

DROP TABLE IF EXISTS logCuentaUsuario;
CREATE TABLE logCuentaUsuario(
idUsuario INT,
FOREIGN KEY (idUsuario) REFERENCES cuentaUsuario(id),
monto float DEFAULT 0,
informacion varchar(50),
fecha datetime DEFAULT now());

DROP VIEW IF EXISTS vistaLog;
CREATE VIEW vistaLog
AS
SELECT c.id, CONCAT(c.rut, '-', c.dv) AS RUT, c.nombre, c.apellido, l.monto, l.informacion, l.fecha
FROM logCuentaUsuario AS l
INNER JOIN cuentaUsuario AS c
ON l.idUsuario = c.id;

DROP PROCEDURE IF EXISTS retiro;
DELIMITER &&
CREATE PROCEDURE retiro(in cuentaRut int, in cuentaDv char(1), in id int, in nuevoSaldo float, 
in nuevoMonto float)
BEGIN
	UPDATE cuentaUsuario SET saldo = nuevoSaldo 
    WHERE rut = cuentaRut AND dv = CuentaDv;
    
    INSERT INTO logCuentaUsuario(idUsuario, monto, informacion)
    VALUES
    (id, nuevoMonto, "SE REALIZO UN RETIRO");
END &&

DROP PROCEDURE IF EXISTS deposito;
DELIMITER &&
CREATE PROCEDURE deposito(in cuentaRut int, in cuentaDv char(1), in id int, in nuevoSaldo float,
in nuevoMonto float)
BEGIN
	UPDATE cuentaUsuario SET saldo = nuevoSaldo
    WHERE rut = cuentaRut AND dv = CuentaDv;
    
    INSERT INTO logCuentaUsuario(idUsuario, monto, informacion)
    VALUES
    (id, nuevoMonto, "SE REALIZO UN DEPOSITO");
END &&

DROP PROCEDURE IF EXISTS transferencia;
DELIMITER &&
CREATE PROCEDURE transferencia(in cuentaRut int, in cuentaDV char(1), in id int, 
in nuevoSaldo float, in rutTransferencia int, in dvTransferencia char(1), 
in idTransferencia int, in nuevoSaldoTransferencia float, in nuevoMonto float
)
BEGIN
	UPDATE cuentaUsuario SET saldo = nuevoSaldo
    WHERE rut = cuentaRut AND dv = cuentaDv;
    
    UPDATE cuentaUsuario SET saldo = nuevoSaldoTransferencia
    WHERE rut = rutTransferencia AND dv = dvTransferencia;
    
    INSERT INTO logCuentaUsuario(idUsuario, monto, informacion)
    VALUES
    (id, nuevoMonto, CONCAT("SE REALIZO UNA TRANSFERENCIA A RUT: ", rutTransferencia, '-', dvTransferencia));
    
    INSERT INTO logCuentaUsuario(idUsuario, monto, informacion)
    VALUES
    (idTransferencia, nuevoMonto, CONCAT("RECIBIO UNA TRANSFERENCIA DE RUT: ", cuentaRut, '-',cuentaDv));
END &&

    

