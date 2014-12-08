package frgp.seminario.cine.repository.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import frgp.seminario.cine.repository.Repository;
import frgp.seminario.cine.dataAccess.DataAccess;
import frgp.seminario.cine.model.Reserva;

@Service("ReservaRepository")
@Transactional
public class ReservaRepository implements Repository<Reserva> {
	@Autowired
	DataAccess dataAccess;

	/** 
	 ** Busca un registro en específico.
	 ** @param entityClass la clase de la entidad Reserva
	 ** @param id el id del objeto buscado
	 ** @return el registro Reserva buscado (no olvidar de castear!)
	 **/
	@SuppressWarnings("rawtypes")
	@Override
	public Reserva get(Class entityClass, Object id) {
		return (Reserva) dataAccess.get(entityClass, id);
	}

	/**
	 ** Recupera todos los registros de la clase Reserva. 
	 ** @param entityClass la clase de la entidad Reserva
	 ** @return un ArrayList con todos los registros de esta entidad
	 **/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ArrayList<Reserva> getAll(Class entityClass) {
		return (ArrayList<Reserva>) dataAccess.getAll(entityClass);
	}

	/**
	 ** Persiste un registro en la base de datos.
	 ** @param registro El objeto a persistir.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean save(Reserva registro) {
		return dataAccess.save(registro);
	}

	/**
	 ** Mergea un registro en la base de datos.
	 ** @param registro El objeto a mergear.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean merge(Reserva registro) {
		return dataAccess.merge(registro);
	}

	/**
	 ** Borra un registro en la base de datos.
	 ** @param registro El objeto a borrar.
	 ** @param true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean delete(Reserva registro) {
		return dataAccess.delete(registro);
	}
}
