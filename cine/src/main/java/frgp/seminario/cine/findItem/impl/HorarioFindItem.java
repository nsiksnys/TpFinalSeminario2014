package frgp.seminario.cine.findItem.impl;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import frgp.seminario.cine.dataAccess.DataAccess;
import frgp.seminario.cine.model.Horario;
import frgp.seminario.cine.utils.FechaUtils;

@Service("HorarioFindItem")//agrego el nombre del bean, para que al momento de llamar al Autowired pueda aclarar cual quiero
public class HorarioFindItem {
	@Autowired
	DataAccess dataAccess;
	
	@Autowired
	FechaUtils utils;
	
	/**
	 * Busca un registro con las mismas caracteristicas en la base de datos
	 * @param item Horario que buscamos en la base
	 * @return id del registro, 0 si no se encontro ninguno
	 **/
	@SuppressWarnings("unchecked")
	public Long getIdByObject(Horario item){
		ArrayList<Horario> todos = (ArrayList<Horario>) dataAccess.getAll(Horario.class);
		
		for (Horario registro : todos) {
			if (registro.equals(item))
				return registro.getId();
		}
		return (long) 0;
	}

	
	@SuppressWarnings("unchecked")
	public Horario findByInicio(Time inicio){
		ArrayList<Horario> todos = (ArrayList<Horario>) dataAccess.getAll(Horario.class);
		
		for (Horario item : todos) {
			if (item.getHoraInicio().compareTo(inicio) == 0)
				return item;
		}
		return null;
	}
	
	public boolean findByInicioBoolean(Time inicio){
		if (findByInicio(inicio) == null)
			return false;
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public Horario findByFin(Time fin){
		ArrayList<Horario> todos = (ArrayList<Horario>) dataAccess.getAll(Horario.class);
		
		for (Horario item : todos) {
			if (item.getHoraFin().compareTo(fin) == 0)
				return item;
		}
		return null;
	}
	
	public boolean findByFinBoolean(Time fin){
		if (findByFin(fin) == null)
			return false;
		return true;
	}
	
	/**
	 * Halla los horarios en los que puede guardarse una pelicula
	 * @param periodo la duracion de la pelicula, expresada en HH:MM:SS
	 * @return un ArrayList con los horarios que cumplen la condicion
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Horario> findByDiferencia(Time periodo)
	{
		ArrayList<Horario> todos = (ArrayList<Horario>) dataAccess.getAll(Horario.class);
		ArrayList<Horario> respuesta = new ArrayList<Horario>();
		
		//System.out.println("Duracion pelicula: " + periodo + "(" + periodo.getTime() + ")");
		
		for (Horario item : todos)
		{//si la duracion de la funcion es igual o mayor al parametro
				//System.out.println(item.getId() + ") " + item.getHoraFin() + " - " + item.getHoraInicio() + " = " + item.getDuracion());
				
			if (item.getDuracion().compareTo(periodo) >= 0)
				respuesta.add(item);
		}
		return respuesta;
	}
		
	@SuppressWarnings("unchecked")
	public boolean findByIdBoolean(int id){
		ArrayList<Horario> todos = (ArrayList<Horario>) dataAccess.getAll(Horario.class);
		
		for (Horario item : todos) {
			if (item.getId() == id)
				return true;
		}
		return false;
	}
}
