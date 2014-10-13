package frgp.seminario.cine.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Asiento {
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="idReserva")
	private Reserva reserva;
	
	@Id
	private String fila;
	
	@Id
	private String columna;

	public Asiento(String fila, String columna) {
		this.fila = fila;
		this.columna = columna;
	}

	public Asiento(Reserva reserva, String fila, String columna) {
		this.reserva = reserva;
		this.fila = fila;
		this.columna = columna;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public String getFila() {
		return fila;
	}

	public void setFila(String fila) {
		this.fila = fila;
	}

	public String getColumna() {
		return columna;
	}

	public void setColumna(String columna) {
		this.columna = columna;
	}
	
	public boolean equals(Asiento item)
	{
		if(reserva.getId() != item.getReserva().getId())
			return false;
		
		if (fila.compareTo(item.getFila()) != 0)
			return false;
		
		if(columna.compareTo(item.getColumna()) != 0)
			return false;
		
		return true;
	}
}
