package frgp.seminario.cine.forms;

public class FuncionForm {
	private Long id;
	private Long sala;
	private Long complejo;
	private Long pelicula;
	private Long horario;
/*	private String nombreComplejo;
	private String nombreSala;
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
	public Long getSala() {
		return sala;
	}
	public void setSala(Long sala) {
		this.sala = sala;
	}
	public Long getComplejo() {
		return complejo;
	}
	public void setComplejo(Long complejo) {
		this.complejo = complejo;
	}
	public Long getPelicula() {
		return pelicula;
	}
	public void setPelicula(Long pelicula) {
		this.pelicula = pelicula;
	}
	public Long getHorario() {
		return horario;
	}
	public void setHorario(Long horario) {
		this.horario = horario;
	}
/*	public String getNombreComplejo() {
		return nombreComplejo;
	}
	public void setNombreComplejo(String nombreComplejo) {
		this.nombreComplejo = nombreComplejo;
	}
	public String getNombreSala() {
		return nombreSala;
	}
	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
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
