package frgp.seminario.cine.forms;

public class SalaForm {
	private String id;
	private String complejo;
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
