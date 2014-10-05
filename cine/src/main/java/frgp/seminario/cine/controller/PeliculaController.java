package frgp.seminario.cine.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PeliculaController {
	@RequestMapping(value = "/pelicula/lista", method = RequestMethod.GET)
	public String lista(Principal principal) {
		return "lista";
	}
		
	@RequestMapping(value = "/pelicula/alta", method = RequestMethod.GET)
	public String alta(Principal principal) {
		return "alta";
	}
	
	@RequestMapping(value = "/pelicula/modificar", method = RequestMethod.GET)
	public String modificar(Principal principal) {
		return "alta";
	}
}
