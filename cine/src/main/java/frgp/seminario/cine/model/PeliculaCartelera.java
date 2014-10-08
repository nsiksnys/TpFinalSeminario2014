package frgp.seminario.cine.model;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cartelera")
public class PeliculaCartelera {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	@OneToOne
	private Pelicula pelicula;
	
	@Column(nullable=false)
	private String proyeccion;
	
	@Column(nullable=false)
	private boolean subtitulada;
	
	@Column(nullable=false)
	ArrayList<Funcion> funciones;
	
	@Column(nullable=false)
	private Date fechaInicio;
	
	@Column(nullable=false)
	private Date fechaFin;
	
	@Column(nullable=false)
	private boolean activo;
}
