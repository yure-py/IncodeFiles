CREATE DATABASE cidades_estados;

USE cidades_estados;

CREATE TABLE ESTADOS (
  id_estado INT UNSIGNED NOT NULL AUTO_INCREMENT,
  nome VARCHAR(50) NOT NULL UNIQUE,
  abbr VARCHAR(2) NOT NULL UNIQUE,
  regiao ENUM('Norte','Nordeste','Centro-Oeste','Sudeste', 'Sul') NOT NULL,
  populacao DECIMAL(5,2) NOT NULL,
  
  PRIMARY KEY (`id_estado`)
);

CREATE TABLE PREFEITOS (
  `id_prefeito` VARCHAR(50) NULL,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id_prefeito`)
);

CREATE TABLE CIDADES (
  `id_cidade` VARCHAR(50),
  `name` VARCHAR(100) NOT NULL,
  `PREFEITO_id_prefeito` VARCHAR(50) NOT NULL,
  `ESTADO_id_estado` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id_cidade`, `ESTADO_id_estado`),
  FOREIGN KEY (`PREFEITO_id_prefeito`) 
  -- FOREIGN KEY (`ESTADO_id_estado`)
);

CREATE TABLE IF NOT EXISTS `mydb`.`EMPRESA` (
  `id_empresa` VARCHAR(50),
  `name` VARCHAR(100) NOT NULL,
  `cnpj` VARCHAR(20) NOT NULL UNIQUE,
  PRIMARY KEY (`id_empresa`)
);