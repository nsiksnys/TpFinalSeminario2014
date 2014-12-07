package frgp.seminario.cine.bo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import frgp.seminario.cine.bo.BusinessObject;
import frgp.seminario.cine.findItem.impl.PromocionFindItem;
import frgp.seminario.cine.findItem.impl.ReservaFindItem;
import frgp.seminario.cine.forms.PromocionForm;
import frgp.seminario.cine.model.Promocion;
import frgp.seminario.cine.repository.Repository;
import frgp.seminario.cine.utils.FechaUtils;

//Funciones pertenecientes a la logica de negocios
@Service("BoPromocion") //agrego el nombre del bean, para que al momento de llamar al Autowired pueda aclarar cual quiero
public class BoPromocion implements BusinessObject<Promocion, PromocionForm>{
	@Autowired
	@Qualifier("PromocionRepository") //aclaro cual es el bean a inyectar
	Repository<Promocion> repositorio; //aclaro la clase que se utiliza en este caso en particular
	
	@Autowired
	@Qualifier("PromocionFindItem")//aclaro cual es el bean a inyectar
	PromocionFindItem busquedaPromocion;
	
	@Autowired
	@Qualifier("ReservaFindItem")//aclaro cual es el bean a inyectar
	ReservaFindItem busquedaReserva;//findItem de reservas 
	
	@Autowired
	FechaUtils utils;
	
	/** 
	 ** Busca un registro en específico.
	 ** @param id el id del objeto buscado
	 ** @return el registro Precio buscado
	 **/
	@Override
	public Promocion get(Object id) {
		return repositorio.get(Promocion.class, id);
	}
	
	/**
	 ** Persiste un registro en la base de datos.
	 ** @param registro El objeto a persistir.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean guardar(Promocion registro) {
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
	public boolean modificar(Promocion registro) {
		//esta sola linea estaba antes de la modificacion 
		//return repositorio.merge(registro);
		if(!(registro instanceof frgp.seminario.cine.model.Promocion))
		return false;
		return repositorio.merge(registro);
	}

	/**
	 ** Desactiva un registro en la base de datos.
	 ** @param registro El objeto a desactivar.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean desactivar(Promocion registro) {
		//estas 3 lineas estaban antes d ela modificacion 
//		if (registro.isActivo())
//			registro.setActivo(false);
//		return repositorio.merge(registro);
		if (busquedaReserva.findActiveByPromocionBoolean(registro.getId()))
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
	public boolean activar(Promocion registro) {		
		if (!registro.isActivo())
			registro.setActivo(true);
		
		return repositorio.merge(registro);
	}

	/**
	 ** Recupera todos los registros de la clase Promocion
	 ** @return un ArrayList con todos los registros
	 **/
/* esta funcion estaba antes d ela modificacion

   @Override	
	public ArrayList<Promocion> listarTodos() {
		return (ArrayList<Promocion>) repositorio.getAll(Promocion.class);
	}*/

	/**
	 ** Recupera todos los registros de la clase Promocion
	 ** @return un ArrayList con todos los registros
	 **/
	@Override
	public List<Promocion> listarTodos() {
		return repositorio.getAll(Promocion.class);
	}
	
	
	/**
	 * Verifica que el registro cumpla con las caracteristicas necesarias
	 * @return true si el registro esta en condiciones de ser guardardo, false si no lo esta
	 **/
	@Override
	public boolean verificar(Promocion registro) {
		if (!(registro instanceof frgp.seminario.cine.model.Promocion))
			return false;
		//esta parte estaba antres de la modificacion
//		if (busquedaPromocion.getIdByObject(registro) != 0)//si el registro ya existe en la base de datos
//			return false;
		if (busquedaPromocion.getActiveIdByObject(registro) !=0)
			return false;
		//esta linea si estaba no fue modificada
		return true;
	}
	
	/**
	 * Convierte un formulario en un registro Promocion
	 * @param formulario el formulario submiteado
	 * @return un objeto Promocion
	 */
	@Override
	public Promocion formToEntity(PromocionForm formulario) {
		return new Promocion(formulario.getNombre(), formulario.getDescripcion(), 
				utils.getFechaFormatoDiaMesAnio(formulario.getFechainicio()),
				utils.getFechaFormatoDiaMesAnio(formulario.getFechafin()));
				//(formulario.getFechainicio()), formulario.getFechafin());
	}
	
	public PromocionForm entityToForm(Promocion registro)
	{
		PromocionForm formulario = new PromocionForm();
		formulario.setId(registro.getId().toString());
		formulario.setNombre(registro.getNombre());
		formulario.setDescripcion(registro.getDescripcion());
		formulario.setFechainicio(utils.getFormatoDiaMesAnio(registro.getFechaInicio()));
		formulario.setFechafin(utils.getFormatoDiaMesAnio(registro.getFechaFin()));
		return formulario;
	}

	
	public ArrayList<PromocionForm> listarTodosActivosForm() {
		ArrayList<PromocionForm> respuesta = new ArrayList<PromocionForm>();
		
		for(Promocion registro : repositorio.getAll(Promocion.class))
			if(registro.isActivo())
				respuesta.add(entityToForm(registro));
		
		return respuesta;
	}
	
	
	public HashMap<String, String> getAllActiveMap() {
		ArrayList<Promocion> activas = busquedaPromocion.getAllEnabled();
		HashMap<String, String> respuesta = new HashMap<String, String>(activas.size());
		
		if (activas.isEmpty())
			return null;
		
		for (Promocion item : activas)
			respuesta.put(item.getId().toString(), item.getNombre());
		
		return respuesta;
	}

}
