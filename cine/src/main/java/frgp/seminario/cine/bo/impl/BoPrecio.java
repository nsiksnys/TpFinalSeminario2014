package frgp.seminario.cine.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import frgp.seminario.cine.bo.BusinessObject;
import frgp.seminario.cine.findItem.impl.PrecioFindItem;
import frgp.seminario.cine.findItem.impl.ReservaFindItem;
import frgp.seminario.cine.forms.PrecioForm;
import frgp.seminario.cine.model.Precio;
import frgp.seminario.cine.repository.Repository;

//Funciones pertenecientes a la logica de negocios
@Service("BoPrecio") //agrego el nombre del bean, para que al momento de llamar al Autowired pueda aclarar cual quiero
public class BoPrecio implements BusinessObject<Precio, PrecioForm> {
	@Autowired
	@Qualifier("PrecioRepository") //aclaro cual es el bean a inyectar
	Repository<Precio> repositorio; //aclaro la clase que se utiliza en este caso en particular
	
	@Autowired
	@Qualifier("PrecioFindItem")//aclaro cual es el bean a inyectar
	PrecioFindItem busquedaPrecio;
	
	@Autowired
	@Qualifier("ReservaFindItem")//aclaro cual es el bean a inyectar
	ReservaFindItem busquedaReserva;//findItem de reservas 
	
	
	/** 
	 ** Busca un registro en específico.
	 ** @param id el id del objeto buscado
	 ** @return el registro Precio buscado
	 **/
	@Override
	public Precio get(Object id) {
		return repositorio.get(Precio.class, id);
	}
	
	/**
	 ** Persiste un registro en la base de datos.
	 ** @param registro El objeto a persistir.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean guardar(Precio registro) {
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
	public boolean modificar(Precio registro) {
		if(!(registro instanceof frgp.seminario.cine.model.Precio))
		return false;
		return repositorio.merge(registro);
	}

	
	public boolean desactivar(Precio registro) {
		
		if (registro.isActivo())
			registro.setActivo(false);
		
		return repositorio.merge(registro);
	}
	
	/**
	 ** Activa un registro en la base de datos.
	 ** @param registro El objeto a activar.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	public boolean activar(Precio registro) {		
		if (!registro.isActivo())
			registro.setActivo(true);
		
		return repositorio.merge(registro);
	}

	/**
	 ** Recupera todos los registros de la clase Precio
	 ** @return un ArrayList con todos los registros
	 **/
	@Override
	public List<Precio> listarTodos() 
	{
		return repositorio.getAll(Precio.class);
	}

	/**
	 * Verifica que el registro cumpla con las caracteristicas necesarias
	 * @return true si el registro esta en condiciones de ser guardardo, false si no lo esta
	 **/
	@Override
	public boolean verificar(Precio registro) {
		if (!(registro instanceof frgp.seminario.cine.model.Precio))
			return false;
		
		if (busquedaPrecio.getActiveIdByObject(registro) != 0)//si el registro ya existe en la base de datos
			return false;
		
		return true;
	}
	
	/**
	 * Convierte un formulario en un registro Precio
	 * @param formulario el formulario submiteado
	 * @return un objeto Precio
	 */
	@Override
	public Precio formToEntity(PrecioForm formulario)
	{
		return new Precio(formulario.getMenor(), formulario.getGeneral(), formulario.getMayor());
	}
	
	public PrecioForm entityToForm(Precio registro)
	{
		PrecioForm formulario = new PrecioForm();
		formulario.setId(registro.getId().toString());
		formulario.setMenor(registro.getMenor());
		formulario.setGeneral(registro.getGeneral());
		formulario.setMayor(registro.getMayor());
		return formulario;
	}
	
	public ArrayList<PrecioForm> listarTodosActivosForm() {
		ArrayList<PrecioForm> respuesta = new ArrayList<PrecioForm>();
		
		for(Precio registro : repositorio.getAll(Precio.class))
			if(registro.isActivo())
				respuesta.add(entityToForm(registro));
		return respuesta;
	}

	public Precio getEnabled() {
		return busquedaPrecio.getEnabled();
	}
}