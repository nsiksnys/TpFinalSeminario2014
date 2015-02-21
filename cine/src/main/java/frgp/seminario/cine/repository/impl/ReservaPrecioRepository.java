package frgp.seminario.cine.repository.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import frgp.seminario.cine.dataAccess.DataAccess;
import frgp.seminario.cine.model.ReservaPrecio;
import frgp.seminario.cine.repository.Repository;

@Service("ReservaPrecioRepository")
@Transactional
public class ReservaPrecioRepository implements Repository<ReservaPrecio> {
	@Autowired
	DataAccess dataAccess;
	
	/** 
	 ** Busca un registro en específico.
	 ** @param entityClass la clase de la entidad ReservaPrecio
	 ** @param id el id del objeto buscado
	 ** @return el registro ReservaPrecio buscado (no olvidar de castear!)
	 **/
	@SuppressWarnings("rawtypes")
	@Override
	public ReservaPrecio get(Class entityClass, Object id) {
		return (ReservaPrecio) dataAccess.get(entityClass, id);
	}

	/**
	 ** Recupera todos los registros de la clase ReservaPrecio. 
	 ** @param entityClass la clase de la entidad ReservaPrecio
	 ** @return un ArrayList con todos los registros de esta entidad
	 **/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ArrayList<ReservaPrecio> getAll(Class entityClass) {
		return (ArrayList<ReservaPrecio>) dataAccess.getAll(entityClass);
	}

	/**
	 ** Persiste un registro en la base de datos.
	 ** @param registro El objeto a persistir.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean save(ReservaPrecio registro) {
		return dataAccess.save(registro);
	}

	/**
	 ** Mergea un registro en la base de datos.
	 ** @param registro El objeto a mergear.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean merge(ReservaPrecio registro) {
		return dataAccess.merge(registro);
	}

	/**
	 ** Borra un registro en la base de datos.
	 ** @param registro El objeto a borrar.
	 ** @param true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean delete(ReservaPrecio registro) {
		return dataAccess.delete(registro);
	}
}
