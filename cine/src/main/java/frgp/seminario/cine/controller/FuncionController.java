package frgp.seminario.cine.controller;

import java.security.Principal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import frgp.seminario.cine.bo.impl.BoFuncion;
import frgp.seminario.cine.forms.CarteleraForm;
import frgp.seminario.cine.forms.FuncionForm;
import frgp.seminario.cine.model.Funcion;
import frgp.seminario.cine.model.Horario;
import frgp.seminario.cine.utils.SecurityUtils;

@RequestMapping(value="/funcion/**")
@Controller
public class FuncionController {
	@Autowired
	@Qualifier("BoFuncion") //aclaro cual es el bean a inyectar
	BoFuncion logicaNegocio;
	
	@Autowired
	SecurityUtils security;
	
	private static String ROLE = "A";
	
	private static final Logger LOG = LoggerFactory.getLogger(FuncionController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(Principal principal)
	{
		security.isAuthorized(principal, ROLE);

		return new ModelAndView("redirect:/funcion/lista");
	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public ModelAndView lista(Principal principal)
	{
		security.isAuthorized(principal, ROLE);

		ArrayList<Funcion> lista = (ArrayList<Funcion>) logicaNegocio.listarTodos();
		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("lista", lista);
		LOG.info("/funcion/lista: listando " + lista.size() + " registro(s)");
		return mav;
	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.POST)
	public ModelAndView lista(@RequestParam("ok") String ok,Principal principal)
	{
		security.isAuthorized(principal, ROLE);

		ArrayList<Funcion> lista = (ArrayList<Funcion>) logicaNegocio.listarTodos();
		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("lista", lista);
		
		if (!ok.equals(""))
			mav.getModelMap().addAttribute("ok", ok);
		
		LOG.info("/funcion/lista: listando " + lista.size() + " registro(s)");
		return mav;
	}
		
	@RequestMapping(value = "/alta", method = RequestMethod.GET)
	public ModelAndView alta(Principal principal)
	{
		security.isAuthorized(principal, ROLE);

		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("complejos", logicaNegocio.listarTodosComplejos());
		mav.getModelMap().addAttribute("salas", logicaNegocio.listarTodasSalas());
		mav.getModelMap().addAttribute("horarios", logicaNegocio.listarTodosHorarios());
		mav.getModelMap().addAttribute("peliculas", logicaNegocio.listarTodasPeliculas());
		return mav;
	}
	

	@RequestMapping(value = "/modificar", method = RequestMethod.GET)
	public ModelAndView modificar(@RequestParam("id") Long id, Principal principal) 
	{
		security.isAuthorized(principal, ROLE);

		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("registro", logicaNegocio.entityToForm(logicaNegocio.get(id)));
		return mav;
	}

	
	@RequestMapping(value = "/borrar", method = RequestMethod.GET)
	public ModelAndView borrar(@RequestParam("id") Long id, Principal principal) 
	{
		security.isAuthorized(principal, ROLE);

		ModelAndView mav =new ModelAndView("redirect:/funcion/lista");//indico que uso la vista "modificar"
		
		if (!logicaNegocio.desactivar(logicaNegocio.get(id))){//si no se guarda
			//mav.getModelMap().addAttribute("error", "Por favor revise el formulario");//agrego el mensaje de error
		}
		else
		{
			LOG.info("/funcion/borrar: desactivado registro con id " + id);
			//mav.getModelMap().addAttribute("ok", "La funcion se guardo correctamente");
		}
		return mav;
	}
	
	@RequestMapping(value = "/activar", method = RequestMethod.GET)
	public ModelAndView activar(@RequestParam("id") Long id, Principal principal) 
	{
		security.isAuthorized(principal, ROLE);

		ModelAndView mav =new ModelAndView("redirect:/funcion/lista");//indico que uso la vista "modificar"
		
		if (!logicaNegocio.activar(logicaNegocio.get(id))){//si no se guarda
			//mav.getModelMap().addAttribute("error", "Por favor revise el formulario");//agrego el mensaje de error
		}
		else
		{
			LOG.info("/funcion/activar: activado registro con id " + id);
		}
		return mav;
	}
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public ModelAndView guardar(@ModelAttribute FuncionForm formulario, Principal principal) 
	{
		security.isAuthorized(principal, ROLE);

		ModelAndView mav =new ModelAndView("redirect:/funcion/lista");
		//ModelAndView mav =new ModelAndView("redirect:/funcion/alta");
		Funcion item = logicaNegocio.formToEntity(formulario);
		
		if (!logicaNegocio.guardar(item)){//si no se guarda
			mav.setViewName("redirect:/funcion/alta");
			//mav.getModelMap().addAttribute("error", "Por favor revise el formulario");//agrego el mensaje de error
			LOG.error("/funcion/alta: Por favor revise el formulario " + item.getId());
		}
		else
		{
			LOG.info("/funcion/alta: agregado registro nuevo con id " + item.getId());
			//mav.getModelMap().addAttribute("ok", "La funcion se guardo correctamente");
		}
		return mav;
	}
	
	@RequestMapping(value = "/modificar", method = RequestMethod.POST)
	public ModelAndView modificar(@ModelAttribute FuncionForm formulario,Principal principal) 
	{
		security.isAuthorized(principal, ROLE);

		ModelAndView mav =new ModelAndView("redirect:/funcion/lista");
		Funcion item = logicaNegocio.formToEntity(formulario);
		item.setId(formulario.getId());
		
		if (!logicaNegocio.modificar(item)){//si no se guarda
			mav.setViewName("redirect:/funcion/modificar?id=" + formulario.getId());
			//mav.getModelMap().addAttribute("error", "Por favor revise el formulario");//agrego el mensaje de error
			LOG.error("/funcion/modificar: Por favor revise el formulario " + item.getId());
		}
		else
		{
			LOG.info("/funcion/modificar: actualizado registro con id " + item.getId());
			//mav.getModelMap().addAttribute("ok", "La funcion se guardo correctamente");
		}
		return mav;
	}
	
	@RequestMapping(value = "/gethorarios", method = RequestMethod.GET)
	
	public @ResponseBody HashMap<String, String> getHorariosDisponibles(@RequestParam Long pelicula , @RequestParam Long complejo, Principal principal)
	{
		LOG.info("/funcion/gethorarios: pedidos horarios para pelicula=" + pelicula + ", complejo=" + complejo);
		return logicaNegocio.getHorariosDisponiblesByComplejo(pelicula, complejo);
	}
	
	@RequestMapping(value = "/getfunciones", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, String> getFuncionesDisponibles(@RequestParam Long pelicula , @RequestParam Long complejo, Principal principal)
	{
		LOG.info("/funcion/getfunciones: pedidos funciones para pelicula=" + pelicula + ", complejo=" + complejo);
		return logicaNegocio.getFuncionesDisponibles(pelicula, complejo);
	}
}
