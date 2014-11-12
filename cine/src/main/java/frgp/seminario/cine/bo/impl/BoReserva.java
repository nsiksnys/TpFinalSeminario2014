package frgp.seminario.cine.bo.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import frgp.seminario.cine.bo.BusinessObject;
import frgp.seminario.cine.bo.impl.BoComplejo;
import frgp.seminario.cine.findItem.impl.FuncionFindItem;
import frgp.seminario.cine.findItem.impl.PeliculaFindItem;
import frgp.seminario.cine.findItem.impl.PromocionFindItem;
import frgp.seminario.cine.findItem.impl.ReservaFindItem;
import frgp.seminario.cine.forms.ReservaForm;
import frgp.seminario.cine.model.Complejo;
import frgp.seminario.cine.model.Entrada;
import frgp.seminario.cine.model.Funcion;
import frgp.seminario.cine.model.Pelicula;
import frgp.seminario.cine.model.Promocion;
import frgp.seminario.cine.model.Reserva;
import frgp.seminario.cine.repository.Repository;
import frgp.seminario.cine.utils.FechaUtils;

//Funciones pertenecientes a la logica de negocios
@Service("BoReserva") //agrego el nombre del bean, para que al momento de llamar al Autowired pueda aclarar cual quiero
public class BoReserva implements BusinessObject<Reserva, ReservaForm> {
	@Autowired
	@Qualifier("ReservaRepository") //aclaro cual es el bean a inyectar
	private Repository<Reserva> reservaRepository; //aclaro las clases que se utilizan en este caso en particular
	
	@Autowired
	@Qualifier("EntradaRepository") //aclaro cual es el bean a inyectar
	private Repository<Entrada> entradaRepository; //aclaro las clases que se utilizan en este caso en particular
	
	@Autowired
	@Qualifier("ReservaFindItem") //aclaro cual es el bean a inyectar
	private ReservaFindItem busquedaReservas;
	
	@Autowired
	@Qualifier("PeliculaFindItem") //aclaro cual es el bean a inyectar
	private PeliculaFindItem busquedaPeliculas;
	
	@Autowired
	@Qualifier("BoComplejo") //aclaro cual es el bean a inyectar
	private BoComplejo complejos;
	
	@Autowired
	@Qualifier("FuncionFindItem") //aclaro cual es el bean a inyectar
	private FuncionFindItem busquedaFunciones;
	
	@Autowired
	@Qualifier("PromocionFindItem") //aclaro cual es el bean a inyectar
	private PromocionFindItem busquedaPromociones;
	
	@Autowired
	private FechaUtils utils;

	/** 
	 ** Busca un registro en específico.
	 ** @param id el id del objeto buscado
	 ** @return el registro Pelicula buscado
	 **/
	@Override
	public Reserva get(Object id) {
		return reservaRepository.get(Reserva.class, id);
	}

	/**
	 ** Persiste un registro en la base de datos.
	 ** @param registro El objeto a persistir.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean guardar(Reserva registro) {
		return reservaRepository.save(registro);
	}

	/**
	 ** Guarda los cambios en un registro en la base de datos.
	 ** @param registro El objeto a guardar.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean modificar(Reserva registro) {
		return reservaRepository.merge(registro);
	}

	/**
	 ** Desactiva un registro en la base de datos.
	 ** @param registro El objeto a desactivar.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean desactivar(Reserva registro) {				
		if (registro.isActivo())
			registro.setActivo(false);
		
		return reservaRepository.merge(registro);
	}

	/**
	 ** Recupera todos los registros de la clase Reserva
	 ** @return un ArrayList con todos los registros
	 **/
	@Override
	public ArrayList<Reserva> listarTodos() {
		return (ArrayList<Reserva>) reservaRepository.getAll(Reserva.class);
	}
	
	/**
	 ** Recupera todos los registros de la clase Reserva
	 ** @return un ArrayList con todos los registros
	 **/
	public ArrayList<Reserva> listarTodos(String email) {
		return busquedaReservas.findByClienteEmail(email);
	}
	
	/**
	 ** Recupera todos los registros activos de la clase Complejo
	 ** @return un ArrayList con todos los registros
	 **/
	public ArrayList<Complejo> getComplejosActivos() {
		return complejos.getAllEnabled();
	}
	
	/**
	 ** Recupera todos los registros activos de la clase Pelicula
	 ** @return un ArrayList con todos los registros
	 **/
	public ArrayList<Pelicula> getPeliculasActivas() {
		return busquedaPeliculas.getAllEnabled();
	}
	
	/**
	 ** Recupera todos los registros activos de la clase Funciones
	 ** @return un ArrayList con todos los registros
	 **/
	public ArrayList<Funcion> getFuncionesPorPeliculayComplejo(Long idComplejo, Long idPelicula) {
		return busquedaFunciones.findActiveByComplejoPelicula(idComplejo, idPelicula);
	}
	
	/**
	 ** Recupera todos los registros activos de la clase Promocion
	 ** @return un ArrayList con todos los registros
	 **/
	public ArrayList<Promocion> getPromocionesActivas(int idComplejo) {
		//return promociones.getAllEnabled(idComplejo);
		return new ArrayList<Promocion>();
	}

	/**
	 * Verifica que el registro cumpla con las caracteristicas necesarias
	 * @return true si el registro esta en condiciones de ser guardardo, false si no lo esta
	 **/
	@Override
	public boolean verificar(Reserva registro) {
		// TODO Escribir funcion
		return true;
	}

	/**
	 * Convierte un formulario en un registro Reserva
	 * @param formulario el formulario submiteado
	 * @return un objeto Reserva
	 */
	@Override
	public Reserva formToEntity(ReservaForm formulario) {
		// TODO Escribir funcion
		return null;
	}

	/**
	 * Convierte un registro Cartelera en un formulario
	 * @param registro el objeto Reserva
	 * @return un objeto formulario
	 */
	public ReservaForm entityToForm(Reserva registro) {
		ReservaForm formulario = new ReservaForm();
		formulario.setCodigo(registro.getCodigo());
		formulario.setComplejo(complejos.get(registro.getFuncion().getSala().getIdComplejo()).getNombre());
		formulario.setFecha(utils.getFormatoDiaMesAnio(registro.getFechaReserva()));
		formulario.setHorario(utils.getFormatoHoraMinuto(registro.getFuncion().getHorario().getHoraInicio()));
		formulario.setPelicula(registro.getFuncion().getPelicula().getNombre());
		formulario.setTotal(String.valueOf(registro.getImporte()));
		
		return formulario;
	}

	/**
	 ** Activa un registro en la base de datos.
	 ** @param registro El objeto a activar.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	public boolean activar(Reserva registro) {		
		if (!registro.isActivo())
			registro.setActivo(true);
		
		return reservaRepository.merge(registro);
	}
}