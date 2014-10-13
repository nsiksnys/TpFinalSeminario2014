package frgp.seminario.cine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Sala {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Complejo complejo;
	
	@Column(nullable = false)
	private int numeroSala;
	
	@Column(nullable = false)
	private boolean activa;

	public Sala(Long id, Complejo complejo, int numeroSala, boolean activa) {
		this.id = id;
		this.complejo = complejo;
		this.numeroSala = numeroSala;
		this.activa = activa;
	}

	public Sala(Complejo complejo, int numeroSala) {
		this.complejo = complejo;
		this.numeroSala = numeroSala;
		this.activa = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Complejo getComplejo() {
		return complejo;
	}

	public void setComplejo(Complejo complejo) {
		this.complejo = complejo;
	}

	public int getNumeroSala() {
		return numeroSala;
	}

	public void setNumeroSala(int numeroSala) {
		this.numeroSala = numeroSala;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}
	
	public boolean equals(Sala item)
	{
		if (complejo.getId() != item.getComplejo().getId())
			return false;
		
		if (!complejo.equals(item.getComplejo()))
			return false;
		
		if (numeroSala != item.getNumeroSala())
			return false;
		
		return true;
	}
}
