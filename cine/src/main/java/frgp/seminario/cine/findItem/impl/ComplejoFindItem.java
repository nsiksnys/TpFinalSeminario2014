package frgp.seminario.cine.findItem.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import frgp.seminario.cine.dataAccess.DataAccess;
import frgp.seminario.cine.model.Complejo;

@Service("ComplejoFindItem")//agrego el nombre del bean, para que al momento de llamar al Autowired pueda aclarar cual quiero
public class ComplejoFindItem {
	@Autowired
	DataAccess dataAccess;
	
	/**
	 * Busca un registro con las mismas caracteristicas en la base de datos
	 * @param item Complejo que buscamos en la base
	 * @return id del registro, 0 si no se encontro ninguno
	 **/
	@SuppressWarnings("unchecked")
	public Long getIdByObject(Complejo item){
		ArrayList<Complejo> todos = (ArrayList<Complejo>) dataAccess.getAll(Complejo.class);
		
		for (Complejo registro : todos) {
			if (registro.equals(item))
				return registro.getId();
		}
		return (long) 0;
	}

	/**
	 * Busca un registro en la base de datos segun un nombre dado
	 * @param name Nombre del complejo que buscamos en la base
	 * @return id del registro, 0 si no se encontro ninguno
	 **/
	@SuppressWarnings("unchecked")
	public Complejo findByName(String name){
		String titulo = name.toUpperCase();
		ArrayList<Complejo> todos = (ArrayList<Complejo>) dataAccess.getAll(Complejo.class);
		
		for (Complejo item : todos) {
			if (item.getNombre().equals(titulo))
				return item;
		}
		return null;
	}
	
	/**
	 * Busca un registro en la base de datos segun un nombre dado
	 * @param address Nombre del complejo que buscamos en la base
	 * @return id del registro, 0 si no se encontro ninguno
	 **/
	@SuppressWarnings("unchecked")
	public Complejo findByAddress(String address){
		String direccion = address.toLowerCase();
		ArrayList<Complejo> todos = (ArrayList<Complejo>) dataAccess.getAll(Complejo.class);
		
		for (Complejo item : todos) {
			if (item.getDireccion().equals(direccion))
				return item;
		}
		return null;
	}
	
	/**
	 * Busca un registro en la base de datos segun un nombre dado
	 * @param name Nombre del complejo que buscamos en la base
	 * @return true si existe, false si no se encontro
	 **/
	@SuppressWarnings("unchecked")
	public boolean findByNameBoolean(String name){
		String titulo = name.toUpperCase();
		ArrayList<Complejo> todos = (ArrayList<Complejo>) dataAccess.getAll(Complejo.class);
		
		for (Complejo item : todos) {
			if (item.getNombre().equals(titulo))
				return true;
		}
		return false;
	}
	
	/**
	 * Busca un registro en la base de datos segun un nombre dado
	 * @param address Nombre del complejo que buscamos en la base
	 * @return true si existe, false si no se encontro
	 **/
	@SuppressWarnings("unchecked")
	public boolean findByAddressBoolean(String address){
		String direccion = address.toUpperCase();
		ArrayList<Complejo> todos = (ArrayList<Complejo>) dataAccess.getAll(Complejo.class);
		
		for (Complejo item : todos) {
			if (item.getDireccion().equals(direccion))
				return true;
		}
		return false;
	}
	
	
	/**
	 * Busca un registro en la base de datos segun un id dado
	 * @param id ID del complejo que buscamos en la base
	 * @return true si existe, false si no se encontro
	 **/
	@SuppressWarnings("unchecked")
	public boolean findByIdBoolean(int id){
		ArrayList<Complejo> todos = (ArrayList<Complejo>) dataAccess.getAll(Complejo.class);
		
		for (Complejo item : todos) {
			if (item.getId() == id)
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
	public ArrayList<Complejo> getAllByFlag(boolean flag) {
		ArrayList<Complejo> todos = (ArrayList<Complejo>) dataAccess.getAll(Complejo.class);
		ArrayList<Complejo> rta = new ArrayList<Complejo>();
		
		for (Complejo item : todos) {
			if (item.isActivo() == flag)
				rta.add(item);
		}
		return rta;
	}
	
	/**
	 * Devuelve todos las Complejos activos
	 * @return un ArrayList con los registros
	 **/
	public ArrayList<Complejo> getAllEnabled(){
		return getAllByFlag(true);
	}
	
	/**
	 * Devuelve todos las Complejos desactivados
	 * @return un ArrayList con los registros
	 **/
	public ArrayList<Complejo> getAllDisabled(){
		return getAllByFlag(false);
	}
}
