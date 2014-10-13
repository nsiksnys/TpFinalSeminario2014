package frgp.seminario.cine.repository.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import frgp.seminario.cine.dataAccess.DataAccessImpl;
import frgp.seminario.cine.model.FichaTecnica;
import frgp.seminario.cine.repository.RepoInterface;

public class FichaTecnicaRepository implements RepoInterface<FichaTecnica> {
	@Autowired
	DataAccessImpl dataAccess;
	
	/** 
	 ** Busca un registro en específico.
	 ** @param entityClass la clase de la entidad Pelicula
	 ** @param id el id del objeto buscado
	 ** @return el registro Pelicula buscado (no olvidar de castear!)
	 **/
	@SuppressWarnings("rawtypes")
	@Override
	public FichaTecnica get(Class entityClass, int id) {
		return (FichaTecnica) dataAccess.get(entityClass, id);
	}

	/**
	 ** Recupera todos los registros de la clase Pelicula. 
	 ** @param entityClass la clase de la entidad Pelicula
	 ** @return un ArrayList con todos los registros de esta entidad
	 **/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ArrayList<FichaTecnica> getAll(Class entityClass) {
		return (ArrayList<FichaTecnica>) dataAccess.getAll(entityClass);
	}

	/**
	 ** Persiste un registro en la base de datos.
	 ** @param registro El objeto a persistir.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean save(FichaTecnica registro) {
		return dataAccess.save(registro);
	}

	/**
	 ** Mergea un registro en la base de datos.
	 ** @param registro El objeto a mergear.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean merge(FichaTecnica registro) {
		return dataAccess.merge(registro);
	}

	/**
	 ** Borra un registro en la base de datos.
	 ** @param registro El objeto a borrar.
	 ** @param true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean delete(FichaTecnica registro) {
		return dataAccess.delete(registro);
	}

}
