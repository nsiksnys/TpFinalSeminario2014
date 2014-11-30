package frgp.seminario.cine.bo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import frgp.seminario.cine.findItem.impl.SalaFindItem;
import frgp.seminario.cine.forms.SalaForm;
import frgp.seminario.cine.model.Complejo;
import frgp.seminario.cine.model.Sala;
import frgp.seminario.cine.repository.Repository;

//Funciones pertenecientes a la logica de negocios
@Service("BoSala") //agrego el nombre del bean, para que al momento de llamar al Autowired pueda aclarar cual quiero
public class BoSala {
	@Autowired
	@Qualifier("SalaRepository") //aclaro cual es el bean a inyectar
	Repository<Sala> repository;
	
	@Autowired
	@Qualifier("ComplejoRepository")
	Repository<Complejo> complejos;
	
	@Autowired
	@Qualifier("SalaFindItem") //aclaro cual es el bean a inyectar
	SalaFindItem busquedaSala;
	
	/** 
	 ** Busca un registro en específico.
	 ** @param id el id del objeto buscado
	 ** @return el registro Sala buscado
	 **/
	public Sala get(Object id) {
		return repository.get(Sala.class, id);
	}
	
	/**
	 ** Persiste un registro en la base de datos.
	 ** @param registro El objeto a persistir.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	public boolean guardar(Sala registro) {
		if (!verificar(registro))
			return false;
		
		return repository.save(registro);
	}
	
	public boolean guardar(ArrayList<Sala> registros) {
		Boolean rta = true;
		for(Sala registro : registros)
			rta = guardar(registro);
		return rta;
	}

	/**
	 ** Guarda los cambios en un registro en la base de datos.
	 ** @param registro El objeto a guardar.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	public boolean modificar(Sala registro) {
		if (!verificar(registro))
			return false;
		
		return repository.merge(registro);
	}

	/**
	 ** Desactiva un registro en la base de datos.
	 ** @param registro El objeto a desactivar.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	public boolean desactivar(Sala registro) {
		//TODO: verificaciones propias de esta clase
		
		if (registro.isActiva())
			registro.setActiva(false);
		
		return repository.merge(registro);
	}
	
	/**
	 ** Activa un registro en la base de datos.
	 ** @param registro El objeto a activar.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	public boolean activar(Sala registro) {		
		if (!registro.isActiva())
			registro.setActiva(true);
		
		return repository.merge(registro);
	}

	/**
	 ** Recupera todos los registros de la clase Sala
	 ** @return un ArrayList con todos los registros
	 **/
	public ArrayList<SalaForm> listarTodasForm() {
		List<Sala> todos = repository.getAll(Sala.class);
		ArrayList<SalaForm> todosForm = new ArrayList<SalaForm>();
		
		for(Sala registro : todos)
			todosForm.add(entityToForm(registro));
		return todosForm;
	}

	/**
	 ** Recupera todos los registros activos de la clase Sala
	 ** @return un ArrayList con todos los registros
	 **/
	public ArrayList<SalaForm> listarTodasActivasForm() {
		List<Sala> todos = busquedaSala.getAllEnabled();
		ArrayList<SalaForm> todosForm = new ArrayList<SalaForm>();
		
		for(Sala registro : todos)
			todosForm.add(entityToForm(registro));
		return todosForm;
	}
	
	private boolean verificar(Sala registro) {
		if (!(registro instanceof frgp.seminario.cine.model.Sala))
			return false;
		return true;
	}
	
	/**
	 * convierte un registro Sala en un SalaForm, listo para mostrar en el listado
	 * @param registro el objeto Sala
	 * @return un SalaForm con los datos del reigstro Sala
	 */
	private SalaForm entityToForm(Sala registro)
	{
		SalaForm form = new SalaForm();
		form.setId(registro.getId().toString());
		form.setComplejo(complejos.get(Complejo.class, registro.getIdComplejo()).getNombre());
		form.setActiva(registro.isActiva());
		form.setNumeroSala(Integer.toString(registro.getNumeroSala()));
		return form;
	}

	public ArrayList<Sala> getByComplejo(Long idComplejo) {
		return busquedaSala.findByComplejo(idComplejo);
	}

	public boolean complejoHasAny(Long idComplejo) {
		return busquedaSala.findByComplejoBoolean(idComplejo);
	}

	public HashMap<String, String> getSalasComplejoMap(Long idComplejo) {
		ArrayList<Sala> salas =  busquedaSala.findByComplejo(idComplejo);
		HashMap<String, String> respuesta = new HashMap<String, String>(salas.size());
		
		if (salas.isEmpty())
			return null;
		
		for (Sala item : salas){
			if(item.getIdComplejo() == idComplejo){
				respuesta.put(item.getId().toString(), Integer.toString(item.getNumeroSala()));
			}	
		}
		return respuesta;

	}
}
