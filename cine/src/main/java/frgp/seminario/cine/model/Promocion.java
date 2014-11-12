package frgp.seminario.cine.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Promocion {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String nombre;
	
	@Column(nullable=false)
	private String descripcion;
	
	@OneToMany
	private List<Complejo> cines;
	
	@Column(nullable=false)
	private Date fechaInicio;
	
	@Column(nullable=false)
	private Date fechaFin;
	
	@Column(nullable=false)
	private boolean activo;

	public Promocion(Long id, String nombre, String descripcion,
			List<Complejo> cines, Date fechaInicio, Date fechaFin,
			boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cines = cines;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.activo = activo;
	}

	public Promocion(String nombre, String descripcion, List<Complejo> cines,
			Date fechaInicio, Date fechaFin) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cines = cines;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.activo = true;
	}
	
	public Promocion(String nombre, String descripcion, Date fechaInicio, Date fechaFin) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.activo = true;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Complejo> getCines() {
		return cines;
	}

	public void setCines(List<Complejo> cines) {
		this.cines = cines;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public boolean equals(Promocion item)
	{
		if (nombre.compareTo(item.getNombre()) != 0)
			return false;
		
		if (descripcion.compareTo(item.getDescripcion()) != 0)
			return false;
		
		if (!isMismaLista(this.cines, item.getCines()))
			return false;
		
		if (fechaInicio.compareTo(item.getFechaInicio()) != 0)
			return false;
		
		if (fechaFin.compareTo(item.getFechaFin()) != 0)
			return false;
		
		if (activo != item.isActivo())
			return false;
		
		return true;
	}
	
	public boolean isMismaLista(List<Complejo> thisCines, List<Complejo> itemCines)
	{
		int cuenta = 0;
		for (int i = 0; i < thisCines.size(); i++) {// uso un for tradicional para evitar que busque un null en elementos
			for (int j = 0; j < thisCines.size(); j++) {
				if (thisCines.get(i).getId() == itemCines.get(j).getId())// comparo los ids de los complejos
					cuenta++;
			}
		}
		if (cuenta == thisCines.size())
			return true;
		return false;
	}
}
