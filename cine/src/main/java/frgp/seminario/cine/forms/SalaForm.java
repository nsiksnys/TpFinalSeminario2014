package frgp.seminario.cine.forms;

import org.hibernate.validator.constraints.NotBlank;

public class SalaForm {
	private static final String NOT_BLANK_MESSAGE = "El campo no puede estar vacio.";
	
	private String id;
	@NotBlank(message = NOT_BLANK_MESSAGE)
	private String complejo;
	@NotBlank(message = NOT_BLANK_MESSAGE)
	private String numeroSala;
	private boolean activa;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getComplejo() {
		return complejo;
	}
	public void setComplejo(String complejo) {
		this.complejo = complejo;
	}
	public String getNumeroSala() {
		return numeroSala;
	}
	public void setNumeroSala(String numeroSala) {
		this.numeroSala = numeroSala;
	}
	public boolean isActiva() {
		return activa;
	}
	public void setActiva(boolean activa) {
		this.activa = activa;
	}
}
