package frgp.seminario.cine.repository.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import frgp.seminario.cine.dataAccess.DataAccess;
import frgp.seminario.cine.model.Entrada;
import frgp.seminario.cine.repository.Repository;

@Service("EntradaRepository")
public class EntradaRepository implements Repository<Entrada> {
	@Autowired
	DataAccess dataAccess;

	/** 
	 ** Busca un registro en específico.
	 ** @param entityClass la clase de la entidad Entrada
	 ** @param id el id del objeto buscado
	 ** @return el registro Entrada buscado (no olvidar de castear!)
	 **/
	@SuppressWarnings("rawtypes")
	@Override
	public Entrada get(Class entityClass, Object id) {
		return (Entrada) dataAccess.get(entityClass, id);
	}

	/**
	 ** Recupera todos los registros de la clase Entrada. 
	 ** @param entityClass la clase de la entidad Entrada
	 ** @return un ArrayList con todos los registros de esta entidad
	 **/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ArrayList<Entrada> getAll(Class entityClass) {
		return (ArrayList<Entrada>) dataAccess.getAll(entityClass);
	}

	/**
	 ** Persiste un registro en la base de datos.
	 ** @param registro El objeto a persistir.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean save(Entrada registro) {
		return dataAccess.save(registro);
	}

	/**
	 ** Mergea un registro en la base de datos.
	 ** @param registro El objeto a mergear.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean merge(Entrada registro) {
		return dataAccess.merge(registro);
	}

	/**
	 ** Borra un registro en la base de datos.
	 ** @param registro El objeto a borrar.
	 ** @param true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean delete(Entrada registro) {
		return dataAccess.delete(registro);
	}
}
