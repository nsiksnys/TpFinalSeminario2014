package frgp.seminario.cine.dataAccess;

import java.util.List;

import javax.persistence.EntityManager;


/*
 * Funciones básicas para el accesso a datos.
 */

public class DataAccessImpl implements DataAccessInterface{
	private EntityManager entityManager;
	
	/** 
	 ** Busca un registro en específico.
	 ** @param entityClass la clase de la entidad correspondiente
	 ** @param id el id del objeto buscado
	 ** @return un Object con el registro buscado (no olvidar de castear!)
	 **/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object get(Class entityClass, int id) {
		return entityManager.find(entityClass, id);
	}

	/**
	 ** Recupera todos los registros de una clase. 
	 ** @param entityClass la clase de la entidad correspondiente
	 ** @return un List con todos los registros de esta entidad
	 **/
	@SuppressWarnings("rawtypes")
	@Override
	public List getAll(Class entityClass) {
		return entityManager.createQuery(createQuery(entityClass)).getResultList();
	}

	/**
	 ** Persiste un registro en la base de datos.
	 ** @param registro El objeto a persistir.
	 **/
	@Override
	public boolean save(Object registro) {
		try {
			entityManager.persist(registro);
			entityManager.flush();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 ** Mergea un registro en la base de datos.
	 ** @param registro El objeto a mergear.
	 **/
	@Override
	public boolean merge(Object registro) {
		try {
			entityManager.merge(registro);
			entityManager.flush();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 ** Persiste o actualiza un registro en la base de datos.
	 ** @param registro El objeto a guardar.
	 **/
/*	@Override
	public void saveOrUpdate(Object registro) {
		// TODO Auto-generated method stub
	}*/

	/**
	 ** Actualiza un registro en la base de datos.
	 ** @param registro El objeto a actualizar.
	 **/
/*	@Override
	public void update(Object registro) {
		// TODO Auto-generated method stub
	}*/

	/**
	 ** Borra un registro en la base de datos.
	 ** @param registro El objeto a borrar.
	 **/
	@Override
	public boolean delete(Object registro) {
		try {
			entityManager.remove(registro);
			entityManager.flush();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 ** Crea una consulta a ejecutar sobre la base de datos.
	 ** @param entityClass Clase de la entidad sobre la que se ejecutará la consulta.
	 **/
	@SuppressWarnings("rawtypes")
	@Override
	public String createQuery(Class entityClass) {//arma una query estandar y la devuelve
		return "from " + entityClass.getName() + " item";
	}
	
}
