package frgp.seminario.cine.bo.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import frgp.seminario.cine.bo.BusinessObject;
import frgp.seminario.cine.bo.impl.BoComplejo;
import frgp.seminario.cine.findItem.impl.AsientoFindItem;
import frgp.seminario.cine.findItem.impl.ReservaFindItem;
import frgp.seminario.cine.forms.ReservaForm;
import frgp.seminario.cine.model.Asiento;
import frgp.seminario.cine.model.Entrada;
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
		formulario.setAsientos("");//TODO: arreglar cuando se puedan recuperar los asientos
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
				
		for(int i=0; i<check.length;i++)
		{
			if (check[i] != null && i <= cantidad)
			{
				char fila = check[i].charAt(0);
				char columna = check[i].charAt(2);
				
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
	
	public ArrayList<Reserva> listarHorariosNoRepetidos() {

		List<Reserva> rev = (ArrayList<Reserva>) reservaRepository.getAll(Reserva.class);
		
		for(int i=0;i<rev.size()-1;i++){
			for(int j=i+1;j<rev.size();j++){
				if(rev.get(i).getFechaCreacion() ==rev.get(j).getFechaCreacion())
					rev.remove(i);
			}
		}
		return (ArrayList<Reserva>) rev;
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
	
	public boolean isAsientoDisponible(Long idAsiento, Long idFuncion)
	{
		/*for (Reserva registro : busquedaReservas.findActiveByFuncion(idFuncion))
		{
			for (Asiento asiento : registro.getAsientos())
					if (asiento.getId().equals(idAsiento))
						return true;
		}*/
		
		Object[] asientosId = asientos.findAsientoIdByFuncion(idFuncion);
		
		//System.out.println("asientosId len " + asientosId.length);
		
		if (asientosId.length < 1)
			return false;
				
		for (Object id : asientosId)
		{
			if (Integer.parseInt(id.toString()) == idAsiento)
				return true;
		}
		return false;
	}
	
	public Map<String,Integer> AsistenciaXPelicula(){
		List<Reserva> rev = (ArrayList<Reserva>) reservaRepository.getAll(Reserva.class);
		
		Map<String,Integer> asistencia = new TreeMap<String,Integer>();
		
		for(Reserva r : rev){
			
			int cantidad = 0;
			for(Reserva r2: rev){
				if(r.getFuncion().getPelicula().getNombre() == r2.getFuncion().getPelicula().getNombre() && r.isActivo() == true){
					cantidad = cantidad + r2.getAsientos().size();
				}
			}
			asistencia.put(r.getFuncion().getPelicula().getNombre().toString(), cantidad);
		}
		return asistencia;
	}
	
	public Map<String,Integer> AsistenciaXPelicula(Date fecha1, Date fecha2){
		List<Reserva> rev = (ArrayList<Reserva>) reservaRepository.getAll(Reserva.class);
		
		Map<String,Integer> asistencia = new TreeMap<String,Integer>();
		
		for(Reserva r : rev){
			
			int cantidad = 0;
			for(Reserva r2: rev){
				
				if(r.getFuncion().getPelicula().getNombre() == r2.getFuncion().getPelicula().getNombre() && r.isActivo() == true){
					
					if(fecha1.after(r2.getFechaReserva()) && fecha2.before(r2.getFechaReserva())){
						cantidad = cantidad + r2.getAsientos().size();	
					}				
					
				}
				
			}
			asistencia.put(r.getFuncion().getPelicula().getNombre().toString(), cantidad);
		}
		return asistencia;
	}

	public Map<String,Integer> CancelacionesXPelicula(){
		List<Reserva> rev = (ArrayList<Reserva>) reservaRepository.getAll(Reserva.class);
		
		Map<String,Integer> asistencia = new TreeMap<String,Integer>();
		
		for(Reserva r : rev){
			
			int cantidad = 0;
			for(Reserva r2: rev){
				if(r.getFuncion().getPelicula().getNombre() == r2.getFuncion().getPelicula().getNombre() && r.isActivo() == false){
					cantidad = cantidad + r2.getAsientos().size();
				}
			}
			asistencia.put(r.getFuncion().getPelicula().getNombre().toString(), cantidad);
		}
		return asistencia;
	}
	
	public Map<String,Integer> CancelacionesXPelicula(Date fecha1, Date fecha2){
		List<Reserva> rev = (ArrayList<Reserva>) reservaRepository.getAll(Reserva.class);
		
		Map<String,Integer> asistencia = new TreeMap<String,Integer>();
		
		for(Reserva r : rev){
			
			int cantidad = 0;
			for(Reserva r2: rev){
				if(r.getFuncion().getPelicula().getNombre() == r2.getFuncion().getPelicula().getNombre() && r.isActivo() == false){
					if(fecha1.after(r2.getFechaReserva()) && fecha2.before(r2.getFechaReserva())){
						cantidad = cantidad + r2.getAsientos().size();
					}					
				}
			}
			asistencia.put(r.getFuncion().getPelicula().getNombre().toString(), cantidad);
		}
		return asistencia;
	}

	public Map<String,Float> PorcentajesAsistencia(){
		List<Reserva> rev = (ArrayList<Reserva>) reservaRepository.getAll(Reserva.class);
		
		Map<String,Float> asistencia = new TreeMap<String,Float>();
		
		int cancelaciones = 0;
		int activos = 0;
		int total = 0;
		
		for(Reserva r : rev){
			
			total = total + r.getAsientos().size();
			
			if(r.isActivo() == true){
				activos=activos + r.getAsientos().size();
			}else{
				cancelaciones = cancelaciones + r.getAsientos().size();
			}
			
			
		}
		asistencia.put("Porcentaje Asistencia", (float) ((total*100)/activos));
		asistencia.put("Porcentade Cancelaciones",(float) ((total*100)/cancelaciones));

		return asistencia;
	}
	
	public Map<String,Float> PorcentajesAsistencia(Date fecha1, Date fecha2){
		List<Reserva> rev = (ArrayList<Reserva>) reservaRepository.getAll(Reserva.class);
		
		Map<String,Float> asistencia = new TreeMap<String,Float>();
		
		int cancelaciones = 0;
		int activos = 0;
		int total = 0;
		
		for(Reserva r : rev){
			
			
			
			if(fecha1.after(r.getFechaReserva()) && fecha2.before(r.getFechaReserva())){
				
				total = total + r.getAsientos().size();
				
				if(r.isActivo() == true){
					activos=activos + r.getAsientos().size();
				}else{
					cancelaciones = cancelaciones + r.getAsientos().size();
				}
				
				
			}		
			
		}
		asistencia.put("Porcentaje Asistencia", (float) ((total*100)/activos));
		asistencia.put("Porcentade Cancelaciones",(float) ((total*100)/cancelaciones));

		return asistencia;
	}

	public Map<String,Integer> HorarioMasSolicitados(){
		List<Reserva> rev = (ArrayList<Reserva>) reservaRepository.getAll(Reserva.class);
		
		Map<String,Integer> asistencia = new TreeMap<String,Integer>();
		
		for(Reserva r : rev){
			
			int cantidad = 0;
			for(Reserva r2: rev){
				if(r.getFuncion().getHorario() == r2.getFuncion().getHorario() && r.isActivo() == true){
					cantidad = cantidad + r2.getAsientos().size();
				}
			}
			asistencia.put(r.getFuncion().getHorario().toString(), cantidad);
		}
		return asistencia;
	}
	public Map<String,Integer> HorarioMasSolicitados(Date fecha1, Date fecha2){
		List<Reserva> rev = (ArrayList<Reserva>) reservaRepository.getAll(Reserva.class);
		
		Map<String,Integer> asistencia = new TreeMap<String,Integer>();
		
		for(Reserva r : rev){
			
			int cantidad = 0;
			for(Reserva r2: rev){
				if(r.getFuncion().getHorario() == r2.getFuncion().getHorario() && r.isActivo() == true){
					
					if(fecha1.after(r2.getFechaReserva()) && fecha2.before(r2.getFechaReserva())){
						cantidad = cantidad + r2.getAsientos().size();
					}	
					
					
				}
			}
			asistencia.put(r.getFuncion().getHorario().toString(), cantidad);
		}
		return asistencia;
	}

	public Map<String,Integer> ComplejoMasSolicitado(){
		List<Reserva> rev = (ArrayList<Reserva>) reservaRepository.getAll(Reserva.class);
		
		Map<String,Integer> asistencia = new TreeMap<String,Integer>();
		
		for(Reserva r : rev){
			
			int cantidad = 0;
			for(Reserva r2: rev){
				if(r.getFuncion().getSala().getIdComplejo() == r2.getFuncion().getSala().getIdComplejo() && r.isActivo() == true){
					cantidad = cantidad + r2.getAsientos().size();
				}
			}
			asistencia.put(r.getFuncion().getSala().getIdComplejo().toString(), cantidad);
		}
		return asistencia;
	}
	
	public Map<String,Integer> ComplejoMasSolicitado(Date fecha1, Date fecha2){
		List<Reserva> rev = (ArrayList<Reserva>) reservaRepository.getAll(Reserva.class);
		
		Map<String,Integer> asistencia = new TreeMap<String,Integer>();
		
		for(Reserva r : rev){
			
			int cantidad = 0;
			for(Reserva r2: rev){
				if(r.getFuncion().getSala().getIdComplejo() == r2.getFuncion().getSala().getIdComplejo() && r.isActivo() == true){
					
					if(fecha1.after(r2.getFechaReserva()) && fecha2.before(r2.getFechaReserva())){
						cantidad = cantidad + r2.getAsientos().size();
					}	
					
				}
			}
			asistencia.put(r.getFuncion().getSala().getIdComplejo().toString(), cantidad);
		}
		return asistencia;
	}

	public Map<String,Integer> GeneroMasSolicitado(){
		List<Reserva> rev = (ArrayList<Reserva>) reservaRepository.getAll(Reserva.class);
		
		Map<String,Integer> asistencia = new TreeMap<String,Integer>();
		
		for(Reserva r : rev){
			
			int cantidad = 0;
			for(Reserva r2: rev){
				if(r.getCliente().getGeneroPreferido() == r2.getCliente().getGeneroPreferido() && r.isActivo() == true){
					cantidad = cantidad + 1;
				}
			}
			asistencia.put(r.getCliente().getGeneroPreferido().toString(), cantidad);
		}
		return asistencia;
	}
	public Map<String,Integer> GeneroMasSolicitado(Date fecha1, Date fecha2){
		List<Reserva> rev = (ArrayList<Reserva>) reservaRepository.getAll(Reserva.class);
		
		Map<String,Integer> asistencia = new TreeMap<String,Integer>();
		
		for(Reserva r : rev){
			
			int cantidad = 0;
			for(Reserva r2: rev){
				if(r.getCliente().getGeneroPreferido() == r2.getCliente().getGeneroPreferido() && r.isActivo() == true){
					
					if(fecha1.after(r2.getFechaReserva()) && fecha2.before(r2.getFechaReserva())){
						cantidad = cantidad + 1;
					}	
				}
			}
			asistencia.put(r.getCliente().getGeneroPreferido().toString(), cantidad);
		}
		
		return asistencia;
	}

	public Map<String,Integer> MenoresEnDiaSemana(){
		List<Reserva> rev = (ArrayList<Reserva>) reservaRepository.getAll(Reserva.class);
		List<Entrada> ent = (ArrayList<Entrada>) entradaRepository.getAll(Entrada.class);
		
		Map<String,Integer> asistencia = new TreeMap<String,Integer>();
		
		// (1) create a java int array
		int[] intArray = {0, 0, 0, 0, 0, 0, 0};
		
		for(Reserva r : rev){
			for(Entrada e : ent){
				if(r.getId() == e.getReserva().getId()){
					if(e.getTipoEntrada()=="Menor"){
						
						Calendar c = Calendar.getInstance();
						c.setTime(r.getFechaReserva());
						int dia = c.get(Calendar.DAY_OF_WEEK);
						
						intArray[dia]=intArray[dia]+1;
					}
				}
			}
		}
		asistencia.put("Domingo", intArray[0]);
		asistencia.put("Lunes", intArray[1]);
		asistencia.put("Martes", intArray[2]);
		asistencia.put("Miercoles", intArray[3]);
		asistencia.put("Jueves", intArray[4]);
		asistencia.put("Viernes", intArray[5]);
		asistencia.put("Sabado", intArray[6]);
		
		return asistencia;
	}
	public Map<String,Integer> MenoresEnDiaSemana(Date fecha1, Date fecha2){
		List<Reserva> rev = (ArrayList<Reserva>) reservaRepository.getAll(Reserva.class);
		List<Entrada> ent = (ArrayList<Entrada>) entradaRepository.getAll(Entrada.class);
		
		Map<String,Integer> asistencia = new TreeMap<String,Integer>();
		
		// (1) create a java int array
		int[] intArray = {0, 0, 0, 0, 0, 0, 0};
		
		for(Reserva r : rev){
			for(Entrada e : ent){
				if(r.getId() == e.getReserva().getId()){
					if(e.getTipoEntrada()=="Menor"){
						if(fecha1.after(r.getFechaReserva()) && fecha2.before(r.getFechaReserva())){
						Calendar c = Calendar.getInstance();
						c.setTime(r.getFechaReserva());
						int dia = c.get(Calendar.DAY_OF_WEEK);
						
						intArray[dia]=intArray[dia]+1;
						
						}	
					}
				}
			}
		}
		asistencia.put("Domingo", intArray[0]);
		asistencia.put("Lunes", intArray[1]);
		asistencia.put("Martes", intArray[2]);
		asistencia.put("Miercoles", intArray[3]);
		asistencia.put("Jueves", intArray[4]);
		asistencia.put("Viernes", intArray[5]);
		asistencia.put("Sabado", intArray[6]);
		
		return asistencia;
	}
}