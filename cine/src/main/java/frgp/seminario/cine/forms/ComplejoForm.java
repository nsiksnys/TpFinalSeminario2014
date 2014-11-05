package frgp.seminario.cine.forms;

import org.hibernate.validator.constraints.NotBlank;

public class ComplejoForm {
	private static final String NOT_BLANK_MESSAGE = "El campo no puede estar vacio.";
	
	private String id;
	@NotBlank(message = NOT_BLANK_MESSAGE)
	private String nombre;
	@NotBlank(message = NOT_BLANK_MESSAGE)
	private String direccion;
	private int salas;
	private boolean activo;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getSalas() {
		return salas;
	}
	public void setSalas(int salas) {
		this.salas = salas;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
