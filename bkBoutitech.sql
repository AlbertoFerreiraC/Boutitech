/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.0.13-rc-nt : Database - boutitech
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`boutitech` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `boutitech`;

/*Table structure for table `categoria` */

DROP TABLE IF EXISTS `categoria`;

CREATE TABLE `categoria` (
  `idcategoria` int(10) unsigned NOT NULL auto_increment,
  `nombre` char(20) default NULL,
  `descripcion` char(100) default NULL,
  PRIMARY KEY  (`idcategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `categoria` */

/*Table structure for table `funcionario` */

DROP TABLE IF EXISTS `funcionario`;

CREATE TABLE `funcionario` (
  `idfuncionario` int(10) unsigned NOT NULL auto_increment,
  `idusuario` int(10) unsigned NOT NULL,
  `nombres` char(20) default NULL,
  `apellidos` char(20) default NULL,
  `contacto` char(50) default NULL,
  `cargo` char(20) default NULL,
  `estado` tinyint(1) default NULL,
  `ci` char(10) default NULL,
  PRIMARY KEY  (`idfuncionario`,`idusuario`),
  KEY `funcionario_FKIndex1` (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `funcionario` */

/*Table structure for table `inventario` */

DROP TABLE IF EXISTS `inventario`;

CREATE TABLE `inventario` (
  `idinventario` int(10) unsigned NOT NULL auto_increment,
  `idfuncionario` int(10) unsigned NOT NULL,
  `idcategoria` int(10) unsigned NOT NULL,
  `idproducto` int(10) unsigned NOT NULL,
  `idproveedor` int(10) unsigned NOT NULL,
  `idusuario` int(10) unsigned NOT NULL,
  `fecha` date default NULL,
  `existencia` int(10) unsigned default NULL,
  `disponibilidad` int(10) unsigned default NULL,
  PRIMARY KEY  (`idinventario`,`idfuncionario`,`idcategoria`,`idproducto`,`idproveedor`,`idusuario`),
  KEY `inventario_FKIndex1` (`idfuncionario`,`idusuario`),
  KEY `inventario_FKIndex2` (`idproducto`,`idcategoria`,`idproveedor`),
  KEY `inventario_FKIndex3` (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `inventario` */

/*Table structure for table `producto` */

DROP TABLE IF EXISTS `producto`;

CREATE TABLE `producto` (
  `idproducto` int(10) unsigned NOT NULL auto_increment,
  `idcategoria` int(10) unsigned NOT NULL,
  `idproveedor` int(10) unsigned NOT NULL,
  `nombre` char(100) default NULL,
  `descripcion` char(100) default NULL,
  `precio` int(10) unsigned default '0',
  `stk_cant` int(10) unsigned zerofill default '0000000000',
  `estado` tinyint(1) default NULL,
  PRIMARY KEY  (`idproducto`,`idcategoria`,`idproveedor`),
  KEY `producto_FKIndex1` (`idcategoria`),
  KEY `producto_FKIndex2` (`idproveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `producto` */

/*Table structure for table `proveedor` */

DROP TABLE IF EXISTS `proveedor`;

CREATE TABLE `proveedor` (
  `idproveedor` int(10) unsigned NOT NULL auto_increment,
  `nombre` char(100) default NULL,
  `contacto` char(50) default NULL,
  `telefono` char(20) default NULL,
  `correo_electronico` char(50) default NULL,
  `direccion` char(100) default NULL,
  PRIMARY KEY  (`idproveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `proveedor` */

/*Table structure for table `sucursal` */

DROP TABLE IF EXISTS `sucursal`;

CREATE TABLE `sucursal` (
  `idsucursal` int(10) unsigned NOT NULL auto_increment,
  `idinventario` int(10) unsigned NOT NULL,
  `idcategoria` int(10) unsigned NOT NULL,
  `idproducto` int(10) unsigned NOT NULL,
  `idfuncionario` int(10) unsigned NOT NULL,
  `idproveedor` int(10) unsigned NOT NULL,
  `idusuario` int(10) unsigned NOT NULL,
  `nombre` char(20) default NULL,
  `direccion` char(50) default NULL,
  `telefono` char(20) default NULL,
  PRIMARY KEY  (`idsucursal`,`idinventario`,`idcategoria`,`idproducto`,`idfuncionario`,`idproveedor`,`idusuario`),
  KEY `sucursal_FKIndex1` (`idinventario`,`idfuncionario`,`idcategoria`,`idproducto`,`idproveedor`,`idusuario`),
  KEY `sucursal_FKIndex2` (`idproducto`,`idcategoria`,`idproveedor`),
  KEY `sucursal_FKIndex3` (`idfuncionario`,`idusuario`),
  KEY `sucursal_FKIndex4` (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `sucursal` */

/*Table structure for table `usuario` */

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `idusuario` int(10) unsigned NOT NULL auto_increment,
  `pass` int(10) unsigned default NULL,
  `username` int(10) unsigned default NULL,
  `correo` int(10) unsigned default NULL,
  `telefono` int(10) unsigned default NULL,
  `estado` int(10) unsigned default NULL,
  PRIMARY KEY  (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `usuario` */

/* Function  structure for function  `calcular_iva` */

/*!50003 DROP FUNCTION IF EXISTS `calcular_iva` */;
DELIMITER $$

/*!50003 CREATE FUNCTION `calcular_iva`(precio INT) RETURNS decimal(10,2)
BEGIN
    DECLARE iva DECIMAL(10, 2);
    SET iva = precio * 0.05;
    RETURN iva;
END */$$
DELIMITER ;

/* Function  structure for function  `determinar_calificacion` */

/*!50003 DROP FUNCTION IF EXISTS `determinar_calificacion` */;
DELIMITER $$

/*!50003 CREATE FUNCTION `determinar_calificacion`(letra INT) RETURNS varchar(20)
BEGIN
    DECLARE calificacion VARCHAR(20);
    IF letra >= 1 AND letra <= 59 THEN
        SET calificacion = 'Uno';
    ELSEIF letra >= 60 AND letra <= 69 THEN
        SET calificacion = 'Dos';
    ELSEIF letra >= 70 AND letra <= 80 THEN
        SET calificacion = 'Tres';
    ELSEIF letra >= 81 AND letra <= 90 THEN
        SET calificacion = 'Cuatro';
    ELSEIF letra >= 91 AND letra <= 99 THEN
        SET calificacion = 'Cinco';
    ELSEIF letra = 100 THEN
        SET calificacion = 'Felicitado';
    ELSE
        SET calificacion = 'Número fuera de rango';
    END IF;
    RETURN calificacion;
END */$$
DELIMITER ;

/*Table structure for table `vista_informacion_columnas` */

DROP TABLE IF EXISTS `vista_informacion_columnas`;

/*!50001 DROP VIEW IF EXISTS `vista_informacion_columnas` */;
/*!50001 DROP TABLE IF EXISTS `vista_informacion_columnas` */;

/*!50001 CREATE TABLE  `vista_informacion_columnas`(
 `TABLE_SCHEMA` varchar(64) ,
 `TABLE_NAME` varchar(64) ,
 `COLUMN_NAME` varchar(64) ,
 `COLUMN_DEFAULT` varchar(64) ,
 `IS_NULLABLE` varchar(3) ,
 `DATA_TYPE` varchar(64) ,
 `COLUMN_COMMENT` varchar(255) 
)*/;

/*Table structure for table `vista_informacion_tablas` */

DROP TABLE IF EXISTS `vista_informacion_tablas`;

/*!50001 DROP VIEW IF EXISTS `vista_informacion_tablas` */;
/*!50001 DROP TABLE IF EXISTS `vista_informacion_tablas` */;

/*!50001 CREATE TABLE  `vista_informacion_tablas`(
 `TABLE_NAME` varchar(64) ,
 `TABLE_TYPE` varchar(64) ,
 `TABLE_ROWS` bigint(21) ,
 `AUTO_INCREMENT` bigint(21) ,
 `TABLE_COMMENT` varchar(80) 
)*/;

/*Table structure for table `vista_informacion_vistas` */

DROP TABLE IF EXISTS `vista_informacion_vistas`;

/*!50001 DROP VIEW IF EXISTS `vista_informacion_vistas` */;
/*!50001 DROP TABLE IF EXISTS `vista_informacion_vistas` */;

/*!50001 CREATE TABLE  `vista_informacion_vistas`(
 `TABLE_NAME` varchar(64) ,
 `TABLE_SCHEMA` varchar(64) ,
 `VIEW_DEFINITION` longtext 
)*/;

/*View structure for view vista_informacion_columnas */

/*!50001 DROP TABLE IF EXISTS `vista_informacion_columnas` */;
/*!50001 DROP VIEW IF EXISTS `vista_informacion_columnas` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `boutitech`.`vista_informacion_columnas` AS select sql_no_cache `columns`.`TABLE_SCHEMA` AS `TABLE_SCHEMA`,`columns`.`TABLE_NAME` AS `TABLE_NAME`,`columns`.`COLUMN_NAME` AS `COLUMN_NAME`,`columns`.`COLUMN_DEFAULT` AS `COLUMN_DEFAULT`,`columns`.`IS_NULLABLE` AS `IS_NULLABLE`,`columns`.`DATA_TYPE` AS `DATA_TYPE`,`columns`.`COLUMN_COMMENT` AS `COLUMN_COMMENT` from `information_schema`.`columns` */;

/*View structure for view vista_informacion_tablas */

/*!50001 DROP TABLE IF EXISTS `vista_informacion_tablas` */;
/*!50001 DROP VIEW IF EXISTS `vista_informacion_tablas` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `boutitech`.`vista_informacion_tablas` AS select sql_no_cache `tables`.`TABLE_NAME` AS `TABLE_NAME`,`tables`.`TABLE_TYPE` AS `TABLE_TYPE`,`tables`.`TABLE_ROWS` AS `TABLE_ROWS`,`tables`.`AUTO_INCREMENT` AS `AUTO_INCREMENT`,`tables`.`TABLE_COMMENT` AS `TABLE_COMMENT` from `information_schema`.`tables` where (`tables`.`TABLE_SCHEMA` = _utf8'boutitech') */;

/*View structure for view vista_informacion_vistas */

/*!50001 DROP TABLE IF EXISTS `vista_informacion_vistas` */;
/*!50001 DROP VIEW IF EXISTS `vista_informacion_vistas` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `boutitech`.`vista_informacion_vistas` AS select sql_no_cache `views`.`TABLE_NAME` AS `TABLE_NAME`,`views`.`TABLE_SCHEMA` AS `TABLE_SCHEMA`,`views`.`VIEW_DEFINITION` AS `VIEW_DEFINITION` from `information_schema`.`views` where (`views`.`TABLE_SCHEMA` = _utf8'boutitech') */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
