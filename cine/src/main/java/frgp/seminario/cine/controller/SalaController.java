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

import frgp.seminario.cine.bo.BusinessObject;
import frgp.seminario.cine.bo.impl.BoSala;
import frgp.seminario.cine.forms.SalaForm;
import frgp.seminario.cine.model.Sala;
import frgp.seminario.cine.support.web.*;
import frgp.seminario.cine.support.web.Message.Type;

@RequestMapping(value="/sala/**")
@Controller

public class SalaController {
	@Autowired
	@Qualifier("BoSala") //aclaro cual es el bean a inyectar
	BoSala logicaNegocio;
	
	private static final Logger LOG = LoggerFactory.getLogger(SalaController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(Principal principal)
	{
		return new ModelAndView("redirect:/sala/lista");
	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public ModelAndView lista(Principal principal)
	{
		ArrayList<SalaForm> lista = (ArrayList<SalaForm>) logicaNegocio.listarTodos();
		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("lista", lista);
		LOG.info("/sala/lista: listando " + lista.size() + " registro(s)");
		return mav;
	}
	
	@RequestMapping(value = "/borrar", method = RequestMethod.GET)
	public ModelAndView borrar(@RequestParam("id") Long id, Principal principal)
	{
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
}
