package frgp.seminario.cine.forms;

import org.hibernate.validator.constraints.NotBlank;

public class PrecioForm {
	private static final String NOT_BLANK_MESSAGE = "El campo no puede estar vacio.";
	
	private String id;
	@NotBlank(message = NOT_BLANK_MESSAGE)
	private float menor;
	@NotBlank(message = NOT_BLANK_MESSAGE)
	private float general;
	@NotBlank(message = NOT_BLANK_MESSAGE)
	private float mayor;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public float getMenor() {
		return menor;
	}
	public void setMenor(float menor) {
		this.menor = menor;
	}
	public float getGeneral() {
		return general;
	}
	public void setGeneral(float general) {
		this.general = general;
	}
	public float getMayor() {
		return mayor;
	}
	public void setMayor(float mayor) {
		this.mayor = mayor;
	}
}