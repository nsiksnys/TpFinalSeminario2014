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

import frgp.seminario.cine.model.Precio;
import frgp.seminario.cine.bo.impl.BoPrecio;
import frgp.seminario.cine.forms.PrecioForm;

@RequestMapping(value="/precio/**")
@Controller
public class PrecioController {
	@Autowired
	@Qualifier("BoPrecio") //aclaro cual es el bean a inyectar
	BoPrecio logicaNegocio; //aclaro las clases que se utilizan en este caso en particular
	
	private static final Logger LOG = LoggerFactory.getLogger(PrecioController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(Principal principal)
	{
		return new ModelAndView("redirect:/precio/lista");
	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public ModelAndView lista(Principal principal)
	{
		ArrayList<Precio> lista = (ArrayList<Precio>) logicaNegocio.listarTodos();
		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("lista", lista);
		LOG.info("/precio/lista: listando " + lista.size() + " registro(s)");
		return mav;
	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.POST)
	public ModelAndView lista(@RequestParam("ok") String ok,Principal principal)
	{
		ArrayList<Precio> lista = (ArrayList<Precio>) logicaNegocio.listarTodos();
		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("lista", lista);
		
		if (!ok.equals(""))
			mav.getModelMap().addAttribute("ok", ok);
		
		LOG.info("/precio/lista: listando " + lista.size() + " registro(s)");
		return mav;
	}
		
	@RequestMapping(value = "/alta", method = RequestMethod.GET)
	public ModelAndView alta(Principal principal)
	{
		return new ModelAndView();
	}
	
	@RequestMapping(value = "/modificar", method = RequestMethod.GET)
	public ModelAndView modificar(@RequestParam("id") Long id, Principal principal) 
	{
		//ModelAndView mav =new ModelAndView("modificar");//indico que uso la vista "modificar"
		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("registro", logicaNegocio.get(id));
		return mav;
	}
	
	@RequestMapping(value = "/borrar", method = RequestMethod.GET)
	public ModelAndView borrar(@RequestParam("id") Long id, Principal principal) 
	{
		ModelAndView mav =new ModelAndView("redirect:/precio/lista");//indico que uso la vista "modificar"
		
		if (!logicaNegocio.desactivar(logicaNegocio.get(id))){//si no se guarda
			//mav.getModelMap().addAttribute("error", "Por favor revise el formulario");//agrego el mensaje de error
		}
		else
		{
			LOG.info("/precio/borrar: desactivado registro con id " + id);
			//mav.getModelMap().addAttribute("ok", "La pelicula se guardo correctamente");
		}
		return mav;
	}
	
	@RequestMapping(value = "/activar", method = RequestMethod.GET)
	public ModelAndView activar(@RequestParam("id") Long id, Principal principal) 
	{
		ModelAndView mav =new ModelAndView("redirect:/precio/lista");//indico que uso la vista "modificar"
		
		if (!logicaNegocio.activar(logicaNegocio.get(id))){//si no se guarda
			//mav.getModelMap().addAttribute("error", "Por favor revise el formulario");//agrego el mensaje de error
		}
		else
		{
			LOG.info("/precio/activar: activado registro con id " + id);
		}
		return mav;
	}
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public ModelAndView guardar(@ModelAttribute PrecioForm formulario, Principal principal) 
	{
		ModelAndView mav =new ModelAndView("redirect:/precio/lista");
		//ModelAndView mav =new ModelAndView("redirect:/precio/alta");
		Precio item = logicaNegocio.formToEntity(formulario);
		
		if (!logicaNegocio.guardar(item)){//si no se guarda
			mav.setViewName("redirect:/precio/alta");
			mav.getModelMap().addAttribute("error", "Por favor revise el formulario");//agrego el mensaje de error
		}
		else
		{
			LOG.info("/precio/alta: agregado registro nuevo con id " + item.getId());
			mav.getModelMap().addAttribute("ok", "El precio se guardo correctamente");
		}
		return mav;
	}
	
	@RequestMapping(value = "/modificar", method = RequestMethod.POST)
	public ModelAndView modificar(@ModelAttribute PrecioForm formulario, Principal principal) 
	{
		ModelAndView mav =new ModelAndView("redirect:/precio/lista");
		//ModelAndView mav =new ModelAndView();
		Precio registro = logicaNegocio.formToEntity(formulario);
		registro.setId(Long.parseLong(formulario.getId()));
		
		if (!logicaNegocio.modificar(registro)){//si no se guarda
			mav.setViewName("redirect:/precio/modificar?id="+formulario.getId());
			mav.getModelMap().addAttribute("error", "Por favor revise el formulario");//agrego el mensaje de error
		}
		else
		{
			LOG.info("/precio/modificar: actualizado registro con id " + registro.getId());
			mav.getModelMap().addAttribute("ok", "El precio se actualizo correctamente");
		}
		return mav;
	}
}