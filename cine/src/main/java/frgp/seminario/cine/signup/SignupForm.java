package frgp.seminario.cine.signup;

import org.hibernate.validator.constraints.*;

public class SignupForm {

	private static final String NOT_BLANK_MESSAGE = "El campo no puede estar vacio.";
	private static final String EMAIL_MESSAGE = "Por favor ingrese un mail valido.";

	@NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	private String dni;
	
	@NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	private String nombre;
	
	@NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	private String apellido;
	
    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	@Email(message = SignupForm.EMAIL_MESSAGE)
	private String email;

    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	private String password;
    
    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
    private String sexo;
    
    private String preguntaSeguridad;
    
    private String respuestaSeguridad;
    
    private String role;
    
    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
    private String fechaNacimiento;
    
    private String genero;
    
    private String direccion;

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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getPreguntaSeguridad() {
		return preguntaSeguridad;
	}

	public void setPreguntaSeguridad(String preguntaSeguridad) {
		this.preguntaSeguridad = preguntaSeguridad;
	}

	public String getRespuestaSeguridad() {
		return respuestaSeguridad;
	}

	public void setRespuestaSeguridad(String respuestaSeguridad) {
		this.respuestaSeguridad = respuestaSeguridad;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}
