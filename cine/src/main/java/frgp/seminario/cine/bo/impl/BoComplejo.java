package frgp.seminario.cine.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import frgp.seminario.cine.bo.BusinessObject;
import frgp.seminario.cine.findItem.impl.ComplejoFindItem;
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
	//@Qualifier("ComplejoFindItem") //aclaro cual es el bean a inyectar
	ComplejoFindItem busquedaComplejo;
	
	@Autowired
	BoSala salas;
	
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
		
		if (!complejos.save(registro))
			return false;
		
		for (Sala sala : registro.getSalas()){//FIXME: por mas que lo guardo el id del complejo en sala sigue siendo cero
			sala.setIdComplejo(registro.getId());
			salas.modificar(sala);
		}
		
		return modificar(registro);
	}

	/**
	 ** Guarda los cambios en un registro en la base de datos.
	 ** @param registro El objeto a guardar.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean modificar(Complejo registro) {
		/*if (!verificar(registro))
			return false;*/
		
		return complejos.merge(registro);
	}

	/**
	 ** Desactiva un registro en la base de datos.
	 ** @param registro El objeto a desactivar.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean desactivar(Complejo registro) {
		//TODO: verificaciones propias de esta clase
		
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
	public List<Complejo> listarTodos(){
		return complejos.getAll(Complejo.class);
	}
	
	
	/**
	 ** Recupera todos los registros de la clase Complejo
	 ** @return un ArrayList con todos los registros
	 **/
	public ArrayList<ComplejoForm> listarTodosForm() {
		List<Complejo> todos = complejos.getAll(Complejo.class);
		ArrayList<ComplejoForm> todosForm = new ArrayList<ComplejoForm>();
		
		for(Complejo registro : todos)
			todosForm.add(entityToForm(registro));
		return todosForm;
	}

	/**
	 ** Recupera todos los registros activos de la clase Complejo
	 ** @return un ArrayList con todos los registros
	 **/
	public ArrayList<ComplejoForm> listarTodosActivosForm() {
		List<Complejo> todos = busquedaComplejo.getAllEnabled();
		ArrayList<ComplejoForm> todosForm = new ArrayList<ComplejoForm>();
		
		for(Complejo registro : todos)
			todosForm.add(entityToForm(registro));
		return todosForm;
	}
	
	/**
	 * Verifica que el registro cumpla con las caracteristicas necesarias
	 * @return true si el registro esta en condiciones de ser guardardo, false si no lo esta
	 **/
	@Override
	public boolean verificar(Complejo registro) {
		if (!(registro instanceof frgp.seminario.cine.model.Complejo))
			return false;
		
		if (busquedaComplejo.getIdByObject(registro) != 0)//si el registro ya existe en la base de datos
			return false;
		
		//TODO: verificaciones propias de la clase
		
		return true;
	}
	
	/**
	 * Convierte un formulario en un registro Complejo
	 * @param formulario el formulario submiteado
	 * @return un objeto Complejo
	 */
	@Override
	public Complejo formToEntity(ComplejoForm formulario)
	{
		Complejo registro =  new Complejo(formulario.getNombre(), formulario.getDireccion());
		
		if (formulario.getSalas()>0)//si se indico la cantidad de salas a generar
		{
			ArrayList<Sala> registroSalas = new ArrayList<Sala>(formulario.getSalas());
			for (int i=1; i<=formulario.getSalas(); i++){
				registroSalas.add(new Sala(Long.parseLong("0"), i));//el id es un null
			}
			
			/*if (!salas.guardar(registroSalas))
				return null;*/
			
			registro.setSalas(registroSalas);
		}
		return registro;
	}
	
	public Complejo formToEntityUpdate(ComplejoForm formulario)
	{
		ArrayList<Sala> existente = new ArrayList<Sala>();
		ArrayList<Sala> nueva;
		Complejo registro =  new Complejo(formulario.getNombre(), formulario.getDireccion());
		registro.setId(Long.parseLong(formulario.getId()));
		
		if (salas.complejoHasAny(registro.getId()))//si el complejo tiene salas
		{
			existente.addAll(salas.getByComplejo(registro.getId()));
		}
		
		if (formulario.getSalas() >0) //si se agregan salas
		{
			nueva = new ArrayList<Sala>(formulario.getSalas());
			for (int i=1; i<=formulario.getSalas(); i++){
				nueva.add(new Sala(registro.getId(), existente.size()+i));//el id es un null
			}
			salas.guardar(nueva);
			existente.addAll(nueva);//las agrego a la lista de existentes
		}
		
		registro.setSalas(existente);
		return registro;
	}
	
	public ComplejoForm entityToForm(Complejo registro)
	{
		ComplejoForm form = new ComplejoForm();
		form.setId(Long.toString(registro.getId()));
		form.setNombre(registro.getNombre());
		form.setDireccion(registro.getDireccion());
		form.setSalas(registro.getSalas().size());
		form.setActivo(registro.isActivo());
		return form;
	}

	public ArrayList<Complejo> getAllEnabled() {
		return busquedaComplejo.getAllEnabled();
	}
}
