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

import frgp.seminario.cine.bo.impl.BoReserva;
import frgp.seminario.cine.forms.ReservaForm;
import frgp.seminario.cine.model.Reserva;

@RequestMapping(value="/reserva/**")
@Controller
public class ReservaController {
	@Autowired
	@Qualifier("BoReserva") //aclaro cual es el bean a inyectar
	BoReserva logicaNegocio; //aclaro las clases que se utilizan en este caso en particular
	
	private static final Logger LOG = LoggerFactory.getLogger(ReservaController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(Principal principal) {
		return new ModelAndView("redirect:/reserva/lista");
	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public ModelAndView lista(Principal principal) {
		ModelAndView mav =new ModelAndView();
		ArrayList<Reserva> lista = logicaNegocio.listarTodos(principal.getName());
		mav.getModelMap().addAttribute("lista", lista);
		LOG.info("/cartelera/lista: listando " + lista.size() + " registro(s)");
		return mav;
	}
		
	@RequestMapping(value = "/reserva/alta", method = RequestMethod.GET)
	public ModelAndView alta(Principal principal)
	{//para elegir complejo, pelicula, funcion cantidad de entradas y promocion en un mismo formulario
		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("complejos", logicaNegocio.getComplejosActivos());
		mav.getModelMap().addAttribute("peliculas", logicaNegocio.getPeliculasActivas());
	//	mav.getModelMap().addAttribute("funciones", logicaNegocio.ggetPeliculasActivas());
		
		return mav;
	}
	
	@RequestMapping(value = "/alta?paso=1", method = RequestMethod.GET)//TODO: probar si funciona
	public ModelAndView pelicula(Principal principal) {//pelicula y complejo
		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("complejos", logicaNegocio.getComplejosActivos());
		mav.getModelMap().addAttribute("peliculas", logicaNegocio.getPeliculasActivas());
		return mav;
	}
	
	@RequestMapping(value = "/modificar", method = RequestMethod.GET)
	public ModelAndView modificar(@RequestParam("id") Long id, Principal principal) 
	{
		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("registro", logicaNegocio.get(id));
		return mav;
	}
	
	@RequestMapping(value = "/borrar", method = RequestMethod.GET)
	public ModelAndView borrar(@RequestParam("id") Long id, Principal principal) 
	{
		ModelAndView mav =new ModelAndView("redirect:/reserva/lista");//indico que uso la vista "modificar"
		
		if (!logicaNegocio.desactivar(logicaNegocio.get(id))){//si no se guarda
			//mav.getModelMap().addAttribute("error", "Por favor revise el formulario");//agrego el mensaje de error
		}
		else
		{
			LOG.info("/reserva/borrar: modificado registro con id " + id);
		}
		return mav;
	}
	
	@RequestMapping(value = "/activar", method = RequestMethod.GET)
	public ModelAndView activar(@RequestParam("id") Long id, Principal principal) 
	{
		ModelAndView mav =new ModelAndView("redirect:/cartelera/lista");//indico que uso la vista "modificar"
		
		if (!logicaNegocio.activar(logicaNegocio.get(id))){//si no se guarda
			//mav.getModelMap().addAttribute("error", "Por favor revise el formulario");//agrego el mensaje de error
		}
		else
		{
			LOG.info("/reserva/activar: activado registro con id " + id);
		}
		return mav;
	}
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public ModelAndView guardar(@ModelAttribute ReservaForm formulario, Principal principal) 
	{
		ModelAndView mav =new ModelAndView("redirect:/reserva/lista");
		//ModelAndView mav =new ModelAndView("redirect:/reserva/alta");
		Reserva item = logicaNegocio.formToEntity(formulario);
		
		if (!logicaNegocio.guardar(item)){//si no se guarda
			mav.setViewName("redirect:/reserva/alta");
			mav.getModelMap().addAttribute("error", "Por favor revise el formulario");//agrego el mensaje de error
		}
		else
		{
			LOG.info("/reserva/alta: agregado registro nuevo con id " + item.getId());
			//mav.getModelMap().addAttribute("ok", "El registro se guardo correctamente");
		}
		return mav;
	}
	
	@RequestMapping(value = "/modificar", method = RequestMethod.POST)
	public ModelAndView modificar(@ModelAttribute ReservaForm formulario, Principal principal) 
	{
		ModelAndView mav =new ModelAndView("redirect:/reserva/lista");
		//ModelAndView mav =new ModelAndView();
		Reserva registro = logicaNegocio.formToEntity(formulario);
		registro.setId(Long.parseLong(formulario.getId()));
		
		if (!logicaNegocio.modificar(registro)){//si no se guarda
			mav.setViewName("redirect:/reserva/modificar?id="+formulario.getId());
			mav.getModelMap().addAttribute("error", "Por favor revise el formulario");//agrego el mensaje de error
		}
		else
		{
			LOG.info("/reserva/modificar: actualizado registro con id " + registro.getId());
			//mav.getModelMap().addAttribute("ok", "La pelicula se guardo correctamente");
		}
		return mav;
	}
}