package frgp.seminario.cine.bo.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import frgp.seminario.cine.bo.BusinessObject;
import frgp.seminario.cine.bo.impl.BoComplejo;
import frgp.seminario.cine.findItem.impl.ReservaFindItem;
import frgp.seminario.cine.forms.ReservaForm;
import frgp.seminario.cine.model.Entrada;
import frgp.seminario.cine.model.Precio;
import frgp.seminario.cine.model.Reserva;
import frgp.seminario.cine.repository.Repository;
import frgp.seminario.cine.utils.FechaUtils;

import org.apache.commons.lang3.RandomStringUtils;

//Funciones pertenecientes a la logica de negocios
@Service("BoReserva") //agrego el nombre del bean, para que al momento de llamar al Autowired pueda aclarar cual quiero
public class BoReserva implements BusinessObject<Reserva, ReservaForm> {
	//Longitud del codigo de reserva
	static private int LONGITUD = 10;
	
	@Autowired
	BoCliente clientes;
	
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
	private BoPelicula peliculas;
	
	@Autowired
	@Qualifier("BoComplejo") //aclaro cual es el bean a inyectar
	private BoComplejo complejos;
	
	@Autowired
	private BoFuncion funciones;
	
	@Autowired
	private BoPromocion promociones;
	
	@Autowired
	private BoPrecio precios;
	
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
		return new Reserva(clientes.get(formulario.getCliente()), 
							funciones.get(Long.parseLong(formulario.getFuncion())),
							null,
							promociones.get(Long.parseLong(formulario.getPromo())), 
							utils.getFechaReserva(Integer.parseInt(formulario.getFecha())), 
							new Date(),
							null,
							0);
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
		formulario.setFuncion(utils.getFormatoHoraMinuto(registro.getFuncion().getHorario().getHoraInicio()));
		formulario.setPelicula(registro.getFuncion().getPelicula().getNombre());
		formulario.setTotal(String.valueOf(registro.getImporte()));
		formulario.setPromo(registro.getPromo().getNombre());
		
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

	/**
	 * Genera un codigo que se utilizara para identificar la reserva
	 * @param longitud un int indicando la longitud del codigo
	 * @return un String con el codigo generado
	 */
	public String generarCodigo()
	{
		return RandomStringUtils.random(LONGITUD);
	}
	
	/**
	 * Trae los dias de la semana que correspondan segun del dia de la fecha
	 */
	public TreeMap<String, String> getDias()
	{
		TreeMap<String, String> dias = getDiasTreeMap();
		TreeMap<String, String> rta = new TreeMap<String, String>();
		int hoy = new GregorianCalendar().get(Calendar.DAY_OF_WEEK);
		
		System.out.println("Hoy DAY_OF_WEEK " + hoy);
		
		for (int i=hoy; i <= dias.size(); i++){
				rta.put(String.valueOf(i), dias.get(Integer.toString(i)));
		}
		
		return rta;
	}
	
	public TreeMap<String, String> getDiasTreeMap()
	{
		TreeMap<String, String> dias = new TreeMap<String, String>();
		dias.put(Integer.toString(Calendar.SUNDAY), "Domingo");
		dias.put(Integer.toString(Calendar.MONDAY), "Lunes");
		dias.put(Integer.toString(Calendar.TUESDAY), "Martes");
		dias.put(Integer.toString(Calendar.WEDNESDAY), "Miercoles");
		dias.put(Integer.toString(Calendar.THURSDAY), "Jueves");
		dias.put(Integer.toString(Calendar.FRIDAY), "Viernes");
		dias.put(Integer.toString(Calendar.SATURDAY), "Sabado");
		return dias;
	}
	
	public int[] getOrdenDiasArray()
	{
		int[] array = {Calendar.THURSDAY, Calendar.FRIDAY, Calendar.SATURDAY, Calendar.SUNDAY, Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY};
		return array;
	}

	public HashMap<Precio, Integer> getPrecios(int menor, int mayor, int general) {
		ArrayList<Precio> todos = precios.getAllEnabled();//FIXME: que pasa si hay mas de un precio activo?
		HashMap<Precio, Integer> rta = new HashMap<Precio, Integer>();
		//TODO: agregar los precios al hashmap
		
		return rta;
	}

	public float calcularTotal(Reserva reserva) {
		float total = 0;
/*
 		//TODO: como hago para reconocer si un precio es menor, mayor o general?
		for (Precio item : precios.getAllEnabled())
		{
			if (reserva.getPrecios().containsKey(item))
				total += item.get
		}
*/	
		return total;
	}
}