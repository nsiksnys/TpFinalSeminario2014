package frgp.seminario.cine.bo.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import frgp.seminario.cine.bo.BoInterface;
import frgp.seminario.cine.findItem.impl.CarteleraFindItem;
import frgp.seminario.cine.forms.CarteleraForm;
import frgp.seminario.cine.model.Cartelera;
import frgp.seminario.cine.model.Pelicula;
import frgp.seminario.cine.repository.impl.CarteleraRepository;
import frgp.seminario.cine.repository.impl.PeliculaRepository;

public class BoCartelera implements BoInterface<Cartelera> {
	@Autowired
	CarteleraRepository repositorio;
	
	@Autowired
	CarteleraFindItem CarteleraFindItem;
	
	@Autowired
	PeliculaRepository peliculaRepositorio;
	
	/** 
	 ** Busca un registro en específico.
	 ** @param id el id del objeto buscado
	 ** @return el registro Pelicula buscado
	 **/
	public Cartelera get(int id) {
		return repositorio.get(Cartelera.class, id);
	}
	
	/**
	 ** Persiste un registro en la base de datos.
	 ** @param registro El objeto a persistir.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean guardar(Cartelera registro) {
		return repositorio.save(registro);
	}

	/**
	 ** Guarda los cambios en un registro en la base de datos.
	 ** @param registro El objeto a guardar.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean modificar(Cartelera registro) {
		return repositorio.merge(registro);
	}

	/**
	 ** Desactiva un registro en la base de datos.
	 ** @param registro El objeto a desactivar.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean desactivar(Cartelera registro) {
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
	public List<Cartelera> listarTodos() {
		return repositorio.getAll(Cartelera.class);
	}

	/**
	 * Verifica que el registro cumpla con las caracteristicas necesarias
	 * @return true si el registro esta en condiciones de ser guardardo, false si no lo esta
	 **/
	@Override
	public boolean verificar(Cartelera registro) {
		if (!registro.getClass().isInstance(Cartelera.class))
			return false;
		
		if (CarteleraFindItem.getIdByObject(registro) != 0)//si el registro ya existe en la base de datos
			return false;
		
		
		return true;
	}
	
	/**
	 * Convierte un formulario en un registro Cartelera
	 * @param formulario el formulario submiteado
	 * @return un objeto Cartelera
	 */
	public Cartelera formToEntity(CarteleraForm formulario){
		return new Cartelera(peliculaRepositorio.get(Pelicula.class, formulario.getPelicula()),
							 formulario.getVersion(),
							 formulario.isSubtitulos(),
							 new Date(),
							 new Date());
	}
}
