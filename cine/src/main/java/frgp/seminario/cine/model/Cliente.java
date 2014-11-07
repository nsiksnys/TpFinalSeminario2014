package frgp.seminario.cine.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import frgp.seminario.cine.account.Account;

@SuppressWarnings("serial")
@Entity
@PrimaryKeyJoinColumn(name="email")
public class Cliente extends Account{
	@Column(nullable=true)
	private String direccion="";
	
	@Column(nullable=true)
	private String generoPreferido="";
	
	public Cliente(){
		//Constructor vacio
	}

	public Cliente(Long dni, String nombre, String apellido, String sexo,
			Date fechaNacimiento, String preguntaSeguridad, String respuestaSeguridad, String email,
			String password, String direccion,
			String generoPreferido) {
		super(dni, nombre, apellido, sexo, fechaNacimiento, preguntaSeguridad, respuestaSeguridad, email, password, "C");
		this.direccion = direccion;
		this.generoPreferido = generoPreferido;
	}

	public Cliente(Long dni, String nombre, String apellido, String email, String password,
			String direccion, String generoPreferido) {
		super(dni, nombre, apellido, email, password, "C");
		this.direccion = direccion;
		this.generoPreferido = generoPreferido;
	}
	
	public Cliente(Long dni, String nombre, String apellido, String email, String password) {
		super(dni, nombre, apellido, email, password, "C");
		this.direccion = "";
		this.generoPreferido = "";
	}

	public Cliente(Account registro) {
		super(registro.getDni(), registro.getNombre(), registro.getApellido(), registro.getEmail(), registro.getPassword(), "C");
		this.direccion = "";
		this.generoPreferido = "";
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getGeneroPreferido() {
		return generoPreferido;
	}

	public void setGeneroPreferido(String generoPreferido) {
		this.generoPreferido = generoPreferido;
	}
	
	public boolean equals(Cliente item)
	{
		if (!super.equals(item))
			return false;
		
		if (direccion.compareTo(item.getDireccion()) != 0)
			return false;
		
		if (generoPreferido.compareTo(item.getGeneroPreferido()) != 0)
			return false;
		
		return true;
	}
}
