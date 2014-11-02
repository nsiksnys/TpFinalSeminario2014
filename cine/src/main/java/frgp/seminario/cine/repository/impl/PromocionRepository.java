package frgp.seminario.cine.repository.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import frgp.seminario.cine.dataAccess.DataAccess;
import frgp.seminario.cine.model.Precio;
import frgp.seminario.cine.model.Promocion;
import frgp.seminario.cine.repository.Repository;

@Service("PromocionRepository")
@Transactional
public class PromocionRepository implements Repository<Promocion>{
	@Autowired
	DataAccess dataAccess;
	
	/** 
	 ** Busca un registro en específico.
	 ** @param entityClass la clase de la entidad Promocion
	 ** @param id el id del objeto buscado
	 ** @return el registro Precio buscado (no olvidar de castear!)
	 **/
	@SuppressWarnings("rawtypes")
	@Override
	public Promocion get(Class entityClass, Object id) {
		return (Promocion) dataAccess.get(entityClass, id);
	}

	/**
	 ** Recupera todos los registros de la clase Precio. 
	 ** @param entityClass la clase de la entidad Precio
	 ** @return un ArrayList con todos los registros de esta entidad
	 **/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ArrayList<Promocion> getAll(Class entityClass) {
		return (ArrayList<Promocion>) dataAccess.getAll(entityClass);
	}

	/**
	 ** Persiste un registro en la base de datos.
	 ** @param registro El objeto a persistir.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean save(Promocion registro) {
		return dataAccess.save(registro);
	}

	/**
	 ** Mergea un registro en la base de datos.
	 ** @param registro El objeto a mergear.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean merge(Promocion registro) {
		return dataAccess.merge(registro);
	}

	/**
	 ** Borra un registro en la base de datos.
	 ** @param registro El objeto a borrar.
	 ** @param true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean delete(Promocion registro) {
		return dataAccess.delete(registro);
	}
}