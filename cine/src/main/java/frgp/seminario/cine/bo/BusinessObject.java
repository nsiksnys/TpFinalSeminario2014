package frgp.seminario.cine.bo;

import java.util.List;

//Funciones pertenecientes a la logica de negocios
public interface BusinessObject<E, F> {
	public E get(int id);
	public boolean guardar(E registro);
	public boolean modificar(E registro);
	public boolean desactivar(E registro);
	public List<E> listarTodos();
	public boolean verificar(E registro);
	public E  formToEntity(F formulario);
}
