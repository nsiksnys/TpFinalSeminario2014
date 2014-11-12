package frgp.seminario.cine.findItem.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import frgp.seminario.cine.dataAccess.DataAccess;
import frgp.seminario.cine.model.Pelicula;

@Service("PeliculaFindItem")//agrego el nombre del bean, para que al momento de llamar al Autowired pueda aclarar cual quiero
public class PeliculaFindItem /* implements FindItemWithFlag<Pelicula> */ {
	@Autowired
	DataAccess dataAccess;
	
	/**
	 * Busca un registro con las mismas caracteristicas en la base de datos
	 * @param item Pelicula que buscamos en la base
	 * @return id del registro, 0 si no se encontro ninguno
	 **/
	@SuppressWarnings("unchecked")
	public Long getIdByObject(Pelicula item){
		ArrayList<Pelicula> todos = (ArrayList<Pelicula>) dataAccess.getAll(Pelicula.class);
		
		for (Pelicula registro : todos) {
			if (registro.equals(item))
				return registro.getId();
		}
		return (long) 0;
	}

	
	@SuppressWarnings("unchecked")
	public Pelicula findByName(String name){
		String titulo = name.toUpperCase();
		ArrayList<Pelicula> todos = (ArrayList<Pelicula>) dataAccess.getAll(Pelicula.class);
		
		for (Pelicula item : todos) {
			if (item.getNombre().equals(titulo))
				return item;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public boolean findByNameBoolean(String name){
		String titulo = name.toUpperCase();
		ArrayList<Pelicula> todos = (ArrayList<Pelicula>) dataAccess.getAll(Pelicula.class);
		
		for (Pelicula item : todos) {
			if (item.getNombre().equals(titulo))
				return true;
		}
		return false;
	}
	
/*	@SuppressWarnings("unchecked")
	public Pelicula findById(int id){
		ArrayList<Pelicula> todos = (ArrayList<Pelicula>) dataAccess.getAll(Pelicula.class);
		
		for (Pelicula item : todos) {
			if (item.getId() == id)
				return item;
		}
		return null;
	}*/
	
	@SuppressWarnings("unchecked")
	public boolean findByIdBoolean(int id){
		ArrayList<Pelicula> todos = (ArrayList<Pelicula>) dataAccess.getAll(Pelicula.class);
		
		for (Pelicula item : todos) {
			if (item.getId() == id)
				return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	//@Override
	public ArrayList<Pelicula> getAllByFlag(boolean flag) {
		ArrayList<Pelicula> todos = (ArrayList<Pelicula>) dataAccess.getAll(Pelicula.class);
		ArrayList<Pelicula> rta = new ArrayList<Pelicula>();
		
		for (Pelicula item : todos) {
			if (item.isActivo() == flag)
				rta.add(item);
		}
		return rta;
	}
	
	/**
	 * Devuelve todos las peliculas activas
	 * @return un ArrayList con los registros
	 **/
	public ArrayList<Pelicula> getAllEnabled(){
		return getAllByFlag(true);
	}
	
	/**
	 * Devuelve todos las peliculas desactivadas
	 * @return un ArrayList con los registros
	 **/
	public ArrayList<Pelicula> getAllDisabled(){
		return getAllByFlag(false);
	}
}
