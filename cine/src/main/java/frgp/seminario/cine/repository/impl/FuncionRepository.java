package frgp.seminario.cine.repository.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import frgp.seminario.cine.dataAccess.DataAccess;
import frgp.seminario.cine.model.Funcion;
import frgp.seminario.cine.repository.Repository;

@Service("FuncionRepository")
@Transactional
public class FuncionRepository implements Repository<Funcion> {
	@Autowired
	DataAccess dataAccess;
	
	/** 
	 ** Busca un registro en específico.
	 ** @param entityClass la clase de la entidad Funcion
	 ** @param id el id del objeto buscado
	 ** @return el registro Funcion buscado (no olvidar de castear!)
	 **/
	@SuppressWarnings("rawtypes")
	@Override
	public Funcion get(Class entityClass, Object id) {
		return (Funcion) dataAccess.get(entityClass, id);
	}

	/**
	 ** Recupera todos los registros de la clase Funcion. 
	 ** @param entityClass la clase de la entidad Funcion
	 ** @return un ArrayList con todos los registros de esta entidad
	 **/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ArrayList<Funcion> getAll(Class entityClass) {
		return (ArrayList<Funcion>) dataAccess.getAll(entityClass);
	}

	/**
	 ** Persiste un registro en la base de datos.
	 ** @param registro El objeto a persistir.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean save(Funcion registro) {
		return dataAccess.save(registro);
	}

	/**
	 ** Mergea un registro en la base de datos.
	 ** @param registro El objeto a mergear.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean merge(Funcion registro) {
		return dataAccess.merge(registro);
	}

	/**
	 ** Borra un registro en la base de datos.
	 ** @param registro El objeto a borrar.
	 ** @param true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean delete(Funcion registro) {
		return dataAccess.delete(registro);
	}

}