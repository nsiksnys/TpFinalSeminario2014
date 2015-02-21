package frgp.seminario.cine.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Sala> salas;
	
	@Column(nullable = false)
	private boolean activo=true;
	
	public Complejo()
	{
		//constructor vacio
	}

	public Complejo(Long id, String nombre, String direccion, boolean activo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.activo = activo;
	}

	public Complejo(String nombre, String direccion) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
	}
	
	public Complejo(String nombre, String direccion, List<Sala> salas) {
		super();
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
		
		if (!isMismaLista(salas, item.getSalas()))
			return false;
		
		return true;
	}
	
	public boolean isMismaLista(List<Sala> thisSalas, List<Sala> registroSalas)
	{
		int cuenta = 0;
		
		if (registroSalas == null)
		{
			if (salas.size() == 0)
				return true;
			return false;
		}
		
		for (int i = 0; i < thisSalas.size(); i++) {// uso un for tradicional para evitar que busque un null en elementos
			for (int j = 0; j < thisSalas.size(); j++) {
				if (thisSalas.get(i).getId() == registroSalas.get(j).getId())// comparo los ids de las salas
					cuenta++;
			}
		}
		if (cuenta == registroSalas.size())
			return true;
		return false;
	}
}