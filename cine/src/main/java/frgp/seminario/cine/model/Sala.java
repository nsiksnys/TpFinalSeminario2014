package frgp.seminario.cine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sala {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	Long idComplejo;
	
	@Column(nullable = false)
	private int numeroSala;
	
	@Column(nullable = false)
	private boolean activa;

	public Sala()
	{
		//constructor vacio
	}
	
	public Sala(Long id, Long complejo, int numeroSala, boolean activa) {
		this.id = id;
		this.idComplejo = complejo;
		this.numeroSala = numeroSala;
		this.activa = activa;
	}

	public Sala(Long complejo, int numeroSala) {
		this.idComplejo = complejo;
		this.numeroSala = numeroSala;
		this.activa = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdComplejo() {
		return idComplejo;
	}

	public void setIdComplejo(Long complejo) {
		this.idComplejo = complejo;
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
		if (idComplejo != item.getIdComplejo())
			return false;
		
		if (numeroSala != item.getNumeroSala())
			return false;
		
		return true;
	}
}
