package frgp.seminario.cine.bo.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import frgp.seminario.cine.bo.BusinessObject;
import frgp.seminario.cine.bo.impl.BoComplejo;
import frgp.seminario.cine.findItem.impl.AsientoFindItem;
import frgp.seminario.cine.findItem.impl.FuncionFindItem;
import frgp.seminario.cine.findItem.impl.ReservaFindItem;
import frgp.seminario.cine.forms.ReservaForm;
import frgp.seminario.cine.model.Asiento;
import frgp.seminario.cine.model.Cliente;
import frgp.seminario.cine.model.Complejo;
import frgp.seminario.cine.model.Entrada;
import frgp.seminario.cine.model.Funcion;
import frgp.seminario.cine.model.Precio;
import frgp.seminario.cine.model.Promocion;
import frgp.seminario.cine.model.Reserva;
import frgp.seminario.cine.model.ReservaPrecio;
import frgp.seminario.cine.repository.Repository;
import frgp.seminario.cine.utils.FechaUtils;

import org.apache.commons.lang3.RandomStringUtils;

//Funciones pertenecientes a la logica de negocios
@Service("BoReserva") //agrego el nombre del bean, para que al momento de llamar al Autowired pueda aclarar cual quiero
public class BoReserva implements BusinessObject<Reserva, ReservaForm> {
	//Longitud del codigo de reserva
	static private int LONGITUD = 10;
	
	//limite de reservas para la funcion
		static private int LIMITE_RESERVAS = 7;
		
		@Autowired
		BoCliente clientes;
		
		@Autowired
		@Qualifier("ReservaRepository") //aclaro cual es el bean a inyectar
		private Repository<Reserva> reservaRepository; //aclaro las clases que se utilizan en este caso en particular
		
		@Autowired
		@Qualifier("EntradaRepository") //aclaro cual es el bean a inyectar
		private Repository<Entrada> entradaRepository; //aclaro las clases que se utilizan en este caso en particular
		
		@Autowired
		@Qualifier("AsientoRepository") //aclaro cual es el bean a inyectar
		private Repository<Asiento> asientoRepository; //aclaro las clases que se utilizan en este caso en particular

		@Autowired
		@Qualifier("ReservaPrecioRepository") //aclaro cual es el bean a inyectar
		private Repository<ReservaPrecio> detallesPrecios;
		
		@Autowired
		@Qualifier("FuncionFindItem") //aclaro cual es el bean a inyectar
		FuncionFindItem busquedaFuncion;

		
		@Autowired
		@Qualifier("ReservaFindItem") //aclaro cual es el bean a inyectar
		private ReservaFindItem busquedaReservas;
		
		@Autowired
		private AsientoFindItem asientos;
		
		
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
			if (registro.isActivo()){
				registro.setActivo(false);
				registro.setCodigo("");//FIXME: por que va esto? Que sentido tiene?
				registro.setAsientos(null);
			}
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
		 * Devuelve una lista de todos las reservas que tiene un usuario
		 * @param name email del usuario
		 * @return ArrayList<ReservaForm>
		 */
		public ArrayList<ReservaForm> listarTodosForm(String name) {
			ArrayList<ReservaForm> lista = new ArrayList<ReservaForm>();
			
			for (Reserva item : busquedaReservas.findByClienteEmail(name))
			{
				lista.add(entityToForm(item));
			}
			
			return lista;
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
			formulario.setId(registro.getId().toString());
			formulario.setCodigo(registro.getCodigo());
			formulario.setComplejo(complejos.get(registro.getFuncion().getSala().getIdComplejo()).getNombre());
			formulario.setFecha(utils.getFormatoDiaMesAnio(registro.getFechaReserva()));
			formulario.setFuncion(utils.getFormatoHoraMinuto(registro.getFuncion().getHorario().getHoraInicio()));
			formulario.setPelicula(registro.getFuncion().getPelicula().getNombre());
			formulario.setAsientos(this.getAsientosString(registro.getId()));
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
			return RandomStringUtils.random(LONGITUD, true, true);
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
		
		/**
		 * Obtengo los asientos elegidos
		 * @param check Array de strings con los valores de los checkboxes
		 * @param cantidad Int con la cantidad de entradas reservadas
		 * @return un List con los asientos
		 * @throws 
		 */
		public List<Asiento> getAsientos(String[] check, int cantidad)
		{
			List<Asiento> lista= new ArrayList<Asiento>(cantidad);
			String[] butaca;
			String fila;
			String columna;
					
			for(int i=0; i<check.length;i++)
			{
				if (check[i] != null && i <= cantidad)
				{
					butaca = check[i].split(",");
					fila = butaca[0];
					columna =butaca[1];
					
					
					Asiento asiento =new Asiento(String.valueOf(fila),String.valueOf(columna));
					lista.add(asientos.findByAsiento(asiento));
				}
			}
			
			if (lista.isEmpty() || lista.size() < cantidad || check.length > cantidad)
				throw new NullPointerException("La cantidad de asientos no coincide con la esperada.");
			
			return lista;
		}

		/**
		 * Obtiene la lista de precios de una reserva
		 * @param idReserva id de la reserva en cuestion
		 * @param menor cantidad de entradas para menores
		 * @param mayor cantidad de entradas para mayores
		 * @param general cantidad de entradas generales
		 * @return ArrayList<ReservaPrecio> con el detalle de los precios para la reserva en cuestion. 
		 */
		public ArrayList<ReservaPrecio> getPrecios(Long idReserva, int menor, int mayor, int general) {
			Precio precio = precios.getEnabled();
			ArrayList<ReservaPrecio> lista = new ArrayList<ReservaPrecio>();
			
			if (menor > 0) //si hay entradas de menores
				lista.add(new ReservaPrecio(idReserva, precio, "menor", menor));
			
			if (mayor > 0) //si hay entradas de mayores
				lista.add(new ReservaPrecio(idReserva, precio, "mayor", mayor));
			
			if (general > 0) //si hay entradas de mayores
				lista.add(new ReservaPrecio(idReserva, precio, "general", general));
			
			lista.trimToSize();

	/*
			for (ReservaPrecio item : lista){
				detallesPrecios.save(item);
			}
	*/
			
			return lista;
		}

		/**
		 * Calcula el total de la reserva
		 * @param detallePrecios el detalle de los precios para esta reserva
		 * @param promo la promocion utilizada, si hay alguna
		 * @return float con el total de la reserva
		 */
		public float calcularTotal(List<ReservaPrecio> detallePrecios, Promocion promo) {
			float total = 0;

			for (ReservaPrecio item : detallePrecios) {
				switch (item.getTipoPrecio())
				{
					case "menor":
						total += item.getPrecio().getMenor() * item.getCantidad();
						break;
					
					case "mayor":
						total += item.getPrecio().getMayor() * item.getCantidad();
						break;
						
					case "general":
						total += item.getPrecio().getGeneral() * item.getCantidad();
						break;
				}
			}
					
			return total;
		}
		//===================================================
		// DATOS PARA GRAFICAR SIN FECHAS
		//===================================================
		public ArrayList<Reserva> listarHorariosPosteriores(Long idfechaInicio) {

			List<Reserva> reservasTodas = (ArrayList<Reserva>) reservaRepository.getAll(Reserva.class);
			Reserva reserva = reservaRepository.get(Reserva.class, idfechaInicio);
			List<Reserva> aux = new ArrayList<Reserva>();
			for(Reserva reservaIndividual : reservasTodas){
				if(reservaIndividual.getFechaReserva().after(reserva.getFechaReserva()) || reservaIndividual.getFechaReserva().equals(reserva.getFechaReserva())){
					aux.add(reservaIndividual);
				}
			}
			return (ArrayList<Reserva>) aux;
		}
		//===================================================
		// DATOS PARA GRAFICAR SIN FECHAS
		//===================================================
		public Map<String,Integer> AsistenciaXPelicula(){
			List<Reserva> todasReservas = (ArrayList<Reserva>) reservaRepository.getAll(Reserva.class);
			List<Funcion> todosFuncion = busquedaFuncion.getAllEnabled();
			Map<String,Integer> asistencia = new TreeMap<String,Integer>();
			for (Funcion funcion : todosFuncion){
				int cantidad = 0;
				for (Reserva reserva : todasReservas)			{
//					if (reserva.getFuncion().getId() == funcion.getId() && reserva.isActivo())				{
					if (reserva.getFuncion().getPelicula().getId() == funcion.getPelicula().getId() && reserva.isActivo())    {
						cantidad += busquedaReservas.findAsientoIdByReserva(reserva.getId());
					}
				}
				asistencia.put(funcion.getPelicula().getNombre().toString(), cantidad);
			}
			return asistencia;
		}
		public Map<String,Integer> CancelacionesXPelicula(){
			List<Reserva> todasReservas = (ArrayList<Reserva>) reservaRepository.getAll(Reserva.class);
			List<Funcion> todosFuncion = busquedaFuncion.getAllEnabled();
			Map<String,Integer> asistencia = new TreeMap<String,Integer>();
			for (Funcion funcion : todosFuncion){
				int cantidad = 0;
				for (Reserva reserva : todasReservas)			{
					if (reserva.getFuncion().getId() == funcion.getId() && !reserva.isActivo())				{
						cantidad += busquedaReservas.findAsientoIdByReserva(reserva.getId());
					}
				}
				asistencia.put(funcion.getPelicula().getNombre().toString(), cantidad);
			}
			return asistencia;
		}
		public Map<String,Integer> PorcentajesAsistencia(){
			List<Reserva> todasReservas = (ArrayList<Reserva>) reservaRepository.getAll(Reserva.class);
			List<Funcion> todosFuncion = busquedaFuncion.getAllEnabled();
			Map<String,Integer> asistencia = new TreeMap<String,Integer>();
			int total_cantidad = 0;
			int total_cancelacion = 0;
			int total_activos = 0;
			for (Funcion funcion : todosFuncion){
				for (Reserva reserva : todasReservas)			{
					if (reserva.getFuncion().getId() == funcion.getId()){
						total_cantidad += busquedaReservas.findAsientoIdByReserva(reserva.getId());
						if(reserva.isActivo()){
							total_activos += busquedaReservas.findAsientoIdByReserva(reserva.getId());
						}else{
							total_cancelacion+= busquedaReservas.findAsientoIdByReserva(reserva.getId());
						}
					}
				}
			}
			asistencia.put("Porcentaje Asistencia", Math.round((total_activos*100)/total_cantidad));
			asistencia.put("Porcentade Cancelaciones", Math.round((total_cancelacion*100)/total_cantidad));
			return asistencia;
		}
		
		public Map<String,Integer> HorarioMasSolicitados(){
			List<Reserva> todasReservas = (ArrayList<Reserva>) reservaRepository.getAll(Reserva.class);
			List<Funcion> todosFuncion = busquedaFuncion.getAllEnabled();
			Map<String,Integer> asistencia = new TreeMap<String,Integer>();
			for (Funcion funcion : todosFuncion){
				int cantidad = 0;
				for (Reserva reserva : todasReservas)			{
					if (reserva.getFuncion().getHorario().equals(funcion.getHorario())  && reserva.isActivo())				{
						cantidad += busquedaReservas.findAsientoIdByReserva(reserva.getId());
					}
				}
				asistencia.put(funcion.getHorario().getHoraInicio().toString(), cantidad);
			}
			return asistencia;
		}
		
		public Map<String,Integer> ComplejoMasSolicitado(){
			List<Reserva> todasReservas = (ArrayList<Reserva>) reservaRepository.getAll(Reserva.class);
			List<Funcion> todosFuncion = busquedaFuncion.getAllEnabled();
			Map<String,Integer> asistencia = new TreeMap<String,Integer>();
			for (Funcion funcion : todosFuncion){
				int cantidad = 0;
				for (Reserva reserva : todasReservas)			{
					if (reserva.getFuncion().getSala().getIdComplejo() == funcion.getSala().getIdComplejo() && reserva.isActivo())				{
						cantidad += busquedaReservas.findAsientoIdByReserva(reserva.getId());
					}
				}
				Complejo complejo = complejos.get(funcion.getSala().getIdComplejo());
				asistencia.put(complejo.getNombre().toString(), cantidad);
			}
			return asistencia;
		}
		
		public Map<String,Integer> GeneroMasSolicitado(){		
			List<String> generos = new ArrayList<String>();
			List<Cliente> clienteTodos = clientes.listarTodos();		
			for(Cliente cliente : clienteTodos){
				generos.add(cliente.getGeneroPreferido());
			}
			Map<String,Integer> asistencia = new TreeMap<String,Integer>();
			for(Cliente cliente : clienteTodos){			
				int cantidad = Collections.frequency(generos, cliente.getGeneroPreferido().toString());
				asistencia.put(cliente.getGeneroPreferido().toString(), cantidad);
			}
			return asistencia;
		}
		
		public Map<String,Integer> MenoresEnDiaSemana(){		
			List<Reserva> todasReservas = (ArrayList<Reserva>) reservaRepository.getAll(Reserva.class);
			List<Funcion> todosFuncion = busquedaFuncion.getAllEnabled();
			List<ReservaPrecio> todosPrecios = detallesPrecios.getAll(ReservaPrecio.class);		
			Map<String,Integer> asistencia = new LinkedHashMap<String,Integer>();		
			int[] intArray = {0, 0, 0, 0, 0, 0, 0};		
			for (Funcion funcion : todosFuncion){			
				for (Reserva reserva : todasReservas){
					if (reserva.getFuncion().getId() == funcion.getId() && reserva.isActivo()){
						Calendar c = Calendar.getInstance();
						c.setTime(reserva.getFechaReserva());
						int dia = c.get(Calendar.DAY_OF_WEEK);
						for(ReservaPrecio tipo :todosPrecios ){
							if(tipo.getTipoPrecio().equals("menor") && tipo.getIdReserva() == reserva.getId()){
								intArray[dia-1]+=tipo.getCantidad();
							}
						} // FOR DE PRECIOS					
					} // IF DE FuNCION ID
				} // FOR DE RESERVA
			} // FOR DE FUNCION
			asistencia.put("Domingo", intArray[0]);
			asistencia.put("Lunes", intArray[1]);
			asistencia.put("Martes", intArray[2]);
			asistencia.put("Miercoles", intArray[3]);
			asistencia.put("Jueves", intArray[4]);
			asistencia.put("Viernes", intArray[5]);
			asistencia.put("Sabado", intArray[6]);
			return asistencia;
		}
		//===================================================
		// DATOS PARA GRAFICAR CON FECHAS
		//===================================================
		public Map<String,Integer> AsistenciaXPelicula(Long idfecha1,Long idfecha2){
			List<Reserva> todasReservas = (ArrayList<Reserva>) reservaRepository.getAll(Reserva.class);
			List<Funcion> todosFuncion = busquedaFuncion.getAllEnabled();
			Map<String,Integer> asistencia = new TreeMap<String,Integer>();
			Reserva rev1 = reservaRepository.get(Reserva.class, idfecha1);
			Reserva rev2 = reservaRepository.get(Reserva.class, idfecha2);
			for (Funcion funcion : todosFuncion){
				int cantidad = 0;
				for (Reserva reserva : todasReservas)			{
//					if (reserva.getFuncion().getId() == funcion.getId() && reserva.isActivo()){
					if (reserva.getFuncion().getPelicula().getId() == funcion.getPelicula().getId() && reserva.isActivo()) {
						if( reserva.getFechaReserva().equals(rev1.getFechaReserva()) || reserva.getFechaReserva().after(rev1.getFechaReserva()) && reserva.getFechaReserva().before(rev2.getFechaReserva()) || reserva.getFechaReserva().equals(rev2.getFechaReserva())){
							cantidad += busquedaReservas.findAsientoIdByReserva(reserva.getId());
						}
						
						
					}
				}
				asistencia.put(funcion.getPelicula().getNombre().toString(), cantidad);
			}
			return asistencia;
		}
		public Map<String,Integer> CancelacionesXPelicula(Long idfecha1,Long idfecha2){
			List<Reserva> todasReservas = (ArrayList<Reserva>) reservaRepository.getAll(Reserva.class);
			List<Funcion> todosFuncion = busquedaFuncion.getAllEnabled();
			Map<String,Integer> asistencia = new TreeMap<String,Integer>();
			Reserva rev1 = reservaRepository.get(Reserva.class, idfecha1);
			Reserva rev2 = reservaRepository.get(Reserva.class, idfecha2);
			for (Funcion funcion : todosFuncion){
				int cantidad = 0;
				for (Reserva reserva : todasReservas)			{
					if (reserva.getFuncion().getId() == funcion.getId() && !reserva.isActivo())				{
						if( reserva.getFechaReserva().equals(rev1.getFechaReserva()) || reserva.getFechaReserva().after(rev1.getFechaReserva()) && reserva.getFechaReserva().before(rev2.getFechaReserva()) || reserva.getFechaReserva().equals(rev2.getFechaReserva())){
							cantidad += busquedaReservas.findAsientoIdByReserva(reserva.getId());
						}
					}
				}
				asistencia.put(funcion.getPelicula().getNombre().toString(), cantidad);
			}
			return asistencia;
		}
		public Map<String,Integer> PorcentajesAsistencia(Long idfecha1,Long idfecha2){
			List<Reserva> todasReservas = (ArrayList<Reserva>) reservaRepository.getAll(Reserva.class);
			List<Funcion> todosFuncion = busquedaFuncion.getAllEnabled();
			Map<String,Integer> asistencia = new TreeMap<String,Integer>();
			Reserva rev1 = reservaRepository.get(Reserva.class, idfecha1);
			Reserva rev2 = reservaRepository.get(Reserva.class, idfecha2);
			int total_cantidad = 0;
			int total_cancelacion = 0;
			int total_activos = 0;
			for (Funcion funcion : todosFuncion){
				for (Reserva reserva : todasReservas){
					
					
					if( reserva.getFechaReserva().equals(rev1.getFechaReserva()) || reserva.getFechaReserva().after(rev1.getFechaReserva()) && reserva.getFechaReserva().before(rev2.getFechaReserva()) || reserva.getFechaReserva().equals(rev2.getFechaReserva())){
						
						
						if (reserva.getFuncion().getId() == funcion.getId()){
							total_cantidad += busquedaReservas.findAsientoIdByReserva(reserva.getId());

							if(reserva.isActivo()){
								total_activos += busquedaReservas.findAsientoIdByReserva(reserva.getId());

							}else{
								total_cancelacion += busquedaReservas.findAsientoIdByReserva(reserva.getId());

							}
						}
						
						
					}
					
					
					
					
				}
			}
			asistencia.put("Porcentaje Asistencia", Math.round((total_activos*100)/total_cantidad));
			asistencia.put("Porcentade Cancelaciones", Math.round((total_cancelacion*100)/total_cantidad));
			return asistencia;
		}
		
		public Map<String,Integer> HorarioMasSolicitados(Long idfecha1,Long idfecha2){
			List<Reserva> todasReservas = (ArrayList<Reserva>) reservaRepository.getAll(Reserva.class);
			List<Funcion> todosFuncion = busquedaFuncion.getAllEnabled();
			Map<String,Integer> asistencia = new TreeMap<String,Integer>();
			Reserva rev1 = reservaRepository.get(Reserva.class, idfecha1);
			Reserva rev2 = reservaRepository.get(Reserva.class, idfecha2);
			for (Funcion funcion : todosFuncion){
				int cantidad = 0;
				for (Reserva reserva : todasReservas){
					if (reserva.getFuncion().getHorario().equals(funcion.getHorario())  && reserva.isActivo())				{
						if( reserva.getFechaReserva().equals(rev1.getFechaReserva()) || reserva.getFechaReserva().after(rev1.getFechaReserva()) && reserva.getFechaReserva().before(rev2.getFechaReserva()) || reserva.getFechaReserva().equals(rev2.getFechaReserva())){
							cantidad += busquedaReservas.findAsientoIdByReserva(reserva.getId());
						}
					}
				}
				asistencia.put(funcion.getHorario().getHoraInicio().toString(), cantidad);
			}
			return asistencia;
		}
		
		public Map<String,Integer> ComplejoMasSolicitado(Long idfecha1,Long idfecha2){
			List<Reserva> todasReservas = (ArrayList<Reserva>) reservaRepository.getAll(Reserva.class);
			List<Funcion> todosFuncion = busquedaFuncion.getAllEnabled();
			Map<String,Integer> asistencia = new TreeMap<String,Integer>();
			Reserva rev1 = reservaRepository.get(Reserva.class, idfecha1);
			Reserva rev2 = reservaRepository.get(Reserva.class, idfecha2);
			for (Funcion funcion : todosFuncion){
				int cantidad = 0;
				for (Reserva reserva : todasReservas)			{
					if (reserva.getFuncion().getSala().getIdComplejo() == funcion.getSala().getIdComplejo() && reserva.isActivo())				{
						if( reserva.getFechaReserva().equals(rev1.getFechaReserva()) || reserva.getFechaReserva().after(rev1.getFechaReserva()) && reserva.getFechaReserva().before(rev2.getFechaReserva()) || reserva.getFechaReserva().equals(rev2.getFechaReserva())){
							cantidad += busquedaReservas.findAsientoIdByReserva(reserva.getId());
						}
					}
				}
				Complejo complejo = complejos.get(funcion.getSala().getIdComplejo());
				asistencia.put(complejo.getNombre().toString(), cantidad);
			}
			return asistencia;
		}
		
		public Map<String,Integer> GeneroMasSolicitado(Long idfecha1,Long idfecha2){		
			List<String> generos = new ArrayList<String>();

			List<Reserva> todasReservas = (ArrayList<Reserva>) reservaRepository.getAll(Reserva.class);
			
			Reserva rev1 = reservaRepository.get(Reserva.class, idfecha1);
			Reserva rev2 = reservaRepository.get(Reserva.class, idfecha2);
			
			for(Reserva reserva : todasReservas){
				if( reserva.getFechaReserva().equals(rev1.getFechaReserva()) || reserva.getFechaReserva().after(rev1.getFechaReserva()) && reserva.getFechaReserva().before(rev2.getFechaReserva()) || reserva.getFechaReserva().equals(rev2.getFechaReserva())){
					generos.add(reserva.getCliente().getGeneroPreferido().toString());
				}
			}
			

			Map<String,Integer> asistencia = new TreeMap<String,Integer>();
			for(Reserva reserva : todasReservas){
				int cantidad = Collections.frequency(generos, reserva.getCliente().getGeneroPreferido().toString());
				asistencia.put(reserva.getCliente().getGeneroPreferido().toString(), cantidad);
			}
			return asistencia;
		}
		
		public Map<String,Integer> MenoresEnDiaSemana(Long idfecha1,Long idfecha2){		
			List<Reserva> todasReservas = (ArrayList<Reserva>) reservaRepository.getAll(Reserva.class);
			List<Funcion> todosFuncion = busquedaFuncion.getAllEnabled();
			List<ReservaPrecio> todosPrecios = detallesPrecios.getAll(ReservaPrecio.class);		
			Map<String,Integer> asistencia = new LinkedHashMap<String,Integer>();		
			Reserva rev1 = reservaRepository.get(Reserva.class, idfecha1);
			Reserva rev2 = reservaRepository.get(Reserva.class, idfecha2);
			int[] intArray = {0, 0, 0, 0, 0, 0, 0};		
			for (Funcion funcion : todosFuncion){			
				for (Reserva reserva : todasReservas){
					
					if( reserva.getFechaReserva().equals(rev1.getFechaReserva()) || reserva.getFechaReserva().after(rev1.getFechaReserva()) && reserva.getFechaReserva().before(rev2.getFechaReserva()) || reserva.getFechaReserva().equals(rev2.getFechaReserva())){
						
						if (reserva.getFuncion().getId() == funcion.getId() && reserva.isActivo()){
							Calendar c = Calendar.getInstance();
							c.setTime(reserva.getFechaReserva());
							int dia = c.get(Calendar.DAY_OF_WEEK);
							for(ReservaPrecio tipo :todosPrecios ){
								if(tipo.getTipoPrecio().equals("menor") && tipo.getIdReserva() == reserva.getId()){						
									intArray[dia-1]+=tipo.getCantidad();
								}
							} // FOR DE PRECIOS					
						} // IF DE FuNCION ID
						
				}
									
					
				} // FOR DE RESERVA
			} // FOR DE FUNCION
			asistencia.put("Domingo", intArray[0]);
			asistencia.put("Lunes", intArray[1]);
			asistencia.put("Martes", intArray[2]);
			asistencia.put("Miercoles", intArray[3]);
			asistencia.put("Jueves", intArray[4]);
			asistencia.put("Viernes", intArray[5]);
			asistencia.put("Sabado", intArray[6]);
			return asistencia;
		}
		//TODO: Este codigo esta repetido
//		public int cantidadTotalReservas(String email, Long idFuncion){//FIXME: a que se refiere esta funcion? cantidad de entradas? cantidad de reservas (Activas o inactivas)? Explicar
//			//List<Reserva> aux = busquedaReservas.findByClienteEmail(email);//FIXME: esta variable no se usa, por que?
//			int contador;
//			contador = asientos.getCantidadAsientosByFuncionMailReserva(email, idFuncion);
//			
//			return contador;
//		}
		
		/**
		 * Verifica que un cliente no se haya excedido del limite establecido por LIMITE_RESERVAS
		 * @param email del cliente
		 * @param idFuncion de la reserva
		 */
		public boolean isLimiteReservasHechasSuperado(Reserva item)
		{
			int cantidadReservasActuales = cantidadTotalReservas(item);
			
			if(cantidadReservasActuales > LIMITE_RESERVAS){
				return true;
			}
			
			return false;
		}
		
		/**
		 * Verifica que un cliente no este por excederse del limite establecido por LIMITE_RESERVAS
		 * @param email del cliente
		 * @param idFuncion de la reserva
		 * @param cantidadEntradas a reservas
		 */
		public boolean isLimiteReservasPorSuperar(Reserva item, int cantidadEntradas)
		{
			if (cantidadEntradas + cantidadTotalReservas(item) > LIMITE_RESERVAS)
				return true;
			
			return false;
		}
	String getAsientosString(Long idReserva){
		return asientos.getAsientoReservaString(idReserva);
	}

	
	/**
	 * Trae los dias de la semana que correspondan segun del dia de la fecha
	 */
	public Map<String, String> getDias()
	{
		Map<String, String> dias = getDiasTreeMap();
		Map<String, String> rta = new LinkedHashMap<String, String>();
		int hoy = new GregorianCalendar().get(Calendar.DAY_OF_WEEK);
		
		int fechames  = new GregorianCalendar().get(Calendar.DAY_OF_MONTH); 
		int fechamescontrol ;
		System.out.println("Hoy DAY_OF_WEEK " + hoy);
		
		fechamescontrol = fechames + ( 7 - hoy + 4);
		
		for (int i=1; i <= dias.size(); i++){
			
			if(fechamescontrol >= fechames){
				rta.put(String.valueOf(hoy), dias.get(Integer.toString(hoy)));
			}
			
			fechames++;
			
			if(hoy==7){
				hoy=1;
			}else{
				hoy++;
			}
			
		}
		
		return rta;
	}

	
	/**
	 * Devuelve un Map con los asientos y su status
	 * @param idFuncion id de la funcion buscada
	 * @return
	 */
	public Map<Long, Integer> getAsientosbyFuncion(Long idFuncion)
	{
		ArrayList<Asiento> asientosFuncion = new ArrayList<Asiento>();
		Map<Long, Integer> rta = new TreeMap<Long, Integer>();
		
		
		//agrego los asientos de esa funcion
		for (Reserva registro : busquedaReservas.getAllEnabled())
		{
			if (registro.getFuncion().getId() == idFuncion)
				asientosFuncion.addAll(registro.getAsientos());
		}
		
		//lleno el map
		for (Asiento registro : asientoRepository.getAll(Asiento.class))
		{
			if (asientosFuncion.contains(registro))
				rta.put(registro.getId(), 1);//asiento ocupado
			else
				rta.put(registro.getId(), 0);//asiento libre
		}
		
		return rta;
	}
	
	public boolean isAsientoDisponible(Long idAsiento, Long idFuncion, String fecha)
	{
		/*for (Reserva registro : busquedaReservas.findActiveByFuncion(idFuncion))
		{
			for (Asiento asiento : registro.getAsientos())
					if (asiento.getId().equals(idAsiento))
						return true;
		}*/
		
		Object[] asientosId = asientos.findAsientoIdByFuncion(idFuncion,fecha);
		
		//System.out.println("asientosId len " + asientosId.length);
		
		if (asientosId.length < 1)
			return false;
				
		for (Object id : asientosId)
		{
			if (Integer.parseInt(id.toString()) == idAsiento )
				return true;
		}
		return false;
	}


	public int cantidadTotalReservas(Reserva item){
		String fecha = new SimpleDateFormat("yyyy-MM-dd").format(item.getFechaReserva());
		int contador;
		contador = busquedaReservas.findCantidadAsientos(item.getCliente().getEmail(), item.getFuncion().getId(), fecha);
		
		return contador;
	}
	
}