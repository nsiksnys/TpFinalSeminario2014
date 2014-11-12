package frgp.seminario.cine.forms;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

public class PromocionForm {
	private static final String NOT_BLANK_MESSAGE = "El campo no puede estar vacio.";
	
	private String id;
	@NotBlank(message = NOT_BLANK_MESSAGE)
	private String nombre;
	@NotBlank(message = NOT_BLANK_MESSAGE)
	private String descripcion;
	@NotBlank(message = NOT_BLANK_MESSAGE)
	private Date fechainicio;
	@NotBlank(message = NOT_BLANK_MESSAGE)
	private Date fechafin;
	
	
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFechainicio() {
		return fechainicio;
	}
	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}
	public Date getFechafin() {
		return fechafin;
	}
	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}
}