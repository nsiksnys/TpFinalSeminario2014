package frgp.seminario.cine.forms;

import org.hibernate.validator.constraints.NotBlank;

public class FuncionForm {
	private static final String NOT_BLANK_MESSAGE = "El campo no puede estar vacio.";
	
	private Long id;
	@NotBlank(message = NOT_BLANK_MESSAGE)
	private Long salas;
	//@NotBlank(message = NOT_BLANK_MESSAGE)
	private Long complejos;
	@NotBlank(message = NOT_BLANK_MESSAGE)
	private Long peliculas;
	@NotBlank(message = NOT_BLANK_MESSAGE)
	private Long horarios;
/*	private String nombreComplejo;
	private String nombresala;
	private String nombrePelicula;*/
	private String inicio;
	private String fin;
	private boolean activo;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getsalas() {
		return salas;
	}
	public void setsalas(Long salas) {
		this.salas = salas;
	}
	public Long getComplejos() {
		return complejos;
	}
	public void setComplejos(Long complejos) {
		this.complejos = complejos;
	}
	public Long getPeliculas() {
		return peliculas;
	}
	public void setPeliculas(Long peliculas) {
		this.peliculas = peliculas;
	}
	public Long getHorarios() {
		return horarios;
	}
	public void setHorarios(Long horarios) {
		this.horarios = horarios;
	}
/*	public String getNombreComplejo() {
		return nombreComplejo;
	}
	public void setNombreComplejo(String nombreComplejo) {
		this.nombreComplejo = nombreComplejo;
	}
	public String getNombresala() {
		return nombresala;
	}
	public void setNombresala(String nombresala) {
		this.nombresala = nombresala;
	}
	public String getNombrePelicula() {
		return nombrePelicula;
	}
	public void setNombrePelicula(String nombrePelicula) {
		this.nombrePelicula = nombrePelicula;
	}*/
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
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
