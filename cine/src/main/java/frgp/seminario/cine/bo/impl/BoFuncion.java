package frgp.seminario.cine.bo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import frgp.seminario.cine.bo.BusinessObject;
import frgp.seminario.cine.findItem.impl.AsientoFindItem;
import frgp.seminario.cine.findItem.impl.FuncionFindItem;
import frgp.seminario.cine.forms.ComplejoForm;
import frgp.seminario.cine.forms.FuncionForm;
import frgp.seminario.cine.forms.PeliculaForm;
import frgp.seminario.cine.forms.SalaForm;
import frgp.seminario.cine.model.Funcion;
import frgp.seminario.cine.model.Horario;
import frgp.seminario.cine.repository.Repository;
import frgp.seminario.cine.repository.impl.HorarioRepository;
import frgp.seminario.cine.utils.FechaUtils;

//Funciones pertenecientes a la logica de negocios
@Service("BoFuncion") //agrego el nombre del bean, para que al momento de llamar al Autowired pueda aclarar cual quiero
public class BoFuncion implements BusinessObject<Funcion, FuncionForm> {
	@Autowired
	@Qualifier("FuncionRepository") //aclaro cual es el bean a inyectar
	Repository<Funcion> funcionRepository;
	
	@Autowired
	@Qualifier("HorarioRepository") //aclaro cual es el bean a inyectar
	HorarioRepository horarioRepository;
	
	@Autowired
	@Qualifier("FuncionFindItem") //aclaro cual es el bean a inyectar
	FuncionFindItem busquedaFuncion;
	
	@Autowired
	@Qualifier("AsientoFindItem") //aclaro cual es el bean a inyectar
	AsientoFindItem busquedaAsiento;
	
	@Autowired
	@Qualifier("BoHorario") //aclaro cual es el bean a inyectar
	BoHorario horarios;
	
	@Autowired
	@Qualifier("BoSala") //aclaro cual es el bean a inyectar
	BoSala salas;
	
	@Autowired
	@Qualifier("BoPelicula") //aclaro cual es el bean a inyectar
	BoPelicula peliculas;
	
	@Autowired
	@Qualifier("BoComplejo") //aclaro cual es el bean a inyectar
	BoComplejo complejos;
	
	@Autowired
	FechaUtils fechaUtils;
	
	/** 
	 ** Busca un registro en específico.
	 ** @param id el id del objeto buscado
	 ** @return el registro Funcion buscado
	 **/
	@Override
	public Funcion get(Object id) {
		return funcionRepository.get(Funcion.class, id);
	}
	
	/**
	 ** Persiste un registro en la base de datos.
	 ** @param registro El objeto a persistir.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean guardar(Funcion registro) {
		if (!verificar(registro))
			return false;
		
		return funcionRepository.save(registro);
	}

	/**
	 ** Guarda los cambios en un registro en la base de datos.
	 ** @param registro El objeto a guardar.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean modificar(Funcion registro) {
		if (!(registro instanceof frgp.seminario.cine.model.Funcion))
			return false;
		
		if (registro.getId() == null)
			return false;
		
		return funcionRepository.merge(registro);
	}

	/**
	 ** Desactiva un registro en la base de datos.
	 ** @param registro El objeto a desactivar.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean desactivar(Funcion registro) {
		//verificar que no haya reservas para esta pelicula
		/*if (boReserva.hasEnabledReservationByMovie(registro.getId()))
					return false;*/
		
		if (registro.isActivo())
			registro.setActivo(false);
		
		return funcionRepository.merge(registro);
	}
	
	/**
	 ** Activa un registro en la base de datos.
	 ** @param registro El objeto a activar.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	public boolean activar(Funcion registro) {		
		if (!registro.isActivo())
			registro.setActivo(true);
		
		return funcionRepository.merge(registro);
	}

	/**
	 ** Recupera todos los registros de la clase Funcion
	 ** @return un ArrayList con todos los registros
	 **/
	@Override
	public List<Funcion> listarTodos() {
		return funcionRepository.getAll(Funcion.class);
	}
	
	/**
	 ** Recupera todos los registros de la clase Complejo
	 ** @return un ArrayList con todos los registros
	 **/
	public ArrayList<ComplejoForm> listarTodosComplejos(){
		return complejos.listarTodosActivosForm();
	}
	
	/**
	 ** Recupera todos los registros de la clase Pelicula
	 ** @return un ArrayList con todos los registros
	 **/
	public ArrayList<PeliculaForm> listarTodasPeliculas(){
		return peliculas.listarTodosActivosForm();
	}
	
	/**
	 ** Recupera todos los registros de la clase Sala
	 ** @return un ArrayList con todos los registros
	 **/
	public ArrayList<SalaForm> listarTodasSalas(){
		return salas.listarTodasActivasForm();
	}
	
	/**
	 ** Recupera todos los registros de la clase Horario
	 ** @return un ArrayList con todos los registros
	 **/
	public ArrayList<Horario> listarTodosHorarios(){
		return horarios.listarTodos();
	}
	
	/**
	 * Busca los horarios disponibles segun la duracion de la pelicula
	 * @param pelicula id de la pelicula por la que se busca
	 * @param idComplejo id del complejo en el que se busca
	 * @return un HashMap con la forma "Horario.id, Horario.horaInicio - Horario.horaFin"
	 */
	public HashMap<String, String> getHorariosDisponiblesByComplejo(Long pelicula, Long idComplejo)
	{
		ArrayList<Horario> horariosSugeridos = horarios.findByDiferencia(peliculas.get(pelicula).getDuracion());
		ArrayList<Funcion> funcionesActivasComplejo = busquedaFuncion.findActiveByComplejo(idComplejo);
		HashMap<String, String> respuesta = new HashMap<String, String>();
		
		//se saca de la lista de sugeridos los horarios que no estan disponibles
		for (Funcion item : funcionesActivasComplejo)
		{
			if (horariosSugeridos.contains(item.getHorario()))
				horariosSugeridos.remove(item.getHorario());
		}
		
		//TODO: Queda verificar que no se asigne la misma función para varias películas.
		
		//cargo los horarios restantes en el hashmap
		for (Horario item : horariosSugeridos)
			respuesta.put(item.getId().toString(), item.getHoraInicio() + " - " + item.getHoraFin()+ " (" + item.getDuracion() + " )");
		
		if (respuesta.isEmpty())
			return null;
		
		return respuesta;
	}
	
	/**
	 * Verifica que el registro cumpla con las caracteristicas necesarias
	 * @return true si el registro esta en condiciones de ser guardardo, false si no lo esta
	 **/
	@Override
	public boolean verificar(Funcion registro) {
		if (!(registro instanceof frgp.seminario.cine.model.Funcion))
			return false;
		
		if (busquedaFuncion.getIdByObject(registro) != 0)//si el registro ya existe en la base de datos
			return false;
		
		return true;
	}
	
	/**
	 * Convierte un formulario en un registro Funcion
	 * @param formulario el formulario submiteado
	 * @return un objeto Funcion
	 */
	@Override
	public Funcion formToEntity(FuncionForm formulario)
	{
		return new Funcion(salas.get(formulario.getsalas()), 
							peliculas.get(formulario.getPeliculas()), 
							horarioRepository.get(Horario.class, formulario.getHorarios()));
	}
	
	public FuncionForm entityToForm(Funcion registro)
	{
		FuncionForm formulario = new FuncionForm();
		
		formulario.setId(registro.getId());
		formulario.setNombreComplejo(complejos.get(registro.getSala().getIdComplejo()).getNombre());
		formulario.setComplejos(registro.getSala().getIdComplejo());
		formulario.setNombreSala(Integer.toString(registro.getSala().getNumeroSala()));
		formulario.setsalas(registro.getSala().getId());
		formulario.setPeliculas(registro.getPelicula().getId());
		formulario.setNombrePelicula(registro.getPelicula().getNombre());
		formulario.setHorarios(registro.getHorario().getId());
		formulario.setInicio(registro.getHorario().getHoraInicio().toString());//TODO: revisar
		formulario.setFin(registro.getHorario().getHoraFin().toString());//TODO: revisar
		formulario.setActivo(registro.isActivo());
		
		return formulario;
	}

	/**
	 * Busca las funciones existentes para la pelicula y complejo indicados
	 * @param pelicula id de la pelicula
	 * @param complejo id del complejo
	 * @return un HashMap con la forma "Horario.id, Horario.horaInicio"
	 */
	public HashMap<String, String> getFuncionesDisponibles(Long pelicula, Long complejo) {
		ArrayList<Funcion> funcionesActivasComplejo = busquedaFuncion.findActiveByComplejo(complejo);
		HashMap<String, String> respuesta = new HashMap<String, String>();
		int asientosReservadosFuncion;
		
		//cargo los horarios restantes en el hashmap
		for (Funcion item : funcionesActivasComplejo)
		{
			asientosReservadosFuncion = busquedaAsiento.getCantidadAsientoByFuncionStatusReserva(item.getId(), true);
			
			//si la pelicula de la funcion coincide y la sala no está cubierta en toda su capacidad
			if (item.getPelicula().getId() == pelicula &&  !salas.isCubierta(asientosReservadosFuncion)) 
				respuesta.put(item.getId().toString(), item.getHorario().getHoraInicio().toString());
		}
		
		if (respuesta.isEmpty())
			return null;
		
		return respuesta;
	}

	/**
	 ** Recupera todos los registros de la clase Funcion como FuncionForm
	 ** @return un List con todos los registros
	 **/
	public List<FuncionForm> listarTodosForm() {
		List<FuncionForm> respuesta = new ArrayList<FuncionForm>();
		
		for (Funcion registro : listarTodos())
		{
			respuesta.add(entityToForm(registro));
		}
		return respuesta;
	}
}
