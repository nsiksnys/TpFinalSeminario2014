package frgp.seminario.cine.signup;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import frgp.seminario.cine.account.*;
import frgp.seminario.cine.model.Cliente;
import frgp.seminario.cine.repository.impl.ClienteRepository;
import frgp.seminario.cine.support.web.*;
import frgp.seminario.cine.utils.FechaUtils;

@Controller
public class SignupController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FechaUtils utils;
	
	@RequestMapping(value = "signup")
	public SignupForm signup() {
		return new SignupForm();
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute SignupForm formulario, RedirectAttributes ra) {
		Account account = clienteRepository.save(
				new Cliente(Long.parseLong(formulario.getDni()), formulario.getNombre(), formulario.getApellido(), formulario.getSexo(), 
        		utils.getFechaFormatoDiaMesAnio(formulario.getFechaNacimiento()), formulario.getPreguntaSeguridad(), 
        		formulario.getRespuestaSeguridad(), formulario.getEmail(), formulario.getPassword(), formulario.getDireccion(),
        		formulario.getGenero()));
		
		userService.signin(account);

        MessageHelper.addSuccessAttribute(ra, "El usuario fue creado.");
		
		return "redirect:/";
	}
}
