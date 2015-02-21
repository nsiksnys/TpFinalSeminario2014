package frgp.seminario.cine.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import frgp.seminario.cine.bo.impl.BoCartelera;
import frgp.seminario.cine.forms.CarteleraForm;
import frgp.seminario.cine.model.Cartelera;
import frgp.seminario.cine.support.web.MessageHelper;
import frgp.seminario.cine.utils.SecurityUtils;

@RequestMapping(value="/cartelera/**")
@Controller
public class CarteleraController {
	@Autowired
	//@Qualifier("BoCartelera") //aclaro cual es el bean a inyectar
	BoCartelera logicaNegocio; //aclaro las clases que se utilizan en este caso en particular
	
	@Autowired
	SecurityUtils security;
	
	private static String ROLE = "A";
	
	private static final Logger LOG = LoggerFactory.getLogger(CarteleraController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(Principal principal) {
		//security.isAuthorized(principal, ROLE);

		return new ModelAndView("redirect:/cartelera/lista");
	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public ModelAndView lista(Principal principal) {
		//security.isAuthorized(principal, ROLE);

		ArrayList<Cartelera> lista = (ArrayList<Cartelera>) logicaNegocio.listarTodos();
		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("lista", lista);
		LOG.info("/cartelera/lista: listando " + lista.size() + " registro(s)");
		return mav;
	}
		
	@RequestMapping(value = "/alta", method = RequestMethod.GET)
	public ModelAndView alta(Principal principal) {
		security.isAuthorized(principal, ROLE);

		ModelAndView mav =new ModelAndView();
		//mav.getModelMap().addAttribute("carteleraForm", new CarteleraForm());
		mav.getModelMap().addAttribute("peliculas", logicaNegocio.getAllPeliculasActivas());
		return mav;
	}
	
	@RequestMapping(value = "/modificar", method = RequestMethod.GET)
	public ModelAndView modificar(@RequestParam("id") Long id, Principal principal) 
	{
		security.isAuthorized(principal, ROLE);

		Cartelera registro = logicaNegocio.get(id); 
		ModelAndView mav =new ModelAndView();
		//mav.getModelMap().addAttribute("carteleraForm", new CarteleraForm());
		mav.getModelMap().addAttribute("registro", logicaNegocio.entityToForm(registro));//FIXME: arreglar error en las lineas 30 y 37
		mav.getModelMap().addAttribute("titulo", registro.getPelicula().getNombre());
		return mav;
	}
	
	@RequestMapping(value = "/borrar", method = RequestMethod.GET)
	public ModelAndView borrar(@RequestParam("id") Long id, Principal principal) 
	{
		security.isAuthorized(principal, ROLE);

		ModelAndView mav =new ModelAndView("redirect:/cartelera/lista");//indico que uso la vista "modificar"
		
		if (!logicaNegocio.desactivar(logicaNegocio.get(id))){//si no se guarda
			//mav.getModelMap().addAttribute("error", "Por favor revise el formulario");//agrego el mensaje de error
		}
		else
		{
			LOG.info("/cartelera/borrar: modificado registro con id " + id);
		}
		return mav;
	}
	
	@RequestMapping(value = "/activar", method = RequestMethod.GET)
	public ModelAndView activar(@RequestParam("id") Long id, Principal principal) 
	{
		security.isAuthorized(principal, ROLE);

		ModelAndView mav =new ModelAndView("redirect:/cartelera/lista");//indico que uso la vista "modificar"
		
		if (!logicaNegocio.activar(logicaNegocio.get(id))){//si no se guarda
			//mav.getModelMap().addAttribute("error", "Por favor revise el formulario");//agrego el mensaje de error
		}
		else
		{
			LOG.info("/cartelera/activar: activado registro con id " + id);
		}
		return mav;
	}
	
	@RequestMapping(value = "/alta", method = RequestMethod.POST)
	public String alta(@ModelAttribute CarteleraForm formulario, Principal principal, RedirectAttributes ra) 
	{
		security.isAuthorized(principal, ROLE);

		Cartelera item = logicaNegocio.formToEntity(formulario);
		
		if (!logicaNegocio.guardar(item)){//si no se guarda
			LOG.error("/cartelera/alta: por favor revise el formulario.");
			MessageHelper.addErrorAttribute(ra, "Por favor, vuelva a llenar el formulario.");
			return "redirect:/cartelera/alta";
		}
		else
		{
			LOG.info("/cartelera/alta: agregado registro nuevo con id " + item.getId());
			MessageHelper.addSuccessAttribute(ra, "El registro se guardo correctamente");
		}
		return "redirect:/cartelera/lista";
	}
	
	@RequestMapping(value = "/modificar", method = RequestMethod.POST)
	public String modificar(@ModelAttribute CarteleraForm formulario, Principal principal, RedirectAttributes ra) 
	{
		security.isAuthorized(principal, ROLE);

		Cartelera registro = logicaNegocio.formToEntity(formulario);
		registro.setId(Long.parseLong(formulario.getId()));
		
		if (!logicaNegocio.modificar(registro)){//si no se guarda
			LOG.error("/cartelera/modificar: por favor revise el formulario.");
			MessageHelper.addErrorAttribute(ra, "Por favor revise el formulario");//agrego el mensaje de error
			return "redirect:/cartelera/modificar?id=" + formulario.getId();
		}
		else
		{
			LOG.info("/cartelera/modificar: actualizado registro con id " + registro.getId());
			MessageHelper.addSuccessAttribute(ra, "La pelicula se actualizo correctamente");
		}
		return "redirect:/cartelera/lista";
	}
	
	@RequestMapping(value = "/getpeliculas", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, String> getFuncionesDisponibles(Principal principal)
	{
		LOG.info("/cartelera/getpeliculas: pedidas peliculas en cartelera");
		return logicaNegocio.getPeliculasCarteleraMap();
	}
}
