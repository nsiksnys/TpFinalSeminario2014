package frgp.seminario.cine.forms;

public class PeliculaForm {
	//Contenido del formulario
	private String titulo;
	private String actores;
	private String director;
	private String sinopsis;
	private String trailer;
	private String idioma;
	private boolean subs;
	private boolean reposicion;
	private String clasificacion;
	
	//getters y setters
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
}
