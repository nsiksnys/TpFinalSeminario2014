package frgp.seminario.cine.repository.impl;

import java.util.ArrayList;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import frgp.seminario.cine.account.Account;
import frgp.seminario.cine.account.AccountRepository;
import frgp.seminario.cine.dataAccess.DataAccess;
import frgp.seminario.cine.model.Cliente;

@Repository
@Transactional()
public class ClienteRepository {
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	DataAccess dataAccess;
	
	@Inject
	private PasswordEncoder passwordEncoder;
	
	/** 
	 ** Busca un registro en específico.
	 ** @param entityClass la clase de la entidad Cliente
	 ** @param id el id del objeto buscado
	 ** @return el registro Cliente buscado (no olvidar de castear!)
	 **/
	@SuppressWarnings("rawtypes")
	public Cliente get(Class entityClass, Object id) {
		return (Cliente) dataAccess.get(entityClass, id);
	}

	/**
	 ** Recupera todos los registros de la clase Cliente. 
	 ** @param entityClass la clase de la entidad Cliente
	 ** @return un ArrayList con todos los registros de esta entidad
	 **/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<Cliente> getAll(Class entityClass) {
		return (ArrayList<Cliente>) dataAccess.getAll(entityClass);
	}

	/**
	 ** Persiste un registro en la base de datos.
	 ** @param registro El objeto a persistir.
	 ** @return objeto Account con la cuenta si se realizo con exito, null si hubo una excepcion.
	 **/
	@Transactional
	public Account save(Cliente registro) {
		registro.setPassword(passwordEncoder.encode(registro.getPassword()));
		if(!dataAccess.save(registro))
			return null;
		else
			return accountRepository.findByEmail(registro.getEmail());
	}

	/**
	 ** Mergea un registro en la base de datos.
	 ** @param registro El objeto a mergear.
	 ** @return true si se realizo con exito, false si hubo una excepcion.
	 **/
	public boolean merge(Cliente registro) {
		return dataAccess.merge(registro);
	}
}
