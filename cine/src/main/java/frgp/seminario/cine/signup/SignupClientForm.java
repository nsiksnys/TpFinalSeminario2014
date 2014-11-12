package frgp.seminario.cine.signup;

import org.hibernate.validator.constraints.*;

import frgp.seminario.cine.model.Cliente;

public class SignupClientForm {

	private static final String NOT_BLANK_MESSAGE = "El campo no puede estar vacio.";
	private static final String EMAIL_MESSAGE = "Por favor ingrese un mail valido.";

	@NotBlank(message = SignupClientForm.NOT_BLANK_MESSAGE)
	private String dni;
	
	@NotBlank(message = SignupClientForm.NOT_BLANK_MESSAGE)
	private String nombre;
	
	@NotBlank(message = SignupClientForm.NOT_BLANK_MESSAGE)
	private String apellido;
	
    @NotBlank(message = SignupClientForm.NOT_BLANK_MESSAGE)
	@Email(message = SignupClientForm.EMAIL_MESSAGE)
	private String email;

    @NotBlank(message = SignupClientForm.NOT_BLANK_MESSAGE)
	private String password;

    
    public Cliente createAccount() {
        return new Cliente(Long.parseLong(getDni()), getNombre(), getApellido(), getEmail(), getPassword());
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
