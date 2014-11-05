package frgp.seminario.cine.signup;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import frgp.seminario.cine.account.*;
import frgp.seminario.cine.model.Cliente;
import frgp.seminario.cine.repository.impl.ClienteRepository;
import frgp.seminario.cine.support.web.*;

@Controller
public class SignupController {
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private UserService userService;
	
	private static final Logger LOG = LoggerFactory.getLogger(SignupController.class);
	
	@RequestMapping(value = "signup")
	public SignupClientForm signup() {
		return new SignupClientForm();
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signup(@Valid @ModelAttribute SignupClientForm signupForm, Errors errors, RedirectAttributes ra) {
		if (errors.hasErrors()) {
			LOG.error("/signup: Por favor revise el formulario");
			return null;
		}
		
		Account account = repository.save(signupForm.createAccount());
		userService.signin(account);
		
		LOG.info("/signup: La cuenta se creo con exito. Usuario: " + account.getEmail());

        MessageHelper.addSuccessAttribute(ra, "La cuenta se creo con exito.");
		
		return "redirect:/";
	}
}
