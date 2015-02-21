package frgp.seminario.cine.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import frgp.seminario.cine.account.Account;
import frgp.seminario.cine.account.UserService;
import frgp.seminario.cine.bo.impl.BoAccount;
import frgp.seminario.cine.support.web.MessageHelper;

@RequestMapping(value="/recuperar/**")
@Controller
public class RecuperarController {
	@Autowired
	BoAccount boAccount;
	
	@Autowired
	private UserService userService;
	
	private static final Logger LOG = LoggerFactory.getLogger(RecuperarController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(Principal principal)
	{
		return new ModelAndView("redirect:/recuperar/uno");
	}
	
	@RequestMapping(value = "/uno", method = RequestMethod.GET)
	public ModelAndView uno(Principal principal)
	{
		return new ModelAndView();
	}
	
	@RequestMapping(value = "/uno", method = RequestMethod.POST)
	public String uno(@RequestParam String email, @RequestParam Long dni, RedirectAttributes ra, HttpServletRequest request) 
	{
		Account cuenta = boAccount.getActiveOrInactive(email);
		if (cuenta == null || !cuenta.getDni().equals(dni)){ //si no existe una cuenta, o el dni la cuenta hallada no coincide con el enviado 
			LOG.error("/usuario/recuperar: la cuenta para el mail " + email + " no existe.");
			MessageHelper.addErrorAttribute(ra, "Por favor revise el formulario.");
			return "redirect:/recuperar/uno";
		}
		else
		{
			LOG.info("/usuario/recuperar: enviando pregunta de seguridad para la cuenta " + email);
			request.getSession().setAttribute("email", email);
			request.getSession().setAttribute("dni", dni);
			request.getSession().setAttribute("pregunta", cuenta.getPreguntaSeguridad());
		}
		return "redirect:/recuperar/dos";
	}
	
	@RequestMapping(value = "/dos", method = RequestMethod.GET)
	public ModelAndView dos(Principal principal, RedirectAttributes ra, HttpServletRequest request) 
	{
		Object email = request.getSession().getAttribute("email");
		Object dni = request.getSession().getAttribute("dni");
		
		//si el mail o el dni no estan cargados (porque se accedio directamente a esta url)
		if (email == null || dni == null || email.equals("") || dni.equals(0))
		{
			LOG.error("/usuario/recuperar: email o dni no validos.");
			MessageHelper.addErrorAttribute(ra, "Email o dni no validos.");
			return new ModelAndView("redirect:/recuperar/uno");
		}
		return new ModelAndView();
	}
	
	@RequestMapping(value = "/dos", method = RequestMethod.POST)
	public String dos(@RequestParam String email, @RequestParam Long dni, @RequestParam String respuesta, RedirectAttributes ra, HttpServletRequest request) 
	{
		//si el mail o el dni no estan cargados (porque se accedio directamente a esta url)
		if (email == null || dni == null || email.equals("") || dni == 0)
		{
			LOG.error("/usuario/recuperar: email o dni no validos.");
			MessageHelper.addErrorAttribute(ra, "Email o dni no validos.");
			return "redirect:/recuperar/uno";
		}
		
		if (!boAccount.recuperar(email, dni, respuesta)){ //si no existe una cuenta, o esta inactiva 
			LOG.error("/usuario/recuperar: la respuesta de seguridad no coincide con la almacenada en la base de datos.");
			MessageHelper.addErrorAttribute(ra, "Por favor revise el formulario.");
			return "redirect:/recuperar/dos";
		}
		
		//remuevo estos atributos de la sesion, porque ya no los necesito
		request.getSession().removeAttribute("email");
		request.getSession().removeAttribute("dni");
		request.getSession().removeAttribute("pregunta");
		
		LOG.info("/usuario/recuperar: recuperada la cuenta del usuario " + email);
		MessageHelper.addSuccessAttribute(ra, "La cuenta se recupero exitosamente. Por favor inicie sesion con su contraseña.");
		return "redirect:/signin";
	}
}
