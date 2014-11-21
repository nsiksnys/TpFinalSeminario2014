package frgp.seminario.cine.findItem.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import frgp.seminario.cine.dataAccess.DataAccess;
import frgp.seminario.cine.model.Cartelera;

@Service("CarteleraFindItem")//agrego el nombre del bean, para que al momento de llamar al Autowired pueda aclarar cual quiero
public class CarteleraFindItem /*implements FindItemWithFlag<Cartelera>*/ {
	@Autowired
	DataAccess dataAccess;
	
	/**
	 * Determina si existe un registro con las mismas caracteristicas en la base de datos
	 * @param item Pelicula que buscamos en la base
	 * @return true si existe un registro, false si no
	 **/
	@SuppressWarnings("unchecked")
	public Long getIdByObject(Cartelera item) {
		ArrayList<Cartelera> todos = (ArrayList<Cartelera>) dataAccess.getAll(Cartelera.class);
		
		for (Cartelera registro : todos) {
			if (registro.equals(item))
				return registro.getId();
		}
		return (long) 0;
	}
	
	/**
	 * Determina si existe un registro activo con las mismas caracteristicas en la base de datos
	 * @param item Pelicula que buscamos en la base
	 * @return true si existe un registro, false si no
	 **/
	public Long getActiveIdByObject(Cartelera item) {
		ArrayList<Cartelera> todos = getAllEnabled();
		
		for (Cartelera registro : todos) {
			if (registro.equals(item))
				return registro.getId();
		}
		return (long) 0;
	}
	
	/**
	 * Busca todos los registros de cartelera en los que figura una pelicula 
	 * @param name Titulo de la pelicula
	 * @return un ArrayList con los registros
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Cartelera> findByMovieName(String name){
		String titulo = name.toUpperCase();
		ArrayList<Cartelera> todos = (ArrayList<Cartelera>) dataAccess.getAll(Cartelera.class);
		ArrayList<Cartelera> rta = new ArrayList<Cartelera>();
		
		for (Cartelera item : todos) {
			if (item.getPelicula().getNombre().equals(titulo))
				rta.add(item);
		}
		return rta;
	}	
	
	/**
	 * Busca todos los registros de cartelera en los que figura una pelicula 
	 * @param id Id de la pelicula
	 * @return un ArrayList con los registros
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Cartelera> findByMovieId(int id){
		ArrayList<Cartelera> todos = (ArrayList<Cartelera>) dataAccess.getAll(Cartelera.class);
		ArrayList<Cartelera> rta = new ArrayList<Cartelera>();
		
		for (Cartelera item : todos) {
			if (item.getPelicula().getId() == id)
				rta.add(item);
		}
		return rta;
	}
	
	@SuppressWarnings("unchecked")
	//@Override
	public ArrayList<Cartelera> getAllByFlag(boolean flag) {
		ArrayList<Cartelera> todos = (ArrayList<Cartelera>) dataAccess.getAll(Cartelera.class);
		ArrayList<Cartelera> rta = new ArrayList<Cartelera>();
		
		for (Cartelera item : todos) {
			if (item.isActivo() == flag)
				rta.add(item);
		}
		return rta;
	}
	
	/**
	 * Devuelve todos las peliculas en cartelera activas
	 * @return un ArrayList con los registros
	 **/
	public ArrayList<Cartelera> getAllEnabled(){
		return getAllByFlag(true);
	}
	
	/**
	 * Devuelve todos las peliculas en cartelera desactivadas
	 * @return un ArrayList con los registros
	 **/
	public ArrayList<Cartelera> getAllDisabled(){
		return getAllByFlag(false);
	}
}
