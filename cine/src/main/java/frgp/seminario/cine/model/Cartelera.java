package frgp.seminario.cine.model;

import java.util.List;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Cartelera")
public class Cartelera {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	@ManyToOne
	private Pelicula pelicula;
	
	@Column(nullable=false)
	private String proyeccion;
	
	@Column(nullable=false)
	private boolean subtitulada;
	
	@Column(nullable=false)
	@OneToMany
	List<Funcion> funciones;
	
	@Column(nullable=false)
	private Date fechaInicio;
	
	@Column(nullable=false)
	private Date fechaFin;
	
	@Column(nullable=false)
	private boolean activo;

	public Cartelera(Long id, Pelicula pelicula, String proyeccion,
			boolean subtitulada, List<Funcion> funciones,
			Date fechaInicio, Date fechaFin, boolean activo) {
		this.id = id;
		this.pelicula = pelicula;
		this.proyeccion = proyeccion;
		this.subtitulada = subtitulada;
		this.funciones = funciones;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.activo = activo;
	}

	public Cartelera(Pelicula pelicula, String proyeccion, boolean subtitulada,
			List<Funcion> funciones, Date fechaInicio, Date fechaFin) {
		this.pelicula = pelicula;
		this.proyeccion = proyeccion;
		this.subtitulada = subtitulada;
		this.funciones = funciones;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.activo = true;
	}

	public Cartelera(Pelicula pelicula, String proyeccion,
			boolean subtitulada, Date fechaInicio, Date fechaFin) {
		this.pelicula = pelicula;
		this.proyeccion = proyeccion;
		this.subtitulada = subtitulada;
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

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public String getProyeccion() {
		return proyeccion;
	}

	public void setProyeccion(String proyeccion) {
		this.proyeccion = proyeccion;
	}

	public boolean isSubtitulada() {
		return subtitulada;
	}

	public void setSubtitulada(boolean subtitulada) {
		this.subtitulada = subtitulada;
	}

	public List<Funcion> getFunciones() {
		return funciones;
	}

	public void setFunciones(List<Funcion> funciones) {
		this.funciones = funciones;
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
	
	public boolean equals(Cartelera registro)
	{
		if (!this.pelicula.equals(registro.getPelicula()))
			return false;
		
		if (this.proyeccion.compareTo(registro.getProyeccion()) != 0)
			return false;
		
		if (this.subtitulada != registro.isSubtitulada())
			return false;
		
		if (!isMismaLista(this.funciones, registro.getFunciones()))
			return false;
		
		if (fechaInicio.compareTo(registro.getFechaInicio()) != 0)
			return false;
		
		if (fechaFin.compareTo(registro.getFechaFin()) != 0)
			return false;
		
		if (activo != registro.isActivo())
			return false;
		
		return true;
	}
	
	public boolean isMismaLista(List<Funcion> thisFunciones, List<Funcion> registroFunciones)
	{
		int cuenta = 0;
		for (int i = 0; i < thisFunciones.size(); i++) {// uso un for tradicional para evitar que busque un null en elementos
			for (int j = 0; j < thisFunciones.size(); j++) {
				if (thisFunciones.get(i).getId() == registroFunciones.get(j).getId())// comparo los ids de las funciones
					cuenta++;
			}
		}
		if (cuenta == thisFunciones.size())
			return true;
		return false;
	}
}
