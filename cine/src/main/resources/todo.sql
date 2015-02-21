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
-- Datos
--

--
-- Tabla `Asiento`
--

INSERT INTO `Asiento` (`id`, `columna`, `fila`) VALUES
(1, '1', '1'),
(2, '2', '1'),
(3, '3', '1'),
(4, '4', '1'),
(5, '5', '1'),
(6, '6', '1'),
(7, '7', '1'),
(8, '8', '1'),
(9, '9', '1'),
(10, '10', '1'),
(11, '1', '2'),
(12, '2', '2'),
(13, '3', '2'),
(14, '4', '2'),
(15, '5', '2'),
(16, '6', '2'),
(17, '7', '2'),
(18, '8', '2'),
(19, '9', '2'),
(20, '10', '2'),
(21, '1', '3'),
(22, '2', '3'),
(23, '3', '3'),
(24, '4', '3'),
(25, '5', '3'),
(26, '6', '3'),
(27, '7', '3'),
(28, '8', '3'),
(29, '9', '3'),
(30, '10', '3'),
(31, '1', '4'),
(32, '2', '4'),
(33, '3', '4'),
(34, '4', '4'),
(35, '5', '4'),
(36, '6', '4'),
(37, '7', '4'),
(38, '8', '4'),
(39, '9', '4'),
(40, '10', '4'),
(41, '1', '5'),
(42, '2', '5'),
(43, '3', '5'),
(44, '4', '5'),
(45, '5', '5'),
(46, '6', '5'),
(47, '7', '5'),
(48, '8', '5'),
(49, '9', '5'),
(50, '10', '5'),
(51, '1', '6'),
(52, '2', '6'),
(53, '3', '6'),
(54, '4', '6'),
(55, '5', '6'),
(56, '6', '6'),
(57, '7', '6'),
(58, '8', '6'),
(59, '9', '6'),
(60, '10', '6'),
(61, '1', '7'),
(62, '2', '7'),
(63, '3', '7'),
(64, '4', '7'),
(65, '5', '7'),
(66, '6', '7'),
(67, '7', '7'),
(68, '8', '7'),
(69, '9', '7'),
(70, '10', '7'),
(71, '1', '8'),
(72, '2', '8'),
(73, '3', '8'),
(74, '4', '8'),
(75, '5', '8'),
(76, '6', '8'),
(77, '7', '8'),
(78, '8', '8'),
(79, '9', '8'),
(80, '10', '8'),
(81, '1', '9'),
(82, '2', '9'),
(83, '3', '9'),
(84, '4', '9'),
(85, '5', '9'),
(86, '6', '9'),
(87, '7', '9'),
(88, '8', '9'),
(89, '9', '9'),
(90, '10', '9'),
(91, '1', '10'),
(92, '2', '10'),
(93, '3', '10'),
(94, '4', '10'),
(95, '5', '10'),
(96, '6', '10'),
(97, '7', '10'),
(98, '8', '10'),
(99, '9', '10'),
(100, '10', '10');

--
-- Tabla `Cartelera`
--

INSERT INTO `Cartelera` (`id`, `activo`, `fechaFin`, `fechaInicio`, `proyeccion`, `subtitulada`, `pelicula_id`) VALUES
(16, 1, '2015-02-26 00:00:00', '2015-02-19 00:00:00', '2d', 0, 1),
(17, 1, '2015-02-26 00:00:00', '2015-02-19 00:00:00', '3D', 0, 6),
(18, 1, '2015-02-26 00:00:00', '2015-02-19 00:00:00', '2D', 0, 16),
(19, 1, '2015-02-26 00:00:00', '2015-02-19 00:00:00', '2D', 0, 20),
(20, 1, '2015-02-26 00:00:00', '2015-02-19 00:00:00', '3D', 0, 24),
(21, 1, '2015-02-26 00:00:00', '2015-02-19 00:00:00', '3D', 0, 25),
(22, 1, '2015-02-26 00:00:00', '2015-02-19 00:00:00', '2D', 0, 27),
(23, 1, '2015-02-26 00:00:00', '2015-02-19 00:00:00', '3D', 0, 29);

--
-- Tabla `Cliente`
--

INSERT INTO `Cliente` (`direccion`, `generoPreferido`, `email`) VALUES
('P.O. Box 651, 892 Malesuada Rd.', 'Accion', 'Alvin.Bennett@prueba.com'),
('Ap #839-2262 Lacus, St.', 'Misterio', 'Ariel.Ochoa@prueba.com'),
('725-4070 Vivamus Rd.', 'Misterio', 'Brianna.Prince@prueba.com'),
('415-2337 Sit Av.', 'Misterio', 'Caesar.Dickson@prueba.com'),
('7428 Cursus. Road', 'Misterio', 'Channing.Saunders@prueba.com'),
('248-7905 At, Avenue', 'Aventuras', 'Charissa.Chaney@prueba.com'),
('P.O. Box 576, 9258 Sed, Av.', 'Accion', 'Charissa.Macdonald@prueba.com'),
('32 Wallaby St', 'Accion', 'Cliente@frgp.utn.edu.ar'),
('Ap #660-2181 Et Rd.', 'Accion', 'Dylan.Maldonado@prueba.com'),
('1370 Nullam Street', 'Misterio', 'Emily.Moran@prueba.com'),
('2184 Amet, Rd.', 'Misterio', 'Hamish.Branch@prueba.com'),
('Ap #134-9782 Auctor St.', 'Accion', 'Hedy.Bradshaw@prueba.com'),
('4638 Mauris Avenue', 'Accion', 'Hunter.Patel@prueba.com'),
('Ap #806-6310 Rhoncus. Av.', 'Accion', 'Jordan.Jensen@prueba.com'),
('1640 Adipiscing Street', 'Accion', 'Josiah.Brady@prueba.com'),
('8165 Est. Ave', 'Aventuras', 'Logan.Chase@prueba.com'),
('890-2915 Ad St.', 'Misterio', 'Madeline.Bright@prueba.com'),
('Ap #437-8684 Semper, Av.', 'Accion', 'Maile.Morin@prueba.com'),
('3348 Mi Rd.', 'Misterio', 'Melvin.Burns@prueba.com'),
('745-4144 Sit Av.', 'Accion', 'Nehru.Flores@prueba.com'),
('2405 Integer Rd.', 'Misterio', 'Phillip.Jennings@prueba.com'),
('32 Wallaby St', 'Misterio', 'prueba@frgp.utn.edu.ar'),
('Ap #231-8566 Non, Ave', 'Misterio', 'Reese.Mcintosh@prueba.com'),
('P.O. Box 419, 4307 Rhoncus. Street', 'Aventuras', 'Reuben.Jenkins@prueba.com'),
('152-5625 Integer Rd.', 'Misterio', 'Rhea.Kirkland@prueba.com'),
('507-5256 Bibendum Avenue', 'Accion', 'Shannon.Yang@prueba.com'),
('P.O. Box 243, 5895 Aliquam Rd.', 'Misterio', 'Sigourney.Snyder@prueba.com'),
('Ap #863-6958 Lorem Av.', 'Accion', 'Virginia.Ball@prueba.com'),
('519-4280 Auctor Av.', 'Aventuras', 'Whitney.Vaughn@prueba.com'),
('P.O. Box 881, 5482 Massa Rd.', 'Accion', 'Zephr.Suarez@prueba.com');

--
-- Tabla `Complejo`
--

INSERT INTO `Complejo` (`id`, `activo`, `direccion`, `nombre`) VALUES
(1, 1, 'Avenida  R Balbin 2712', 'Hoyts General Cinema'),
(2, 1, 'Av. Melian 4620 Saavedra', 'Hoyts'),
(3, 1, 'Lavalle 896', 'Sweet Srl'),
(4, 0, 'E. Echeverria 3750', 'Showcase norte'),
(5, 0, 'Avenida Cabildo 1428', 'Belgrano Multiplex'),
(6, 0, 'Lavalle 869', 'Atlas cine'),
(7, 0, 'Avenida Cabildo 2829', 'Savoy'),
(8, 0, 'Monroe 1655', 'Showcase cinema'),
(9, 1, 'Soler 4123', 'Produccion cines'),
(10, 0, 'Avenida R.S.Ortiz 140', 'Cinema group');

--
-- Tabla `Complejo_Sala`
--

INSERT INTO `Complejo_Sala` (`Complejo_id`, `salas_id`) VALUES
(1, 1),
(1, 2),
(2, 3),
(3, 4),
(3, 5),
(3, 6),
(4, 7),
(4, 8),
(5, 9),
(5, 10),
(5, 11),
(6, 12),
(7, 13),
(8, 14),
(9, 15),
(9, 16),
(10, 17),
(10, 18),
(10, 19);

--
-- Tabla `FichaTecnica`
--

INSERT INTO `FichaTecnica` (`id`, `actores`, `descripcion`, `director`, `urlTrailer`) VALUES
(1, 'Ricardo Darin, Oscar Martinez', 'La desigualdad, la injusticia y las demandas del mundo en el que vivimos le causan estres y depresion a mucha gente. Algunas de ellas explotan.', 'Damian Szifron', 'http://www.youtube.com/watch?v=xdv61uCTso0'),
(2, 'Billy Bob Thornton, Vera Farmiga, Robert Downey Jr, Robert Duvall, Vincent D Onofrio, Dax Shephard, Jeremy Strong', 'Hank Palmer es un abogado que regresa a la ciudad de su niÃ±ez en donde su padre, del que esta distanciado, es juez y es sospechoso de haber cometido un asesinato', 'Bobby Farrelly', 'http://www.youtube.com/watch?v=qHMPxUX6cKA'),
(3, 'Jeff Daniels, Jim Carrey, Brady Bluhm, Cam Neely, Kathleen Turner, Laurie Holden, Rachel Melvin, Steve Tom.', 'Veinte aÃ±os despues de su primer aventura, Lloyd y Harry vuelven a las andadas para buscar a la hija perdida de Harry de quien nunca supo nada hasta ahora.', 'Bobby Farrelly, Peter Farrelly.', 'http://www.youtube.com/watch?v=camB3kmKg1U'),
(4, 'Robin Wright, Xavier Samuel, Naomi Watts, James Frecheville, Sophie Lowe.', 'Dos amigas encuentran la felicidad de forma inesperada en relaciones que rompen las convenciones.', 'Anne Fontaine.', 'http://www.youtube.com/watch?v=2phTUVU0PNA'),
(5, 'Ron Perlman, Channing Tatum, Zoe Saldana, Diego Luna, Christina Applegate.', 'Narra la leyenda de Manolo, un heroe soÃ±ador que emprende una misionn tipica a traves de mundos magicos, miticos y asombrosos, para poder reunirse con el amor de su vida y defender a su pueblo.', 'Jorge Gutierrez.', 'http://www.youtube.com/watch?v=JvIvF8ST8CY'),
(6, 'Jennifer Lawrence, Josh Hutcherson, Liam Hemsworth, Julianne Moore, Phillip Seymour Hoffman', 'Katniss es la cabeza de la rebelion contra el Capitolio, mientras debe encontrar la manera de rescatar a Peeta, que fue tomado como prisionero por el Presidente Snow.', 'Francis Lawrence.', 'http://www.youtube.com/watch?v=b1XQymoxcAQ'),
(7, 'Jessica Chastain, Michael Caine, Topher Grace, Anne Hathaway, Matthew Mcconaughey, Casey Affleck', 'Cuando el tiempo de la humanidad en la Tierra esta llegando a su fin, un grupo de exploradores emprende la mision mas importante en la historia: viajar mas alla de esta galaxia para descubrir si los humanos tienen futuro entre las estrellas.', 'Christopher Nolan', 'http://www.youtube.com/watch?v=F1BiKNCNDBY'),
(8, 'Liam Neeson, David Harbour, Boyd Holbrook, Dan Stevens.', 'Matt Scudder es un ex-policiaa de la ciudad de Nueva York que ahora trabaja como detective privado fuera de la ley. Cuando Scudder acepta ayudar a un traficante de heroina a atrapar a los hombres que secuestraron y asesinaron a su esposa.', 'Scott Frank', 'http://www.youtube.com/watch?v=k4qPneZLxfw'),
(9, 'Colin Firth, Mark Strong, Nicole Kidman, Anne Marie Duff, Ben Crompton.', 'Christine sufre las secuelas de un terrible accidente. Solo logra retener recuerdos durante un dia. Vive atrapada en una existencia en la que desconoce casi todo lo que la vincula con el mundo.', 'Rowan Joffe.', 'http://www.youtube.com/watch?v=z48AQ2RpA3Y'),
(10, 'Bruce Willlis,Ben Affleck,Liv tyler,Billy Bob Thornton, Steve Buscemi, Udo Kier, Will Patton, Peter Stormare, Michael Clarke Duncan, Keith David, Owen Wilson, Mark Curry, Jason Isaacs, William Fichtner, Jessica Steen', 'Cuando un meteorito esta a punto de chocar con la tierra se buscara la solucion enviando a un grupo de hombres entrenados en unos dias para realizar una excavacion en el meteorito y volarlo antes de que sea demasiado tarde.', 'Michael Bay', 'http://www.youtube.com/watch?v=iq6q2BrTino'),
(11, 'Annabelle Wallis,Alfre Woodard,Eric Ladin', 'John Form ha encontrado el regalo perfecto para su mujer Mia, quien esta embarazada: una hermosa muÃ±eca antigua y dificil de conseguir que luce un vestido blanco de novia', 'John R. Leonetti', 'http://www.youtube.com/embed/t5-9hrkexZI'),
(12, 'Elle Faning,Simon Pegg,Toni Colette', 'Los Boxtrolls es una fabula comica que se desarrolla en Cheesebridge, una ciudad de la epoca victoriana, cuya sociedad esta obsesionada con la elegancia, la riqueza, la clase alta y los quesos finos, aunque sean algo apestosos.', 'Graham Annable,Anthony Stacchi', 'http://www.youtube.com/embed/TnaYUdnBkYQ'),
(13, 'Kate Winslet Leonardo DiCaprio Victor Garber Kathy Bates', 'Jack, un joven artista, gana en una partida de cartas un pasaje para viajar a AmÃ©rica en el Titanic, el trasatlÃ¡ntico mÃ¡s grande y seguro jamÃ¡s construido. A bordo conoce a Rose, una joven de una buena familia', 'James Cameron', 'http://www.youtube.com/watch?v=ZQ6klONCq4s'),
(14, 'Edwin Hodge,Ben Feldman,Perdita Weeks', 'Miles de catacumbas laberinticas se encuentran por debajo de las calles de Paris, conformando un hogar eterno para innumerables almas.', 'John Erick Dowdle', 'http://www.youtube.com/embed/pkNjAx8odU'),
(15, 'Juan Gil Navarro,Agustina Lecouna, Nicolas Alberti', 'De sus profundidades de un bosque emerge, desnudo y baÃ±ado en sangre, Elias, sin un solo recuerdo de lo que les ha ocurrido a el y a sus tres amigos desaparecidos.', 'Ezio Massa', 'http://www.youtube.com/embed/PyNockpbFF0'),
(16, 'Christian Bale,Aaron Paul,Joel Edgerton', 'Biografia sobre Moises, uno de los mÃ¡s importantes personajes biblicos, y que lidera el exodo de los judios por Egipto', 'Ridley Scott', 'http://www.youtube.com/embed/XVJEm3c9eoA'),
(17, 'Helene Giraud,Thomas Szabo', 'En un pacifico bosque, los restos de un picnic desatan una guerra entre colonias de hormigas rivales obsesionadas con tener el control de un mismo premio: una caja de cubos de azucar.', 'Helenene Giraud,Tomas Szabo', 'http://www.youtube.com/embed/5xOdazHpoYs'),
(18, 'Kristin Scott Thomas, Daniel Auteuil, LeÃ¯la Bekhti, Vicky Krieps, Richard Berry, Jerome Varanfrain, Laure Killing, Anne Metzler, Laurent Claret, Jean-FranÃ§ois Wolff.', 'Paul es un neurocirujano de sesenta aÃ±os. EstÃ¡ casado con Lucie y siempre han sido felices, hasta que un buen dÃ­a empiezan a llegar ramos de rosas a su casa coincidiendo con que Lou, una joven de veinte aÃ±os, no deja de cruzarse en el camino de Paul.', 'Philippe Claudel', 'http://www.youtube.com/watch?v=Wv3HsXyjWNY'),
(19, 'Johannes Kuhnke,Lisa Loven Kongsli,Clara Wettergren', 'Una familia sueca viaja a los Alpes franceses para disfrutar de unos dias de esqui y tiempo de calidad en familia.', 'Ruben Astlund', 'http://www.youtube.com/embed/qf4T0freLbY'),
(20, 'Olivia Newton John,John Travolta', 'Verano de 1959. Sandy (Olivia Newton John) y Danny (John Travolta) han pasado un romantico y maravilloso verano juntos, pero, cuando las vacaciones se acaban, sus caminos se separan.', 'Randal Kleiser', 'http://www.youtube.com/embed/q6CuHRX_dml'),
(21, 'Audrey Hepburn,Mickey Rooney', 'Holly Golightly es una bella joven neoyorquina que, aparentemente, lleva una vida facil y alegre. Tiene un comportamiento bastante extravagante, por ejemplo, desayunar contemplando el escaparate de la lujosa joyeria Tiffanys.', 'Blake Edwards', 'http://www.youtube.com/embed/urQVzgEO_w8'),
(22, 'Saul Swimmer', 'Celebra la magia de Queen con los fans de todo el mundo en este dia tan especial. Queen Rock Montreal, un recital unico de una de las bandas mas grandes del mundo, este recital se conoce como una de las mejores actuaciones de Queen.', 'Saul Swimmer', 'http://www.youtube.com/embed/e0NDSVsSqx0'),
(23, 'Michael J Fox, Marty McFly', 'un tipico adolescente americano de los aÃ±os 80 es accidentalmente enviado al aÃ±o 1955 en un DeLorean inventado por su amigo Doc, un cientifico al que todos toman por loco que puede viajar a traves del tiempo y que se alimenta con plutonio', 'Robert Zemeckis', 'http://www.youtube.com/embed/Es3df08rTb0'),
(24, 'Genesis Rodriguez, Damon Wayans Jr, Jamie Chung', 'grandes hÃ©roes nos presenta al genio de la robÃ³tica, Hiro Hamada, quien se encuentra en las garras de un complot criminal que amenaza con destruir la enÃ©rgica y tecnolÃ³gica ciudad de San Fransokyo. Con la ayuda de su compaÃ±ero mÃ¡s cercano.', 'Don Hall, Chris Williams', 'https://www.youtube.com/watch?v=ygc5X3vRG5M'),
(25, 'Benedict Cumberbatch, Evangeline Lilly, Martin Freeman', 'El director Peter Jackson culmina el ciclo de El hobbit con esta tercera parte que se centrarÃ¡ en el final de las aventuras del pequeÃ±o Bilbo Bolson y su regreso a BolsÃ³n Cerrado, su hogar. DespuÃ©s de enfrentarse con el dragÃ³n Smaug.', 'Peter Jackson', 'https://www.youtube.com/watch?v=qkPYofGuZS4'),
(26, 'Jon Favreau, John Leguizamo, Bobby Cannavale, Emjay Anthony, Scarlett Johansson, Dustin Hoffman', 'Cuando el chef Carl Casper repentinamente deja su trabajo en un famoso restaurante de Los Ãngeles, al enfrentarse a su controlador dueÃ±o y negarse a renunciar a su integridad creativa.', 'Jon Favreau', 'https://www.youtube.com/watch?v=Ho0XIi-oCmQ'),
(27, 'Bill Murray, Melissa McCarthy, Naomi Watts', 'Maggie es una madre separada que se muda a Brooklyn con su hijo de doce aÃ±os, Oliver. Al tener que trabajar muchas horas, no le queda mÃ¡s opciÃ³n que dejar a Oliver al cargo de su nuevo vecino, Vincent.', 'Theodore Melfi', 'https://www.youtube.com/watch?v=Iy-4VbRelvw'),
(28, 'Stellan Skarsgard, Emma Rigby, Ben Kingsley', 'Rob J. Cole, un niÃ±o huÃ©rfano de nueve aÃ±os es adoptado por un barbero que le enseÃ±a el oficio. Durante aÃ±os recorren Inglaterra montando espectÃ¡culos para atraer al pÃºblico hasta que su padre adoptivo tambiÃ©n muere.', 'Philipp Stolzl', 'https://www.youtube.com/watch?v=kFm9Sy3DZm4'),
(29, 'Hugh Bonneville, Sally Hawkins, Julie Walters', 'La aventura comienza. Un oso peruano joven con una pasion por todo lo britanico viaja a Londres para encontrar un hogar.', 'Paul King', 'https://www.youtube.com/watch?v=Dec_07CjBA8');

--
-- Tabla `Funcion`
--

INSERT INTO `Funcion` (`id`, `activo`, `horario_id`, `pelicula_id`, `sala_id`) VALUES
(9, 1, 15, 1, 1),
(10, 1, 18, 1, 1),
(11, 1, 25, 1, 1),
(12, 1, 21, 25, 3),
(13, 1, 22, 25, 3),
(14, 1, 23, 25, 3),
(15, 1, 15, 6, 5),
(16, 1, 15, 16, 15),
(17, 1, 4, 20, 16);

--
-- Tabla `Horario`
--

INSERT INTO `Horario` (`id`, `duracion`, `horaFin`, `horaInicio`) VALUES
(1, '02:00:00', '13:00:00', '11:00:00'),
(2, '02:00:00', '15:00:00', '13:00:00'),
(3, '02:00:00', '17:00:00', '15:00:00'),
(4, '02:00:00', '19:00:00', '17:00:00'),
(5, '02:00:00', '21:00:00', '19:00:00'),
(6, '02:00:00', '23:00:00', '21:00:00'),
(7, '02:00:00', '01:00:00', '23:00:00'),
(8, '02:00:00', '12:00:00', '10:00:00'),
(9, '02:00:00', '14:00:00', '12:00:00'),
(10, '02:00:00', '16:00:00', '14:00:00'),
(11, '02:00:00', '18:00:00', '16:00:00'),
(12, '02:00:00', '20:00:00', '18:00:00'),
(13, '02:00:00', '22:00:00', '20:00:00'),
(14, '02:00:00', '00:00:00', '22:00:00'),
(15, '02:30:00', '12:30:00', '10:00:00'),
(16, '02:30:00', '15:00:00', '12:30:00'),
(17, '02:30:00', '17:30:00', '15:00:00'),
(18, '02:30:00', '20:00:00', '17:30:00'),
(19, '02:30:00', '22:30:00', '20:00:00'),
(20, '02:30:00', '01:00:00', '22:30:00'),
(21, '03:00:00', '13:00:00', '10:00:00'),
(22, '03:00:00', '16:00:00', '13:00:00'),
(23, '03:00:00', '19:00:00', '16:00:00'),
(24, '03:00:00', '22:00:00', '19:00:00'),
(25, '03:00:00', '01:00:00', '22:00:00');

--
-- Tabla `Pelicula`
--

INSERT INTO `Pelicula` (`id`, `activo`, `clasificacion`, `duracion`, `fechaCreacion`, `idioma`, `nombre`, `reposicion`, `subs`, `detalles_id`) VALUES
(1, 1, '13', '02:01:00', '2014-11-20 23:31:38', 'ES', 'RELATOS SALVAJES', 0, 0, 1),
(2, 0, '13', '02:21:00', '2014-11-20 21:00:53', 'EN', 'THE JUDGE', 0, 1, 2),
(3, 0, '13', '01:50:00', '2014-11-13 00:00:00', 'EN', 'DUMD AND DUMBER TO', 0, 1, 3),
(4, 1, '18', '01:42:00', '2014-11-13 00:00:00', 'EN', 'ADORE', 0, 1, 4),
(5, 1, 'ATP', '01:33:00', '2014-10-16 00:00:00', 'EN', 'THE BOOK OF LIFE', 0, 1, 5),
(6, 1, '13', '02:03:00', '2014-11-20 00:00:00', 'EN', 'THE HUNGER GAMES: Mockingjay. Part 1', 0, 1, 6),
(7, 0, '13', '02:48:00', '2014-11-06 00:00:00', 'EN', ' INTERSTELLAR', 0, 1, 7),
(8, 0, '16', '01:53:00', '2014-11-13 00:00:00', 'EN', 'CAMINANDO ENTRE TUMBAS', 0, 1, 8),
(9, 1, '13', '01:53:00', '2014-11-13 00:00:00', 'EN', 'ANTES DE DESPERTAR', 0, 1, 9),
(10, 0, '13', '02:30:00', '2014-11-20 23:32:17', 'EN', 'ARMAGEDON', 0, 0, 10),
(11, 0, '16', '01:30:00', '2014-11-13 00:00:00', 'ES', 'ANNABELLE', 0, 0, 11),
(12, 0, 'ATP', '01:25:00', '2014-11-13 00:00:00', 'ES', 'BOXTROLLS', 0, 0, 12),
(13, 0, '16', '02:00:00', '2014-11-13 00:00:00', 'ES', 'TITANIC', 0, 0, 13),
(14, 0, '16', '01:30:00', '2014-11-13 00:00:00', 'ES', 'ASI EN LA TIERRA COMO EN EL INFIERNO', 0, 0, 14),
(15, 0, '16', '01:30:00', '2014-11-13 00:00:00', 'ES', 'EL DIA DE LOS MUERTOS', 0, 0, 15),
(16, 1, 'ATP', '02:30:00', '2014-11-13 00:00:00', 'ES', 'EXODO DIOSES Y REYES', 0, 0, 16),
(17, 0, 'ATP', '01:20:00', '2014-11-13 00:00:00', 'ES', 'MINUSCULOS', 0, 0, 17),
(18, 1, '16', '01:20:00', '2014-11-13 00:00:00', 'ES', 'ANTES DEL FRIO INVIERNO', 0, 0, 18),
(19, 0, '13', '01:10:00', '2014-11-13 00:00:00', 'ES', 'FORCE MAJEURE: La traicion del instinto', 0, 0, 19),
(20, 1, 'ATP', '01:40:00', '2014-11-13 00:00:00', 'EN', 'GREASE', 0, 1, 20),
(21, 0, 'ATP', '01:40:00', '2014-11-13 00:00:00', 'EN', 'MU&Ntilde;EQUITA DE LUJO', 0, 1, 21),
(22, 0, 'ATP', '01:45:00', '2014-11-13 00:00:00', 'EN', 'QUEEN ROCK MONTREAL', 0, 1, 22),
(23, 0, 'ATP', '01:50:00', '2014-11-13 00:00:00', 'EN', 'VOLVER AL FUTURO', 0, 1, 23),
(24, 1, 'ATP', '02:00:00', '2015-02-19 00:00:00', 'EN', 'GRANDES HEROES', 0, 1, 24),
(25, 1, '13', '03:00:00', '2015-02-19 00:00:00', 'EN', 'THE HOBBIT', 0, 1, 25),
(26, 1, '13', '02:00:00', '2015-02-19 00:00:00', 'EN', '#CHEF', 0, 1, 26),
(27, 1, '16', '02:00:00', '2015-02-19 00:00:00', 'EN', 'SN. VINCENT', 0, 1, 27),
(28, 1, '16', '02:00:00', '2015-02-19 00:00:00', 'EN', 'MEDICUS', 0, 1, 28),
(29, 1, 'ATP', '02:00:00', '2015-02-19 00:00:00', 'EN', 'PADDINGTON', 0, 1, 29);

--
-- Tabla `Precio`
--

INSERT INTO `Precio` (`id`, `activo`, `general`, `mayor`, `menor`) VALUES
(1, 1, 55, 45, 35),
(2, 0, 45, 35, 25),
(3, 0, 35, 25, 15);

--
-- Tabla `Precios_Detalle`
--

INSERT INTO `Precios_Detalle` (`id`, `cantidad`, `idReserva`, `tipoPrecio`, `precio_id`) VALUES
(21, 2, 11, 'mayor', 1),
(22, 5, 12, 'general', 1),
(23, 3, 13, 'mayor', 1),
(24, 3, 13, 'general', 1),
(25, 1, 14, 'menor', 1),
(26, 3, 14, 'mayor', 1),
(27, 3, 14, 'general', 1),
(28, 3, 15, 'mayor', 1),
(29, 2, 15, 'general', 1),
(30, 2, 16, 'mayor', 1),
(31, 4, 16, 'general', 1);

--
-- Tabla `Promocion`
--

INSERT INTO `Promocion` (`id`, `activo`, `descripcion`, `fechaFin`, `fechaInicio`, `nombre`) VALUES
(1, 1, 'Ninguna', '2015-02-28 00:00:00', '2015-02-01 00:00:00', 'Ninguna'),
(2, 0, 'Dos por Uno', '2014-12-31 00:00:00', '2014-01-01 00:00:00', 'Banco Galicia'),
(3, 1, 'Banco Galicia', '2015-02-28 00:00:00', '2015-02-01 00:00:00', 'Descuento'),
(4, 0, 'Dos por Uno', '2014-12-31 00:00:00', '2014-01-01 00:00:00', 'Club La Nacion');

--
-- Tabla `Reserva`
--

INSERT INTO `Reserva` (`id`, `activo`, `codigo`, `fechaCreacion`, `fechaReserva`, `importe`, `cliente_email`, `funcion_id`, `promo_id`) VALUES
(11, 1, 'VhxgzsPBn1', '2015-02-18 20:42:08', '2015-02-24 20:42:08', 90, 'Cliente@frgp.utn.edu.ar', 9, 1),
(12, 1, 'U0iwhygchs', '2015-02-18 20:43:10', '2015-02-24 20:43:10', 275, 'Brianna.Prince@prueba.com', 9, 1),
(13, 1, '8Lxnchgd9N', '2015-02-18 20:45:00', '2015-02-22 20:45:00', 300, 'Caesar.Dickson@prueba.com', 13, 1),
(14, 1, 'SqUL3K8Mx0', '2015-02-18 20:46:32', '2015-02-22 20:46:32', 335, 'Phillip.Jennings@prueba.com', 13, 1),
(15, 0, 'veWCDAunGg', '2015-02-18 20:48:12', '2015-02-20 20:48:12', 245, 'Virginia.Ball@prueba.com', 15, 1),
(16, 1, 'X8gQTv0vrq', '2015-02-18 20:49:51', '2015-02-23 20:49:51', 310, 'Cliente@frgp.utn.edu.ar', 16, 1);

--
-- Tabla `Reserva_Asiento`
--

INSERT INTO `Reserva_Asiento` (`Reserva_id`, `asientos_id`) VALUES
(11, 47),
(11, 48),
(12, 31),
(12, 32),
(12, 33),
(12, 34),
(12, 35),
(13, 61),
(13, 62),
(13, 63),
(13, 64),
(13, 65),
(13, 66),
(14, 35),
(14, 36),
(14, 37),
(14, 38),
(14, 39),
(14, 40),
(14, 50),
(15, 11),
(15, 12),
(15, 21),
(15, 22),
(15, 23),
(16, 61),
(16, 62),
(16, 81),
(16, 82),
(16, 83),
(16, 84);

--
-- Tabla `Reserva_Precios_Detalle`
--

INSERT INTO `Reserva_Precios_Detalle` (`Reserva_id`, `precios_detalle_id`) VALUES
(11, 21),
(12, 22),
(13, 23),
(13, 24),
(14, 25),
(14, 26),
(14, 27),
(15, 28),
(15, 29),
(16, 30),
(16, 31);

--
-- Tabla `Sala`
--

INSERT INTO `Sala` (`id`, `activa`, `idComplejo`, `numeroSala`) VALUES
(1, 1, 1, 1),
(2, 1, 1, 2),
(3, 1, 2, 1),
(4, 1, 3, 1),
(5, 1, 3, 2),
(6, 1, 3, 3),
(7, 0, 4, 1),
(8, 0, 4, 2),
(9, 0, 5, 1),
(10, 0, 5, 2),
(11, 0, 5, 3),
(12, 0, 6, 1),
(13, 0, 7, 1),
(14, 0, 8, 1),
(15, 1, 9, 1),
(16, 1, 9, 2),
(17, 0, 10, 1),
(18, 0, 10, 2),
(19, 0, 10, 3);

--
-- Tabla `Usuario`
--

INSERT INTO `Usuario` (`email`, `active`, `apellido`, `dni`, `fechaNacimiento`, `nombre`, `password`, `preguntaSeguridad`, `respuestaSeguridad`, `role`, `sexo`) VALUES
('admin@frgp.utn.edu.ar', 1, 'Pink', 1337, '1963-11-23 00:00:00', 'Danny', '4a40c2ed30e7f45d55c8e9fb3c2812026e51de9b5dc1815e2d64eec51063d64641a5482bffa258d8', 'pregunta', '8eb7b5df8eb6dfc96c68a2db73da838a8b7a44cfcacb08281b4d57f7c8cc874401d005e9f9992fae', 'A', 'M'),
('Alvin.Bennett@prueba.com', 0, 'Bennett', 26912797, '1969-12-31 00:00:00', 'Alvin', 'd8b37740ad4701a4b00df93406fbeb18a02425584daeac09ecfe865e5668a89b4aa35f60d026acb9', 'pregunta', 'a9616ba9f096d2041aa5b807d0f8de73110999aa8f83f504914fd8eff5e13879352059bdd98df277', 'C', 'F'),
('Ariel.Ochoa@prueba.com', 0, 'Ochoa', 93214454, '1969-12-31 00:00:00', 'Ariel', '4941b1e01852aa06d9e26f41eac7b1ec80a03444393d3a8d36d7bb93301486b9b84b1771bd3f1f19', 'pregunta', '09478e003fa9535a2950da29fd65b8745cc8b58e9bd6d86dd060fc4bdcf5ace0f8595740fc4c7285', 'C', 'F'),
('Brianna.Prince@prueba.com', 1, 'Prince', 66275832, '1969-12-31 00:00:00', 'Brianna', '09cad6d72b741b3ce6f79667f311d66f256b12e0555b3a815ba850b737fb885c766daf9fa035cfb4', 'pregunta', '1d498a1dc41f7ab230f83c3855de5bd0f0c9fe55dfaf32ce1e12eacc4f842e18ef98267ab0ece16a', 'C', 'F'),
('Caesar.Dickson@prueba.com', 1, 'Dickson', 88289571, '1969-12-31 00:00:00', 'Caesar', '8d9c6dd3f29d9a52483535dccf301c0c82d2fbabbaa6cf339c44ca2b07894217fba4fb00b81ef4a6', 'pregunta', '0e26aa30c6031039cd595fbbccf0b093cc7193d7ca73da0c3ba3340073c81cee753ef1af430752ea', 'C', 'F'),
('Channing.Saunders@prueba.com', 0, 'Saunders', 31967618, '1969-12-31 00:00:00', 'Channing', '4458ea0fd30a9296ff1b26b13e7ff5b0fe0cb0c3b54a71446af545af98143a4636e35f7b2530fa73', 'pregunta', 'f603549c28cf64edf3254cd3ca355df6206fd99fdf1cf05622bbf599dca9739bb0e86b798f0a5980', 'C', 'M'),
('Charissa.Chaney@prueba.com', 0, 'Chaney', 18316286, '1969-12-31 00:00:00', 'Charissa', '5406a6bab272817d3c86552cfc338484d439cef5a9fba2e27f25456b110ce81588fbc7fc03dbde0b', 'pregunta', '15f4aad54b335366eb247a0eae2d6c2d9ea508388e07a5c93e36f59408df7c4d6f08af3ab0547143', 'C', 'F'),
('Charissa.Macdonald@prueba.com', 0, 'Macdonald', 83126992, '1969-12-31 00:00:00', 'Charissa', '6c4b5e756a4845126cfe54920cc35caef09293b6c80e4c981f0ae511a5a6239a39b59130c9e05dcf', 'pregunta', 'c3a60c1f5d433eefff9465ea1b00ca000a33c30ed06fdd916f37c5770491f9d5fee11d35d695c137', 'C', 'F'),
('Cliente@frgp.utn.edu.ar', 1, 'Oswald', 7893, '1963-11-23 00:00:00', 'Clara', '7309a63a624e992ad8da431eee88f252ccfa40a0e9af7ea88faf6278409efdc38bf6d9134fd5fafd', 'pregunta', '1e8d0b3aabeda3f32ae37e991b6dafbba1c299b37c3a1d90bf3ca823a1de9c6247a9dc8929112d89', 'C', 'F'),
('Dylan.Maldonado@prueba.com', 0, 'Maldonado', 41169745, '1969-12-31 00:00:00', 'Dylan', '2c57ac8051340def8f29049ea15ec24faf58eb7c444b93a952a0afb4fe379cfe83d56d19878b615f', 'pregunta', 'b6c4d6408d7ee757a5c25ca10f67cf641ecb0286695210d8921bfb1ef9d265a327fcef553e644b87', 'C', 'M'),
('Emily.Moran@prueba.com', 0, 'Moran', 21148747, '1969-12-31 00:00:00', 'Emily', '58bc6bf3b6df9f4e06b3621e445aca8f2cd8aada414967ba6c64d1a5179772343e947639c3db84cd', 'pregunta', '966a86c0972472080112932b46d4bdbc07f1caefdbd21153b2ec2459e98a20cfa42100cc90abd940', 'C', 'M'),
('gerente@frgp.utn.edu.ar', 1, 'Noble', 1234, '1963-11-23 00:00:00', 'Donna', 'c42623d2fad583bdf69b7d388859a871563becb39ce31ab8587bda5de037d5514333a0721119c8e3', 'pregunta', '6878592e64027d43580ae7084c26cc16495fccb582579075d089ca9730af15c9f7eeb48256ce1c3f', 'G', 'F'),
('Hamish.Branch@prueba.com', 0, 'Branch', 61444967, '1969-12-31 00:00:00', 'Hamish', 'c3f5edcdcb1df1d9eb8e27330a92461dfca1d46c2010f850079733a4635a24fe285eccf57d2ceaf4', 'pregunta', '97d13bce74940b51301e0ec0d4f47c5977f191aeb6a0e1fd94b45f3b0ce44203e1f97b7e0af6129b', 'C', 'M'),
('Hedy.Bradshaw@prueba.com', 0, 'Bradshaw', 72572694, '1969-12-31 00:00:00', 'Hedy', '740b51e1dd19d18deea08fca2d6d3d464331e59ccb2adf1c114e7ea3c3eff462b3bac023dbfab3d6', 'pregunta', '0f8eeeb34a69b1922bb22053b16cdc833b490f9f3a8773a9c1b13e21a77ca9c6916b7142885eb675', 'C', 'M'),
('Hunter.Patel@prueba.com', 1, 'Patel', 19957625, '1969-12-31 00:00:00', 'Hunter', '1f100efb418c0cf042c1d3e04b30069e8a5cfba643fcd1519affb83cc70958661496ec44f6e77c34', 'pregunta', 'cc7adc413bf4676bc779f17ca93fec4e17e97570930fdc67b06fe7239c1abcc676e35ba4bb3ff428', 'C', 'M'),
('Jordan.Jensen@prueba.com', 0, 'Jensen', 52247469, '1969-12-31 00:00:00', 'Jordan', 'df87d72e2d8f2eaff26c0f2357593dee2ebd2223653857bb8ab7c70bbd5700d0cf678c57c2d121c2', 'pregunta', '8f08a3b0af2c10ba35ef00fec3e0f5addbeefb4b233ebbcee650f549f7b61e6e8f715972eb9eb078', 'C', 'M'),
('Josiah.Brady@prueba.com', 1, 'Brady', 82672493, '1969-12-31 00:00:00', 'Josiah', '24d74fee9bcd46ec97e4dc299340d7757c0ef0beab87817fd69457798df446d8afd77c22258ad0bb', 'pregunta', 'fe36be08004dc8b47c7473e82daec1e2eaaaa60f0d6f31e507504ab8b19f2a8c7f0b0918a3b2d914', 'C', 'M'),
('Logan.Chase@prueba.com', 1, 'Chase', 83395372, '1969-12-31 00:00:00', 'Logan', 'b4f3791f893d7a92dcc915283cca98fa879e5c40a2a83acc10949d17bde726765f68c3ecf6eda85f', 'pregunta', '7744384131b404ea663acdfc0306256f70cd3bb5670c1f2172cf9963384cc4dc5693869c0484b727', 'C', 'F'),
('Madeline.Bright@prueba.com', 0, 'Bright', 91712162, '1969-12-31 00:00:00', 'Madeline', 'ffb339f8bc9c93925f6bb74b30d629aa2f38f5c5cf2a35ce814ab8faf5ad22777d8527d26d047318', 'pregunta', 'ca0dd4cbb1a66123f81887cbcd2b06661924fb64e4bf9b8a239b6878494aec8fb6c66fd0b80d51e9', 'C', 'F'),
('Maile.Morin@prueba.com', 0, 'Morin', 93584437, '1969-12-31 00:00:00', 'Maile', '3ee53d04dda3186cbeb471d98818bf2fa636e18df676878b72468e26a3448568ea96092137286f92', 'pregunta', '4e76351629a0d875f58ff7616067142dc248685cee5c3171239bb8a7665273a61f4cdca17b41588f', 'C', 'M'),
('Melvin.Burns@prueba.com', 0, 'Burns', 15144683, '1969-12-31 00:00:00', 'Melvin', 'b6f4db43b88ab4880d04ebfc0cb821b79be0594e383695292d5360c8d47c344e2b2c9a9e4be06612', 'pregunta', '3888ee864fe61bff98b7c15b06c5a484f9f2fc700f8064d8c35b78381602340a95e5ec16b72588e6', 'C', 'M'),
('Nehru.Flores@prueba.com', 1, 'Flores', 96261658, '1969-12-31 00:00:00', 'Nehru', 'ad81127a95bb2712dd0255f4f2f946aeddce1ba26336478919207c0184905142e3ef1f9a482bf288', 'pregunta', '751eed6ae457252dd3db114725708868ffac2a3e1b0b8c4363383dc9b7bb47bb3cc5e0328d3a5cd0', 'C', 'F'),
('Phillip.Jennings@prueba.com', 1, 'Jennings', 51822956, '1969-12-31 00:00:00', 'Phillip', '02d0718f7cf8f6e36d558dc3f53b792d57eea62c2f7c5f517f0af9f8517bfbdcc49484ae1052a134', 'pregunta', 'be5534648919bcc0093ca82298f695e003c18fe8a66a33f5c433c5655608d6968ebd09f0423a4b8d', 'C', 'F'),
('prueba@frgp.utn.edu.ar', 0, 'Perez', 123456, '1988-11-01 00:00:00', 'Juan', '7348f32b49df80286883fe3828a9a8cad7d18b9645ffbd623846097b086b24205e046294a82413ad', 'pregunta', '59bb7921602be31b184779fcf3de42e0207db081029c5c67ec8a284c5018f1fc06538d6f50fe2c38', 'C', 'M'),
('Reese.Mcintosh@prueba.com', 0, 'Mcintosh', 45161489, '1969-12-31 00:00:00', 'Reese', 'd037cf4521c21957b0454c30606a3a4ea039e3a283ef810099427a36e31c5e940ac200e477a5215c', 'pregunta', 'd57ab1e5409032d9551b5e929bf7950c41fc887e38db07b581860470a85029898d20f3e14666975f', 'C', 'M'),
('Reuben.Jenkins@prueba.com', 0, 'Jenkins', 61123281, '1969-12-31 00:00:00', 'Reuben', 'f0e9323427c008057dae1e55990971f1043c87a06c6279f3799a5a8bb8df383a01a9731a355cb720', 'pregunta', '9e7141646109cb708b4315d5ce7345d9d9bdfd51ad68b5fb16cb266db085e7efc1ce114d723b4f04', 'C', 'F'),
('Rhea.Kirkland@prueba.com', 0, 'Kirkland', 82732926, '1969-12-31 00:00:00', 'Rhea', '4ef6f31203f0fbc90db6f1c228d16571b01790d3ceb673be5b4bb2a1528c0721a383949d4221076b', 'pregunta', 'e3310703ebcdc0c1b1fc058feab64da6311739c2142662e5676b772dd3387cb51351d41e2960267b', 'C', 'M'),
('Shannon.Yang@prueba.com', 1, 'Yang', 49293522, '1969-12-31 00:00:00', 'Shannon', '7987b2dcd01b7628dd9ea331b842681c3f582a40f4f9171b456d63f3f2a6b43dba80ea15438b13f2', 'pregunta', '35212b153070e66712f59a0bdb7d9560e68d0f3b0aed1efba083d8d48d90bf17f0c794a1079c2469', 'C', 'M'),
('Sigourney.Snyder@prueba.com', 1, 'Snyder', 36114643, '1969-12-31 00:00:00', 'Sigourney', '82a3b04e8326aba2e98be48d04901ee44d1da1b0a064b8911c60f976a9b67c7e9a80cb3dffed7ad2', 'pregunta', '80e90b67da823becd859924f9e6f37eb8f7debaaa81136e98f8d9841dc90091d395597b01223f2d4', 'C', 'M'),
('Virginia.Ball@prueba.com', 1, 'Ball', 99467266, '1969-12-31 00:00:00', 'Virginia', '9b6fe49ba4951daeb2b90efd8ee18f038496cb4538635132a90ff507cbef2ae82ea12d963a7f1d4f', 'pregunta', 'b83119c89cfb2416266c9e3d96eb71fa7b588face126170bef1c4c18d464c95e0f4e6bf3f942cf1e', 'C', 'F'),
('Whitney.Vaughn@prueba.com', 0, 'Vaughn', 47831324, '1969-12-31 00:00:00', 'Whitney', 'adbfb3a66e82baa91cce45758f8c3a1122b22ab82d8920c70185a95552c2ac2a957a638578e02850', 'pregunta', 'c82bf63e317a2d692f49ac92d9da15d7437cc0f1ce9da7dce5a4636c1993482eee5e0034a0bd54b8', 'C', 'F'),
('Zephr.Suarez@prueba.com', 0, 'Suarez', 14381328, '1969-12-31 00:00:00', 'Zephr', '4daf47ee933b813c04cd12a7c4028fc1d65385b2fcea5c3966b54865444899ee211f84b9521c5e00', 'pregunta', '9cf2f8abf2cb9e839d3a27775ef22bffbf8f241f65079b7e3c79f0db2e0b595452c984afa1a1efdb', 'C', 'F');


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
CREATE DEFINER=`root`@`localhost` EVENT `carteleraStatus` ON SCHEDULE EVERY 1 HOUR STARTS NOW() ENDS '2015-03-01 00:00:00' ON COMPLETION PRESERVE ENABLE COMMENT 'Activa o desactiva los registros' DO BEGIN
UPDATE cine.Cartelera SET activo=0 WHERE fechaInicio<NOW() AND fechaFin<=NOW();
UPDATE cine.Cartelera SET activo=1 WHERE fechaInicio<=NOW() AND fechaFin>NOW();
END$$

CREATE DEFINER=`root`@`localhost` EVENT `reservaStatus` ON SCHEDULE EVERY 1 HOUR STARTS NOW() ENDS '2015-03-01 00:00:00' ON COMPLETION NOT PRESERVE ENABLE COMMENT 'Desactiva los registros' DO UPDATE cine.Reserva SET activo=0 WHERE fechaReserva<=NOW()$$

CREATE DEFINER=`root`@`localhost` EVENT `promocionStatus` ON SCHEDULE EVERY 1 HOUR STARTS NOW() ENDS '2015-03-01 00:00:00' ON COMPLETION NOT PRESERVE ENABLE COMMENT 'Setea el status de los registros' DO BEGIN
UPDATE cine.Promocion SET activo=0 WHERE fechaInicio<NOW() AND fechaFin<=NOW();
UPDATE cine.Promocion SET activo=1 WHERE fechaInicio<=NOW() AND fechaFin>NOW();
END$$

DELIMITER ;

SET GLOBAL event_scheduler="ON";