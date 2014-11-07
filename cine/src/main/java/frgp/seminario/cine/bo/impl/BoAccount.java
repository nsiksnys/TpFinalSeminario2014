package frgp.seminario.cine.bo.impl;

import java.util.ArrayList;
import java.util.HashMap;

import javax.management.relation.RoleStatus;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import frgp.seminario.cine.account.Account;
import frgp.seminario.cine.account.AccountRepository;
import frgp.seminario.cine.bo.BusinessObject;
import frgp.seminario.cine.model.Cliente;
import frgp.seminario.cine.repository.impl.ClienteRepository;
import frgp.seminario.cine.signup.SignupForm;
import frgp.seminario.cine.utils.FechaUtils;

//Funciones pertenecientes a la logica de negocios
@Service("BoAccount") //agrego el nombre del bean, para que al momento de llamar al Autowired pueda aclarar cual quiero
public class BoAccount implements BusinessObject<Account, SignupForm> {
	@Autowired
	AccountRepository accounts;
	
	@Autowired
	ClienteRepository clientes;
	
	@Autowired
	FechaUtils utils;
	
	private HashMap<String, String> roles;
	
	
	@Override
	public Account get(Object email) {
		return accounts.findByEmail((String) email);
	}
	
	public Cliente getCliente(Object email){
		return clientes.get(Cliente.class, email);
	}

	@Override
	public boolean guardar(Account registro) {
		try
		{
			if (registro.getRole().equals("C"))//si el usuario es un cliente
				clientes.save(new Cliente(registro));
			else
				accounts.save(registro);
		}
		catch (PersistenceException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean modificar(Account registro) {
		return accounts.merge(registro);
	}

	@Override
	public boolean desactivar(Account registro) {
		//TODO: verificaciones propias de esta clase
		
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
		// TODO verificaciones propias de la clase
		return true;
	}

	@Override
	public Account formToEntity(SignupForm formulario) {
		return new Account(	Long.parseLong(formulario.getDni()), 
							formulario.getNombre(), 
							formulario.getApellido(), 
							formulario.getSexo(),
							utils.getFechaFormatoDiaMesAnio(formulario.getFechaNacimiento()),
							formulario.getPreguntaSeguridad(),
							formulario.getRespuestaSeguridad(),
							formulario.getEmail(),
							formulario.getPassword(),
							formulario.getRole());
	}
	
	public SignupForm entityToForm(Account registro){
		SignupForm formulario = new SignupForm();
		formulario.setDni(registro.getDni().toString());
		formulario.setNombre(registro.getNombre());
		formulario.setApellido(registro.getApellido());
		formulario.setSexo(registro.getSexo());
		formulario.setFechaNacimiento(utils.getFormatoDiaMesAnio(registro.getFechaNacimiento()));
		formulario.setPreguntaSeguridad(registro.getPreguntaSeguridad());
		formulario.setRespuestaSeguridad(registro.getRespuestaSeguridad());
		formulario.setEmail(registro.getEmail());
		formulario.setRole(registro.getRole());
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
