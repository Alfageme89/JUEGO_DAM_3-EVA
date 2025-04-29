-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS minirpg;

-- Usar la base de datos
USE minirpg;

-- Crear la tabla 'partidas'
CREATE TABLE IF NOT EXISTS partidas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME NOT NULL,
    duracion Long NOT NULL,
    jugador VARCHAR(50) NOT NULL,
    resultado VARCHAR(10) NOT NULL,
    puntos INT NOT NULL
);
