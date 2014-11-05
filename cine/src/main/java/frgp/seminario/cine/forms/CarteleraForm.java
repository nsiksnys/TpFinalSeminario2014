package frgp.seminario.cine.forms;

import org.hibernate.validator.constraints.NotBlank;

public class CarteleraForm {
	private static final String NOT_BLANK_MESSAGE = "El campo no puede estar vacio.";
	
	private String id;
	@NotBlank(message = NOT_BLANK_MESSAGE)
	private String pelicula;
	@NotBlank(message = NOT_BLANK_MESSAGE)
	private String version;
	private boolean subtitulos;
	@NotBlank(message = NOT_BLANK_MESSAGE)
	private String inicio;
	@NotBlank(message = NOT_BLANK_MESSAGE)
	private String fin;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPelicula() {
		return pelicula;
	}
	public void setPelicula(String pelicula) {
		this.pelicula = pelicula;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public boolean isSubtitulos() {
		return subtitulos;
	}
	public void setSubtitulos(boolean subtitulos) {
		this.subtitulos = subtitulos;
	}
	public String getInicio() {
		return inicio;
	}
	public void setInicio(String inicio) {
		this.inicio = inicio;
	}
	public String getFin() {
		return fin;
	}
	public void setFin(String fin) {
		this.fin = fin;
	}
}
