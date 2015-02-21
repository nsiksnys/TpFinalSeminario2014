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
	private List<Complejo> complejo;
	
	@Column(nullable=false)
	private Date fechaInicio;
	
	@Column(nullable=false)
	private Date fechaFin;
	
	@Column(nullable=false)
	private boolean activo;
	
	public Promocion() {
		//constructor por defecto
	}

	public Promocion(Long id, String nombre, String descripcion, List<Complejo> complejo, Date fechaInicio, Date fechaFin, boolean activo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.complejo = complejo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.activo = activo;
	}

	public Promocion(String nombre, String descripcion, List<Complejo> complejo, Date fechaInicio, Date fechaFin) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.complejo = complejo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.activo = true;
	}
	
	public Promocion(String nombre, String descripcion, Date fechaInicio, Date fechaFin) {
		super();
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


	public List<Complejo> getComplejo() {
		return complejo;
	}

	public void setComplejo(List<Complejo> complejo) {
		this.complejo = complejo;
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
	
	
	public boolean equals(Promocion registro)
	{
		if (nombre.compareTo(registro.getNombre()) != 0)
			return false;
		
		if (descripcion.compareTo(registro.getDescripcion()) != 0)
			return false;
		
		if (fechaInicio.compareTo(registro.getFechaInicio()) != 0)
			return false;
		
		if (fechaFin.compareTo(registro.getFechaFin()) != 0)
			return false;
		
		if (activo != registro.isActivo())
			return false;
		
		return true;
	}
}
	