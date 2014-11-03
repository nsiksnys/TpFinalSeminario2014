package frgp.seminario.cine.findItem.impl;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import frgp.seminario.cine.dataAccess.DataAccess;
import frgp.seminario.cine.model.Horario;

@Service("HorarioFindItem")//agrego el nombre del bean, para que al momento de llamar al Autowired pueda aclarar cual quiero
public class HorarioFindItem {
	@Autowired
	DataAccess dataAccess;
	
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
	public Horario findByInicio(Date inicio){
		ArrayList<Horario> todos = (ArrayList<Horario>) dataAccess.getAll(Horario.class);
		
		for (Horario item : todos) {
			if (item.getHoraInicio().equals(inicio))
				return item;
		}
		return null;
	}
	
	public boolean findByInicioBoolean(Date inicio){
		if (findByInicio(inicio) == null)
			return false;
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public Horario findByFin(Date fin){
		ArrayList<Horario> todos = (ArrayList<Horario>) dataAccess.getAll(Horario.class);
		
		for (Horario item : todos) {
			if (item.getHoraFin().equals(fin))
				return item;
		}
		return null;
	}
	
	public boolean findByFinBoolean(Date fin){
		if (findByFin(fin) == null)
			return false;
		return true;
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
