package frgp.seminario.cine.account;

import java.util.ArrayList;

import javax.persistence.*;
import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service("AccountRepository")
@Repository
@Transactional//(readOnly = false)
public class AccountRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Inject
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public Account save(Account account) {
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		if (!account.getRespuestaSeguridad().equals(""))
			account.setRespuestaSeguridad(passwordEncoder.encode(account.getRespuestaSeguridad()));
		entityManager.persist(account);
		return account;
	}
	
	public boolean merge(Account account) {
		try {
			entityManager.merge(account);
			entityManager.flush();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public Account findByEmail(String email) {
		try {
			return entityManager.createNamedQuery(Account.FIND_BY_EMAIL, Account.class)
					.setParameter("email", email)
					.getSingleResult();
		} catch (PersistenceException e) {
			return null;
		}
	}
	
	public String getIdByObject(Account item)
	{
		ArrayList<Account> todos =  getAll();
		
		for (Account registro : todos)
		{
			if (registro.equals(item))
				return registro.getEmail();
		}
		
		return "";
	}
	
	/**
	 * Recupera un registro de la base de datos, sin importar si esta activo o no
	 * @param email email de la cuenta
	 * @return un Account con la cuenta, o null si no lo encontro
	 */
	public Account getActiveOrInactive(Object email){
		ArrayList<Account> todos = this.getAll();
		
		for (Account item : todos){
			if (item.getEmail().equals(email))
				return item;
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<Account> getAll()
	{
		return (ArrayList<Account>) entityManager.createQuery("from Account item").getResultList();
	}	
}
