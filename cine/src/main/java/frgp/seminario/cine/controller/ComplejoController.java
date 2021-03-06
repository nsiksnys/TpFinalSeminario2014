package frgp.seminario.cine.controller;

import java.security.Principal;

import org.codehaus.jackson.map.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import frgp.seminario.cine.bo.impl.BoFuncion;
import frgp.seminario.cine.bo.impl.BoSala;
import frgp.seminario.cine.findItem.impl.ReservaFindItem;
import frgp.seminario.cine.forms.ComplejoForm;
import frgp.seminario.cine.model.Complejo;
import frgp.seminario.cine.model.Funcion;
import frgp.seminario.cine.model.Sala;
import frgp.seminario.cine.support.web.*;
import frgp.seminario.cine.support.web.Message.Type;
import frgp.seminario.cine.utils.SecurityUtils;

@RequestMapping(value="/complejo/**")
@Controller

public class ComplejoController {
	@Autowired
	@Qualifier("BoComplejo") //aclaro cual es el bean a inyectar
	BoComplejo logicaNegocio;
		
	@Qualifier("BoFuncion") //aclaro cual es el bean a inyectar
	BoFuncion logicaFuncion;
	
	@Autowired
	SecurityUtils security;
	
	private static String ROLE = "A";
	
	private static final Logger LOG = LoggerFactory.getLogger(ComplejoController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(Principal principal)
	{
		//security.isAuthorized(principal, ROLE);

		return new ModelAndView("redirect:/complejo/lista");
	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public ModelAndView lista(Principal principal)
	{
		//security.isAuthorized(principal, ROLE);

		ArrayList<ComplejoForm> lista = (ArrayList<ComplejoForm>) logicaNegocio.listarTodosForm();
		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("lista", lista);
		LOG.info("/complejo/lista: listando " + lista.size() + " registro(s)");
		return mav;
	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.POST)
	public ModelAndView lista(@RequestParam("message") String message,Principal principal)
	{
		security.isAuthorized(principal, ROLE);

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
		security.isAuthorized(principal, ROLE);

		return new ModelAndView();
	}
	
	@RequestMapping(value = "/modificar", method = RequestMethod.GET)
	public ModelAndView modificar(@RequestParam("id") Long id, Principal principal) 
	{
		security.isAuthorized(principal, ROLE);

		//ModelAndView mav =new ModelAndView("modificar");//indico que uso la vista "modificar"
		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("registro", logicaNegocio.get(id));//FIXME: el registro no tiene salas
		return mav;
	}
	
	@RequestMapping(value = "/borrar", method = RequestMethod.GET)
	public ModelAndView borrar(@RequestParam("id") Long id, Principal principal) 
	{
		security.isAuthorized(principal, ROLE);

		ModelAndView mav =new ModelAndView("redirect:/complejo/lista");//indico que uso la vista "modificar"
		
		
		if(!logicaNegocio.isReservaActivaComplejo(logicaNegocio.get(id))){
			
			if (!logicaNegocio.desactivar(logicaNegocio.get(id))){//si no se guarda
				mav.getModelMap().addAttribute("message", new Message("No se pudo borrar el registro", Type.ERROR));//agrego el mensaje de error
			}
			else
			{
				LOG.info("/complejo/borrar: desactivado registro con id " + id);
				mav.getModelMap().addAttribute("message",  new Message("El complejo se borro correctamente", Type.SUCCESS));
			
				logicaNegocio.desactivarSalasComplejo(logicaNegocio.get(id)); // SI LOGRA DESACTIVAR EL REGISTRO DESACTIVO LAS SALAS
				logicaNegocio.desactivarFuncionComplejo(logicaNegocio.get(id));
			}
			
			
		}else{
			mav.getModelMap().addAttribute("message", new Message("No se pudo borrar el registro, hay un reserva activa", Type.ERROR));//agrego el mensaje de error
			
		}
			
			
			
			
		

		
		
		return mav;
	}
	
	@RequestMapping(value = "/activar", method = RequestMethod.GET)
	public ModelAndView activar(@RequestParam("id") Long id, Principal principal) 
	{
		security.isAuthorized(principal, ROLE);

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
		security.isAuthorized(principal, ROLE);

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
		security.isAuthorized(principal, ROLE);

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
	public @ResponseBody HashMap<String, String> getDisponibles(Principal principal)
	{
		LOG.info("/complejo/getcomplejos: pedidos complejos activos");
		return logicaNegocio.getAllActiveMap();
	}
}