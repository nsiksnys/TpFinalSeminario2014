package frgp.seminario.cine.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import frgp.seminario.cine.account.Account;
import frgp.seminario.cine.bo.impl.BoAccount;
import frgp.seminario.cine.signup.SignupForm;
import frgp.seminario.cine.support.web.MessageHelper;

public class UsuarioController {
	@Autowired
	BoAccount boAccount;
	
	private static final Logger LOG = LoggerFactory.getLogger(UsuarioController.class);
	
	@RequestMapping(value = "usuario/actual", method = RequestMethod.GET)
	public ModelAndView current (Principal principal, RedirectAttributes ra) {
		ModelAndView mav =new ModelAndView();
		Account account = boAccount.get(principal.getName());
		if (account == null)
		{
			LOG.error("/usuario/actual: No se encontro el usuario principal.getName()= " + principal.getName());
			MessageHelper.addErrorAttribute(ra, "No se encontro el usuario.");
			return null;
		}
		LOG.info("usuario/actual: mostrando detalles del usuario" + principal.getName());
		mav.getModelMap().addAttribute("account", account);
		return mav;
	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public ModelAndView lista(Principal principal) {
		ArrayList<Account> lista = (ArrayList<Account>) boAccount.listarTodos();
		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("lista", lista);
		LOG.info("/usuario/lista: listando " + lista.size() + " registro(s)");
		return mav;
	}
		
	@RequestMapping(value = "/usuario/alta", method = RequestMethod.GET)
	public ModelAndView alta(Principal principal) {
		ModelAndView mav =new ModelAndView();
		return mav;
	}
	
	@RequestMapping(value = "/modificar", method = RequestMethod.GET)
	public ModelAndView modificar(@RequestParam("id") String email, Principal principal) 
	{
		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("registro", boAccount.get(email));
		return mav;
	}
	
	@RequestMapping(value = "/borrar", method = RequestMethod.GET)
	public ModelAndView borrar(@RequestParam("id") String email, Principal principal) 
	{
		ModelAndView mav =new ModelAndView("redirect:/usuario/lista");//indico que uso la vista "modificar"
		
		if (!boAccount.desactivar(boAccount.get(email))){//si no se guarda
			//mav.getModelMap().addAttribute("error", "Por favor revise el formulario");//agrego el mensaje de error
		}
		else
		{
			LOG.info("/usuario/borrar: modificado registro " + email);
		}
		return mav;
	}
	
	@RequestMapping(value = "/activar", method = RequestMethod.GET)
	public ModelAndView activar(@RequestParam("id") String email, Principal principal) 
	{
		ModelAndView mav =new ModelAndView("redirect:/usuario/lista");//indico que uso la vista "modificar"
		
		if (!boAccount.activar(boAccount.get(email))){//si no se guarda
			//mav.getModelMap().addAttribute("error", "Por favor revise el formulario");//agrego el mensaje de error
		}
		else
		{
			LOG.info("/usuario/activar: activado registro " + email);
		}
		return mav;
	}
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public ModelAndView guardar(@ModelAttribute SignupForm formulario, Principal principal, RedirectAttributes ra) 
	{
		ModelAndView mav =new ModelAndView("redirect:/usuario/lista");
		//ModelAndView mav =new ModelAndView("redirect:/usuario/alta");
		Account item = boAccount.formToEntity(formulario);
		
		if (!boAccount.guardar(item)){//si no se guarda
			//mav.setViewName("redirect:/usuario/alta");
			LOG.error("/usuario/alta: por favor revise el formulario.");
			MessageHelper.addErrorAttribute(ra, "Por favor revise el formulario.");
			return null;
		}
		else
		{
			LOG.info("/usuario/alta: agregado registro para " + item.getEmail());
			MessageHelper.addSuccessAttribute(ra, "El registro se guardo correctamente");
		}
		return mav;
	}
	
	@RequestMapping(value = "/modificar", method = RequestMethod.POST)
	public ModelAndView modificar(@ModelAttribute SignupForm formulario, Principal principal, RedirectAttributes ra) 
	{
		ModelAndView mav =new ModelAndView("redirect:/usuario/lista");
		//ModelAndView mav =new ModelAndView();
		Account registro = boAccount.formToEntity(formulario);
		
		if (!boAccount.modificar(registro)){//si no se guarda
			LOG.error("/usuario/alta: por favor revise el formulario.");
			MessageHelper.addErrorAttribute(ra, "Por favor revise el formulario.");
			return null;
		}
		else
		{
			LOG.info("/usuario/modificar: actualizado registro para " + registro.getEmail());
			MessageHelper.addSuccessAttribute(ra, "El usuario se guardo correctamente");
		}
		return mav;
	}

}
