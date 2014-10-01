package frgp.seminario.cine.repository;

import java.util.List;

public interface RepoInterface {
//funciones comunes a todos los repositorios
	@SuppressWarnings("rawtypes")
	public Object get(Class entityClass, int id);
	@SuppressWarnings("rawtypes")
	public List getAll(Class entityClass);
	public void save(Object registro);
	public void merge(Object registro);
	public void delete (Object registro);
}