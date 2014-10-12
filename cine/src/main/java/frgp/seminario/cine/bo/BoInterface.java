package frgp.seminario.cine.bo;

import java.util.List;

//Funciones pertenecientes a la logica de negocios
public interface BoInterface<E> {
	public boolean guardar(E registro);
	public boolean modificar(E registro);
	public boolean desactivar(E registro);
	public List<E> listarTodos();
	public boolean verificar(E registro);
}
