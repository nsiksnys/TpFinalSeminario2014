package frgp.seminario.cine.findItem.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import frgp.seminario.cine.dataAccess.DataAccess;
import frgp.seminario.cine.model.Precio;

@Service("PrecioFindItem")//agrego el nombre del bean, para que al momento de llamar al Autowired pueda aclarar cual quiero

public class PrecioFindItem {
	@Autowired
	DataAccess dataAccess;
	
	/**
	 * Busca un registro con las mismas caracteristicas en la base de datos
	 * @param item Pelicula que buscamos en la base
	 * @return id del registro, 0 si no se encontro ninguno
	 **/
	@SuppressWarnings("unchecked")
	public Long getIdByObject(Precio item){
		ArrayList<Precio> todos = (ArrayList<Precio>) dataAccess.getAll(Precio.class);
		
		for (Precio registro : todos) {
			if (registro.equals(item))
				return registro.getId();
		}
		return (long) 0;
	}

	/**
	 * Busca un registro activo con las mismas caracteristicas en la base de datos
	 * @param item Pelicula que buscamos en la base
	 * @return id del registro, 0 si no se encontro ninguno
	 **/
	public Long getActiveIdByObject(Precio item){
		ArrayList<Precio> todos = getAllEnabled();
		
		for (Precio registro : todos) {
			if (registro.equals(item))
				return registro.getId();
		}
		return (long) 0;
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean findByIdBoolean(int id){
		ArrayList<Precio> todos = (ArrayList<Precio>) dataAccess.getAll(Precio.class);
		
		for (Precio item : todos) {
			if (item.getId() == id)
				return true;
		}
		return false;
	}
	
	

	@SuppressWarnings("unchecked")
	//@Override
	public ArrayList<Precio> getAllByFlag(boolean flag) {
		ArrayList<Precio> todos = (ArrayList<Precio>) dataAccess.getAll(Precio.class);
		ArrayList<Precio> rta = new ArrayList<Precio>();
		
		for (Precio item : todos) {
			if (item.isActivo() == flag)
				rta.add(item);
		}
		return rta;
	}
	
	/**
	 * Devuelve todos las precios activos
	 * @return un ArrayList con los registros
	 **/
	public ArrayList<Precio> getAllEnabled()
	{
		return getAllByFlag(true);
	}
	
	/**
	 * Devuelve el unico precio activo
	 * @return un ArrayList con los registros
	 **/
	@SuppressWarnings("unchecked")
	public Precio getEnabled(){
		ArrayList<Precio> todos = (ArrayList<Precio>) dataAccess.getAll(Precio.class);
		
		for (Precio item : todos) {
			if (item.isActivo() == true)
				return item;
		}
		return null;
	}
	
	/**
	 * Devuelve todos las Precio desactivadas
	 * @return un ArrayList con los registros
	 **/
	public ArrayList<Precio> getAllDisabled(){
		return getAllByFlag(false);
	}
}