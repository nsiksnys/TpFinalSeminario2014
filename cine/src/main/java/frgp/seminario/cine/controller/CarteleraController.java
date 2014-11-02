package frgp.seminario.cine.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import frgp.seminario.cine.bo.impl.BoCartelera;
import frgp.seminario.cine.forms.CarteleraForm;
import frgp.seminario.cine.model.Cartelera;

@RequestMapping(value="/cartelera/**")
@Controller
public class CarteleraController {
	@Autowired
	//@Qualifier("BoCartelera") //aclaro cual es el bean a inyectar
	BoCartelera logicaNegocio; //aclaro las clases que se utilizan en este caso en particular
	
	private static final Logger LOG = LoggerFactory.getLogger(CarteleraController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(Principal principal) {
		return new ModelAndView("redirect:/cartelera/lista");
	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public ModelAndView lista(Principal principal) {
		ArrayList<Cartelera> lista = (ArrayList<Cartelera>) logicaNegocio.listarTodos();
		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("lista", lista);
		LOG.info("/cartelera/lista: listando " + lista.size() + " registro(s)");
		return mav;
	}
		
	@RequestMapping(value = "/cartelera/alta", method = RequestMethod.GET)
	public ModelAndView alta(Principal principal) {
		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("peliculas", logicaNegocio.getAllPeliculasActivas());
		return mav;
	}
	
	@RequestMapping(value = "/modificar", method = RequestMethod.GET)
	public ModelAndView modificar(@RequestParam("id") Long id, Principal principal) 
	{
		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("registro", logicaNegocio.get(id));
		return mav;
	}
	
	@RequestMapping(value = "/borrar", method = RequestMethod.GET)
	public ModelAndView borrar(@RequestParam("id") Long id, Principal principal) 
	{
		ModelAndView mav =new ModelAndView("redirect:/cartelera/lista");//indico que uso la vista "modificar"
		
		if (!logicaNegocio.desactivar(logicaNegocio.get(id))){//si no se guarda
			//mav.getModelMap().addAttribute("error", "Por favor revise el formulario");//agrego el mensaje de error
		}
		else
		{
			LOG.info("/cartelera/borrar: modificado registro con id " + id);
		}
		return mav;
	}
	
	@RequestMapping(value = "/activar", method = RequestMethod.GET)
	public ModelAndView activar(@RequestParam("id") Long id, Principal principal) 
	{
		ModelAndView mav =new ModelAndView("redirect:/cartelera/lista");//indico que uso la vista "modificar"
		
		if (!logicaNegocio.activar(logicaNegocio.get(id))){//si no se guarda
			//mav.getModelMap().addAttribute("error", "Por favor revise el formulario");//agrego el mensaje de error
		}
		else
		{
			LOG.info("/cartelera/activar: activado registro con id " + id);
		}
		return mav;
	}
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public ModelAndView guardar(@ModelAttribute CarteleraForm formulario, Principal principal) 
	{
		ModelAndView mav =new ModelAndView("redirect:/cartelera/lista");
		//ModelAndView mav =new ModelAndView("redirect:/cartelera/alta");
		Cartelera item = logicaNegocio.formToEntity(formulario);
		
		if (!logicaNegocio.guardar(item)){//si no se guarda
			mav.setViewName("redirect:/cartelera/alta");
			mav.getModelMap().addAttribute("error", "Por favor revise el formulario");//agrego el mensaje de error
		}
		else
		{
			LOG.info("/cartelera/alta: agregado registro nuevo con id " + item.getId());
			//mav.getModelMap().addAttribute("ok", "El registro se guardo correctamente");
		}
		return mav;
	}
	
	@RequestMapping(value = "/modificar", method = RequestMethod.POST)
	public ModelAndView modificar(@ModelAttribute CarteleraForm formulario, Principal principal) 
	{
		ModelAndView mav =new ModelAndView("redirect:/cartelera/lista");
		//ModelAndView mav =new ModelAndView();
		Cartelera registro = logicaNegocio.formToEntity(formulario);
		registro.setId(Long.parseLong(formulario.getId()));
		
		if (!logicaNegocio.modificar(registro)){//si no se guarda
			mav.setViewName("redirect:/pelicula/modificar?id="+formulario.getId());
			mav.getModelMap().addAttribute("error", "Por favor revise el formulario");//agrego el mensaje de error
		}
		else
		{
			LOG.info("/cartelera/modificar: actualizado registro con id " + registro.getId());
			//mav.getModelMap().addAttribute("ok", "La pelicula se guardo correctamente");
		}
		return mav;
	}
}