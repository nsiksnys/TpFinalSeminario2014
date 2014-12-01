package frgp.seminario.cine.model;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Horario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	private Time horaInicio;
	@Column(nullable=false)
	private Time horaFin;
	@Column(nullable=false)
	private Time duracion;	
	
	public Horario(){
		//constructor vacio
	}
	
	public Horario(Long id, Time horaInicio, Time horaFin) {
		this.id = id;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}
	
	public Horario(Time horaInicio, Time horaFin) {
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Time getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}
	public Time getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(Time horaFin) {
		this.horaFin = horaFin;
	}
	
	public Time getDuracion() {
		return duracion;
	}

	public void setDuracion(Time duracion) {
		this.duracion = duracion;
	}

	public boolean equals(Horario item)
	{
		if (horaInicio.compareTo(item.getHoraInicio()) != 0)
			return false;
		
		if (horaFin.compareTo(item.getHoraFin()) != 0)
			return false;
		
		if (duracion.compareTo(item.getDuracion()) != 0)
			return false;
		
		return true;
	}
}
