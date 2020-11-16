-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ProyectoFinal
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ProyectoFinal
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ProyectoFinal` ;
USE `ProyectoFinal` ;

-- -----------------------------------------------------
-- Table `ProyectoFinal`.`CAJERO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ProyectoFinal`.`CAJERO` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(60) NOT NULL,
  `turno` VARCHAR(20) NOT NULL,
  `DPI` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(100) NOT NULL,
  `sexo` VARCHAR(20) NOT NULL,
  `password` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ProyectoFinal`.`USUARIO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ProyectoFinal`.`USUARIO` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(60) NOT NULL,
  `DPI` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(100) NOT NULL,
  `sexo` VARCHAR(20) NOT NULL,
  `password` VARCHAR(80) NOT NULL,
  `tipo_usuario` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ProyectoFinal`.`CLIENTE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ProyectoFinal`.`CLIENTE` (
  `codigo_cliente` INT NOT NULL,
  `birth` DATE NOT NULL,
  `PDF_DPI` MEDIUMBLOB NOT NULL,
  PRIMARY KEY (`codigo_cliente`),
  INDEX `fk_CLIENTE_USUARIO1_idx` (`codigo_cliente` ASC) ,
  CONSTRAINT `fk_CLIENTE_USUARIO1`
    FOREIGN KEY (`codigo_cliente`)
    REFERENCES `ProyectoFinal`.`USUARIO` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ProyectoFinal`.`CUENTA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ProyectoFinal`.`CUENTA` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `fecha_creacion` DATE NOT NULL,
  `monto` DECIMAL(15) NOT NULL,
  `codigo_cliente` INT NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_CUENTA_CLIENTE1_idx` (`codigo_cliente` ASC) ,
  CONSTRAINT `fk_CUENTA_CLIENTE1`
    FOREIGN KEY (`codigo_cliente`)
    REFERENCES `ProyectoFinal`.`CLIENTE` (`codigo_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ProyectoFinal`.`TURNO_TRABAJO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ProyectoFinal`.`TURNO_TRABAJO` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `tipo_turno` VARCHAR(45) NOT NULL,
  `hora_entrada` TIME NOT NULL,
  `hora_salida` TIME NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ProyectoFinal`.`EMPLEADO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ProyectoFinal`.`EMPLEADO` (
  `codigo_empleado` INT NOT NULL,
  `tipo_empleado` VARCHAR(45) NOT NULL,
  `codigo_turno` INT NOT NULL,
  INDEX `fk_EMPLEADO_TURNO_TRABAJO1_idx` (`codigo_turno` ASC) ,
  PRIMARY KEY (`codigo_empleado`),
  CONSTRAINT `fk_EMPLEADO_TURNO_TRABAJO1`
    FOREIGN KEY (`codigo_turno`)
    REFERENCES `ProyectoFinal`.`TURNO_TRABAJO` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_EMPLEADO_USUARIO1`
    FOREIGN KEY (`codigo_empleado`)
    REFERENCES `ProyectoFinal`.`USUARIO` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ProyectoFinal`.`TRANSACCION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ProyectoFinal`.`TRANSACCION` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATE NOT NULL,
  `hora` TIME NOT NULL,
  `tipo` VARCHAR(15) NOT NULL,
  `monto` DECIMAL(15) NOT NULL,
  `codigo_cuenta` INT NOT NULL,
  `codigo_cajero` INT NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_Transaccion_Cuenta1_idx` (`codigo_cuenta` ASC) ,
  INDEX `fk_TRANSACCION_EMPLEADO1_idx` (`codigo_cajero` ASC) ,
  CONSTRAINT `fk_Transaccion_Cuenta1`
    FOREIGN KEY (`codigo_cuenta`)
    REFERENCES `ProyectoFinal`.`CUENTA` (`codigo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_TRANSACCION_EMPLEADO1`
    FOREIGN KEY (`codigo_cajero`)
    REFERENCES `ProyectoFinal`.`EMPLEADO` (`codigo_empleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ProyectoFinal`.`HISTORIAL_CAMBIOS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ProyectoFinal`.`HISTORIAL_CAMBIOS` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(300) NOT NULL,
  `fecha` DATE NOT NULL,
  `codigo_usuario` INT NOT NULL,
  `codigo_gerente` INT NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_HISTORIAL_CAMBIOS_USUARIO1_idx` (`codigo_usuario` ASC) ,
  INDEX `fk_HISTORIAL_CAMBIOS_EMPLEADO1_idx` (`codigo_gerente` ASC) ,
  CONSTRAINT `fk_HISTORIAL_CAMBIOS_USUARIO1`
    FOREIGN KEY (`codigo_usuario`)
    REFERENCES `ProyectoFinal`.`USUARIO` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_HISTORIAL_CAMBIOS_EMPLEADO1`
    FOREIGN KEY (`codigo_gerente`)
    REFERENCES `ProyectoFinal`.`EMPLEADO` (`codigo_empleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ProyectoFinal`.`LIMITE_REPORTES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ProyectoFinal`.`LIMITE_REPORTES` (
  `codigo_gerente` INT NOT NULL,
  `limite_transacciones` DECIMAL(15) NOT NULL,
  `limite_suma_transacciones` DECIMAL(20) NOT NULL,
  PRIMARY KEY (`codigo_gerente`),
  INDEX `fk_LIMITE_REPORTES_EMPLEADO1_idx` (`codigo_gerente` ASC) ,
  CONSTRAINT `fk_LIMITE_REPORTES_EMPLEADO1`
    FOREIGN KEY (`codigo_gerente`)
    REFERENCES `ProyectoFinal`.`EMPLEADO` (`codigo_empleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ProyectoFinal`.`SOLICITUD_ASOCIACION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ProyectoFinal`.`SOLICITUD_ASOCIACION` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `estado` VARCHAR(45) NOT NULL,
  `codigo_cuenta` INT NOT NULL,
  `codigo_cliente` INT NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_SOLICITUD_ASOCIACION_CUENTA1_idx` (`codigo_cuenta` ASC) ,
  INDEX `fk_SOLICITUD_ASOCIACION_CLIENTE1_idx` (`codigo_cliente` ASC) ,
  CONSTRAINT `fk_SOLICITUD_ASOCIACION_CUENTA1`
    FOREIGN KEY (`codigo_cuenta`)
    REFERENCES `ProyectoFinal`.`CUENTA` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SOLICITUD_ASOCIACION_CLIENTE1`
    FOREIGN KEY (`codigo_cliente`)
    REFERENCES `ProyectoFinal`.`CLIENTE` (`codigo_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ProyectoFinal`.`ASOCIACION_CUENTA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ProyectoFinal`.`ASOCIACION_CUENTA` (
  `codigo_cuenta` INT NOT NULL,
  `codigo_cliente` INT NOT NULL,
  PRIMARY KEY (`codigo_cuenta`, `codigo_cliente`),
  INDEX `fk_ASOCIACION_CUENTA_CLIENTE1_idx` (`codigo_cliente` ASC) ,
  CONSTRAINT `fk_ASOCIACION_CUENTA_CUENTA1`
    FOREIGN KEY (`codigo_cuenta`)
    REFERENCES `ProyectoFinal`.`CUENTA` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ASOCIACION_CUENTA_CLIENTE1`
    FOREIGN KEY (`codigo_cliente`)
    REFERENCES `ProyectoFinal`.`CLIENTE` (`codigo_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


INSERT INTO TURNO_TRABAJO (tipo_turno, hora_entrada, hora_salida) VALUES ('Matutino', '6:00','14:30');
INSERT INTO TURNO_TRABAJO (tipo_turno, hora_entrada, hora_salida) VALUES ('Vespertino', '13:00','22:00');