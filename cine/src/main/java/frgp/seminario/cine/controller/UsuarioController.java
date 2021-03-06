package frgp.seminario.cine.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import frgp.seminario.cine.account.Account;
import frgp.seminario.cine.bo.impl.BoAccount;
import frgp.seminario.cine.model.Cliente;
import frgp.seminario.cine.signup.SignupForm;
import frgp.seminario.cine.support.web.MessageHelper;
import frgp.seminario.cine.utils.SecurityUtils;

@RequestMapping(value="/usuario/**")
@Controller
public class UsuarioController {
	@Autowired
	BoAccount boAccount;
	
	@Autowired
	SecurityUtils security;
	
	private static String ROLE = "A";
	
	private static final Logger LOG = LoggerFactory.getLogger(UsuarioController.class);
	
	@RequestMapping(value = "/actual", method = RequestMethod.GET)
	public ModelAndView current (Principal principal, RedirectAttributes ra) {
		ModelAndView mav =new ModelAndView();
		LOG.info("/usuario/actual: se quieren mostrar detalles del usuario " + principal.getName());
		
		//si no encontro al usuario
		if (!boAccount.getActiveBoolean(principal.getName()))
		{
			LOG.error("/usuario/actual: No se encontro el usuario principal.getName()= " + principal.getName());
			MessageHelper.addErrorAttribute(ra, "No se encontro el usuario.");
			mav.setViewName("redirect: /");
			return mav;
		}
		
		SignupForm form = boAccount.entityToForm(boAccount.get(principal.getName()));
		Cliente cliente = null;
		if (form == null)
		{
			LOG.error("/usuario/actual: No se encontro el usuario principal.getName()= " + principal.getName());
			MessageHelper.addErrorAttribute(ra, "No se encontro el usuario.");
			mav.setViewName("redirect: /");
			return mav;
		}
		mav.getModelMap().addAttribute("command", new SignupForm());
		mav.getModelMap().addAttribute("registro", form);
		mav.getModelMap().addAttribute("sexos", boAccount.getSexos());

		if (form.getRole().equals("C"))//si el usuario es un cliente
		{
			cliente = boAccount.getCliente(principal.getName());
			mav.getModelMap().addAttribute("direccion", cliente.getDireccion());
			mav.getModelMap().addAttribute("genero", cliente.getGeneroPreferido());
		}
		LOG.info("/usuario/actual: mostrando detalles del usuario" + principal.getName());
		return mav;
	}
	
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public ModelAndView lista(Principal principal) {
		security.isAuthorized(principal, ROLE);

		ArrayList<Account> lista = (ArrayList<Account>) boAccount.listarTodos();
		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("lista", lista);
		LOG.info("/usuario/lista: listando " + lista.size() + " registro(s)");
		return mav;
	}
		
	
	@RequestMapping(value = "/usuario/alta", method = RequestMethod.GET)
	public ModelAndView alta(Principal principal) {
		security.isAuthorized(principal, ROLE);

		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("signupForm", new SignupForm());
		mav.getModelMap().addAttribute("roles", boAccount.getRoles());
		mav.getModelMap().addAttribute("sexos", boAccount.getSexos());
		return mav;
	}
	
	
	@RequestMapping(value = "/modificar", method = RequestMethod.GET)
	public ModelAndView modificar(@RequestParam("id") String email, Principal principal) 
	{
		security.isAuthorized(principal, ROLE);

		ModelAndView mav =new ModelAndView();
		SignupForm registro = boAccount.entityToForm(boAccount.get(email));
		mav.getModelMap().addAttribute("registro", registro);
		mav.getModelMap().addAttribute("roles", boAccount.getRoles());
		
		if (registro.getRole().equals("C"))//si el usuario es un cliente
		{
			Cliente cliente = boAccount.getCliente(email);
			mav.getModelMap().addAttribute("direccion", cliente.getDireccion());
			mav.getModelMap().addAttribute("genero", cliente.getGeneroPreferido());
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/borrar", method = RequestMethod.GET)
	public ModelAndView borrar(@RequestParam("id") String email, Principal principal) 
	{
		security.isAuthorized(principal, ROLE);

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
		security.isAuthorized(principal, ROLE);

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
	
	
	@RequestMapping(value = "/alta", method = RequestMethod.POST)
	public String alta(@ModelAttribute SignupForm formulario, Principal principal, RedirectAttributes ra) 
	{
		security.isAuthorized(principal, ROLE);

		Account usuario;
		Cliente cliente;
		boolean status;
		
		if (formulario.getRole().equals("C"))
		{
			cliente = boAccount.formToClienteEntity(formulario);
			status = boAccount.guardar(cliente);
		}
		else
		{
			usuario = boAccount.formToEntity(formulario);
			status = boAccount.guardar(usuario);
		}
		
		if (!status){//si no se guarda
			LOG.error("/usuario/alta: por favor revise el formulario.");
			MessageHelper.addErrorAttribute(ra, "Por favor revise el formulario.");
			return "redirect:/usuario/alta";
		}
		else
		{
			LOG.info("/usuario/alta: agregado registro para " + formulario.getEmail());
			MessageHelper.addSuccessAttribute(ra, "El registro se guardo correctamente");
		}
		return "redirect:/usuario/lista";
	}
	
	
	@RequestMapping(value = "/modificar", method = RequestMethod.POST)
	public String modificar(@ModelAttribute SignupForm formulario, Principal principal, RedirectAttributes ra) 
	{
		security.isAuthorized(principal, ROLE);

		if (formulario.getRole().equals("C"))
		{
			LOG.error("/usuario/alta: No esta permitido cambiar el rol a Cliente.");
			MessageHelper.addErrorAttribute(ra, "No esta permitido cambiar el rol a Cliente.");
			return "redirect:/usuario/modificar?id=" + formulario.getEmail();
		}
		
		if (!boAccount.modificar(boAccount.formToEntity(formulario))){//si no se guarda
			LOG.error("/usuario/alta: por favor revise el formulario.");
			MessageHelper.addErrorAttribute(ra, "Por favor revise el formulario.");
			return "redirect:/usuario/modificar?id=" + formulario.getEmail();
		}
		else
		{
			LOG.info("/usuario/modificar: actualizado registro para " + formulario.getEmail());
			MessageHelper.addSuccessAttribute(ra, "El usuario se guardo correctamente");
		}
		return "redirect:/usuario/lista";
	}

	@RequestMapping(value = "/actual", method = RequestMethod.POST)
	public String current(@ModelAttribute SignupForm formulario, Principal principal, RedirectAttributes ra) 
	{
		formulario.setEmail(principal.getName());
				
		if (!boAccount.modificar(formulario)){//si no se guarda
			LOG.error("/usuario/alta: por favor revise el formulario.");
			MessageHelper.addErrorAttribute(ra, "Por favor revise el formulario.");
		}
		else
		{
			LOG.info("/usuario/modificar: actualizado registro para " + formulario.getEmail());
			MessageHelper.addSuccessAttribute(ra, "El registro se actualizo correctamente");
		}
		return "redirect:/usuario/actual";
	}
}
