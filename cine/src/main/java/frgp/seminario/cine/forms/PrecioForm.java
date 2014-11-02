package frgp.seminario.cine.forms;

public class PrecioForm {
	private String id;
	private float menor;
	private float general;
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