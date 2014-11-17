package frgp.seminario.cine.forms;

import java.sql.Time;

import org.hibernate.validator.constraints.NotBlank;

public class PeliculaForm {
	private static final String NOT_BLANK_MESSAGE = "El campo no puede estar vacio.";
	
	//Contenido del formulario
	private String id;
	@NotBlank(message = NOT_BLANK_MESSAGE)
	private String titulo;
	@NotBlank(message = NOT_BLANK_MESSAGE)
	private String actores;
	@NotBlank(message = NOT_BLANK_MESSAGE)
	private String director;
	@NotBlank(message = NOT_BLANK_MESSAGE)
	private String sinopsis;
	private String trailer;
	@NotBlank(message = NOT_BLANK_MESSAGE)
	private String idioma;
	private boolean subs;
	private boolean reposicion;
	@NotBlank(message = NOT_BLANK_MESSAGE)
	private String clasificacion;
	@NotBlank(message = NOT_BLANK_MESSAGE)
	private Time duracion;
	
	//getters y setters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getActores() {
		return actores;
	}
	public void setActores(String actores) {
		this.actores = actores;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getSinopsis() {
		return sinopsis;
	}
	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}
	public String getTrailer() {
		return trailer;
	}
	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public boolean isSubs() {
		return subs;
	}
	public void setSubs(boolean subs) {
		this.subs = subs;
	}
	public boolean isReposicion() {
		return reposicion;
	}
	public void setReposicion(boolean reposicion) {
		this.reposicion = reposicion;
	}
	public String getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	public Time getDuracion() {
		return duracion;
	}
	public void setDuracion(Time duracion) {
		this.duracion = duracion;
	}
}
