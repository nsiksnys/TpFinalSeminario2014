package frgp.seminario.cine.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Funcion {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Sala sala;
	
	@ManyToOne
	private Pelicula pelicula;
	
	@ManyToOne
	private Horario horario;
	private boolean activo;
	
	public Funcion()
	{
		//constructor vacio
	}
	
	public Funcion(Long id, Sala sala, Pelicula pelicula, Horario horario, boolean activo) {
		this.id = id;
		this.sala = sala;
		this.pelicula = pelicula;
		this.horario = horario;
		this.activo = activo;
	}

	public Funcion(Sala sala, Pelicula pelicula, Horario horario) {
		this.sala = sala;
		this.pelicula = pelicula;
		this.horario = horario;
		this.activo=true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public boolean equals(Funcion item)
	{
		if (!sala.equals(item.getSala()))
			return false;
		
		if (!pelicula.equals(item.getPelicula()))
			return false;
		
		if (!horario.equals(item.getHorario()))
			return false;
		
		return true;
	}
}
