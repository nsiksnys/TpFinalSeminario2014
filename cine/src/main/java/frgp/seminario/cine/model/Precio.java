package frgp.seminario.cine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Precio {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String descripcion;
	
	@Column(nullable=false)
	private float precio;

	public Precio(Long id, String descripcion, float precio) {
		this.id = id;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public Precio(String descripcion, float precio) {
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public boolean equals(Precio item)
	{
		if (descripcion.compareTo(item.getDescripcion()) != 0)
			return false;
		
		if (precio != item.getPrecio())
			return false;
		
		return true;
	}
}
