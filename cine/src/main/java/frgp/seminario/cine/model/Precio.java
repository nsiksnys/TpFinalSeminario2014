package frgp.seminario.cine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Precio {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private float menor;
	
	@Column(nullable=false)
	private float general;
	
	@Column(nullable=false)
	private float mayor;
	
	@Column(nullable=false)
	private boolean activo;

	public Precio(Long id, float menor, float general, float mayor) {
		this.id = id;
		this.menor = menor;
		this.general = general;
		this.mayor = mayor;
	}

	public Precio(float menor, float general, float mayor) {
		this.menor = menor;
		this.general = general;
		this.mayor = mayor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getMenor() {
		return menor;
	}

	public void setMenor(float menor) {
		this.menor = menor;
	}

	public float getGeneral() {
		return general;
	}

	public void setGeneral(float general) {
		this.general = general;
	}

	public float getMayor() {
		return mayor;
	}

	public void setMayor(float mayor) {
		this.mayor = mayor;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	 public boolean equals(Precio registro)
	
	{
		if (activo != registro.isActivo())
			return false;
		
		return true;
	}
	
}