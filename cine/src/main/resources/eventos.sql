SET GLOBAL event_scheduler="ON" 

CREATE DEFINER=`root`@`localhost` EVENT `carteleraStatus` ON SCHEDULE EVERY 1 DAY STARTS NOW() ENDS '2015-03-01 00:00:00' ON COMPLETION PRESERVE ENABLE COMMENT 'Activa o desactiva los registros' DO BEGIN
UPDATE cine.Cartelera SET activo=0 WHERE fechaInicio<NOW() AND fechaFin<=NOW();
UPDATE cine.Cartelera SET activo=1 WHERE fechaInicio<=NOW() AND fechaFin>NOW();
END

CREATE DEFINER=`root`@`localhost` EVENT `promocionStatus` ON SCHEDULE EVERY 1 DAY STARTS NOW() ENDS '2015-03-01 00:00:00' ON COMPLETION NOT PRESERVE ENABLE COMMENT 'Setea el status de los registros' DO BEGIN
UPDATE cine.Promocion SET activo=0 WHERE fechaInicio<NOW() AND fechaFin<=NOW();
UPDATE cine.Promocion SET activo=1 WHERE fechaInicio<=NOW() AND fechaFin>NOW();
END

CREATE DEFINER=`root`@`localhost` EVENT `reservaStatus` ON SCHEDULE EVERY 1 DAY STARTS NOW() ENDS '2015-03-01 00:00:00' ON COMPLETION NOT PRESERVE ENABLE COMMENT 'Desactiva los registros' DO UPDATE cine.Reserva SET activo=0 WHERE fechaReserva<=NOW()