package frgp.seminario.cine.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import frgp.seminario.cine.bo.BusinessObject;
import frgp.seminario.cine.findItem.impl.ComplejoFindItem;
import frgp.seminario.cine.findItem.impl.SalaFindItem;
import frgp.seminario.cine.forms.ComplejoForm;
import frgp.seminario.cine.model.Complejo;
import frgp.seminario.cine.model.Sala;
import frgp.seminario.cine.repository.Repository;

//Funciones pertenecientes a la logica de negocios
@Service("BoComplejo") //agrego el nombre del bean, para que al momento de llamar al Autowired pueda aclarar cual quiero
public class BoComplejo implements BusinessObject<Complejo, ComplejoForm> {
	@Autowired
	@Qualifier("ComplejoRepository") //aclaro cual es el bean a inyectar
	Repository<Complejo> complejos;
	
	@Autowired
	@Qualifier("SalaRepository") //aclaro cual es el bean a inyectar
	Repository<Sala> salas;
	
	@Autowired
	//@Qualifier("ComplejoFindItem") //aclaro cual es el bean a inyectar
	ComplejoFindItem complejoFindItem;
	
	@Autowired
	//@Qualifier("SalaFindItem") //aclaro cual es el bean a inyectar
	SalaFindItem salaFindItem;

	/** 
	 ** Busca un registro en específico.
	 ** @param id el id del objeto buscado
	 ** @return el registro Complejo buscado
	 **/
	@Override
	public Complejo get(Object id) {
		return complejos.get(Complejo.class, id);
	}
	
	/**
	 ** Persiste un registro en la base de datos.
	 ** @param registro El objeto a persistir.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean guardar(Complejo registro) {
		if (!verificar(registro))
			return false;
		
		return complejos.save(registro);
	}

	/**
	 ** Guarda los cambios en un registro en la base de datos.
	 ** @param registro El objeto a guardar.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean modificar(Complejo registro) {
		if (!verificar(registro))
			return false;
		
		return complejos.merge(registro);
	}

	/**
	 ** Desactiva un registro en la base de datos.
	 ** @param registro El objeto a desactivar.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean desactivar(Complejo registro) {
		//verificar que no haya reservas para esta Complejo
		/*if (boReserva.hasEnabledReservationByMovie(registro.getId()))
					return false;*/
		
		if (registro.isActivo())
			registro.setActivo(false);
		
		return complejos.merge(registro);
	}
	
	/**
	 ** Activa un registro en la base de datos.
	 ** @param registro El objeto a activar.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	public boolean activar(Complejo registro) {		
		if (!registro.isActivo())
			registro.setActivo(true);
		
		return complejos.merge(registro);
	}

	/**
	 ** Recupera todos los registros de la clase Complejo
	 ** @return un ArrayList con todos los registros
	 **/
	@Override
	public List<Complejo> listarTodos() {
		return complejos.getAll(Complejo.class);
	}

	/**
	 * Verifica que el registro cumpla con las caracteristicas necesarias
	 * @return true si el registro esta en condiciones de ser guardardo, false si no lo esta
	 **/
	@Override
	public boolean verificar(Complejo registro) {
		if (!(registro instanceof frgp.seminario.cine.model.Complejo))
			return false;
		
		if (complejoFindItem.getIdByObject(registro) != 0)//si el registro ya existe en la base de datos
			return false;
		
		return true;
	}
	
	/**
	 * Convierte un formulario en un registro Complejo
	 * @param formulario el formulario submiteado
	 * @return un objeto Complejo
	 */
	@SuppressWarnings("unused")
	@Override
	public Complejo formToEntity(ComplejoForm formulario)
	{
		int numero= 1;
		Complejo registro =  new Complejo(formulario.getNombre(), formulario.getDireccion());
		
		if (guardar(registro))
		{
			ArrayList<Sala> salas = new ArrayList<Sala>(formulario.getSalas());
			for (Sala item : salas){
				item = new Sala(registro.getId(), numero++);
			}
			
			registro.setSalas(salas);
		}
		return registro;
	}
}
