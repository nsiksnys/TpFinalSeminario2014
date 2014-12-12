package frgp.seminario.cine.repository.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import frgp.seminario.cine.dataAccess.DataAccess;
import frgp.seminario.cine.model.Asiento;
import frgp.seminario.cine.repository.Repository;

@Service("AsientoRepository")
@Transactional
public class AsientoRepository implements Repository<Asiento> {
	@Autowired
	DataAccess dataAccess;
	
	/** 
	 ** Busca un registro en específico.
	 ** @param entityClass la clase de la entidad Asiento
	 ** @param id el id del objeto buscado
	 ** @return el registro Asiento buscado (no olvidar de castear!)
	 **/
	@SuppressWarnings("rawtypes")
	@Override
	public Asiento get(Class entityClass, Object id) {
		return (Asiento) dataAccess.get(entityClass, id);
	}

	/**
	 ** Recupera todos los registros de la clase Asiento. 
	 ** @param entityClass la clase de la entidad Asiento
	 ** @return un ArrayList con todos los registros de esta entidad
	 **/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ArrayList<Asiento> getAll(Class entityClass) {
		return (ArrayList<Asiento>) dataAccess.getAll(entityClass);
	}

	/**
	 ** Persiste un registro en la base de datos.
	 ** @param registro El objeto a persistir.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean save(Asiento registro) {
		return dataAccess.save(registro);
	}

	/**
	 ** Mergea un registro en la base de datos.
	 ** @param registro El objeto a mergear.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean merge(Asiento registro) {
		return dataAccess.merge(registro);
	}

	/**
	 ** Borra un registro en la base de datos.
	 ** @param registro El objeto a borrar.
	 ** @param true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean delete(Asiento registro) {
		return dataAccess.delete(registro);
	}
}
