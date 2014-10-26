package frgp.seminario.cine.bo.impl;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import frgp.seminario.cine.bo.BusinessObject;
import frgp.seminario.cine.findItem.impl.CarteleraFindItem;
import frgp.seminario.cine.findItem.impl.PeliculaFindItem;
import frgp.seminario.cine.forms.CarteleraForm;
import frgp.seminario.cine.model.Cartelera;
import frgp.seminario.cine.model.Pelicula;
import frgp.seminario.cine.repository.Repository;
import frgp.seminario.cine.utils.FechaUtils;

//Funciones pertenecientes a la logica de negocios
@Service("BoCartelera") //agrego el nombre del bean, para que al momento de llamar al Autowired pueda aclarar cual quiero
public class BoCartelera implements BusinessObject<Cartelera, CarteleraForm> {
	@Autowired
	@Qualifier("CarteleraRepository") //aclaro cual es el bean a inyectar
	Repository<Cartelera> repositorio; //aclaro la clase que se utiliza en este caso en particular
	
	@Autowired
	@Qualifier("CarteleraFindItem") //aclaro cual es el bean a inyectar
	CarteleraFindItem carteleraFindItem;
	
	@Autowired
	@Qualifier("PeliculaFindItem") //aclaro cual es el bean a inyectar
	PeliculaFindItem peliculaFindItem;
	
	@Autowired
	@Qualifier("PeliculaRepository") //aclaro cual es el bean a inyectar
	Repository<Pelicula> peliculaRepositorio; //aclaro la clase que se utiliza en este caso en particular
	
	@Autowired
	FechaUtils utils;
	
	/** 
	 ** Busca un registro en específico.
	 ** @param id el id del objeto buscado
	 ** @return el registro Pelicula buscado
	 **/
	@Override
	public Cartelera get(Object id) {
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
		if (registro.isActivo())
			registro.setActivo(false);
		
		return repositorio.merge(registro);
	}

	/**
	 ** Activa un registro en la base de datos.
	 ** @param registro El objeto a activar.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	public boolean activar(Cartelera registro) {		
		if (!registro.isActivo())
			registro.setActivo(true);
		
		return repositorio.merge(registro);
	}
	
	/**
	 ** Recupera todos los registros de la clase Cartelera
	 ** @return un ArrayList con todos los registros
	 **/
	@Override
	public ArrayList<Cartelera> listarTodos() {
		return (ArrayList<Cartelera>) repositorio.getAll(Cartelera.class);
	}
	
	public ArrayList<Pelicula> getAllPeliculasActivas()
	{
		return peliculaFindItem.getAllEnabled();
	}

	/**
	 * Verifica que el registro cumpla con las caracteristicas necesarias
	 * @return true si el registro esta en condiciones de ser guardardo, false si no lo esta
	 **/
	@Override
	public boolean verificar(Cartelera registro) {
		if (!(registro instanceof frgp.seminario.cine.model.Cartelera))
			return false;
		
		if (carteleraFindItem.getIdByObject(registro) != 0)//si el registro ya existe en la base de datos
			return false;
		
		
		return true;
	}
	
	/**
	 * Convierte un formulario en un registro Cartelera
	 * @param formulario el formulario submiteado
	 * @return un objeto Cartelera
	 */
	@Override
 	public Cartelera formToEntity(CarteleraForm formulario){
		return new Cartelera(peliculaRepositorio.get(Pelicula.class, Long.parseLong(formulario.getPelicula())),
							 formulario.getVersion(),
							 formulario.isSubtitulos(),
							 utils.getFechaFormatoDiaMesAnio(formulario.getInicio()),
							 utils.getFechaFormatoDiaMesAnio(formulario.getFin()));
	}
}
