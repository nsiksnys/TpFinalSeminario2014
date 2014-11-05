package frgp.seminario.cine.forms;

import org.hibernate.validator.constraints.NotBlank;

public class ReservaForm {
	private static final String NOT_BLANK_MESSAGE = "El campo no puede estar vacio.";
	
	private String id;
	private String codigo;
	@NotBlank(message = NOT_BLANK_MESSAGE)
	private String complejo;
	@NotBlank(message = NOT_BLANK_MESSAGE)
	private String pelicula;
	@NotBlank(message = NOT_BLANK_MESSAGE)
	private String fecha;
	@NotBlank(message = NOT_BLANK_MESSAGE)
	private String horario;
	@NotBlank(message = NOT_BLANK_MESSAGE)
	private String total;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getComplejo() {
		return complejo;
	}
	public void setComplejo(String complejo) {
		this.complejo = complejo;
	}
	public String getPelicula() {
		return pelicula;
	}
	public void setPelicula(String pelicula) {
		this.pelicula = pelicula;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
}