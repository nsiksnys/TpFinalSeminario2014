package frgp.seminario.cine.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import frgp.seminario.cine.bo.BusinessObject;
import frgp.seminario.cine.findItem.impl.CarteleraFindItem;
import frgp.seminario.cine.findItem.impl.PeliculaFindItem;
import frgp.seminario.cine.findItem.impl.ReservaFindItem;
import frgp.seminario.cine.forms.PeliculaForm;
import frgp.seminario.cine.model.Pelicula;
import frgp.seminario.cine.repository.Repository;
import frgp.seminario.cine.utils.FechaUtils;

//Funciones pertenecientes a la logica de negocios
@Service("BoPelicula") //agrego el nombre del bean, para que al momento de llamar al Autowired pueda aclarar cual quiero
public class BoPelicula implements BusinessObject<Pelicula, PeliculaForm>{
	@Autowired
	@Qualifier("PeliculaRepository") //aclaro cual es el bean a inyectar
	Repository<Pelicula> repositorio; //aclaro la clase que se utiliza en este caso en particular
	
	@Autowired
	@Qualifier("PeliculaFindItem")//aclaro cual es el bean a inyectar
	PeliculaFindItem busquedaPelicula;
	
	@Autowired
	@Qualifier("ReservaFindItem")//aclaro cual es el bean a inyectar
	ReservaFindItem busquedaReserva;//findItem de reservas 
	
	@Autowired
	@Qualifier("CarteleraFindItem")//aclaro cual es el bean a inyectar
	CarteleraFindItem busquedaCartelera;//findItem de peliculas en cartelera
	
	@Autowired
	FechaUtils utils;
	
	/** 
	 ** Busca un registro en específico.
	 ** @param id el id del objeto buscado
	 ** @return el registro Pelicula buscado
	 **/
	@Override
	public Pelicula get(Object id) {
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
		if (busquedaReserva.findActiveByPeliculaBoolean(registro.getId()))
			return false;
		
		if (registro.isActivo())
			registro.setActivo(false);
		
		return repositorio.merge(registro);
	}
	
	/**
	 ** Activa un registro en la base de datos.
	 ** @param registro El objeto a activar.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	public boolean activar(Pelicula registro) {		
		if (!registro.isActivo())
			registro.setActivo(true);
		
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
		if (!(registro instanceof frgp.seminario.cine.model.Pelicula))
			return false;
		
		if (busquedaPelicula.getActiveIdByObject(registro) != 0)//si el registro ya existe en la base de datos
			return false;
		
		return true;
	}
	
	/**
	 * Convierte un formulario en un registro Pelicula
	 * @param formulario el formulario submiteado
	 * @return un objeto Pelicula
	 */
	@Override
	public Pelicula formToEntity(PeliculaForm formulario)
	{
		return new Pelicula(formulario.getTitulo(), formulario.getIdioma(), formulario.isSubs(),
				formulario.getClasificacion(), formulario.isReposicion(), formulario.getSinopsis(),
				formulario.getActores(), formulario.getDirector(), formulario.getTrailer(), utils.getFormatoHoraMinuto(formulario.getDuracion()));
	}
	
	public PeliculaForm entityToForm(Pelicula registro)
	{
		PeliculaForm formulario = new PeliculaForm();
		formulario.setId(registro.getId().toString());
		formulario.setTitulo(registro.getNombre());
		formulario.setIdioma(registro.getIdioma());
		formulario.setSubs(registro.isSubs());
		formulario.setClasificacion(registro.getClasificacion());
		formulario.setReposicion(registro.isReposicion());
		formulario.setSinopsis(registro.getDetalles().getDescripcion());
		formulario.setActores(registro.getDetalles().getActores());
		formulario.setDirector(registro.getDetalles().getDirector());
		formulario.setTrailer(registro.getDetalles().getUrlTrailer());
		formulario.setDuracion(utils.getFormatoHoraMinuto(registro.getDuracion()));
		return formulario;
	}

	public ArrayList<PeliculaForm> listarTodosActivosForm() {
		ArrayList<PeliculaForm> respuesta = new ArrayList<PeliculaForm>(); 
		
		for(Pelicula registro : repositorio.getAll(Pelicula.class))
			if (registro.isActivo())
				respuesta.add(entityToForm(registro));
		
		return respuesta;
	}
}
