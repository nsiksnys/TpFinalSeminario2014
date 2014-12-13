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
/*		accountRepository.save(new Account(Long.parseLong("1337"), "Danny", "Pink", "M", fecha.getFechaFormatoDiaMesAnio("23/11/1963"), "pregunta", "respuesta", "admin@frgp.utn.edu.ar", "test", "A"));
		accountRepository.save(new Account(Long.parseLong("1234"), "Donna", "Noble", "F", fecha.getFechaFormatoDiaMesAnio("23/11/1963"), "pregunta", "respuesta","gerente@frgp.utn.edu.ar", "test", "G"));
		clienteRepository.save(new Cliente(Long.parseLong("7893"), "Clara", "Oswald", "F", fecha.getFechaFormatoDiaMesAnio("23/11/1963"), "pregunta", "respuesta" , "cliente@frgp.utn.edu.ar", "test", "32 Wallaby St", "Accion"));
		clienteRepository.save(new Cliente(Long.parseLong("123456"), "Juan", "Perez", "M", fecha.getFechaFormatoDiaMesAnio("01/11/1988"), "pregunta", "respuesta" , "prueba@frgp.utn.edu.ar", "test", "32 Wallaby St", "Misterio", false));
		clienteRepository.save(new Cliente(Long.parseLong("49293522"),"Shannon","Yang","M",fecha.getFechaFormatoDiaMesAnio("31/12/1969"),"pregunta","respuesta","Shannon.Yang@prueba.com","test","507-5256 Bibendum Avenue","Accion",true));
		clienteRepository.save(new Cliente(Long.parseLong("41169745"),"Dylan","Maldonado","M",fecha.getFechaFormatoDiaMesAnio("31/12/1969"),"pregunta","respuesta","Dylan.Maldonado@prueba.com","test","Ap #660-2181 Et Rd.","Accion",false));
		clienteRepository.save(new Cliente(Long.parseLong("93584437"),"Maile","Morin","M",fecha.getFechaFormatoDiaMesAnio("31/12/1969"),"pregunta","respuesta","Maile.Morin@prueba.com","test","Ap #437-8684 Semper, Av.","Accion",false));
		clienteRepository.save(new Cliente(Long.parseLong("52247469"),"Jordan","Jensen","M",fecha.getFechaFormatoDiaMesAnio("31/12/1969"),"pregunta","respuesta","Jordan.Jensen@prueba.com","test","Ap #806-6310 Rhoncus. Av.","Accion",false));
		clienteRepository.save(new Cliente(Long.parseLong("82672493"),"Josiah","Brady","M",fecha.getFechaFormatoDiaMesAnio("31/12/1969"),"pregunta","respuesta","Josiah.Brady@prueba.com","test","1640 Adipiscing Street","Accion",true));
		clienteRepository.save(new Cliente(Long.parseLong("72572694"),"Hedy","Bradshaw","M",fecha.getFechaFormatoDiaMesAnio("31/12/1969"),"pregunta","respuesta","Hedy.Bradshaw@prueba.com","test","Ap #134-9782 Auctor St.","Accion",false));
		clienteRepository.save(new Cliente(Long.parseLong("19957625"),"Hunter","Patel","M",fecha.getFechaFormatoDiaMesAnio("31/12/1969"),"pregunta","respuesta","Hunter.Patel@prueba.com","test","4638 Mauris Avenue","Accion",true));
		clienteRepository.save(new Cliente(Long.parseLong("99467266"),"Virginia","Ball","F",fecha.getFechaFormatoDiaMesAnio("31/12/1969"),"pregunta","respuesta","Virginia.Ball@prueba.com","test","Ap #863-6958 Lorem Av.","Accion",true));
		clienteRepository.save(new Cliente(Long.parseLong("26912797"),"Alvin","Bennett","F",fecha.getFechaFormatoDiaMesAnio("31/12/1969"),"pregunta","respuesta","Alvin.Bennett@prueba.com","test","P.O. Box 651, 892 Malesuada Rd.","Accion",false));
		clienteRepository.save(new Cliente(Long.parseLong("14381328"),"Zephr","Suarez","F",fecha.getFechaFormatoDiaMesAnio("31/12/1969"),"pregunta","respuesta","Zephr.Suarez@prueba.com","test","P.O. Box 881, 5482 Massa Rd.","Accion",false));
		clienteRepository.save(new Cliente(Long.parseLong("83126992"),"Charissa","Macdonald","F",fecha.getFechaFormatoDiaMesAnio("31/12/1969"),"pregunta","respuesta","Charissa.Macdonald@prueba.com","test","P.O. Box 576, 9258 Sed, Av.","Accion",false));
		clienteRepository.save(new Cliente(Long.parseLong("96261658"),"Nehru","Flores","F",fecha.getFechaFormatoDiaMesAnio("31/12/1969"),"pregunta","respuesta","Nehru.Flores@prueba.com","test","745-4144 Sit Av.","Accion",true));
		clienteRepository.save(new Cliente(Long.parseLong("91712162"),"Madeline","Bright","F",fecha.getFechaFormatoDiaMesAnio("31/12/1969"),"pregunta","respuesta","Madeline.Bright@prueba.com","test","890-2915 Ad St.","Misterio",false));
		clienteRepository.save(new Cliente(Long.parseLong("51822956"),"Phillip","Jennings","F",fecha.getFechaFormatoDiaMesAnio("31/12/1969"),"pregunta","respuesta","Phillip.Jennings@prueba.com","test","2405 Integer Rd.","Misterio",true));
		clienteRepository.save(new Cliente(Long.parseLong("21148747"),"Emily","Moran","M",fecha.getFechaFormatoDiaMesAnio("31/12/1969"),"pregunta","respuesta","Emily.Moran@prueba.com","test","1370 Nullam Street","Misterio",false));
		clienteRepository.save(new Cliente(Long.parseLong("61444967"),"Hamish","Branch","M",fecha.getFechaFormatoDiaMesAnio("31/12/1969"),"pregunta","respuesta","Hamish.Branch@prueba.com","test","2184 Amet, Rd.","Misterio",false));
		clienteRepository.save(new Cliente(Long.parseLong("31967618"),"Channing","Saunders","M",fecha.getFechaFormatoDiaMesAnio("31/12/1969"),"pregunta","respuesta","Channing.Saunders@prueba.com","test","7428 Cursus. Road","Misterio",false));
		clienteRepository.save(new Cliente(Long.parseLong("15144683"),"Melvin","Burns","M",fecha.getFechaFormatoDiaMesAnio("31/12/1969"),"pregunta","respuesta","Melvin.Burns@prueba.com","test","3348 Mi Rd.","Misterio",false));
		clienteRepository.save(new Cliente(Long.parseLong("82732926"),"Rhea","Kirkland","M",fecha.getFechaFormatoDiaMesAnio("31/12/1969"),"pregunta","respuesta","Rhea.Kirkland@prueba.com","test","152-5625 Integer Rd.","Misterio",false));
		clienteRepository.save(new Cliente(Long.parseLong("45161489"),"Reese","Mcintosh","M",fecha.getFechaFormatoDiaMesAnio("31/12/1969"),"pregunta","respuesta","Reese.Mcintosh@prueba.com","test","Ap #231-8566 Non, Ave","Misterio",false));
		clienteRepository.save(new Cliente(Long.parseLong("36114643"),"Sigourney","Snyder","M",fecha.getFechaFormatoDiaMesAnio("31/12/1969"),"pregunta","respuesta","Sigourney.Snyder@prueba.com","test","P.O. Box 243, 5895 Aliquam Rd.","Misterio",true));
		clienteRepository.save(new Cliente(Long.parseLong("88289571"),"Caesar","Dickson","F",fecha.getFechaFormatoDiaMesAnio("31/12/1969"),"pregunta","respuesta","Caesar.Dickson@prueba.com","test","415-2337 Sit Av.","Misterio",true));
		clienteRepository.save(new Cliente(Long.parseLong("93214454"),"Ariel","Ochoa","F",fecha.getFechaFormatoDiaMesAnio("31/12/1969"),"pregunta","respuesta","Ariel.Ochoa@prueba.com","test","Ap #839-2262 Lacus, St.","Misterio",false));
		clienteRepository.save(new Cliente(Long.parseLong("66275832"),"Brianna","Prince","F",fecha.getFechaFormatoDiaMesAnio("31/12/1969"),"pregunta","respuesta","Brianna.Prince@prueba.com","test","725-4070 Vivamus Rd.","Misterio",true));
		clienteRepository.save(new Cliente(Long.parseLong("83395372"),"Logan","Chase","F",fecha.getFechaFormatoDiaMesAnio("31/12/1969"),"pregunta","respuesta","Logan.Chase@prueba.com","test","8165 Est. Ave","Aventuras",true));
		clienteRepository.save(new Cliente(Long.parseLong("47831324"),"Whitney","Vaughn","F",fecha.getFechaFormatoDiaMesAnio("31/12/1969"),"pregunta","respuesta","Whitney.Vaughn@prueba.com","test","519-4280 Auctor Av.","Aventuras",false));
		clienteRepository.save(new Cliente(Long.parseLong("61123281"),"Reuben","Jenkins","F",fecha.getFechaFormatoDiaMesAnio("31/12/1969"),"pregunta","respuesta","Reuben.Jenkins@prueba.com","test","P.O. Box 419, 4307 Rhoncus. Street","Aventuras",false));
		clienteRepository.save(new Cliente(Long.parseLong("18316286"),"Charissa","Chaney","F",fecha.getFechaFormatoDiaMesAnio("31/12/1969"),"pregunta","respuesta","Charissa.Chaney@prueba.com","test","248-7905 At, Avenue","Aventuras",false));
*/
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
