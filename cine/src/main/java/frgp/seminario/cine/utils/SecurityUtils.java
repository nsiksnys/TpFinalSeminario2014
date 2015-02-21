package frgp.seminario.cine.utils;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import frgp.seminario.cine.account.Account;
import frgp.seminario.cine.bo.impl.BoAccount;

@Service
public class SecurityUtils {
	@Autowired
	private BoAccount account;

/**
 * Verifica que el rol de un usuario dado coincida con el que se espera
 * @param principal Objeto Principal
 * @param expectedRole rol de la funcion a la que se quiere acceder.
 * @throws import org.springframework.security.access.AccessDeniedException si los roles no coinciden.
 */
	public void isAuthorized(Principal principal, String expectedRole){
		Account cuenta = account.get(principal.getName());
		
		if (cuenta == null)
			return;
		
		//si el rol de la cuenta no coincide con el rol esperado se genera la excepcion
		if (!cuenta.getRole().equals(expectedRole))
			throw new AccessDeniedException("Se esperaba el rol " + expectedRole + ", pero " + cuenta.getEmail() + " tiene el rol " + cuenta.getRole() );
		
		return;
	}
}
