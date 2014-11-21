package frgp.seminario.cine.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Asiento{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String fila;
	
	private String columna;
	
	public Asiento()
	{
		//constructor vacio
	}

	public Asiento(String fila, String columna) {
		this.fila = fila;
		this.columna = columna;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		if (fila.compareTo(item.getFila()) != 0)
			return false;
		
		if(columna.compareTo(item.getColumna()) != 0)
			return false;
		
		return true;
	}
}
