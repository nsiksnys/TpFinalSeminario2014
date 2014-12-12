package frgp.seminario.cine.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import frgp.seminario.cine.bo.impl.BoReserva;
import frgp.seminario.cine.forms.ReservaForm;
import frgp.seminario.cine.model.Asiento;
import frgp.seminario.cine.model.Reserva;
import frgp.seminario.cine.support.web.Message;
import frgp.seminario.cine.support.web.MessageHelper;
import frgp.seminario.cine.utils.SecurityUtils;

@RequestMapping(value="/reserva/**")
@Controller
public class ReservaController {
	@Autowired
	@Qualifier("BoReserva") //aclaro cual es el bean a inyectar
	BoReserva logicaNegocio; //aclaro las clases que se utilizan en este caso en particular
	
	@Autowired
	SecurityUtils security;
	
	private static String ROLE = "C";
	
	private static final Logger LOG = LoggerFactory.getLogger(ReservaController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(Principal principal) {
		security.isAuthorized(principal, ROLE);

		return new ModelAndView("redirect:/reserva/lista");
	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public ModelAndView lista(Principal principal) {
		security.isAuthorized(principal, ROLE);

		ModelAndView mav =new ModelAndView();
		ArrayList<ReservaForm> lista = logicaNegocio.listarTodosForm(principal.getName());
		mav.getModelMap().addAttribute("lista", lista);
		LOG.info("/cartelera/lista: listando " + lista.size() + " registro(s)");
		return mav;
	}
		
	@RequestMapping(value = "/alta", method = RequestMethod.GET)
	public ModelAndView alta(Principal principal)
	{
		security.isAuthorized(principal, ROLE);

		ModelAndView mav = new ModelAndView();
		mav.getModelMap().addAttribute("dias", logicaNegocio.getDias());
		return mav;
	}
	
	@RequestMapping(value = "/asientos", method = RequestMethod.GET)
	public ModelAndView asientos(Principal principal, HttpServletRequest request, RedirectAttributes ra) {
		security.isAuthorized(principal, ROLE);

		Object idReserva = request.getSession().getAttribute("idReserva");
		
		//si no esta guardado el id de reserva en la sesion o el id no corresponde a un registro en la base de datos
		if (idReserva == null || logicaNegocio.get(Long.parseLong(idReserva.toString())) == null)
		{
			LOG.error("/reserva/ubicacion: idReserva no valido.");
			MessageHelper.addErrorAttribute(ra, "Por favor intente nuevamente.");
			return new ModelAndView("redirect:/reserva/alta");
		}
		return new ModelAndView();
	}
	
	@RequestMapping(value = "/entradas", method = RequestMethod.GET)
	public ModelAndView entradas(Principal principal, HttpServletRequest request, RedirectAttributes ra) {
		security.isAuthorized(principal, ROLE);

		Object idReserva = request.getSession().getAttribute("idReserva");
		
		//si no esta guardado el id de reserva en la sesion o el id no corresponde a un registro en la base de datos
		if (idReserva == null || logicaNegocio.get(Long.parseLong(idReserva.toString())) == null)
		{
			LOG.error("/reserva/ubicacion: idReserva no valido.");
			MessageHelper.addErrorAttribute(ra, "Por favor intente nuevamente.");
			return new ModelAndView("redirect:/reserva/alta");
		}
		return new ModelAndView();
	}
		
	@RequestMapping(value = "/detalle", method = RequestMethod.GET)
	public ModelAndView detalle(@RequestParam("id") Long id, Principal principal, RedirectAttributes ra) {//pelicula y complejo
		security.isAuthorized(principal, ROLE);

		//si no esta guardado el id de reserva en la sesion o el id no corresponde a un registro en la base de datos
		if (id == null ||  id == 0 || logicaNegocio.get(id) == null)
		{
			LOG.error("/reserva/detalle: id no valido.");
			MessageHelper.addErrorAttribute(ra, "No existe el registro buscado.");
			return new ModelAndView("redirect:/reserva/lista");
		}
		
		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("registro", logicaNegocio.entityToForm(logicaNegocio.get(id)));
		return mav;
	}
	
	@RequestMapping(value = "/modificar", method = RequestMethod.GET)
	public ModelAndView modificar(@RequestParam("id") Long id, Principal principal) 
	{
		security.isAuthorized(principal, ROLE);

		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("registro", logicaNegocio.get(id));
		return mav;
	}
	
	@RequestMapping(value = "/borrar", method = RequestMethod.GET)
	public ModelAndView borrar(@RequestParam("id") Long id, Principal principal) 
	{
		security.isAuthorized(principal, ROLE);

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
		security.isAuthorized(principal, "A");

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
	
	@RequestMapping(value = "/alta", method = RequestMethod.POST)
	public String alta(@ModelAttribute ReservaForm formulario, Principal principal, HttpServletRequest request, RedirectAttributes ra) 
	{
		security.isAuthorized(principal, ROLE);

		formulario.setCliente(principal.getName());
		Reserva item = logicaNegocio.formToEntity(formulario);
		
		if (!logicaNegocio.guardar(item)){//si no se guarda
			MessageHelper.addErrorAttribute(ra, "Por favor revise el formulario");//agrego el mensaje de error
			return "redirect:/reserva/alta";
		}
		
		request.getSession().setAttribute("idReserva", item.getId());//agrego el id del registro reserva creado, que se usa en el siguiente paso
		request.getSession().setAttribute("cantidadEntradas", formulario.getCantidad());
		request.getSession().setAttribute("funcion", formulario.getFuncion());
		LOG.info("/reserva/alta: agregado registro nuevo con id " + item.getId());

		return "redirect:/reserva/asientos";
	}
	
	@RequestMapping(value = "/asientos", method = RequestMethod.POST)
	public String asientos(Principal principal,RedirectAttributes ra, HttpServletRequest request)
	{	
		security.isAuthorized(principal, ROLE);
		
		Long id = Long.parseLong(request.getSession().getAttribute("idReserva").toString());
		int cantidad = Integer.parseInt((String) request.getSession().getAttribute("cantidadEntradas"));
		
		if (request.getParameterValues("checkbox") == null){//si no se guarda
			MessageHelper.addErrorAttribute(ra, "Por favor intente nuevamente");//agrego el mensaje de error
			return "redirect:/reserva/asientos";
		}
		
		Reserva reserva = logicaNegocio.get(id);
		reserva.setAsientos(logicaNegocio.getAsientos(request.getParameterValues("checkbox"), cantidad));
		
		if (!logicaNegocio.modificar(reserva)){//si no se guarda
			MessageHelper.addErrorAttribute(ra, "Por favor intente nuevamente");//agrego el mensaje de error
			return "redirect:/reserva/asientos";
		}

		return "redirect:/reserva/entradas";
	}
	
	@RequestMapping(value = "/entradas", method = RequestMethod.POST)
	public String entradas(Principal principal, @RequestParam int menor, @RequestParam int mayor, @RequestParam int general, HttpServletRequest request, RedirectAttributes ra) {
		security.isAuthorized(principal, ROLE);

		Long id = Long.parseLong(request.getSession().getAttribute("idReserva").toString());
		int cantidad = Integer.parseInt(request.getSession().getAttribute("cantidadEntradas").toString());
		
		Reserva reserva = logicaNegocio.get(id);
		
		//si la cantidad indicada no es la que se indico al principio
		if (menor + mayor + general != cantidad)
		{
			MessageHelper.addErrorAttribute(ra, "Por favor revise la cantidad de entradas e intente de nuevo");//agrego el mensaje de error
			return "redirect:/reserva/entradas";
		}
		
		reserva.setPrecios(logicaNegocio.getPrecios(id, menor, mayor, general));//agrego los precios
		reserva.setCodigo(logicaNegocio.generarCodigo());
		reserva.setImporte(logicaNegocio.calcularTotal(reserva.getPrecios(), reserva.getPromo()));
		reserva.setActivo(true);//activo la reserva
		
		if (!logicaNegocio.modificar(reserva)){//si no se guarda
			MessageHelper.addErrorAttribute(ra, "error", "Por favor intente de nuevo");//agrego el mensaje de error
			return "redirect:/reserva/entradas";
		}
		
		//los sacos de la sesion porque ya no hacen falta
		request.getSession().removeAttribute("idReserva");
		request.getSession().removeAttribute("cantidadEntradas");

		
		return "redirect:/reserva/detalle?id=" + reserva.getId();
	}
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public ModelAndView guardar(@ModelAttribute ReservaForm formulario, Principal principal) 
	{
		security.isAuthorized(principal, ROLE);

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
		security.isAuthorized(principal, ROLE);

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
	
	@RequestMapping(value = "/getasientostatus", method = RequestMethod.GET)
	public @ResponseBody boolean isAsientoDisponible(@RequestParam Long asiento, @RequestParam Long funcion, Principal principal)
	{
		//LOG.info("/reserva/getAsientoStatus: pedidas status del asiento " + asiento + " para la funcion " + funcion);
		return logicaNegocio.isAsientoDisponible(asiento, funcion);
	}
	
	@RequestMapping(value = "/getidfuncion", method = RequestMethod.GET)
	public @ResponseBody Object getidFuncionReserva(Principal principal, HttpServletRequest request)
	{
		Long id = Long.parseLong(request.getSession().getAttribute("idReserva").toString());
		Reserva reserva = logicaNegocio.get(id);

		LOG.info("/reserva/getIdFuncion: enviado el valor de la funcion reservada");
		return reserva.getFuncion().getId();
	}
}