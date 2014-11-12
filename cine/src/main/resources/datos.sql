INSERT INTO `Complejo` (`id`, `activo`, `direccion`, `nombre`) VALUES (1, 0, 'direccion', 'complejo');
INSERT INTO `Sala` (`id`, `activa`, `idComplejo`, `numeroSala`) VALUES (1, 1, 1, 1),(2, 1, 1, 2);
INSERT INTO `Complejo_Sala` (`Complejo_id`, `salas_id`) VALUES (1, 1),(1, 2);
INSERT INTO `FichaTecnica` (`id`, `actores`, `descripcion`, `director`, `urlTrailer`) VALUES (1, 'Actor1, Actor2', 'sinopsis', 'Director', 'Director');
INSERT INTO `Pelicula` (`id`, `activo`, `clasificacion`, `fechaCreacion`, `idioma`, `nombre`, `reposicion`, `subs`, `detalles_id`) VALUES (1, 1, '13', '2014-11-01 16:02:35', 'ES', 'RELATOS SALVAJES', 0, 0, 1);
INSERT INTO `Cartelera` (`id`, `activo`, `fechaFin`, `fechaInicio`, `proyeccion`, `subtitulada`, `pelicula_id`) VALUES (1, 1, '2014-12-31 00:00:00', '2014-08-14 00:00:00', '2D', 0, 1);