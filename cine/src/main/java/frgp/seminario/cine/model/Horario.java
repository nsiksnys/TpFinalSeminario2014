package frgp.seminario.cine.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Horario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date horaInicio;
	private Date horaFin;
	
	
	public Horario(Long id, Date horaInicio, Date horaFin) {
		this.id = id;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}
	
	public Horario(Date horaInicio, Date horaFin) {
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}
	public Date getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}
	
	public boolean equals(Horario item)
	{
		if (horaInicio.compareTo(item.getHoraInicio()) != 0)
			return false;
		
		if (horaFin.compareTo(item.getHoraFin()) != 0)
			return false;
		
		return true;
	}
}
