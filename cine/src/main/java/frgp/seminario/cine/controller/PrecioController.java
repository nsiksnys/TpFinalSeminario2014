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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import frgp.seminario.cine.model.Precio;
import frgp.seminario.cine.support.web.MessageHelper;
import frgp.seminario.cine.utils.SecurityUtils;
import frgp.seminario.cine.bo.impl.BoPrecio;
import frgp.seminario.cine.forms.PrecioForm;

@RequestMapping(value="/precio/**")
@Controller
public class PrecioController {
	@Autowired
	@Qualifier("BoPrecio") //aclaro cual es el bean a inyectar
	BoPrecio logicaNegocio; //aclaro las clases que se utilizan en este caso en particular
	
	@Autowired
	SecurityUtils security;
	
	private static String ROLE = "A";
	
	private static final Logger LOG = LoggerFactory.getLogger(PrecioController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(Principal principal)
	{
		security.isAuthorized(principal, ROLE);

		return new ModelAndView("redirect:/precio/lista");
	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public ModelAndView lista(Principal principal)
	{
		security.isAuthorized(principal, ROLE);

		ArrayList<Precio> lista = (ArrayList<Precio>) logicaNegocio.listarTodos();
		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("lista", lista);
		LOG.info("/precio/lista: listando " + lista.size() + " registro(s)");
		return mav;
	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.POST)
	public ModelAndView lista(@RequestParam("ok") String ok,Principal principal)
	{
		security.isAuthorized(principal, ROLE);

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
		security.isAuthorized(principal, ROLE);

		ModelAndView mav = new ModelAndView();
		mav.getModelMap().addAttribute("precioForm", new PrecioForm());
		return mav;
	}
	
	@RequestMapping(value = "/modificar", method = RequestMethod.GET)
	public ModelAndView modificar(@RequestParam("id") Long id, Principal principal) 
	{
		security.isAuthorized(principal, ROLE);

		Precio registro = logicaNegocio.get(id);
		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("PrecioForm", logicaNegocio.entityToForm(registro));
		mav.getModelMap().addAttribute("registro", registro);
		return mav;
	}
	
	@RequestMapping(value = "/borrar", method = RequestMethod.GET)
	public ModelAndView borrar(@RequestParam("id") Long id, Principal principal) 
	{
		security.isAuthorized(principal, ROLE);

		ModelAndView mav =new ModelAndView("redirect:/precio/lista");//indico que uso la vista "modificar"
		
		if (!logicaNegocio.desactivar(logicaNegocio.get(id))){//si no se guarda
			//mav.getModelMap().addAttribute("error", "Por favor revise el formulario");//agrego el mensaje de error
		}
		else
		{
			LOG.info("/precio/borrar: desactivado registro con id " + id);
		}
		return mav;
	}
	
	@RequestMapping(value = "/activar", method = RequestMethod.GET)
	public ModelAndView activar(@RequestParam("id") Long id, Principal principal) 
	{
		security.isAuthorized(principal, ROLE);

		ModelAndView mav =new ModelAndView("redirect:/precio/lista");//indico que uso la vista "modificar"
		
		if (!logicaNegocio.activar(logicaNegocio.get(id))){//si no se guarda
		}
		else
		{
			LOG.info("/precio/activar: activado registro con id " + id);
		}
		return mav;
	}
	
	@RequestMapping(value = "/alta", method = RequestMethod.POST)
	public String alta(@ModelAttribute PrecioForm formulario, Principal principal, RedirectAttributes ra) 
	{
		security.isAuthorized(principal, ROLE);

		Precio item = logicaNegocio.formToEntity(formulario);
		
		if (!logicaNegocio.guardar(item)){//si no se guarda
			LOG.error("/precio/alta. por favor revise el formulario.");
			MessageHelper.addErrorAttribute(ra, "Por favor, revise el formulario.");
			return "redirect:/precio/alta";
		}
		else
		{
			LOG.info("/precio/alta: agregado registro nuevo con id " + item.getId());
			MessageHelper.addSuccessAttribute(ra, "El precio se guardo correctamente.");
		}
		return "redirect:/precio/lista";
	}
	
	@RequestMapping(value = "/modificar", method = RequestMethod.POST)
	public String modificar(@ModelAttribute PrecioForm formulario, Principal principal, RedirectAttributes ra) 
	{
		security.isAuthorized(principal, ROLE);

		Precio registro = logicaNegocio.formToEntity(formulario);
		registro.setId(Long.parseLong(formulario.getId()));
		
		if (!logicaNegocio.modificar(registro)){//si no se guarda
			LOG.error("/precio/modificar: por favor revise el formulario.");
			MessageHelper.addErrorAttribute(ra, "Por favor revise el formulario.");
			return "redirect:/precio/modificar?id=" + formulario.getId();
		}
		else
		{
			LOG.info("/precio/modificar: actualizado registro con id " + registro.getId());
			MessageHelper.addSuccessAttribute(ra, "El precio se actualizo correctament.");
		}
		return "redirect:/precio/lista";
	}
	
}