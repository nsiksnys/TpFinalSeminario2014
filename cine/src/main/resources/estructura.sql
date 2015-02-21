-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 21-02-2015 a las 11:53:44
-- Versión del servidor: 5.6.21
-- Versión de PHP: 5.5.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `cine`
--
CREATE DATABASE IF NOT EXISTS `cine` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `cine`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Asiento`
--

CREATE TABLE IF NOT EXISTS `Asiento` (
`id` bigint(20) NOT NULL,
  `columna` varchar(255) DEFAULT NULL,
  `fila` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Cartelera`
--

CREATE TABLE IF NOT EXISTS `Cartelera` (
`id` bigint(20) NOT NULL,
  `activo` tinyint(1) NOT NULL,
  `fechaFin` datetime NOT NULL,
  `fechaInicio` datetime NOT NULL,
  `proyeccion` varchar(255) NOT NULL,
  `subtitulada` tinyint(1) NOT NULL,
  `pelicula_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Disparadores `Cartelera`
--
DELIMITER //
CREATE TRIGGER `funcionStatus` AFTER UPDATE ON `Cartelera`
 FOR EACH ROW BEGIN
IF NEW.activo=0 THEN
UPDATE Funcion SET activo=0 WHERE Funcion.pelicula_id=NEW.pelicula_id AND activo=1;
END IF;
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Cartelera_Funcion`
--

CREATE TABLE IF NOT EXISTS `Cartelera_Funcion` (
  `Cartelera_id` bigint(20) NOT NULL,
  `funciones_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Cliente`
--

CREATE TABLE IF NOT EXISTS `Cliente` (
  `direccion` varchar(255) DEFAULT NULL,
  `generoPreferido` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Complejo`
--

CREATE TABLE IF NOT EXISTS `Complejo` (
`id` bigint(20) NOT NULL,
  `activo` tinyint(1) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Complejo_Sala`
--

CREATE TABLE IF NOT EXISTS `Complejo_Sala` (
  `Complejo_id` bigint(20) NOT NULL,
  `salas_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Entrada_reserva`
--

CREATE TABLE IF NOT EXISTS `Entrada_reserva` (
`id` bigint(20) NOT NULL,
  `cantidadEntrada` int(11) NOT NULL,
  `tipoEntrada` varchar(255) NOT NULL,
  `idPrecio` bigint(20) DEFAULT NULL,
  `idReserva` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `FichaTecnica`
--

CREATE TABLE IF NOT EXISTS `FichaTecnica` (
`id` bigint(20) NOT NULL,
  `actores` varchar(255) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `director` varchar(255) NOT NULL,
  `urlTrailer` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Funcion`
--

CREATE TABLE IF NOT EXISTS `Funcion` (
`id` bigint(20) NOT NULL,
  `activo` tinyint(1) NOT NULL,
  `horario_id` bigint(20) DEFAULT NULL,
  `pelicula_id` bigint(20) DEFAULT NULL,
  `sala_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Horario`
--

CREATE TABLE IF NOT EXISTS `Horario` (
`id` bigint(20) NOT NULL,
  `duracion` time NOT NULL,
  `horaFin` time NOT NULL,
  `horaInicio` time NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Pelicula`
--

CREATE TABLE IF NOT EXISTS `Pelicula` (
`id` bigint(20) NOT NULL,
  `activo` tinyint(1) NOT NULL,
  `clasificacion` varchar(255) NOT NULL,
  `duracion` time NOT NULL,
  `fechaCreacion` datetime NOT NULL,
  `idioma` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `reposicion` tinyint(1) NOT NULL,
  `subs` tinyint(1) NOT NULL,
  `detalles_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Precio`
--

CREATE TABLE IF NOT EXISTS `Precio` (
`id` bigint(20) NOT NULL,
  `activo` tinyint(1) NOT NULL,
  `general` float NOT NULL,
  `mayor` float NOT NULL,
  `menor` float NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Precios_Detalle`
--

CREATE TABLE IF NOT EXISTS `Precios_Detalle` (
`id` bigint(20) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `idReserva` bigint(20) DEFAULT NULL,
  `tipoPrecio` varchar(255) DEFAULT NULL,
  `precio_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Promocion`
--

CREATE TABLE IF NOT EXISTS `Promocion` (
`id` bigint(20) NOT NULL,
  `activo` tinyint(1) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `fechaFin` datetime NOT NULL,
  `fechaInicio` datetime NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Promocion_Complejo`
--

CREATE TABLE IF NOT EXISTS `Promocion_Complejo` (
  `Promocion_id` bigint(20) NOT NULL,
  `complejo_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Reserva`
--

CREATE TABLE IF NOT EXISTS `Reserva` (
`id` bigint(20) NOT NULL,
  `activo` tinyint(1) NOT NULL,
  `codigo` varchar(255) NOT NULL,
  `fechaCreacion` datetime NOT NULL,
  `fechaReserva` datetime NOT NULL,
  `importe` float NOT NULL,
  `cliente_email` varchar(255) DEFAULT NULL,
  `funcion_id` bigint(20) DEFAULT NULL,
  `promo_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Reserva_Asiento`
--

CREATE TABLE IF NOT EXISTS `Reserva_Asiento` (
  `Reserva_id` bigint(20) NOT NULL,
  `asientos_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Reserva_Precios_Detalle`
--

CREATE TABLE IF NOT EXISTS `Reserva_Precios_Detalle` (
  `Reserva_id` bigint(20) NOT NULL,
  `precios_detalle_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Sala`
--

CREATE TABLE IF NOT EXISTS `Sala` (
`id` bigint(20) NOT NULL,
  `activa` tinyint(1) NOT NULL,
  `idComplejo` bigint(20) NOT NULL,
  `numeroSala` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Usuario`
--

CREATE TABLE IF NOT EXISTS `Usuario` (
  `email` varchar(255) NOT NULL,
  `active` tinyint(1) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `dni` bigint(20) NOT NULL,
  `fechaNacimiento` datetime DEFAULT NULL,
  `nombre` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `preguntaSeguridad` varchar(255) DEFAULT NULL,
  `respuestaSeguridad` varchar(255) DEFAULT NULL,
  `role` varchar(255) NOT NULL,
  `sexo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Asiento`
--
ALTER TABLE `Asiento`
 ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Cartelera`
--
ALTER TABLE `Cartelera`
 ADD PRIMARY KEY (`id`), ADD KEY `FK_3tu01ku14slg4dfylgipvyalm` (`pelicula_id`);

--
-- Indices de la tabla `Cartelera_Funcion`
--
ALTER TABLE `Cartelera_Funcion`
 ADD UNIQUE KEY `UK_trgbgo386fbxtk5l5b0uiuaqe` (`funciones_id`), ADD KEY `FK_trgbgo386fbxtk5l5b0uiuaqe` (`funciones_id`), ADD KEY `FK_57kx047fcrtookbkb4hghrmbk` (`Cartelera_id`);

--
-- Indices de la tabla `Cliente`
--
ALTER TABLE `Cliente`
 ADD PRIMARY KEY (`email`), ADD KEY `FK_tfai0ea59ylhrubk6at672968` (`email`);

--
-- Indices de la tabla `Complejo`
--
ALTER TABLE `Complejo`
 ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Complejo_Sala`
--
ALTER TABLE `Complejo_Sala`
 ADD UNIQUE KEY `UK_1k0ac4dj2wcsgel541m778htl` (`salas_id`), ADD KEY `FK_1k0ac4dj2wcsgel541m778htl` (`salas_id`), ADD KEY `FK_o0dghabb3x4au72hg46ue3orn` (`Complejo_id`);

--
-- Indices de la tabla `Entrada_reserva`
--
ALTER TABLE `Entrada_reserva`
 ADD PRIMARY KEY (`id`), ADD KEY `FK_8uybilwuyyrb2p95r4sg3s2no` (`idPrecio`), ADD KEY `FK_9x85ro7uco7ocf9l2iwp4x6p7` (`idReserva`);

--
-- Indices de la tabla `FichaTecnica`
--
ALTER TABLE `FichaTecnica`
 ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Funcion`
--
ALTER TABLE `Funcion`
 ADD PRIMARY KEY (`id`), ADD KEY `FK_8pvjs7i4ll0dpt9mj5xi0wkyg` (`horario_id`), ADD KEY `FK_1we7nb6gob10vuwtw9na9wxpc` (`pelicula_id`), ADD KEY `FK_oaclbsaeaycmih0fwx14mudva` (`sala_id`);

--
-- Indices de la tabla `Horario`
--
ALTER TABLE `Horario`
 ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Pelicula`
--
ALTER TABLE `Pelicula`
 ADD PRIMARY KEY (`id`), ADD KEY `FK_ebk4h30rlxuq8v2252iu8ndm9` (`detalles_id`);

--
-- Indices de la tabla `Precio`
--
ALTER TABLE `Precio`
 ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Precios_Detalle`
--
ALTER TABLE `Precios_Detalle`
 ADD PRIMARY KEY (`id`), ADD KEY `FK_q95210j5tc3jd4002tyvrm5g2` (`precio_id`);

--
-- Indices de la tabla `Promocion`
--
ALTER TABLE `Promocion`
 ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Promocion_Complejo`
--
ALTER TABLE `Promocion_Complejo`
 ADD UNIQUE KEY `UK_dgqj1aifqdwj42iqihnw23w3m` (`complejo_id`), ADD KEY `FK_dgqj1aifqdwj42iqihnw23w3m` (`complejo_id`), ADD KEY `FK_8qje7cq4h6y8dup6c96s48w56` (`Promocion_id`);

--
-- Indices de la tabla `Reserva`
--
ALTER TABLE `Reserva`
 ADD PRIMARY KEY (`id`), ADD KEY `FK_pesclbriwxrt9y3ke6dfbqgvv` (`cliente_email`), ADD KEY `FK_rwor5hwxpyif0p29i4tgc3o0g` (`funcion_id`), ADD KEY `FK_g4s1lpqiw75kv7jid7ftqrm8p` (`promo_id`);

--
-- Indices de la tabla `Reserva_Asiento`
--
ALTER TABLE `Reserva_Asiento`
 ADD KEY `FK_5sxk85s2ccsyvu5i3ep5kpiwk` (`asientos_id`), ADD KEY `FK_fete5fico331txqfnv66twxiy` (`Reserva_id`);

--
-- Indices de la tabla `Reserva_Precios_Detalle`
--
ALTER TABLE `Reserva_Precios_Detalle`
 ADD UNIQUE KEY `UK_boyucky1r3hx3fnl5i213jabk` (`precios_detalle_id`), ADD KEY `FK_boyucky1r3hx3fnl5i213jabk` (`precios_detalle_id`), ADD KEY `FK_qej4b5dort4gg0l8mcgwwcjkw` (`Reserva_id`);

--
-- Indices de la tabla `Sala`
--
ALTER TABLE `Sala`
 ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Usuario`
--
ALTER TABLE `Usuario`
 ADD PRIMARY KEY (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Asiento`
--
ALTER TABLE `Asiento`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=101;
--
-- AUTO_INCREMENT de la tabla `Cartelera`
--
ALTER TABLE `Cartelera`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT de la tabla `Complejo`
--
ALTER TABLE `Complejo`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT de la tabla `Entrada_reserva`
--
ALTER TABLE `Entrada_reserva`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `FichaTecnica`
--
ALTER TABLE `FichaTecnica`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=25;
--
-- AUTO_INCREMENT de la tabla `Funcion`
--
ALTER TABLE `Funcion`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `Horario`
--
ALTER TABLE `Horario`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT de la tabla `Pelicula`
--
ALTER TABLE `Pelicula`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT de la tabla `Precio`
--
ALTER TABLE `Precio`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `Precios_Detalle`
--
ALTER TABLE `Precios_Detalle`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT de la tabla `Promocion`
--
ALTER TABLE `Promocion`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `Reserva`
--
ALTER TABLE `Reserva`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT de la tabla `Sala`
--
ALTER TABLE `Sala`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=20;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `Cartelera`
--
ALTER TABLE `Cartelera`
ADD CONSTRAINT `FK_3tu01ku14slg4dfylgipvyalm` FOREIGN KEY (`pelicula_id`) REFERENCES `Pelicula` (`id`);

--
-- Filtros para la tabla `Cartelera_Funcion`
--
ALTER TABLE `Cartelera_Funcion`
ADD CONSTRAINT `FK_57kx047fcrtookbkb4hghrmbk` FOREIGN KEY (`Cartelera_id`) REFERENCES `Cartelera` (`id`),
ADD CONSTRAINT `FK_trgbgo386fbxtk5l5b0uiuaqe` FOREIGN KEY (`funciones_id`) REFERENCES `Funcion` (`id`);

--
-- Filtros para la tabla `Cliente`
--
ALTER TABLE `Cliente`
ADD CONSTRAINT `FK_tfai0ea59ylhrubk6at672968` FOREIGN KEY (`email`) REFERENCES `Usuario` (`email`);

--
-- Filtros para la tabla `Complejo_Sala`
--
ALTER TABLE `Complejo_Sala`
ADD CONSTRAINT `FK_1k0ac4dj2wcsgel541m778htl` FOREIGN KEY (`salas_id`) REFERENCES `Sala` (`id`),
ADD CONSTRAINT `FK_o0dghabb3x4au72hg46ue3orn` FOREIGN KEY (`Complejo_id`) REFERENCES `Complejo` (`id`);

--
-- Filtros para la tabla `Entrada_reserva`
--
ALTER TABLE `Entrada_reserva`
ADD CONSTRAINT `FK_8uybilwuyyrb2p95r4sg3s2no` FOREIGN KEY (`idPrecio`) REFERENCES `Precio` (`id`),
ADD CONSTRAINT `FK_9x85ro7uco7ocf9l2iwp4x6p7` FOREIGN KEY (`idReserva`) REFERENCES `Reserva` (`id`);

--
-- Filtros para la tabla `Funcion`
--
ALTER TABLE `Funcion`
ADD CONSTRAINT `FK_1we7nb6gob10vuwtw9na9wxpc` FOREIGN KEY (`pelicula_id`) REFERENCES `Pelicula` (`id`),
ADD CONSTRAINT `FK_8pvjs7i4ll0dpt9mj5xi0wkyg` FOREIGN KEY (`horario_id`) REFERENCES `Horario` (`id`),
ADD CONSTRAINT `FK_oaclbsaeaycmih0fwx14mudva` FOREIGN KEY (`sala_id`) REFERENCES `Sala` (`id`);

--
-- Filtros para la tabla `Pelicula`
--
ALTER TABLE `Pelicula`
ADD CONSTRAINT `FK_ebk4h30rlxuq8v2252iu8ndm9` FOREIGN KEY (`detalles_id`) REFERENCES `FichaTecnica` (`id`);

--
-- Filtros para la tabla `Precios_Detalle`
--
ALTER TABLE `Precios_Detalle`
ADD CONSTRAINT `FK_q95210j5tc3jd4002tyvrm5g2` FOREIGN KEY (`precio_id`) REFERENCES `Precio` (`id`);

--
-- Filtros para la tabla `Promocion_Complejo`
--
ALTER TABLE `Promocion_Complejo`
ADD CONSTRAINT `FK_8qje7cq4h6y8dup6c96s48w56` FOREIGN KEY (`Promocion_id`) REFERENCES `Promocion` (`id`),
ADD CONSTRAINT `FK_dgqj1aifqdwj42iqihnw23w3m` FOREIGN KEY (`complejo_id`) REFERENCES `Complejo` (`id`);

--
-- Filtros para la tabla `Reserva`
--
ALTER TABLE `Reserva`
ADD CONSTRAINT `FK_g4s1lpqiw75kv7jid7ftqrm8p` FOREIGN KEY (`promo_id`) REFERENCES `Promocion` (`id`),
ADD CONSTRAINT `FK_pesclbriwxrt9y3ke6dfbqgvv` FOREIGN KEY (`cliente_email`) REFERENCES `Cliente` (`email`),
ADD CONSTRAINT `FK_rwor5hwxpyif0p29i4tgc3o0g` FOREIGN KEY (`funcion_id`) REFERENCES `Funcion` (`id`);

--
-- Filtros para la tabla `Reserva_Asiento`
--
ALTER TABLE `Reserva_Asiento`
ADD CONSTRAINT `FK_5sxk85s2ccsyvu5i3ep5kpiwk` FOREIGN KEY (`asientos_id`) REFERENCES `Asiento` (`id`),
ADD CONSTRAINT `FK_fete5fico331txqfnv66twxiy` FOREIGN KEY (`Reserva_id`) REFERENCES `Reserva` (`id`);

--
-- Filtros para la tabla `Reserva_Precios_Detalle`
--
ALTER TABLE `Reserva_Precios_Detalle`
ADD CONSTRAINT `FK_boyucky1r3hx3fnl5i213jabk` FOREIGN KEY (`precios_detalle_id`) REFERENCES `Precios_Detalle` (`id`),
ADD CONSTRAINT `FK_qej4b5dort4gg0l8mcgwwcjkw` FOREIGN KEY (`Reserva_id`) REFERENCES `Reserva` (`id`);

DELIMITER $$
--
-- Eventos
--
CREATE DEFINER=`root`@`localhost` EVENT `carteleraStatus` ON SCHEDULE EVERY 1 HOUR STARTS '2015-02-09 00:00:00' ENDS '2015-03-01 00:00:00' ON COMPLETION PRESERVE ENABLE COMMENT 'Activa o desactiva los registros' DO BEGIN
UPDATE cine.Cartelera SET activo=0 WHERE fechaInicio<NOW() AND fechaFin<=NOW();
UPDATE cine.Cartelera SET activo=1 WHERE fechaInicio<=NOW() AND fechaFin>NOW();
END$$

CREATE DEFINER=`root`@`localhost` EVENT `reservaStatus` ON SCHEDULE EVERY 1 HOUR STARTS '2015-02-09 00:00:00' ENDS '2015-03-01 00:00:00' ON COMPLETION NOT PRESERVE ENABLE COMMENT 'Desactiva los registros' DO UPDATE cine.Reserva SET activo=0 WHERE fechaReserva<=NOW()$$

CREATE DEFINER=`root`@`localhost` EVENT `promocionStatus` ON SCHEDULE EVERY 1 HOUR STARTS '2015-02-09 00:00:00' ENDS '2015-03-01 00:00:00' ON COMPLETION NOT PRESERVE ENABLE COMMENT 'Setea el status de los registros' DO BEGIN
UPDATE cine.Promocion SET activo=0 WHERE fechaInicio<NOW() AND fechaFin<=NOW();
UPDATE cine.Promocion SET activo=1 WHERE fechaInicio<=NOW() AND fechaFin>NOW();
END$$

DELIMITER ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
