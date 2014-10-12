package frgp.seminario.cine.repository;

import java.util.List;

public interface RepoInterface<E> {
//funciones comunes a todos los repositorios
	@SuppressWarnings("rawtypes")
	public E get(Class entityClass, int id);
	@SuppressWarnings("rawtypes")
	public List<E> getAll(Class entityClass);
	public boolean save(E registro);
	public boolean merge(E registro);
	public boolean delete (E registro);
}