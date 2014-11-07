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
@Transactional(readOnly = true)
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
	
	@SuppressWarnings("unchecked")
	public ArrayList<Account> getAll()
	{
		return (ArrayList<Account>) entityManager.createQuery("from Account item").getResultList();
	}	
}
