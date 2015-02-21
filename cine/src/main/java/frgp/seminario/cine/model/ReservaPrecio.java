package frgp.seminario.cine.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Precios_Detalle")
public class ReservaPrecio {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long idReserva;
	
	@ManyToOne
	private Precio precio;
	private String tipoPrecio;
	private int cantidad;
	
	public ReservaPrecio()
	{
		//constructor vacio
	}
	
	public ReservaPrecio(Long idReserva, Precio precio, String tipoPrecio, int cantidad) {
		this.precio = precio;
		this.idReserva = idReserva;
		this.tipoPrecio = tipoPrecio;
		this.cantidad = cantidad;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(Long idReserva) {
		this.idReserva = idReserva;
	}

	public Precio getPrecio() {
		return precio;
	}

	public void setPrecio(Precio precio) {
		this.precio = precio;
	}

	public String getTipoPrecio() {
		return tipoPrecio;
	}

	public void setTipoPrecio(String tipoPrecio) {
		this.tipoPrecio = tipoPrecio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
