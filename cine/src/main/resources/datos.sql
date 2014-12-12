INSERT INTO `Complejo` (`id`,`activo`,`direccion`,`nombre`) VALUES (1,1,'Avenida  R Balbin 2712','Hoyts General Cinema'), (2,1,'Av. Melian 4620 Saavedra','Hoyts'), (3,1,'Lavalle 896','Sweet Srl'), (4,0,'E. Echeverria 3750','Showcase norte'), (5,0,'Avenida Cabildo 1428','Belgrano Multiplex'), (6,0,'Lavalle 869','Atlas cine'), (7,0,'Avenida Cabildo 2829','Savoy'), (8,1,'Monroe 1655','Showcase cinema'), (9,0,'Soler 4123','Produccion cines'), (10,0,'Avenida R.S.Ortiz 140','Cinema group');
INSERT INTO `Sala` (`id`,`activa`,`idComplejo`,`numeroSala`) VALUES (1,1,1,1), (2,1,1,2), (3,1,2,1), (4,1,3,1), (5,1,3,2), (6,1,3,3), (7,1,4,1), (8,1,4,2), (9,1,5,1), (10,1,5,2), (11,1,5,3), (12,1,6,1), (13,1,7,1), (14,1,8,1), (15,1,9,1), (16,1,9,2), (17,1,10,1), (18,1,10,2), (19,1,10,3);
INSERT INTO `Complejo_Sala` (`Complejo_id`, `salas_id`) VALUES (1,1), (1,2), (2,3), (3,4), (3,5), (3,6), (4,7), (4,8), (5,9), (5,10), (5,11), (6,12), (7,13), (8,14), (9,15), (9,16), (10,17), (10,18), (10,19);
INSERT INTO `Asiento` (`id`,`columna`,`fila`) VALUES (1,'1','1'),(2,'2','1'),(3,'3','1'),(4,'4','1'),(5,'5','1'),(6,'6','1'),(7,'7','1'),(8,'8','1'),(9,'9','1'),(10,'10','1'),(11,'1','2'),(12,'2','2'),(13,'3','2'),(14,'4','2'),(15,'5','2'),(16,'6','2'),(17,'7','2'),(18,'8','2'),(19,'9','2'),(20,'10','2'),(21,'1','3'),(22,'2','3'),(23,'3','3'),(24,'4','3'),(25,'5','3'),(26,'6','3'),(27,'7','3'),(28,'8','3'),(29,'9','3'),(30,'10','3'),(31,'1','4'),(32,'2','4'),(33,'3','4'),(34,'4','4'),(35,'5','4'),(36,'6','4'),(37,'7','4'),(38,'8','4'),(39,'9','4'),(40,'10','4'),(41,'1','5'),(42,'2','5'),(43,'3','5'),(44,'4','5'),(45,'5','5'),(46,'6','5'),(47,'7','5'),(48,'8','5'),(49,'9','5'),(50,'10','5'),(51,'1','6'),(52,'2','6'),(53,'3','6'),(54,'4','6'),(55,'5','6'),(56,'6','6'),(57,'7','6'),(58,'8','6'),(59,'9','6'),(60,'10','6'),(61,'1','7'),(62,'2','7'),(63,'3','7'),(64,'4','7'),(65,'5','7'),(66,'6','7'),(67,'7','7'),(68,'8','7'),(69,'9','7'),(70,'10','7'),(71,'1','8'),(72,'2','8'),(73,'3','8'),(74,'4','8'),(75,'5','8'),(76,'6','8'),(77,'7','8'),(78,'8','8'),(79,'9','8'),(80,'10','8'),(81,'1','9'),(82,'2','9'),(83,'3','9'),(84,'4','9'),(85,'5','9'),(86,'6','9'),(87,'7','9'),(88,'8','9'),(89,'9','9'),(90,'10','9'),(91,'1','10'),(92,'2','10'),(93,'3','10'),(94,'4','10'),(95,'5','10'),(96,'6','10'),(97,'7','10'),(98,'8','10'),(99,'9','10'),(100,'10','10');
INSERT INTO `Horario`(`horaInicio`, `horaFin`, `duracion`) VALUES ('11:00:00', '13:00:00', '02:00:00'), ('13:00:00', '15:00:00', '02:00:00'), ('15:00:00', '17:00:00', '02:00:00'), ('17:00:00', '19:00:00', '02:00:00'), ('19:00:00', '21:00:00', '02:00:00'), ('21:00:00', '23:00:00', '02:00:00'), ('23:00:00', '01:00:00', '02:00:00'), ('10:00:00', '12:00:00', '02:00:00'), ('12:00:00', '14:00:00', '02:00:00'), ('14:00:00', '16:00:00', '02:00:00'), ('16:00:00', '18:00:00', '02:00:00'), ('18:00:00', '20:00:00', '02:00:00'), ('20:00:00', '22:00:00', '02:00:00'), ('22:00:00', '00:00:00', '02:00:00'), ('10:00:00', '12:30:00', '02:30:00'), ('12:30:00', '15:00:00', '02:30:00'), ('15:00:00', '17:30:00', '02:30:00'), ('17:30:00', '20:00:00', '02:30:00'), ('20:00:00', '22:30:00', '02:30:00'), ('22:30:00', '01:00:00', '02:30:00'), ('10:00:00', '13:00:00', '03:00:00'),('13:00:00', '16:00:00', '03:00:00'),('16:00:00', '19:00:00', '03:00:00'),('19:00:00', '22:00:00', '03:00:00'),('22:00:00', '01:00:00', '03:00:00');
INSERT INTO `FichaTecnica` (`id`,`actores`,`descripcion`,`director`,`urlTrailer`) VALUES (1,'Ricardo Darin, Oscar Martinez','sinopsis','Damian Szifron','Director'), (2,'Billy Bob Thornton, Vera Farmiga, Robert Downey Jr, Robert Duvall, Vincent D Onofrio, Dax Shephard, Jeremy Strong','Hank Palmer es un abogado que regresa a la ciudad de su niñez en donde su padre, del que esta distanciado, es juez y es sospechoso de haber cometido un asesinato','Bobby Farrelly','http://www.youtube.com/watch?v=qHMPxUX6cKA'), (4,'Jeff Daniels, Jim Carrey, Brady Bluhm, Cam Neely, Kathleen Turner, Laurie Holden, Rachel Melvin, Steve Tom.','Veinte años despuess de su primer aventura, Lloyd y Harry vuelven a las andadas para buscar a la hija perdida de Harry de quien nunca supo nada hasta ahora.','Bobby Farrelly, Peter Farrelly.','http://www.youtube.com/watch?v=camB3kmKg1U'), (5,'Robin Wright, Xavier Samuel, Naomi Watts, James Frecheville, Sophie Lowe.','Dos amigas encuentran la felicidad de forma inesperada en relaciones que rompen las convenciones.','Anne Fontaine.','http://www.youtube.com/watch?v=2phTUVU0PNA'), (6,'Ron Perlman, Channing Tatum, Zoe Saldana, Diego Luna, Christina Applegate.','Narra la leyenda de Manolo, un heroe soñador que emprende una misionn tipica a travÃ©s de mundos magicos, miticos y asombrosos, para poder reunirse con el amor de su vida y defender a su pueblo.','Jorge Gutierrez.','http://www.youtube.com/watch?v=JvIvF8ST8CY'), (7,'Jennifer Lawrence, Josh Hutcherson, Liam Hemsworth, Julianne Moore, Phillip Seymour Hoffman','Katniss es la cabeza de la rebelion contra el Capitolio, mientras debe encontrar la manera de rescatar a Peeta, que fue tomado como prisionero por el Presidente Snow.','Francis Lawrence.','http://www.youtube.com/watch?v=b1XQymoxcAQ'), (8,'Jessica Chastain, Michael Caine, Topher Grace, Anne Hathaway, Matthew Mcconaughey, Casey Affleck','Cuando el tiempo de la humanidad en la Tierra esta llegando a su fin, un grupo de exploradores emprende la mision mas importante en la historia: viajar mas alla de esta galaxia para descubrir si los humanos tienen futuro entre las estrellas.',' Christopher Nolan','http://www.youtube.com/watch?v=F1BiKNCNDBY'), (9,'Liam Neeson, David Harbour, Boyd Holbrook, Dan Stevens.','Matt Scudder es un ex-policÃ­a de la ciudad de Nueva York que ahora trabaja como detective privado fuera de la ley. Cuando Scudder acepta ayudar a un traficante de heroina a atrapar a los hombres que secuestraron y asesinaron a su esposa, descubre ','Scott Frank','http://www.youtube.com/watch?v=k4qPneZLxfw'), (10,'Colin Firth, Mark Strong, Nicole Kidman, Anne Marie Duff, Ben Crompton.','Christine sufre las secuelas de un terrible accidente. Solo logra retener recuerdos durante un dia. Vive atrapada en una existencia en la que desconoce casi todo lo que la vincula con el mundo. Su marido, Ben se lo explica en el comienzo de cada diaa.','Rowan Joffe.','http://www.youtube.com/watch?v=z48AQ2RpA3Y'), (11,'xxxxx','','',NULL), (12,'Darin','aaaaa','Director','Director'), (13,'Bruce Willlis','Cuando un meteorito esta a a punto de chocar con la tierra se buscara una solucionn enviando a un grupo de hombres entrenados en unos dias para realizar una excavacion en el meteorito y volarlo antes de que sea demasiado tarde.','Xaaa Duuuu',''), (14,'Darin','trata de una familia que ','Director','Director'), (15,'Bruce Willlis','aaaaaaaaaaaaaaaaaa','aaaaaaa','www.dddd.com.ar'), (16,'Annabelle Wallis,Alfre Woodard,Eric Ladin','John Form ha encontrado el regalo perfecto para su mujer Mia, quien estÃ¡ embarazada: una hermosa muñeca antigua y dificil de conseguir que luce un vestido blanco de novia','John R. Leonetti','http://www.youtube.com/embed/t5-9hrkexZI'), (17,'Elle Faning,Simon Pegg,Toni Colette','Los Boxtrolls es una fabula comica que se desarrolla en Cheesebridge, una ciudad de la epoca victoriana, cuya sociedad esta obsesionada con la elegancia, la riqueza, la clase alta y los quesos finos, aunque sean algo apestosos.','Graham Annable,Anthony Stacchi','http://www.youtube.com/embed/TnaYUdnBkYQ'), (18,'Liam Neeson,Dan Stevens,David Harbour','Basada en la exitosa serie de novelas de misterio de Lawrence Block, CAMINANDO ENTRE TUMBAS es protagonizada por Liam Neeson como Matt Scudder, ','Scott Frank','http://www.youtube.com/embed/k4qPneZLxfw'), (19,'Edwin Hodge,Ben Feldman,Perdita Weeks','Miles de catacumbas laberinticas se encuentran por debajo de las calles de Paris, conformando un hogar eterno para innumerables almas.','John Erick Dowdle','http://www.youtube.com/embed/pkNjAx8odU4'), (20,'Juan Gil Navarro,Agustina Lecouna, Nicolas Alberti','De sus profundidades de un bosque emerge, desnudo y bañado en sangre, Elias, sin un solo recuerdo de lo que les ha ocurrido a el y a sus tres amigos desaparecidos. ',' Ezio Massa','http://www.youtube.com/embed/PyNockpbFF0'), (21,'Christian Bale,Aaron Paul,Joel Edgerton','BiografÃ­a sobre Moises, uno de los mÃ¡s importantes personajes biblicos, y que lidera el exodo de los judios por Egipto','Ridley Scott','http://www.youtube.com/embed/XVJEm3c9eoA'), (22,'Channing Tatum,Zoe Saldana,Ron Perlman','EL LIBRO DELA VIDA es el viaje de Manolo, un joven que se debate entre el cumplimiento de las expectativas de su familia y luego de su corazonn.','Jorge R. Gutierrez','http://www.youtube.com/embed/JvIvF8ST8CY'), (23,'Matthew McConaughey,Anne Hathaway,Jessica Chastain','La humanidad nacio en la Tierra, pero nunca estuvo destinada a morir en ella. ','Christopher Nolan','http://www.youtube.com/embed/F1BiKNCNDBY'), (24,'Helene Giraud,Thomas Szabo','En un pacifico bosque, los restos de un picnic desatan una guerra entre colonias de hormigas rivales obsesionadas con tener el control de un mismo premio: una caja de cubos de azucar!','Helenene Giraud,Tomas Szabo','http://www.youtube.com/embed/5xOdazHpoYs'), (25,'Nicole Kidman,Colin Firth,Mark Strong','Es la historia de Christine, una escritora de cuarenta y siete aÃ±os de edad que, a raiz de un accidente sufrido a los 25 años, es incapaz de formar nuevos recuerdos y mantener los nuevos durante mas de un dia.','Rowan Joffe','http://www.youtube.com/embed/z48AQ2RpA3Y'), (26,'No tiene','Pau y Lucie aparentan ser la pareja feliz a la que nada puede afectarle. Pero luego de que comiencen a recibir ramos de flores animos y que Lou, una joven de 20 aÃ±os comience a cruzarse en el camino de Paul, las mascaras caen.','No tiene','http://www.youtube.com/embed/JC3_UhZrCuQ'), (27,'Johannes Kuhnke,Lisa Loven Kongsli,Clara Wettergren','Una familia sueca viaja a los Alpes franceses para disfrutar de unos dias de esqui y tiempo de calidad en familia.','Ruben Astlund','http://www.youtube.com/embed/qf4T0freLbY'), (28,'Olivia Newton John,John Travolta','Verano de 1959. Sandy (Olivia Newton John) y Danny (John Travolta) han pasado un romantico y maravilloso verano juntos, pero, cuando las vacaciones se acaban, sus caminos se separan.','Randal Kleiser','http://www.youtube.com/embed/q6CuHRX_dmI'), (29,'Audrey Hepburn,Mickey Rooney','Holly Golightly es una bella joven neoyorquina que, aparentemente, lleva una vida facil y alegre. Tiene un comportamiento bastante extravagante, por ejemplo, desayunar contemplando el escaparate de la lujosa joyeria Tiffanys.','Blake Edwards','http://www.youtube.com/embed/urQVzgEO_w8'), (30,'Saul Swimmer','Celebra la magia de Queen con los fans de todo el mundo en este diaa tan especial. Queen Rock Montreal, un recital unico de una de las bandas mas grandes del mundo, este recital se conoce como una de las mejores actuaciones de Queen. ','Saul Swimmer','http://www.youtube.com/embed/e0NDSVsSqx0'), (31,'Michael J Fox, Marty McFly','un tipico adolescente americano de los años 80 es accidentalmente enviado al año 1955 en un DeLorean inventado por su amigo Doc, un cientifico al que todos toman por loco que puede viajar a traves del tiempo y que se alimenta con plutonio','Robert Zemeckis','http://www.youtube.com/embed/Es3df08rTb0');
INSERT INTO `Pelicula` (`id`,`activo`,`clasificacion`,`duracion`,`fechaCreacion`,`idioma`,`nombre`,`reposicion`,`subs`,`detalles_id`) VALUES (1,1,'13','02:01:00','2014-11-20 23:31:38','ES','RELATOS SALVAJES',0,0,14), (2,1,'13','02:21:00','2014-11-20 21:00:53','EN','The judge',0,1,2), (3,1,'13','01:34:00','2014-10-30 00:00:00','ES','Refugiado',0,0,2), (4,1,'13','01:50:00','2014-11-13 00:00:00','EN','Dumb and Dumber To',0,1,4), (5,1,'18','01:42:00','2014-11-13 00:00:00','EN','Adore',0,1,5), (6,1,'ATP','01:33:00','2014-10-16 00:00:00','EN','The book of life',0,1,6), (7,1,'13','02:03:00','2014-11-20 00:00:00','EN','The Hunger Games: Mockingjay. Part 1',0,1,7), (8,1,'13','02:48:00','2014-11-06 00:00:00','EN',' Interstellar',0,1,8), (9,1,'16','01:53:00','2014-11-13 00:00:00','EN','Caminando entre Tumbas',0,1,9), (10,1,'13','01:53:00','2014-11-13 00:00:00','EN','Antes de Despertar',0,1,10), (12,1,'13','02:30:00','2014-11-20 23:32:17','EN','ARMAGEDON',0,0,15), (13,1,'16','01:30:00','2014-11-13 00:00:00','ES','ANNABELLE',0,0,16), (14,1,'ATP','01:25:00','2014-11-13 00:00:00','ES','BOXTROLLS',0,0,17), (15,1,'16','01:10:00','2014-11-13 00:00:00','ES','CAMINANDO ENTRE TUMBAS',0,0,18), (16,1,'16','01:30:00','2014-11-13 00:00:00','ES','ASI EN LA TIERRA COMO EN EL INFIERNO',0,0,19), (17,1,'16','01:30:00','2014-11-13 00:00:00','ES','EL DIA DE LOS MUERTOS',0,0,20), (18,1,'ATP','02:30:00','2014-11-13 00:00:00','ES','EXODO DIOSES Y REYES',0,0,21), (19,1,'ATP','01:20:00','2014-11-13 00:00:00','ES','MINUSCULOS',0,0,24), (20,1,'16','01:20:00','2014-11-13 00:00:00','ES','ANTES DEL FRIO INVIERNO',0,0,26), (21,1,'13','01:10:00','2014-11-13 00:00:00','ES','FORCE MAJEURE LA TRAICION DEL INSTINTO',0,0,27), (22,1,'ATP','01:40:00','2014-11-13 00:00:00','EN','GREASE',0,1,28), (23,1,'ATP','01:40:00','2014-11-13 00:00:00','EN','MUÑEQUITA DE LUJO',0,1,29), (24,1,'ATP','01:45:00','2014-11-13 00:00:00','EN','QUEEN ROCK MONTREAL',0,1,30), (25,1,'ATP','01:50:00','2014-11-13 00:00:00','EN','VOLVER AL FUTURO',0,1,31), (27,1,'13','01:30:00','2014-11-13 00:00:00','EN','Titanic',1,1,6);
INSERT INTO `Cartelera` (`id`,`activo`,`fechaFin`,`fechaInicio`,`proyeccion`,`subtitulada`,`pelicula_id`) VALUES (1,1,'2014-11-26 00:00:00','2014-11-20 00:00:00','2d',0,1), (2,1,'2014-11-26 00:00:00','2014-11-20 00:00:00','2d',0,2), (3,1,'2014-11-27 00:00:00','2014-11-20 00:00:00','3d',0,15), (4,1,'2014-11-20 00:00:00','2014-11-20 00:00:00','3d',0,3), (5,0,'2014-11-20 00:00:00','2014-11-13 00:00:00','3d',0,12), (6,1,'2014-11-27 00:00:00','2014-11-20 00:00:00','3d',0,8), (7,0,'2014-11-13 00:00:00','2014-11-06 00:00:00','3d',0,6), (8,0,'2014-11-20 00:00:00','2014-11-13 00:00:00','2d',0,18), (9,1,'2014-11-27 00:00:00','2014-11-20 00:00:00','2d',0,17), (10,1,'2014-11-27 00:00:00','2014-11-20 00:00:00','3d',0,4);
INSERT INTO `Precio` (`id`,`activo`,`general`,`mayor`,`menor`) VALUES (1,1,55,45,35), (2,0,45,35,25), (3,0,35,25,15);
INSERT INTO `Funcion` (`id`, `activo`, `horario_id`, `pelicula_id`, `sala_id`) VALUES (1,1,7,1,1), (2,1,7,7,3), (3,1,7,9,4), (4,1,7,22,5), (5,1,7,25,6); 
INSERT INTO `Promocion` (`id`,`activo`,`descripcion`,`fechainicio`,`fechafin`,`nombre`) VALUES (1,1,'Ninguna','1970-01-01 00:00:00','1970-01-01 00:00:00','Ninguna'), (2,1,'Dos por Uno','2014-11-03 00:00:00','2014-11-20 00:00:00','Banco Galicia'), (3,1,'Banco Galicia','2014-11-04 00:00:00','2014-11-20 00:00:00','Descuento'), (4,1,'Dos por Uno','2014-11-14 00:00:00','2014-11-20 00:00:00','Coca-cola');