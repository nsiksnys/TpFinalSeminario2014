CREATE TRIGGER `funcionStatus` AFTER UPDATE ON `Cartelera` FOR EACH ROW BEGIN IF NEW.activo=0 THEN UPDATE Funcion SET activo=0 WHERE Funcion.pelicula_id=NEW.pelicula_id AND activo=1; END IF; END 