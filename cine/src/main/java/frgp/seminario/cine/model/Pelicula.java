package frgp.seminario.cine.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pelicula")
public class Pelicula {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private String nombre;
	
	@Column(nullable=false)
	private String idioma;
	
	@Column(nullable=false)
	private boolean subs;
	
	@Column(nullable=false)
	private String clasificacion;
	
	@Column(nullable=false)
	private boolean reposicion;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch= FetchType.EAGER)
	private FichaTecnica detalles;
	
	@Column(nullable=false)
	private Date fechaCreacion;
	
	@Column(nullable=false)
	private boolean activo;

	public Pelicula(String nombre, String idioma, boolean subs,
			String clasificacion, boolean reposicion, FichaTecnica detalles,
			Date fechaCreacion) {
		super();
		this.nombre = nombre;
		this.idioma = idioma;
		this.subs = subs;
		this.clasificacion = clasificacion;
		this.reposicion = reposicion;
		this.detalles = detalles;
		this.fechaCreacion = fechaCreacion;
	}
	
	public Pelicula(String nombre, String idioma, boolean subs,
			String clasificacion, boolean reposicion, String descripcion, String actores, String director,
			String urlTrailer,
			Date fechaCreacion) {
		super();
		this.nombre = nombre;
		this.idioma = idioma;
		this.subs = subs;
		this.clasificacion = clasificacion;
		this.reposicion = reposicion;
		this.detalles = new FichaTecnica(descripcion, actores, director, urlTrailer);
		this.fechaCreacion = fechaCreacion;
	}

	public Pelicula(String nombre, String idioma, boolean subs,
			String clasificacion, boolean reposicion, FichaTecnica detalles,
			Date fechaCreacion, boolean activo) {
		super();
		this.nombre = nombre;
		this.idioma = idioma;
		this.subs = subs;
		this.clasificacion = clasificacion;
		this.reposicion = reposicion;
		this.detalles = detalles;
		this.fechaCreacion = fechaCreacion;
		this.activo = activo;
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

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public boolean isSubs() {
		return subs;
	}

	public void setSubs(boolean subs) {
		this.subs = subs;
	}

	public String getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	public boolean isReposicion() {
		return reposicion;
	}

	public void setReposicion(boolean reposicion) {
		this.reposicion = reposicion;
	}

	public FichaTecnica getDetalles() {
		return detalles;
	}

	public void setDetalles(FichaTecnica detalles) {
		this.detalles = detalles;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
