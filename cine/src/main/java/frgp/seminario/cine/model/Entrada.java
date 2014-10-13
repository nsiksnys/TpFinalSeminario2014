package frgp.seminario.cine.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Entrada_reserva")
public class Entrada implements Serializable{
	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="idReserva")
	private Reserva reserva;
	
	@ManyToOne
	@JoinColumn(name="idPrecio")
	private Precio precio;
	
	@Column(nullable=false)
	private String tipoEntrada;
	
	@Column(nullable=false)
	private int cantidadEntrada;

	public Entrada(Reserva reserva, Precio precio, String tipoEntrada,
			int cantidadEntrada) {
		this.reserva = reserva;
		this.precio = precio;
		this.tipoEntrada = tipoEntrada;
		this.cantidadEntrada = cantidadEntrada;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Precio getPrecio() {
		return precio;
	}

	public void setPrecio(Precio precio) {
		this.precio = precio;
	}

	public String getTipoEntrada() {
		return tipoEntrada;
	}

	public void setTipoEntrada(String tipoEntrada) {
		this.tipoEntrada = tipoEntrada;
	}

	public int getCantidadEntrada() {
		return cantidadEntrada;
	}

	public void setCantidadEntrada(int cantidadEntrada) {
		this.cantidadEntrada = cantidadEntrada;
	}
	
	public boolean equals(Entrada item)
	{
		if (reserva.getId() != item.getReserva().getId())
			return false;
		
		if (!precio.equals(item.getPrecio()))
			return false;
		
		if (cantidadEntrada != item.getCantidadEntrada())
			return false;
		
		return true;
	}
}
