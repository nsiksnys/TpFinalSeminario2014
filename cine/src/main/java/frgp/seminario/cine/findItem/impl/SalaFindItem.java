package frgp.seminario.cine.findItem.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import frgp.seminario.cine.dataAccess.DataAccess;
import frgp.seminario.cine.model.Sala;

@Service("SalaFindItem")//agrego el nombre del bean, para que al momento de llamar al Autowired pueda aclarar cual quiero
public class SalaFindItem {
	@Autowired
	DataAccess dataAccess;
	
	/**
	 * Busca un registro con las mismas caracteristicas en la base de datos
	 * @param item Sala que buscamos en la base
	 * @return id del registro, 0 si no se encontro ninguno
	 **/
	@SuppressWarnings("unchecked")
	public Long getIdByObject(Sala item){
		ArrayList<Sala> todos = (ArrayList<Sala>) dataAccess.getAll(Sala.class);
		
		for (Sala registro : todos) {
			if (registro.equals(item))
				return registro.getId();
		}
		return (long) 0;
	}

	/**
	 * Busca las salas activas pertenecientes a un complejo.
	 * @param idComplejo id del complejo buscado
	 * @return ArrayList con todos las salas
	 **/
	public ArrayList<Sala> findByComplejo(Long idComplejo){
		ArrayList<Sala> todos = getAllEnabled();
		ArrayList<Sala> rta = new ArrayList<Sala>();
		
		for (Sala item : todos) {
			if (item.getIdComplejo() == idComplejo);
				rta.add(item);
		}
		return rta;
	}
	
	/**
	 * Busca las salas activas pertenecientes a un complejo.
	 * @param idComplejo id del complejo buscado
	 * @return true existen salas para ese complejo, false si no se encontro ninguna
	 **/
	public boolean findByComplejoBoolean(Long idComplejo){
		ArrayList<Sala> lista = findByComplejo(idComplejo);
		
		if (lista.isEmpty())
			return false;
		return true;
	}
	
	/**
	 * Busca un registro en la base de datos segun un nombre dado
	 * @param complejo id del complejo al que pertenece
	 * @param numero nombre de la sala que buscamos en la base
	 * @return true si existe, false si no se encontro
	 **/
	@SuppressWarnings("unchecked")
	public Sala findByNumberCinema(Long complejo, int numero){
		ArrayList<Sala> todos = (ArrayList<Sala>) dataAccess.getAll(Sala.class);
		
		for (Sala item : todos) {
			if (item.getIdComplejo()==complejo && item.getNumeroSala() == numero)
				return item;
		}
		return null;
	}
		
	/**
	 * Busca un registro en la base de datos segun un nombre dado
	 * @param complejo id del complejo al que pertenece
	 * @param numero nombre de la sala que buscamos en la base
	 * @return true si existe, false si no se encontro
	 **/
	@SuppressWarnings("unchecked")
	public boolean findByNumberCinemaBoolean(Long complejo, int numero){
		ArrayList<Sala> todos = (ArrayList<Sala>) dataAccess.getAll(Sala.class);
		
		for (Sala item : todos) {
			if (item.getIdComplejo()==complejo && item.getNumeroSala() == numero)
				return true;
		}
		return false;
	}

	/**
	 * Busca los registros en los que el valor del atributo 'activo' coincide con el ingresado
	 * @param flag el valor booleano que se busca (true=activo, false=inactivo)
	 * @return un ArrayList con los resultados de la busqueda
	 */
	@SuppressWarnings("unchecked")
	//@Override
	public ArrayList<Sala> getAllByFlag(boolean flag) {
		ArrayList<Sala> todos = (ArrayList<Sala>) dataAccess.getAll(Sala.class);
		ArrayList<Sala> rta = new ArrayList<Sala>();
		
		for (Sala item : todos) {
			if (item.isActiva() == flag)
				rta.add(item);
		}
		return rta;
	}
	
	/**
	 * Devuelve todos las Salas activos
	 * @return un ArrayList con los registros
	 **/
	public ArrayList<Sala> getAllEnabled(){
		return getAllByFlag(true);
	}
	
	/**
	 * Devuelve todos las Salas desactivados
	 * @return un ArrayList con los registros
	 **/
	public ArrayList<Sala> getAllDisabled(){
		return getAllByFlag(false);
	}
}
