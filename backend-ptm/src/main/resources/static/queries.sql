-- Creacion de la base de datos
CREATE DATABASE crud_app;

-- Creacion de la tabla producto
CREATE TABLE producto
(
    id                INT PRIMARY KEY,
    nombre            VARCHAR(255)   NOT NULL,
    descripcion       VARCHAR(255),
    precio            DECIMAL(10, 2) NOT NULL,
    cantidad_en_stock INT            NOT NULL
);
