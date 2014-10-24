package frgp.seminario.cine.repository.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import frgp.seminario.cine.dataAccess.DataAccess;
import frgp.seminario.cine.model.Pelicula;
import frgp.seminario.cine.repository.Repository;

@Service("PeliculaRepository")
public class PeliculaRepository implements Repository<Pelicula> {
	@Autowired
	DataAccess dataAccess;
	
	/** 
	 ** Busca un registro en específico.
	 ** @param entityClass la clase de la entidad Pelicula
	 ** @param id el id del objeto buscado
	 ** @return el registro Pelicula buscado (no olvidar de castear!)
	 **/
	@SuppressWarnings("rawtypes")
	@Override
	public Pelicula get(Class entityClass, int id) {
		return (Pelicula) dataAccess.get(entityClass, id);
	}

	/**
	 ** Recupera todos los registros de la clase Pelicula. 
	 ** @param entityClass la clase de la entidad Pelicula
	 ** @return un ArrayList con todos los registros de esta entidad
	 **/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ArrayList<Pelicula> getAll(Class entityClass) {
		return (ArrayList<Pelicula>) dataAccess.getAll(entityClass);
	}

	/**
	 ** Persiste un registro en la base de datos.
	 ** @param registro El objeto a persistir.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean save(Pelicula registro) {
		return dataAccess.save(registro);
	}

	/**
	 ** Mergea un registro en la base de datos.
	 ** @param registro El objeto a mergear.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean merge(Pelicula registro) {
		return dataAccess.merge(registro);
	}

	/**
	 ** Borra un registro en la base de datos.
	 ** @param registro El objeto a borrar.
	 ** @param true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean delete(Pelicula registro) {
		return dataAccess.delete(registro);
	}

}
