package frgp.seminario.cine.forms;

public class ComplejoForm {
	private String id;
	private String nombre;
	private String direccion;
	private int salas;
	private boolean activo;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getSalas() {
		return salas;
	}
	public void setSalas(int salas) {
		this.salas = salas;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
