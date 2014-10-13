package frgp.seminario.cine.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import frgp.seminario.cine.bo.BoInterface;
import frgp.seminario.cine.findItem.impl.PeliculaFindItem;
import frgp.seminario.cine.forms.PeliculaForm;
import frgp.seminario.cine.model.Pelicula;
import frgp.seminario.cine.repository.impl.PeliculaRepository;

//Funciones pertenecientes a la logica de negocios
public class BoPelicula implements BoInterface<Pelicula>{
	@Autowired
	PeliculaRepository repositorio;
	
	@Autowired
	PeliculaFindItem findItem;
	//ReservasFindItem findReservas;//findItem de reservas 
	//CarteleraFindItem findCartelera;//findItem de peliculas en cartelera
	
	/** 
	 ** Busca un registro en específico.
	 ** @param id el id del objeto buscado
	 ** @return el registro Pelicula buscado
	 **/
	public Pelicula get(int id) {
		return repositorio.get(Pelicula.class, id);
	}
	
	/**
	 ** Persiste un registro en la base de datos.
	 ** @param registro El objeto a persistir.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean guardar(Pelicula registro) {
		if (!verificar(registro))
			return false;
		
		return repositorio.save(registro);
	}

	/**
	 ** Guarda los cambios en un registro en la base de datos.
	 ** @param registro El objeto a guardar.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean modificar(Pelicula registro) {
		if (!verificar(registro))
			return false;
		
		return repositorio.merge(registro);
	}

	/**
	 ** Desactiva un registro en la base de datos.
	 ** @param registro El objeto a desactivar.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean desactivar(Pelicula registro) {
		//verificar que no haya reservas para esta pelicula
		/*if (boReserva.hasEnabledReservationByMovie(registro.getId()))
					return false;*/
		
		if (registro.isActivo())
			registro.setActivo(false);
		
		return repositorio.merge(registro);
	}

	/**
	 ** Recupera todos los registros de la clase Pelicula
	 ** @return un ArrayList con todos los registros
	 **/
	@Override
	public List<Pelicula> listarTodos() {
		return repositorio.getAll(Pelicula.class);
	}

	/**
	 * Verifica que el registro cumpla con las caracteristicas necesarias
	 * @return true si el registro esta en condiciones de ser guardardo, false si no lo esta
	 **/
	@Override
	public boolean verificar(Pelicula registro) {
		if (!registro.getClass().isInstance(Pelicula.class))
			return false;
		
		if (findItem.getIdByObject(registro) != 0)//si el registro ya existe en la base de datos
			return false;
		
		
		return true;
	}
	
	/**
	 * Convierte un formulario en un registro Pelicula
	 * @param formulario el formulario submiteado
	 * @return un objeto Pelicula
	 */
	public Pelicula formToEntity(PeliculaForm formulario)
	{
		return new Pelicula(formulario.getTitulo(), formulario.getIdioma(), formulario.isSubs(),
				formulario.getClasificacion(), formulario.isReposicion(), formulario.getSinopsis(),
				formulario.getActores(), formulario.getDirector(), formulario.getDirector());
	}
}
