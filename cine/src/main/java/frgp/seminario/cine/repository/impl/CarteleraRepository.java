package frgp.seminario.cine.repository.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import frgp.seminario.cine.dataAccess.DataAccessImpl;
import frgp.seminario.cine.model.Cartelera;
import frgp.seminario.cine.repository.RepoInterface;

public class CarteleraRepository implements RepoInterface<Cartelera> {
	@Autowired
	DataAccessImpl dataAccess;
	
	/** 
	 ** Busca un registro en específico.
	 ** @param entityClass la clase de la entidad Cartelera
	 ** @param id el id del objeto buscado
	 ** @return el registro Cartelera buscado (no olvidar de castear!)
	 **/
	@SuppressWarnings("rawtypes")
	@Override
	public Cartelera get(Class entityClass, int id) {
		return (Cartelera) dataAccess.get(entityClass, id);
	}

	/**
	 ** Recupera todos los registros de la clase Cartelera. 
	 ** @param entityClass la clase de la entidad Cartelera
	 ** @return un ArrayList con todos los registros de esta entidad
	 **/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ArrayList<Cartelera> getAll(Class entityClass) {
		return (ArrayList<Cartelera>) dataAccess.getAll(entityClass);
	}

	/**
	 ** Persiste un registro en la base de datos.
	 ** @param registro El objeto a persistir.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean save(Cartelera registro) {
		return dataAccess.save(registro);
	}

	/**
	 ** Mergea un registro en la base de datos.
	 ** @param registro El objeto a mergear.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean merge(Cartelera registro) {
		return dataAccess.merge(registro);
	}

	/**
	 ** Borra un registro en la base de datos.
	 ** @param registro El objeto a borrar.
	 ** @param true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean delete(Cartelera registro) {
		return dataAccess.delete(registro);
	}
}
