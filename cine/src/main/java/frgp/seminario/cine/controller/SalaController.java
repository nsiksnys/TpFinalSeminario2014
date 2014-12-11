package frgp.seminario.cine.controller;

import org.codehaus.jackson.map.ObjectMapper;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import frgp.seminario.cine.bo.impl.BoSala;
import frgp.seminario.cine.forms.SalaForm;
import frgp.seminario.cine.model.Complejo;
import frgp.seminario.cine.model.Sala;
import frgp.seminario.cine.support.web.*;
import frgp.seminario.cine.support.web.Message.Type;
import frgp.seminario.cine.utils.SecurityUtils;

@RequestMapping(value="/sala/**")
@Controller

public class SalaController {
	@Autowired
	@Qualifier("BoSala") //aclaro cual es el bean a inyectar
	BoSala logicaNegocio;
	
	@Autowired
	SecurityUtils security;
	
	private static String ROLE = "A";
	
	private static final Logger LOG = LoggerFactory.getLogger(SalaController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(Principal principal)
	{
		security.isAuthorized(principal, ROLE);

		return new ModelAndView("redirect:/sala/lista");
	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public ModelAndView lista(Principal principal)
	{
		security.isAuthorized(principal, ROLE);

		ArrayList<SalaForm> lista = (ArrayList<SalaForm>) logicaNegocio.listarTodasForm();
		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("lista", lista);
		LOG.info("/sala/lista: listando " + lista.size() + " registro(s)");
		return mav;
	}
	
	@RequestMapping(value = "/borrar", method = RequestMethod.GET)
	public ModelAndView borrar(@RequestParam("id") Long id, Principal principal)
	{
		security.isAuthorized(principal, ROLE);

		Long complejoId=logicaNegocio.get(id).getIdComplejo();//lo usamos para el redirect
		
		ModelAndView mav =new ModelAndView("redirect:/complejo/modificar?id=" + complejoId);//indico que uso la vista "modificar"
		
		if (!logicaNegocio.desactivar(logicaNegocio.get(id))){//si no se guarda
			mav.getModelMap().addAttribute("message", new Message("No se pudo borrar el registro", Type.ERROR));//agrego el mensaje de error
		}
		else
		{
			LOG.info("/sala/borrar: desactivado registro con id " + id);
			mav.getModelMap().addAttribute("message",  new Message("El complejo se borro correctamente", Type.SUCCESS));
		}
		return mav;
	}
	
	@RequestMapping(value = "/activar", method = RequestMethod.GET)
	public ModelAndView activar(@RequestParam("id") Long id, Principal principal) 
	{
		security.isAuthorized(principal, ROLE);

		Long complejoId=logicaNegocio.get(id).getIdComplejo();//lo usamos para el redirect
		ModelAndView mav =new ModelAndView("redirect:/complejo/modificar?id=" + complejoId);//indico que uso la vista "modificar"
		
		if (!logicaNegocio.activar(logicaNegocio.get(id))){//si no se guarda
			mav.getModelMap().addAttribute("message",  new Message("No se pudo activar el registro", Type.ERROR));//agrego el mensaje de error
		}
		else
		{
			LOG.info("/sala/activar: activado registro con id " + id);
			mav.getModelMap().addAttribute("message",  new Message("El complejo se activo correctamente", Type.SUCCESS));
		}
		return mav;
	}
	
	@RequestMapping(value = "/getsalas", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, String> getSalasDisponibles(@RequestParam Long complejo,Principal principal)
	{
		LOG.info("/salas/getsalas: pedidas salas del complejo=" + complejo.toString());
		return logicaNegocio.getSalasComplejoMap(complejo);
	}
}
