package frgp.seminario.cine.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;

import javax.validation.Valid;
import org.codehaus.jackson.map.ObjectMapper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import frgp.seminario.cine.model.Pelicula;
import frgp.seminario.cine.support.web.MessageHelper;
import frgp.seminario.cine.bo.impl.BoPelicula;
import frgp.seminario.cine.forms.PeliculaForm;

@RequestMapping(value="/pelicula/**")
@Controller
public class PeliculaController {
	@Autowired
	@Qualifier("BoPelicula") //aclaro cual es el bean a inyectar
	BoPelicula logicaNegocio; //aclaro las clases que se utilizan en este caso en particular
	
	private static final Logger LOG = LoggerFactory.getLogger(PeliculaController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(Principal principal)
	{
		return new ModelAndView("redirect:/pelicula/lista");
	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public ModelAndView lista(Principal principal)
	{
		ArrayList<Pelicula> lista = (ArrayList<Pelicula>) logicaNegocio.listarTodos();
		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("lista", lista);
		LOG.info("/pelicula/lista: listando " + lista.size() + " registro(s)");
		return mav;
	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.POST)
	public ModelAndView lista(@RequestParam("ok") String ok,Principal principal)
	{
		ArrayList<Pelicula> lista = (ArrayList<Pelicula>) logicaNegocio.listarTodos();
		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("lista", lista);
		
		if (!ok.equals(""))
			mav.getModelMap().addAttribute("ok", ok);
		
		LOG.info("/pelicula/lista: listando " + lista.size() + " registro(s)");
		return mav;
	}
		
	@RequestMapping(value = "/alta", method = RequestMethod.GET)
	public ModelAndView alta(Principal principal)
	{
		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("peliculaForm", new PeliculaForm());
		return mav;
	}
	
	@RequestMapping(value = "/modificar", method = RequestMethod.GET)
	public ModelAndView modificar(@RequestParam("id") Long id, Principal principal) 
	{
		//ModelAndView mav =new ModelAndView("modificar");//indico que uso la vista "modificar"
		Pelicula registro = logicaNegocio.get(id);
		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("peliculaForm", logicaNegocio.entityToForm(registro));
		mav.getModelMap().addAttribute("registro", registro);//FIXME: esto queda o se va?
		return mav;
	}
	
	@RequestMapping(value = "/borrar", method = RequestMethod.GET)
	public ModelAndView borrar(@RequestParam("id") Long id, Principal principal) 
	{
		ModelAndView mav =new ModelAndView("redirect:/pelicula/lista");//indico que uso la vista "modificar"
		
		if (!logicaNegocio.desactivar(logicaNegocio.get(id))){//si no se guarda
			//mav.getModelMap().addAttribute("error", "Por favor revise el formulario");//agrego el mensaje de error
		}
		else
		{
			LOG.info("/pelicula/borrar: desactivado registro con id " + id);
			//mav.getModelMap().addAttribute("ok", "La pelicula se guardo correctamente");
		}
		return mav;
	}
	
	@RequestMapping(value = "/activar", method = RequestMethod.GET)
	public ModelAndView activar(@RequestParam("id") Long id, Principal principal) 
	{
		ModelAndView mav =new ModelAndView("redirect:/pelicula/lista");//indico que uso la vista "modificar"
		
		if (!logicaNegocio.activar(logicaNegocio.get(id))){//si no se guarda
			//mav.getModelMap().addAttribute("error", "Por favor revise el formulario");//agrego el mensaje de error
		}
		else
		{
			LOG.info("/pelicula/activar: activado registro con id " + id);
		}
		return mav;
	}
	
	@RequestMapping(value = "/alta", method = RequestMethod.POST)
	public String alta(@Valid @ModelAttribute PeliculaForm formulario, /*Principal principal,*/ Errors errors, RedirectAttributes ra) 
	{
		//ModelAndView mav =new ModelAndView("redirect:/pelicula/lista");
		//ModelAndView mav =new ModelAndView("redirect:/pelicula/alta");
		Pelicula item = logicaNegocio.formToEntity(formulario);
		
		if (errors.hasErrors())
		{
			LOG.error("/pelicula/alta: por favor revise el formulario.");
			MessageHelper.addErrorAttribute(ra, "Por favor revise el formulario.");
			return null;
		}
		
		if (!logicaNegocio.guardar(item)){//si no se guarda
			LOG.error("/pelicula/alta: por favor revise el formulario.");
			MessageHelper.addErrorAttribute(ra, "Por favor, vuelva a llenar el formulario.");
			return "redirect:/pelicula/alta";
		}
		else
		{
			LOG.info("/pelicula/alta: agregado registro nuevo con id " + item.getId());
			MessageHelper.addSuccessAttribute(ra, "La pelicula se guardo correctamente");
		}
		return "redirect:/pelicula/lista";
	}
	
	@RequestMapping(value = "/modificar", method = RequestMethod.POST)
	public String modificar(@Valid @ModelAttribute PeliculaForm formulario, /*Principal principal,*/ Errors errors, RedirectAttributes ra) 
	{
		//ModelAndView mav =new ModelAndView("redirect:/pelicula/lista");
		//ModelAndView mav =new ModelAndView();
		Pelicula registro = logicaNegocio.formToEntity(formulario);
		registro.setId(Long.parseLong(formulario.getId()));
		
		if (errors.hasErrors())
		{
			LOG.error("/pelicula/modificar: por favor revise el formulario.");
			MessageHelper.addErrorAttribute(ra, "Por favor revise el formulario.");
			return null;
		}
		
		if (!logicaNegocio.modificar(registro)){//si no se guarda
			LOG.error("/pelicula/modificar: por favor revise el formulario.");
			MessageHelper.addErrorAttribute(ra, "Por favor revise el formulario.");
			return null;
		}
		else
		{
			LOG.info("/pelicula/modificar: actualizado registro con id " + registro.getId());
			MessageHelper.addSuccessAttribute(ra, "El usuario se actualizo correctamente");
		}
		return "redirect:/pelicula/lista";
	}
	
	@RequestMapping(value = "/getpeliculas", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, String> getPeliculasDisponibles(Principal principal)
	{
		LOG.info("/pelicula/getpeliculas: pedidas peliculas activas");
		return logicaNegocio.getAllActiveMap();
	}
}
