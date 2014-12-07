package frgp.seminario.cine.controller;

import java.security.Principal;
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
import org.springframework.web.bind.annotation.ResponseBody;//
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;//

import frgp.seminario.cine.model.Promocion;
import frgp.seminario.cine.support.web.MessageHelper;//
import frgp.seminario.cine.bo.impl.BoPromocion;
import frgp.seminario.cine.forms.PromocionForm;

@RequestMapping(value="/promocion/**")
@Controller
public class PromocionController {
	@Autowired
	@Qualifier("BoPromocion") //aclaro cual es el bean a inyectar
	BoPromocion logicaNegocio; //aclaro las clases que se utilizan en este caso en particular
	
	private static final Logger LOG = LoggerFactory.getLogger(PromocionController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(Principal principal)
	{
		return new ModelAndView("redirect:/promocion/lista");
	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public ModelAndView lista(Principal principal)
	{
		ArrayList<Promocion> lista = (ArrayList<Promocion>) logicaNegocio.listarTodos();
		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("lista", lista);	
		LOG.info("/promocion/lista: listando " + lista.size() + " registro(s)");
		return mav;
	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.POST)
	public ModelAndView lista(@RequestParam("ok") String ok,Principal principal)
	{
		ArrayList<Promocion> lista = (ArrayList<Promocion>) logicaNegocio.listarTodos();
		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("lista", lista);
		
		if (!ok.equals(""))
			mav.getModelMap().addAttribute("ok", ok);
		
		LOG.info("/promocion/lista: listando " + lista.size() + " registro(s)");
		return mav;
	}
		
	@RequestMapping(value = "/alta", method = RequestMethod.GET)
	public ModelAndView alta(Principal principal)
	{	

		ModelAndView mav = new ModelAndView();
		mav.getModelMap().addAttribute("promocionForm", new PromocionForm());
		return mav;
	}
	
	@RequestMapping(value = "/modificar", method = RequestMethod.GET)
	public ModelAndView modificar(@RequestParam("id") Long id, Principal principal) 
	{

		Promocion registro = logicaNegocio.get(id);
		ModelAndView mav = new ModelAndView();
		mav.getModelMap().addAttribute("PromocionForm", logicaNegocio.entityToForm(registro));
		mav.getModelMap().addAttribute("registro", registro);
		return mav;		
	}
	
	@RequestMapping(value = "/borrar", method = RequestMethod.GET)
	public ModelAndView borrar(@RequestParam("id") Long id, Principal principal) 
	{
		ModelAndView mav =new ModelAndView("redirect:/promocion/lista");//indico que uso la vista "modificar"
		
		if (!logicaNegocio.desactivar(logicaNegocio.get(id))){//si no se guarda
			//mav.getModelMap().addAttribute("error", "Por favor revise el formulario");//agrego el mensaje de error
		}
		else
		{
			LOG.info("/promocion/borrar: desactivado registro con id " + id);
			//mav.getModelMap().addAttribute("ok", "La promocion se guardo correctamente");
		}
		return mav;
	}
	
	@RequestMapping(value = "/activar", method = RequestMethod.GET)
	public ModelAndView activar(@RequestParam("id") Long id, Principal principal) 
	{
		ModelAndView mav =new ModelAndView("redirect:/promocion/lista");//indico que uso la vista "modificar"
		
		if (!logicaNegocio.activar(logicaNegocio.get(id))){//si no se guarda
		}
		else
		{
			LOG.info("/promocion/activar: activado registro con id " + id);
		}
		return mav;
	}
	
	@RequestMapping(value = "/alta", method = RequestMethod.POST) 
	public String alta(@ModelAttribute PromocionForm formulario, RedirectAttributes ra)
	{
		Promocion item = logicaNegocio.formToEntity(formulario);
		
		
		if(!logicaNegocio.guardar(item)) {
			LOG.error("/promocion/alta. por favor revise el formulario. ");
			MessageHelper.addErrorAttribute(ra, "Por favor, revise el formulario.");
			return "redirect:/promocion/alta";	
		}
		else
		{
			LOG.info("promocion/alta: agregado registro nuevo con id " + item.getId());
			MessageHelper.addSuccessAttribute(ra, "La promocion se guardo correctamente.");
		}
		return "redirect:/promocion/lista";
	}

	
	@RequestMapping(value = "/modificar", method = RequestMethod.POST)
	public String modificar(@ModelAttribute PromocionForm formulario, RedirectAttributes ra) 
	{
		Promocion registro = logicaNegocio.formToEntity(formulario);
		registro.setId(Long.parseLong(formulario.getId()));
		

		if (!logicaNegocio.modificar(registro)){//si no se guarda
			LOG.error("/promocion/modificar: por favor revise el formulario.");
			MessageHelper.addErrorAttribute(ra, "Por favor revise el formulario");//agrego el mensaje de error
			return "redirect:/promocion/modificar?id=" + formulario.getId();
		}
		else
		{
			LOG.info("/promocion/modificar: actualizado registro con id " + registro.getId());
			MessageHelper.addSuccessAttribute(ra, "La promocion se actualizo correctamente");
		}
		return "redirect:/promocion/lista";
	}
	
	@RequestMapping(value = "/getpromocion", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, String> getPromocionesDisponibles(Principal principal)
	{
		LOG.info("/promocion/getpromocion: pedidas promociones activas");
		return logicaNegocio.getAllActiveMap();
	}
}

