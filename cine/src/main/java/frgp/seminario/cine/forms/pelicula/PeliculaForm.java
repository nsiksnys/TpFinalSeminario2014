package frgp.seminario.cine.forms.pelicula;

public class PeliculaForm {
	//Contenido del formulario
	private String nombre;
	private String idioma;
	private boolean subs;
	private String clasificacion;
	private boolean reposicion;
	private String descripcion;
	private String actores;
	private String director;
	private String urlTrailer;
	
	//getters y setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public String getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	public boolean isReposicion() {
		return reposicion;
	}
	public void setReposicion(boolean reposicion) {
		this.reposicion = reposicion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	public String getUrlTrailer() {
		return urlTrailer;
	}
	public void setUrlTrailer(String urlTrailer) {
		this.urlTrailer = urlTrailer;
	}
}
