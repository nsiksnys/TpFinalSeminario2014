package frgp.seminario.cine.bo.impl;

import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import frgp.seminario.cine.account.Account;
import frgp.seminario.cine.account.AccountRepository;
import frgp.seminario.cine.bo.BusinessObject;
import frgp.seminario.cine.model.Cliente;
import frgp.seminario.cine.signup.SignupForm;
import frgp.seminario.cine.utils.FechaUtils;

//Funciones pertenecientes a la logica de negocios
@Service("BoAccount") //agrego el nombre del bean, para que al momento de llamar al Autowired pueda aclarar cual quiero
public class BoAccount implements BusinessObject<Account, SignupForm> {
	@Autowired
	AccountRepository accounts;
	
	@Autowired
	BoCliente clientes;
	
	@Autowired
	FechaUtils utils;
	
	private HashMap<String, String> roles;
	
	
	@Override
	public Account get(Object email) {
		return accounts.findByEmail((String) email);
	}
	
	public Cliente getCliente(Object email){
		return clientes.get(email);
	}

	@Override
	public boolean guardar(Account registro) {
		try
		{
			accounts.save(registro);
		}
		catch (PersistenceException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean guardar(Cliente registro){
		return clientes.guardar(registro);
	}

	@Override
	public boolean modificar(Account registro) {
		Account registroActual = this.get(registro.getEmail());
		
		if (registroActual.equals(registro))
			return true;
		
		//compraro uno a uno y guardo los cambios (si los hay)
		if (registroActual.getDni() != registro.getDni())
			registroActual.setDni(registro.getDni());
		
		if (registroActual.getNombre().compareTo(registro.getNombre()) != 0)
			registroActual.setNombre(registro.getNombre());
		
		if (registroActual.getApellido().compareTo(registro.getApellido()) != 0)
			registroActual.setApellido(registro.getApellido());
		
		if (registroActual.getSexo().compareTo(registro.getSexo()) != 0)
			registroActual.setSexo(registro.getSexo());
		
		if (registroActual.getFechaNacimiento().compareTo(registro.getFechaNacimiento()) != 0)
			registroActual.setFechaNacimiento(registro.getFechaNacimiento());
		
		if (registroActual.getPreguntaSeguridad() != null && registro.getPreguntaSeguridad() != null &&
				registroActual.getPreguntaSeguridad().compareTo(registro.getPreguntaSeguridad()) != 0)
			registroActual.setPreguntaSeguridad(registro.getPassword());
		
		if (registroActual.getRespuestaSeguridad() != null && registro.getRespuestaSeguridad() != null &&
				registroActual.getRespuestaSeguridad().compareTo(registro.getRespuestaSeguridad())!=0)
			registroActual.setRespuestaSeguridad(registro.getRespuestaSeguridad());
		
		if (registroActual.getEmail().compareTo(registro.getEmail()) != 0)
			registroActual.setEmail(registro.getEmail());
		
		if (registroActual.getRole().compareTo(registro.getRole()) != 0)
			registroActual.setRole(registro.getRole());
				
		return accounts.merge(registroActual);
	}
	
	public boolean modificar(Cliente registro){
		if (!(registro instanceof frgp.seminario.cine.model.Cliente))
			return false;
		
		return clientes.modificar(registro);
	}

	@Override
	public boolean desactivar(Account registro) {
		//TODO: verificaciones propias de esta clase
		if (registro.getRole().equals("C"))
			return clientes.desactivar(clientes.get(registro.getEmail()));
		
		if (registro.isActive())
			registro.setActive(false);
		
		return accounts.merge(registro);
	}
	
	public boolean activar(Account registro) {
		if (!registro.isActive())
			registro.setActive(true);
		
		return accounts.merge(registro);
	}

	@Override
	public ArrayList<Account> listarTodos() {
		return accounts.getAll();
	}

	@Override
	public boolean verificar(Account registro) {
		if (!(registro instanceof frgp.seminario.cine.account.Account))
			return false;
		
		if (!accounts.getIdByObject(registro).equals(""))
			return false;
		
		return true;
	}

	@Override
	public Account formToEntity(SignupForm formulario) {
		//parametros no nulleables
		Account entity = new Account(	Long.parseLong(formulario.getDni()), 
										formulario.getNombre(), 
										formulario.getApellido(), 
										formulario.getEmail(),
										formulario.getPassword(),
										formulario.getRole());
		//parametros nulleables
		if (formulario.getSexo() != null)
			entity.setSexo(formulario.getSexo());
		
		if (formulario.getFechaNacimiento() != null)
			entity.setFechaNacimiento(utils.getFechaFormatoDiaMesAnio(formulario.getFechaNacimiento()));
		
		if (formulario.getPreguntaSeguridad() != null)
			entity.setPreguntaSeguridad(formulario.getPreguntaSeguridad());
		
		if (formulario.getRespuestaSeguridad() != null)
			entity.setRespuestaSeguridad(formulario.getRespuestaSeguridad());
		
		return entity;
	}
	
	public Cliente formToClienteEntity(SignupForm formulario) {
        return clientes.formToEntity(formulario);
	}
	
	public Account formToEntityModificar(SignupForm formulario) {
		//parametros no nulleables
		Account entity = new Account(	Long.parseLong(formulario.getDni()), 
										formulario.getNombre(), 
										formulario.getApellido(), 
										formulario.getEmail(),
										"",//como es una modificacion el password no va
										formulario.getRole());
		//parametros nulleables
		if (formulario.getSexo() != null)
			entity.setSexo(formulario.getSexo());
		
		if (formulario.getFechaNacimiento() != null)
			entity.setFechaNacimiento(utils.getFechaFormatoDiaMesAnio(formulario.getFechaNacimiento()));
		
		if (formulario.getPreguntaSeguridad() != null)
			entity.setPreguntaSeguridad(formulario.getPreguntaSeguridad());
		
		if (formulario.getRespuestaSeguridad() != null)
			entity.setRespuestaSeguridad(formulario.getRespuestaSeguridad());
		
		return entity;
	}
	
	public Cliente formToClienteEntityModificar(SignupForm formulario)
	{
		return clientes.formToEntityModificar(formulario);
	}
	
	public SignupForm entityToForm(Account registro){
		SignupForm formulario = new SignupForm();
		
		//parametros no nulleables
		formulario.setDni(registro.getDni().toString());
		formulario.setNombre(registro.getNombre());
		formulario.setApellido(registro.getApellido());
		formulario.setEmail(registro.getEmail());
		formulario.setRole(registro.getRole());
		
		//parametros nulleables
		if (registro.getSexo() != null)
			formulario.setSexo(registro.getSexo());
		
		if (registro.getFechaNacimiento() != null)
		formulario.setFechaNacimiento(utils.getFormatoDiaMesAnio(registro.getFechaNacimiento()));
		
		if (registro.getPreguntaSeguridad() != null)
		formulario.setPreguntaSeguridad(registro.getPreguntaSeguridad());
		
		if (registro.getRespuestaSeguridad() != null)
		formulario.setRespuestaSeguridad(registro.getRespuestaSeguridad());
		
		return formulario;
	}
	
	public HashMap<String, String> getRoles(){
		roles = new HashMap<String, String>();
		roles.put("A", "Administrador");
		roles.put("C", "Cliente");
		roles.put("G", "Gerente");
		return roles;
	}
}
