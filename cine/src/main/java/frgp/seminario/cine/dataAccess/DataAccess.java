package frgp.seminario.cine.dataAccess;

import java.util.List;

public interface DataAccess {
//Funciones comunes a todas las entidades
	
	@SuppressWarnings("rawtypes")
	public Object get(Class entityClass, Object id);
	@SuppressWarnings("rawtypes")
	public List getAll(Class entityClass);
	@SuppressWarnings("rawtypes")
	public boolean save(Object registro);
	public boolean merge(Object registro);
//	public void saveOrUpdate(Object registro);
//	public void update(Object registro);
	public boolean delete (Object registro);
	@SuppressWarnings("rawtypes")
	public Object createQuery(Class entityClass);
	@SuppressWarnings("rawtypes")
	List getCustomQueryResult(String query);
}