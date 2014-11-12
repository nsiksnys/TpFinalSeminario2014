package frgp.seminario.cine.bo.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import frgp.seminario.cine.bo.BusinessObject;
import frgp.seminario.cine.model.Cliente;
import frgp.seminario.cine.repository.impl.ClienteRepository;
import frgp.seminario.cine.signup.SignupForm;
import frgp.seminario.cine.utils.FechaUtils;

//Funciones pertenecientes a la logica de negocios
@Service("BoCliente") //agrego el nombre del bean, para que al momento de llamar al Autowired pueda aclarar cual quiero
public class BoCliente implements BusinessObject<Cliente, SignupForm> {
	@Autowired
	@Qualifier("ClienteRepository") //aclaro cual es el bean a inyectar
	ClienteRepository repositorio; //aclaro la clase que se utiliza en este caso en particular
	
	@Autowired
	FechaUtils utils;
	
	/** 
	 ** Busca un registro en específico.
	 ** @param id el id del objeto buscado
	 ** @return el registro Cliente buscado
	 **/
	@Override
	public Cliente get(Object id) {
		return repositorio.get(Cliente.class, id);
	}
	
	/**
	 ** Persiste un registro en la base de datos.
	 ** @param registro El objeto a persistir.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean guardar(Cliente registro) {
		if (!verificar(registro))
			return false;
		
		if (repositorio.save(registro) == null)
			return false;
		
		return true;
	}

	/**
	 ** Guarda los cambios en un registro en la base de datos.
	 ** @param registro El objeto a guardar.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean modificar(Cliente registro) {
		if (!verificar(registro))
			return false;
		
		Cliente registroActual = this.get(registro.getEmail());
		
		if (registroActual.equals(registro))
			return true;
		
		registro.setPassword(registroActual.getPassword());//la password no cambia aca
		
		return repositorio.merge(registro);
	}

	/**
	 ** Desactiva un registro en la base de datos.
	 ** @param registro El objeto a desactivar.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	@Override
	public boolean desactivar(Cliente registro) {
		//Verificaciones propias de la clase
		
		if (registro.isActive())
			registro.setActive(false);
		
		return repositorio.merge(registro);
	}
	
	/**
	 ** Activa un registro en la base de datos.
	 ** @param registro El objeto a activar.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	public boolean activar(Cliente registro) {		
		if (!registro.isActive())
			registro.setActive(true);
		
		return repositorio.merge(registro);
	}

	/**
	 ** Recupera todos los registros de la clase Cliente
	 ** @return un ArrayList con todos los registros
	 **/
	@Override
	public ArrayList<Cliente> listarTodos() {
		return (ArrayList<Cliente>) repositorio.getAll(Cliente.class);
	}

	/**
	 * Verifica que el registro cumpla con las caracteristicas necesarias
	 * @return true si el registro esta en condiciones de ser guardardo, false si no lo esta
	 **/
	@Override
	public boolean verificar(Cliente registro) {
		if (!(registro instanceof frgp.seminario.cine.model.Cliente))
			return false;
				
		return true;
	}
	
	/**
	 * Convierte un formulario en un registro Cliente
	 * @param formulario el formulario submiteado
	 * @return un objeto Cliente
	 */
	@Override
	public Cliente formToEntity(SignupForm formulario)
	{
		return new Cliente(Long.parseLong(formulario.getDni()), formulario.getNombre(), formulario.getApellido(), formulario.getSexo(), 
        		utils.getFechaFormatoDiaMesAnio(formulario.getFechaNacimiento()), formulario.getPreguntaSeguridad(), 
        		formulario.getRespuestaSeguridad(), formulario.getEmail(), formulario.getPassword(), formulario.getDireccion(),
        		formulario.getGenero());
	}
	
	public Cliente formToEntityModificar(SignupForm formulario)
	{
		return new Cliente(Long.parseLong(formulario.getDni()), formulario.getNombre(), formulario.getApellido(), formulario.getSexo(), 
        		utils.getFechaFormatoDiaMesAnio(formulario.getFechaNacimiento()), formulario.getPreguntaSeguridad(), 
        		formulario.getRespuestaSeguridad(), formulario.getEmail(), "", formulario.getDireccion(),
        		formulario.getGenero());
	}
}
