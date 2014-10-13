package frgp.seminario.cine.account;

import java.util.Date;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

@SuppressWarnings("serial")
@Entity
@Table(name = "Usuario")
@Inheritance(strategy=InheritanceType.JOINED)//una tabla por clase hija
@NamedQuery(name = Account.FIND_BY_EMAIL, query = "select a from Account a where a.email = :email")
public class Account implements java.io.Serializable {

	public static final String FIND_BY_EMAIL = "Account.findByEmail";

	@Id
	@Column(unique = true)
	private Long dni;

	@Column(unique = true)
	private String nombre;
	
	@Column(unique = true)
	private String apellido;
	
	@Column(unique = true)
	private String sexo;
	
	@Column(unique = true)
	private Date fechaNacimiento;
	
	@Column(unique = true)
	private String preguntaSeguridad;
	
	@Column(unique = true)
	private String email;//Username
	
	@Column(unique = true)
	@JsonIgnore
	private String password;

	@Column(unique = true)
	private String role = "ROLE_USER";//TODO: reemplazar por el rol que corresponda
	//ROLES DISPONIBLES: A=Administrador, G=Gerente, C=Cliente

	private boolean active;
	
    protected Account() {

	}
	
	public Account(String email, String password, String role) {
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public Account(Long dni, String email, String password, String role) {
		super();
		this.dni = dni;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public Account(Long dni, String nombre, String apellido, String sexo,
			Date fechaNacimiento, String preguntaSeguridad, String email,
			String password, String role) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.fechaNacimiento = fechaNacimiento;
		this.preguntaSeguridad = preguntaSeguridad;
		this.email = email;
		this.password = password;
		this.role = role;
		this.active = true;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getPreguntaSeguridad() {
		return preguntaSeguridad;
	}

	public void setPreguntaSeguridad(String preguntaSeguridad) {
		this.preguntaSeguridad = preguntaSeguridad;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean equals(Account item)
	{
		if (dni != item.getDni())
			return false;
		
		if (nombre.compareTo(item.getNombre()) != 0)
			return false;
		
		if (apellido.compareTo(item.getApellido()) != 0)
			return false;
		
		if (sexo.compareTo(item.getSexo()) != 0)
			return false;
		
		if (fechaNacimiento.compareTo(item.getFechaNacimiento()) != 0)
			return false;
		
		if (preguntaSeguridad.compareTo(item.getPreguntaSeguridad()) != 0)
			return false;
		
		if (email.compareTo(item.getEmail()) != 0)
			return false;
		
		if (password.compareTo(item.getPassword()) != 0)
			return false;
		
		if (role.compareTo(item.getRole()) != 0)
			return false;
		
		return true;
	}
}
