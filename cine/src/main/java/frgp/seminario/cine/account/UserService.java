package frgp.seminario.cine.account;

import java.util.Collections;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;

import frgp.seminario.cine.model.Cliente;
import frgp.seminario.cine.repository.impl.ClienteRepository;
import frgp.seminario.cine.utils.FechaUtils;

public class UserService implements UserDetailsService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private FechaUtils fecha;
	
	@PostConstruct	
	protected void initialize() {//Usuarios de prueba
		accountRepository.save(new Account(Long.parseLong("1337"), "Danny", "Pink", "M", fecha.getFechaFormatoDiaMesAnio("23/11/1963"), "animal", "blue whale", "admin@frgp.utn.edu.ar", "test", "A"));
		accountRepository.save(new Account(Long.parseLong("1234"), "Donna", "Noble", "F", fecha.getFechaFormatoDiaMesAnio("23/11/1963"), "animal", "blue whale","gerente@frgp.utn.edu.ar", "test", "G"));
		clienteRepository.save(new Cliente(Long.parseLong("7893"), "Clara", "Oswald", "F", fecha.getFechaFormatoDiaMesAnio("23/11/1963"), "animal", "blue whale" , "cliente@frgp.utn.edu.ar", "test", "32 Wallaby St", "Accion"));
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByEmail(username);
		if(account == null) {
			throw new UsernameNotFoundException("user not found");
		}
		return createUser(account);
	}
	
	public void signin(Account account) {
		SecurityContextHolder.getContext().setAuthentication(authenticate(account));
	}
	
	private Authentication authenticate(Account account) {
		return new UsernamePasswordAuthenticationToken(createUser(account), null, Collections.singleton(createAuthority(account)));		
	}
	
	private User createUser(Account account) {
		return new User(account.getEmail(), account.getPassword(), Collections.singleton(createAuthority(account)));
	}

	private GrantedAuthority createAuthority(Account account) {
		return new SimpleGrantedAuthority(account.getRole());
	}

}
