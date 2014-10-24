package frgp.seminario.cine.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import frgp.seminario.cine.model.Pelicula;
import frgp.seminario.cine.bo.BusinessObject;
import frgp.seminario.cine.bo.impl.BoPelicula;
import frgp.seminario.cine.forms.PeliculaForm;

@RequestMapping(value="/pelicula/**")
@Controller
public class PeliculaController {
	@Autowired
	@Qualifier("BoPelicula") //aclaro cual es el bean a inyectar
	BusinessObject<Pelicula, PeliculaForm> logicaNegocio; //aclaro las clases que se utilizan en este caso en particular
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(Principal principal)
	{
		ModelAndView mav =new ModelAndView("lista");//indico que uso la vista "lista"
		mav.getModelMap().addAttribute("lista", logicaNegocio.listarTodos());
		return mav;
	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
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
		Pelicula item = logicaNegocio.formToEntity(formulario);
		
		if (!logicaNegocio.guardar(item)){//si no se guarda
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
	public ModelAndView modificar(@RequestParam("id") String id, @ModelAttribute PeliculaForm formulario, Principal principal) 
	{
		ModelAndView mav =new ModelAndView("redirect:lista");
		Pelicula registro = logicaNegocio.formToEntity(formulario);
		registro.setId(Long.parseLong(id));
		
		if (!logicaNegocio.modificar(registro)){//si no se guarda
			mav.setViewName("modificar");
			mav.getModelMap().addAttribute("error", "Por favor revise el formulario");//agrego el mensaje de error
		}
		else
		{
			mav.getModelMap().addAttribute("ok", "La pelicula se guardo correctamente");
		}
		return mav;
	}
}
