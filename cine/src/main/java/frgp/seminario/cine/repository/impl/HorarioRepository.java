package frgp.seminario.cine.repository.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import frgp.seminario.cine.dataAccess.DataAccess;
import frgp.seminario.cine.model.Horario;

@Service("HorarioRepository")
@Transactional
public class HorarioRepository{
	@Autowired
	DataAccess dataAccess;
	
	/** 
	 ** Busca un registro en específico.
	 ** @param entityClass la clase de la entidad Horario
	 ** @param id el id del objeto buscado
	 ** @return el registro Horario buscado (no olvidar de castear!)
	 **/
	@SuppressWarnings("rawtypes")
	public Horario get(Class entityClass, Object id) {
		return (Horario) dataAccess.get(entityClass, id);
	}

	/**
	 ** Recupera todos los registros de la clase Horario. 
	 ** @param entityClass la clase de la entidad Horario
	 ** @return un ArrayList con todos los registros de esta entidad
	 **/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<Horario> getAll(Class entityClass) {
		return (ArrayList<Horario>) dataAccess.getAll(entityClass);
	}
}