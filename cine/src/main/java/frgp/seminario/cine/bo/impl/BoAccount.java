package frgp.seminario.cine.bo.impl;

import java.util.ArrayList;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;

import frgp.seminario.cine.account.Account;
import frgp.seminario.cine.account.AccountRepository;
import frgp.seminario.cine.bo.BusinessObject;
import frgp.seminario.cine.repository.impl.ClienteRepository;
import frgp.seminario.cine.signup.SignupForm;
import frgp.seminario.cine.utils.FechaUtils;

public class BoAccount implements BusinessObject<Account, SignupForm> {
	@Autowired
	AccountRepository accounts;
	
	@Autowired
	ClienteRepository clientes;
	
	@Autowired
	FechaUtils utils;
	
	@Override
	public Account get(Object email) {
		return accounts.findByEmail((String) email);
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
	
}
