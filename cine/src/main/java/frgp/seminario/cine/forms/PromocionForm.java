package frgp.seminario.cine.forms;

public class PromocionForm {
	
	private String id;
	private String nombre;
	private String descripcion;
	private String inicio;
	private String fin;
	
	
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getinicio() {
		return inicio;
	}
	public void setFechainicio(String fechainicio) {
		this.inicio = fechainicio;
	}
	public String getfin() {
		return fin;
	}
	public void setFechafin(String fechafin) {
		this.fin = fechafin;
	}
}