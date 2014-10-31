package frgp.seminario.cine.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Complejo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nombre;
	
	@Column(nullable = false)
	private String direccion;
	
	@OneToMany
	private List<Sala> salas;
	
	@Column(nullable = false)
	private boolean activo;
	
	public Complejo()
	{
		//constructor vacio
	}

	public Complejo(Long id, String nombre, String direccion, boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.activo = activo;
	}

	public Complejo(String nombre, String direccion) {
		this.nombre = nombre;
		this.direccion = direccion;
	}
	
	public Complejo(String nombre, String direccion, List<Sala> salas) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.salas = salas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public List<Sala> getSalas() {
		return salas;
	}

	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public boolean equals(Complejo item)
	{
		if (nombre.compareTo(item.getNombre()) != 0)
			return false;
		
		if (direccion.compareTo(item.getDireccion()) != 0)
			return false;
		
		//TODO: comparar la list de salas.
		
		return true;
	}
}
