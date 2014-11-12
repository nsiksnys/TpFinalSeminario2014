package frgp.seminario.cine.findItem.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import frgp.seminario.cine.dataAccess.DataAccess;
import frgp.seminario.cine.model.Promocion;

@Service("PromocionFindItem")//agrego el nombre del bean, para que al momento de llamar al Autowired pueda aclarar cual quiero
public class PromocionFindItem {
	@Autowired
	DataAccess dataAccess;
	
	/**
	 * Busca un registro con las mismas caracteristicas en la base de datos
	 * @param item Pelicula que buscamos en la base
	 * @return id del registro, 0 si no se encontro ninguno
	 **/
	@SuppressWarnings("unchecked")
	public Long getIdByObject(Promocion item){
		ArrayList<Promocion> todos = (ArrayList<Promocion>) dataAccess.getAll(Promocion.class);
		
		for (Promocion registro : todos) {
			if (registro.equals(item))
				return registro.getId();
		}
		return (long) 0;
	}

	
	@SuppressWarnings("unchecked")
	public Promocion findByName(String name){
		String titulo = name.toUpperCase();
		ArrayList<Promocion> todos = (ArrayList<Promocion>) dataAccess.getAll(Promocion.class);
		
		for (Promocion item : todos) {
			if (item.getNombre().equals(titulo))
				return item;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public boolean findByNameBoolean(String name){
		String titulo = name.toUpperCase();
		ArrayList<Promocion> todos = (ArrayList<Promocion>) dataAccess.getAll(Promocion.class);
		
		for (Promocion item : todos) {
			if (item.getNombre().equals(name))
				return true;
		}
		return false;
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean findByIdBoolean(int id){
		ArrayList<Promocion> todos = (ArrayList<Promocion>) dataAccess.getAll(Promocion.class);
		
		for (Promocion item : todos) {
			if (item.getId() == id)
				return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	//@Override
	public ArrayList<Promocion> getAllByFlag(boolean flag) {
		ArrayList<Promocion> todos = (ArrayList<Promocion>) dataAccess.getAll(Promocion.class);
		ArrayList<Promocion> rta = new ArrayList<Promocion>();
		
		for (Promocion item : todos) {
			if (item.isActivo() == flag)
				rta.add(item);
		}
		return rta;
	}
	
	/**
	 * Devuelve todos las Promocion activas
	 * @return un ArrayList con los registros
	 **/
	public ArrayList<Promocion> getAllEnabled(){
		return getAllByFlag(true);
	}
	
	/**
	 * Devuelve todos las Promocion desactivadas
	 * @return un ArrayList con los registros
	 **/
	public ArrayList<Promocion> getAllDisabled(){
		return getAllByFlag(false);
	}
}