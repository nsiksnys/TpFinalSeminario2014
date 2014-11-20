package frgp.seminario.cine.controller;

import java.security.Principal;

import org.codehaus.jackson.map.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;

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

import frgp.seminario.cine.bo.impl.BoComplejo;
import frgp.seminario.cine.forms.ComplejoForm;
import frgp.seminario.cine.model.Complejo;
import frgp.seminario.cine.support.web.*;
import frgp.seminario.cine.support.web.Message.Type;

@RequestMapping(value="/complejo/**")
@Controller

public class ComplejoController {
	@Autowired
	@Qualifier("BoComplejo") //aclaro cual es el bean a inyectar
	BoComplejo logicaNegocio;
	
	private static final Logger LOG = LoggerFactory.getLogger(ComplejoController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(Principal principal)
	{
		return new ModelAndView("redirect:/complejo/lista");
	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public ModelAndView lista(Principal principal)
	{
		ArrayList<ComplejoForm> lista = (ArrayList<ComplejoForm>) logicaNegocio.listarTodosForm();
		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("lista", lista);
		LOG.info("/complejo/lista: listando " + lista.size() + " registro(s)");
		return mav;
	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.POST)
	public ModelAndView lista(@RequestParam("message") String message,Principal principal)
	{
		ArrayList<ComplejoForm> lista = (ArrayList<ComplejoForm>) logicaNegocio.listarTodosForm();
		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("lista", lista);
		
		if (!message.equals(""))
			mav.getModelMap().addAttribute("message", message);
		
		LOG.info("/complejo/lista: listando " + lista.size() + " registro(s)");
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
		mav.getModelMap().addAttribute("registro", logicaNegocio.get(id));//FIXME: el registro no tiene salas
		return mav;
	}
	
	@RequestMapping(value = "/borrar", method = RequestMethod.GET)
	public ModelAndView borrar(@RequestParam("id") Long id, Principal principal) 
	{
		ModelAndView mav =new ModelAndView("redirect:/complejo/lista");//indico que uso la vista "modificar"
		
		if (!logicaNegocio.desactivar(logicaNegocio.get(id))){//si no se guarda
			mav.getModelMap().addAttribute("message", new Message("No se pudo borrar el registro", Type.ERROR));//agrego el mensaje de error
		}
		else
		{
			LOG.info("/complejo/borrar: desactivado registro con id " + id);
			mav.getModelMap().addAttribute("message",  new Message("El complejo se borro correctamente", Type.SUCCESS));
		}
		return mav;
	}
	
	@RequestMapping(value = "/activar", method = RequestMethod.GET)
	public ModelAndView activar(@RequestParam("id") Long id, Principal principal) 
	{
		ModelAndView mav =new ModelAndView("redirect:/complejo/lista");//indico que uso la vista "modificar"
		
		if (!logicaNegocio.activar(logicaNegocio.get(id))){//si no se guarda
			mav.getModelMap().addAttribute("message",  new Message("No se pudo activar el registro", Type.ERROR));//agrego el mensaje de error
		}
		else
		{
			LOG.info("/complejo/activar: activado registro con id " + id);
			mav.getModelMap().addAttribute("message",  new Message("El complejo se activo correctamente", Type.SUCCESS));
		}
		return mav;
	}
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public ModelAndView guardar(@ModelAttribute ComplejoForm formulario, Principal principal) 
	{
		ModelAndView mav =new ModelAndView("redirect:/complejo/lista");
		//ModelAndView mav =new ModelAndView("redirect:/complejo/alta");
		Complejo item = logicaNegocio.formToEntity(formulario);
		
		if (!logicaNegocio.guardar(item)){//si no se guarda
			mav.setViewName("redirect:/complejo/alta");
			mav.getModelMap().addAttribute("message",  new Message("Por favor revise el formulario", Type.ERROR));//agrego el mensaje de error
		}
		else
		{
			LOG.info("/complejo/alta: agregado registro nuevo con id " + item.getId());
			mav.getModelMap().addAttribute("message",  new Message("El complejo se guardo correctamente", Type.SUCCESS));
		}
		return mav;
	}
	
	@RequestMapping(value = "/modificar", method = RequestMethod.POST)
	public ModelAndView modificar(@ModelAttribute ComplejoForm formulario, Principal principal) 
	{
		ModelAndView mav =new ModelAndView("redirect:/complejo/lista");
		//ModelAndView mav =new ModelAndView();
		Complejo registro = logicaNegocio.formToEntityUpdate(formulario);
		
		if (!logicaNegocio.modificar(registro)){//si no se guarda
			mav.setViewName("redirect:/complejo/modificar?id="+formulario.getId());
			LOG.info("/complejo/modificar: Por favor revise el formulario");
			mav.getModelMap().addAttribute("message", new Message("Por favor revise el formulario", Type.ERROR));//agrego el mensaje de error
		}
		else
		{
			LOG.info("/complejo/modificar: actualizado registro con id " + registro.getId());
			mav.getModelMap().addAttribute("message",  new Message("El complejo se guardo correctamente", Type.SUCCESS));
		}
		return mav;
	}
	
	@RequestMapping(value = "/getcomplejos", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, String> getPeliculasDisponibles(Principal principal)
	{
		LOG.info("/complejo/getcomplejos: pedidos complejos activos");
		return logicaNegocio.getAllActiveMap();
	}
}
