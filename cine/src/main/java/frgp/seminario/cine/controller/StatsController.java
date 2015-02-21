package frgp.seminario.cine.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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

import frgp.seminario.cine.bo.impl.BoReserva;
import frgp.seminario.cine.model.Reserva;
import frgp.seminario.cine.utils.SecurityUtils;

@RequestMapping(value="/stats/**")
@Controller
public class StatsController {
	@Autowired
	@Qualifier("BoReserva") //aclaro cual es el bean a inyectar
	BoReserva logicaNegocio; //aclaro las clases que se utilizan en este caso en particular
	
	@Autowired
	SecurityUtils security;
	
	private static String ROLE = "G";
	
	private static final Logger LOG = LoggerFactory.getLogger(StatsController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(Principal principal) {
		security.isAuthorized(principal, ROLE);

		return new ModelAndView("redirect:/stats/alta");
	}
	
	@RequestMapping(value = "/alta", method = RequestMethod.GET)
	public ModelAndView alta(Principal principal) {
		security.isAuthorized(principal, ROLE);

		ArrayList<Reserva> lista =logicaNegocio.listarTodos();
		ModelAndView mav =new ModelAndView();
		mav.getModelMap().addAttribute("lista", lista);
		LOG.info("/stats/lista: listando " + lista.size() + " registro(s)");
		return mav;
	}
	@RequestMapping(value = "/guardarAll", method = RequestMethod.POST)
	public ModelAndView guardar(Principal principal) 
	{
		security.isAuthorized(principal, ROLE);

		ModelAndView mav =new ModelAndView("redirect:/stats/listaAll");
		
		return mav;
	}
	@RequestMapping(value = "/guardarRango", method = RequestMethod.POST)
	public ModelAndView guardar(@RequestParam("fechaInicial") String fecha1, @RequestParam("fechaFinal") String fecha2,Principal principal){
		security.isAuthorized(principal, ROLE);

		ModelAndView mav =new ModelAndView("redirect:/stats/listaRango?fecha1="+fecha1+"&fecha2="+fecha2);
		return mav;
	}
	@RequestMapping(value = "/listaAll", method = RequestMethod.GET)
	public ModelAndView lista(Principal principal) {
		security.isAuthorized(principal, ROLE);

		LOG.info("/stats/lista: grafico de barras");
		ModelAndView mav =new ModelAndView();
		
		return mav;
	}
	@RequestMapping(value = "/listaRango", method = RequestMethod.GET)
	public ModelAndView listaR(@RequestParam("fecha1") String fecha1, @RequestParam("fecha2") String fecha2,Principal principal){
		security.isAuthorized(principal, ROLE);

		LOG.info("/stats/lista: esto deberia graficor segun el rango de fechas");
		ModelAndView mav =new ModelAndView();
		mav.addObject("fecha1", fecha1);
		mav.addObject("fecha2", fecha2);
		
		return mav;
	}
	
	//=============================================
	// OBTENGO EL VALOR DE INICIAL Y PARSE LOS MAYORES A EL
	//==============================================
	@RequestMapping(value = "/gethora", method = RequestMethod.GET)
	public @ResponseBody Map<String, String> gethora(@RequestParam("fechaInicial") Long fechaInicial,Principal principal)
	{
		LOG.info("/estadistica/gethora: pedido de horas posteriores");
			
		ArrayList<Reserva> reserva = logicaNegocio.listarHorariosPosteriores(fechaInicial);
				
		Map<String, String> respuesta = new TreeMap<String, String>();
		for (Reserva item : reserva){				
			respuesta.put(item.getId().toString(), item.getFechaReserva().toString());			
		}
		return respuesta;
		
	}
	//==============================================
	// PARAMETROS A JSON SIN FECHA
	//==============================================	
	@RequestMapping(value = "/punto1", method = RequestMethod.GET)
	public @ResponseBody Map<String, Integer> punto1(Principal principal){
		Map<String, Integer> pelicula =  logicaNegocio.AsistenciaXPelicula();
		return pelicula;
	}
	@RequestMapping(value = "/punto2", method = RequestMethod.GET)
	public @ResponseBody Map<String, Integer> punto2(Principal principal){		    
		Map<String, Integer> pelicula =  logicaNegocio.CancelacionesXPelicula();			
		return pelicula;
	}
	@RequestMapping(value = "/punto3", method = RequestMethod.GET)
	public @ResponseBody Map<String, Integer> punto3(Principal principal){		    
		Map<String, Integer> pelicula =  logicaNegocio.PorcentajesAsistencia();				
		return pelicula;
	}
	@RequestMapping(value = "/punto4", method = RequestMethod.GET)
	public @ResponseBody Map<String, Integer> punto4(Principal principal){		    
		Map<String, Integer> pelicula =  logicaNegocio.HorarioMasSolicitados();			
		return pelicula;
	}
	@RequestMapping(value = "/punto5", method = RequestMethod.GET)
	public @ResponseBody Map<String, Integer> punto5(Principal principal){		    
		Map<String, Integer> pelicula =  logicaNegocio.ComplejoMasSolicitado();			
		return pelicula;
	}
	@RequestMapping(value = "/punto6", method = RequestMethod.GET)
	public @ResponseBody Map<String, Integer> punto6(Principal principal){		    
		Map<String, Integer> pelicula =  logicaNegocio.GeneroMasSolicitado();			
		return pelicula;
	}
	@RequestMapping(value = "/punto7", method = RequestMethod.GET)
	public @ResponseBody Map<String, Integer> punto7(Principal principal){		    
		Map<String, Integer> pelicula =  logicaNegocio.MenoresEnDiaSemana();		
		return pelicula;
	}
	//==============================================
	// PARAMETROS A JSON CON FECHA
	//==============================================
	@RequestMapping(value = "/puntoR1", method = RequestMethod.GET)
	public @ResponseBody Map<String, Integer> puntoR1(@RequestParam("idfecha1") Long idfecha1,@RequestParam("idfecha2") Long idfecha2,Principal principal){
		Map<String, Integer> pelicula =  logicaNegocio.AsistenciaXPelicula(idfecha1,idfecha2);
		return pelicula;
	}
	@RequestMapping(value = "/puntoR2", method = RequestMethod.GET)
	public @ResponseBody Map<String, Integer> puntoR2(@RequestParam("idfecha1") Long idfecha1,@RequestParam("idfecha2") Long idfecha2,Principal principal){
		Map<String, Integer> pelicula =  logicaNegocio.CancelacionesXPelicula(idfecha1,idfecha2);			
		return pelicula;
	}
	@RequestMapping(value = "/puntoR3", method = RequestMethod.GET)
	public @ResponseBody Map<String, Integer> puntoR(@RequestParam("idfecha1") Long idfecha1,@RequestParam("idfecha2") Long idfecha2,Principal principal){
		Map<String, Integer> pelicula =  logicaNegocio.PorcentajesAsistencia(idfecha1,idfecha2);				
		return pelicula;
	}
	@RequestMapping(value = "/puntoR4", method = RequestMethod.GET)
	public @ResponseBody Map<String, Integer> puntoR4(@RequestParam("idfecha1") Long idfecha1,@RequestParam("idfecha2") Long idfecha2,Principal principal){
		Map<String, Integer> pelicula =  logicaNegocio.HorarioMasSolicitados(idfecha1,idfecha2);			
		return pelicula;
	}
	@RequestMapping(value = "/puntoR5", method = RequestMethod.GET)
	public @ResponseBody Map<String, Integer> puntoR5(@RequestParam("idfecha1") Long idfecha1,@RequestParam("idfecha2") Long idfecha2,Principal principal){
		Map<String, Integer> pelicula =  logicaNegocio.ComplejoMasSolicitado(idfecha1,idfecha2);			
		return pelicula;
	}
	@RequestMapping(value = "/puntoR6", method = RequestMethod.GET)
	public @ResponseBody Map<String, Integer> puntoR6(@RequestParam("idfecha1") Long idfecha1,@RequestParam("idfecha2") Long idfecha2,Principal principal){
		Map<String, Integer> pelicula =  logicaNegocio.GeneroMasSolicitado(idfecha1,idfecha2);			
		return pelicula;
	}
	@RequestMapping(value = "/puntoR7", method = RequestMethod.GET)
	public @ResponseBody Map<String, Integer> puntoR7(@RequestParam("idfecha1") Long idfecha1,@RequestParam("idfecha2") Long idfecha2,Principal principal){
		Map<String, Integer> pelicula =  logicaNegocio.MenoresEnDiaSemana(idfecha1,idfecha2);		
		return pelicula;
	}
}