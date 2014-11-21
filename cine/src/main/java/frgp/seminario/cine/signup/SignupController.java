package frgp.seminario.cine.signup;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import frgp.seminario.cine.account.*;
import frgp.seminario.cine.support.web.*;

@Controller
public class SignupController {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "signup")
	public SignupClientForm signup() {
		return new SignupClientForm();
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signup(@Valid @ModelAttribute SignupClientForm signupForm, Errors errors, RedirectAttributes ra) {
		if (errors.hasErrors()) {
			return null;
		}
		
		Account account = accountRepository.save(signupForm.createAccount());
		userService.signin(account);

        MessageHelper.addSuccessAttribute(ra, "El usuario fue creado.");
		
		return "redirect:/";
	}
}
