package frgp.seminario.cine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FichaTecnica {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String descripcion;
	
	@Column(nullable=false)
	private String actores;
	
	@Column(nullable=false)
	private String director;
	
	@Column(nullable=true)
	private String urlTrailer;
	
	public FichaTecnica(){
		//constructor por defecto
	}

	public FichaTecnica(String descripcion, String actores, String director,
			String urlTrailer) {
		this.descripcion = descripcion;
		this.actores = actores;
		this.director = director;
		this.urlTrailer = urlTrailer;
	}

	public FichaTecnica(Long id, String descripcion, String actores,
			String director, String urlTrailer) {
		this.id = id;
		this.descripcion = descripcion;
		this.actores = actores;
		this.director = director;
		this.urlTrailer = urlTrailer;
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

	public String getActores() {
		return actores;
	}

	public void setActores(String actores) {
		this.actores = actores;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getUrlTrailer() {
		return urlTrailer;
	}

	public void setUrlTrailer(String urlTrailer) {
		this.urlTrailer = urlTrailer;
	}
	
	public boolean equals(FichaTecnica item)
	{
		if (descripcion.compareTo(item.getDescripcion()) != 0)
			return false;
		
		if (actores.compareTo(item.getActores()) != 0)
			return false;
		
		if (director.compareTo(item.getDirector()) != 0)
			return false;
		
		if (urlTrailer.compareTo(item.getUrlTrailer()) != 0)
			return false;
		
		return true;
	}
}
