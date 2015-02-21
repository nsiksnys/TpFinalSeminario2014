package frgp.seminario.cine.findItem.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import frgp.seminario.cine.dataAccess.DataAccess;
import frgp.seminario.cine.model.Funcion;

@Service("FuncionFindItem")//agrego el nombre del bean, para que al momento de llamar al Autowired pueda aclarar cual quiero
public class FuncionFindItem /* implements FindItemWithFlag<Funcion> */ {
	@Autowired
	DataAccess dataAccess;
	
	/**
	 * Busca un registro con las mismas caracteristicas en la base de datos
	 * @param item Funcion que buscamos en la base
	 * @return id del registro, 0 si no se encontro ninguno
	 **/
	@SuppressWarnings("unchecked")
	public Long getIdByObject(Funcion item){
		ArrayList<Funcion> todos = (ArrayList<Funcion>) dataAccess.getAll(Funcion.class);
		
		for (Funcion registro : todos) {
			if (registro.equals(item))
				return registro.getId();
		}
		return (long) 0;
	}
	
	/**
	 * Busca las funciones con el mismo horario.
	 * @param idHorario id del horario buscado
	 * @return ArrayList con todos las funciones
	 **/
	@SuppressWarnings("unchecked")
	public ArrayList<Funcion> findByHorario(Long idHorario){
		ArrayList<Funcion> todos = (ArrayList<Funcion>) dataAccess.getAll(Funcion.class);
		ArrayList<Funcion> rta = new ArrayList<Funcion>();
		
		for (Funcion item : todos) {
			if (item.getHorario().getId() == idHorario)
				rta.add(item);
		}
		return rta;
	}
	
	/**
	 * Busca las funciones con el mismo horario.
	 * @param idHorario id del horario buscado
	 * @return true existen resultados, false si no se encontro ninguno
	 **/
	public boolean findByHorarioBoolean(Long idHorario){
		if (findByHorario(idHorario).isEmpty())
			return false;
		return true;
	}
	
	/**
	 * Busca las funciones de la misma pelicula.
	 * @param idPelicula id de la pelicula buscada
	 * @return ArrayList con todos las funciones
	 **/
	@SuppressWarnings("unchecked")
	public ArrayList<Funcion> findByPelicula(Long idPelicula){
		ArrayList<Funcion> todos = (ArrayList<Funcion>) dataAccess.getAll(Funcion.class);
		ArrayList<Funcion> rta = new ArrayList<Funcion>();
		
		for (Funcion item : todos) {
			if (item.getPelicula().getId() == idPelicula)
				rta.add(item);
		}
		return rta;
	}
	
	/**
	 * Busca las funciones con el mismo horario.
	 * @param idPelicula id de la pelicula buscada
	 * @return true existen resultados, false si no se encontro ninguno
	 **/
	public boolean findByPeliculaBoolean(Long idPelicula){
		if (findByPelicula(idPelicula).isEmpty())
			return false;
		return true;
	}
		
	/**
	 * Busca las funciones de la misma sala del mismo complejo.
	 * @param idComplejo id del complejo buscado
	 * @param idSala id de la sala buscada
	 * @return ArrayList con todos las funciones
	 **/
	@SuppressWarnings("unchecked")
	public ArrayList<Funcion> findByComplejoSala(Long idComplejo, Long idSala){
		ArrayList<Funcion> todos = (ArrayList<Funcion>) dataAccess.getAll(Funcion.class);
		ArrayList<Funcion> rta = new ArrayList<Funcion>();
		
		for (Funcion item : todos) {
			if (item.getSala().getIdComplejo() == idComplejo && item.getSala().getId() == idSala)
				rta.add(item);
		}
		return rta;
	}
	
	/**
	 * Busca las funciones de la misma sala del mismo complejo.
	 * @param idComplejo id del complejo buscado
	 * @param idSala id de la sala buscada
	 * @return true existen resultados, false si no se encontro ninguno
	 **/
	public boolean findByComplejoSalaBoolean(Long idComplejo, Long idSala){
		if (findByComplejoSala(idComplejo, idSala).isEmpty())
			return false;
		return true;
	}
	
	/**
	 * Busca las funciones del mismo complejo.
	 * @param idComplejo id del complejo buscado
	 * @return ArrayList con todos las funciones
	 **/
	@SuppressWarnings("unchecked")
	public ArrayList<Funcion> findByComplejo(Long idComplejo){
		ArrayList<Funcion> todos = (ArrayList<Funcion>) dataAccess.getAll(Funcion.class);
		ArrayList<Funcion> rta = new ArrayList<Funcion>();
		
		for (Funcion item : todos) {
			if (item.getSala().getIdComplejo() == idComplejo)
				rta.add(item);
		}
		return rta;
	}
	
	/**
	 * Busca las funciones del mismo complejo.
	 * @param idComplejo id del complejo buscado
	 * @return true existen resultados, false si no se encontro ninguno
	 **/
	public boolean findByComplejoBoolean(Long idComplejo){
		if (findByComplejo(idComplejo).isEmpty())
			return false;
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public boolean findByIdBoolean(int id){
		ArrayList<Funcion> todos = (ArrayList<Funcion>) dataAccess.getAll(Funcion.class);
		
		for (Funcion item : todos) {
			if (item.getId() == id)
				return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	//@Override
	public ArrayList<Funcion> getAllByFlag(boolean flag) {
		ArrayList<Funcion> todos = (ArrayList<Funcion>) dataAccess.getAll(Funcion.class);
		ArrayList<Funcion> rta = new ArrayList<Funcion>();
		
		for (Funcion item : todos) {
			if (item.isActivo() == flag)
				rta.add(item);
		}
		return rta;
	}
	
	/**
	 * Devuelve todos las peliculas activas
	 * @return un ArrayList con los registros
	 **/
	public ArrayList<Funcion> getAllEnabled(){
		return getAllByFlag(true);
	}
	
	/**
	 * Devuelve todos las peliculas desactivadas
	 * @return un ArrayList con los registros
	 **/
	public ArrayList<Funcion> getAllDisabled(){
		return getAllByFlag(false);
	}
	
	/**
	 * Busca las funciones activas con el mismo horario.
	 * @param idHorario id del horario buscado
	 * @return ArrayList con todos las funciones
	 **/
	public ArrayList<Funcion> findActiveByHorario(Long idHorario){
		ArrayList<Funcion> todos = getAllEnabled();
		ArrayList<Funcion> rta = new ArrayList<Funcion>();
		
		for (Funcion item : todos) {
			if (item.getHorario().getId() == idHorario)
				rta.add(item);
		}
		return rta;
	}
	
	/**
	 * Busca las funciones activas con el mismo horario.
	 * @param idHorario id del horario buscado
	 * @return true existen resultados, false si no se encontro ninguno
	 **/
	public boolean findActiveByHorarioBoolean(Long idHorario){
		if (findActiveByHorario(idHorario).isEmpty())
			return false;
		return true;
	}
	
	/**
	 * Busca las funciones activas de la misma pelicula.
	 * @param idPelicula id de la pelicula buscada
	 * @return ArrayList con todos las funciones
	 **/
	public ArrayList<Funcion> findActiveByPelicula(Long idPelicula){
		ArrayList<Funcion> todos = getAllEnabled();
		ArrayList<Funcion> rta = new ArrayList<Funcion>();
		
		for (Funcion item : todos) {
			if (item.getPelicula().getId() == idPelicula)
				rta.add(item);
		}
		return rta;
	}
	
	/**
	 * Busca las funciones activas con el mismo horario.
	 * @param idPelicula id de la pelicula buscada
	 * @return true existen resultados, false si no se encontro ninguno
	 **/
	public boolean findActiveByPeliculaBoolean(Long idPelicula){
		if (findActiveByPelicula(idPelicula).isEmpty())
			return false;
		return true;
	}
		
	/**
	 * Busca las funciones activas de la misma sala del mismo complejo.
	 * @param idComplejo id del complejo buscado
	 * @param idSala id de la sala buscada
	 * @return ArrayList con todos las funciones
	 **/
	public ArrayList<Funcion> findActiveByComplejoSala(Long idComplejo, Long idSala){
		ArrayList<Funcion> todos = getAllEnabled();
		ArrayList<Funcion> rta = new ArrayList<Funcion>();
		
		for (Funcion item : todos) {
			if (item.getSala().getIdComplejo() == idComplejo && item.getSala().getId() == idSala)
				rta.add(item);
		}
		return rta;
	}
	
	/**
	 * Busca las funciones activas de la misma sala del mismo complejo.
	 * @param idComplejo id del complejo buscado
	 * @param idSala id de la sala buscada
	 * @return true existen resultados, false si no se encontro ninguno
	 **/
	public boolean findActiveByComplejoSalaBoolean(Long idComplejo, Long idSala){
		if (findActiveByComplejoSala(idComplejo, idSala).isEmpty())
			return false;
		return true;
	}
	
	/**
	 * Busca las funciones activas del mismo complejo.
	 * @param idComplejo id del complejo buscado
	 * @return ArrayList con todos las funciones
	 **/
	public ArrayList<Funcion> findActiveByComplejo(Long idComplejo){
		ArrayList<Funcion> todos = getAllEnabled();
		ArrayList<Funcion> rta = new ArrayList<Funcion>();
		
		for (Funcion item : todos) {
			if (item.getSala().getIdComplejo() == idComplejo)
				rta.add(item);
		}
		return rta;
	}
	
	/**
	 * Busca las funciones activas del mismo complejo.
	 * @param idComplejo id del complejo buscado
	 * @return true existen resultados, false si no se encontro ninguno
	 **/
	public boolean findActiveByComplejoBoolean(Long idComplejo){
		if (findActiveByComplejo(idComplejo).isEmpty())
			return false;
		return true;
	}

	/**
	 * Busca las funciones activas del mismo complejo y la misma pelicula.
	 * @param idComplejo id del complejo buscado
	 * @param idPelicula id de la pelicual buscada
	 * @return ArrayList con todos las funciones
	 **/
	public ArrayList<Funcion> findActiveByComplejoPelicula(Long idComplejo,	Long idPelicula) {
		ArrayList<Funcion> todos = findActiveByComplejo(idComplejo);
		ArrayList<Funcion> rta = new ArrayList<Funcion>();
		
		for (Funcion item : todos) {
			if (item.getPelicula().getId() == idPelicula)
				rta.add(item);
		}
		return rta;
	}
	
	/**INGRESADA AHORA 
	 * busca las funciones activas del mismo complejo, misma pelicula.
	 * del mismo horario y misma sala.
	 * @param idComplejo id del complejo buscado
	 * @param idPelicula id de la pelicula buscada
	 * @return ArrayList con todos las funciones
	 */
	public boolean FuncionRepetida(Long idcomplejo, Long sala_id, Long horario_id, Long idPelicula) {
		if(findActiveByComplejoSalaBoolean(idcomplejo, sala_id) == true)
		{
			if(findActiveByPeliculaBoolean(idPelicula)== true && findByHorarioBoolean(horario_id)==true)
			{
				return true;
			}
		}
		return false;
	}
	

	/**
	 * Busca las funciones activas del mismo complejo y la misma pelicula.
	 * @param idComplejo id del complejo buscado
	 * @param idPelicula id de la pelicual buscada
	 * @return true existen resultados, false si no se encontro ninguno
	 **/
	public boolean findActiveByComplejoPeliculaBoolean(Long idComplejo, Long idPelicula){
		if (findActiveByComplejoPelicula(idComplejo, idPelicula).isEmpty())
			return false;
		return true;
	}
	/**
	 * Busca si hay diferentes peliculas para la misma sala.
	 * @param idComplejo id del complejo buscado
	 * @param idPelicula id de la pelicual buscada
	 * @return true existen resultados, false si no se encontro ninguno
	 **/
	public boolean findSalaOcupadaBoolean(Long idcomplejo, Long sala_id, Long idPelicula){
		
		if(findByComplejoSalaBoolean(idcomplejo,sala_id)){
			if(findPeliculaSalaBoolean( idcomplejo,  sala_id,  idPelicula)){
				return false;
			}else{
				return true;
			}
		}
		return false;
	}
	public boolean findPeliculaSalaBoolean(Long idcomplejo, Long sala_id, Long idPelicula){
		ArrayList<Funcion> lista = findByComplejoSala(idcomplejo,sala_id);
		for (Funcion item : lista) {
			if (item.getPelicula().getId() == idPelicula)
				return true;
		}
		return false;
	}
}