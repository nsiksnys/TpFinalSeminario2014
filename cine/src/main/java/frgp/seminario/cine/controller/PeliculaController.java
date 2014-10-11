package frgp.seminario.cine.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import frgp.seminario.cine.model.Pelicula;
import frgp.seminario.cine.bo.impl.BoPelicula;
import frgp.seminario.cine.forms.pelicula.PeliculaForm;

@RequestMapping(value="/pelicula/**")
@Controller
public class PeliculaController {
	BoPelicula logicaNegocio;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView lista(Principal principal)
	{
		ModelAndView mav =new ModelAndView("lista");//indico que uso la vista "lista"
		mav.getModelMap().addAttribute("lista", logicaNegocio.listarTodos());
		return mav;
	}
		
	@RequestMapping(value = "/alta", method = RequestMethod.GET)
	public String alta(Principal principal) 
	{
		return "alta";
	}
	
	@RequestMapping(value = "/modificar", method = RequestMethod.GET)
	public ModelAndView modificar(@RequestParam("id") int id, Principal principal) 
	{
		ModelAndView mav =new ModelAndView("modificar");//indico que uso la vista "modificar"
		mav.getModelMap().addAttribute("registro", logicaNegocio.get(id));
		return mav;
	}
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public ModelAndView guardar(@ModelAttribute PeliculaForm formulario, Principal principal) 
	{
		ModelAndView mav =new ModelAndView("redirect:lista");
		Pelicula pelicula = new Pelicula(formulario.getNombre(), formulario.getIdioma(), formulario.isSubs(),
				formulario.getClasificacion(), formulario.isReposicion(), formulario.getDescripcion(),
				formulario.getActores(), formulario.getDirector(), formulario.getDirector());
		
		if (!logicaNegocio.guardar(pelicula)){//si no se guarda
			mav.setViewName("alta");
			mav.getModelMap().addAttribute("error", "Por favor revise el formulario");//agrego el mensaje de error
		}
		else
		{
			mav.getModelMap().addAttribute("ok", "La pelicula se guardo correctamente");
		}
		return mav;
	}
	
	@RequestMapping(value = "/modificar", method = RequestMethod.POST)
	public ModelAndView modificar(@ModelAttribute PeliculaForm formulario, Principal principal) 
	{
		ModelAndView mav =new ModelAndView("redirect:lista");
		Pelicula pelicula = new Pelicula(formulario.getNombre(), formulario.getIdioma(), formulario.isSubs(),
				formulario.getClasificacion(), formulario.isReposicion(), formulario.getDescripcion(),
				formulario.getActores(), formulario.getDirector(), formulario.getDirector());
		
		if (!logicaNegocio.modificar(pelicula)){//si no se guarda
			mav.setViewName("alta");
			mav.getModelMap().addAttribute("error", "Por favor revise el formulario");//agrego el mensaje de error
		}
		else
		{
			mav.getModelMap().addAttribute("ok", "La pelicula se guardo correctamente");
		}
		return mav;
	}
}
