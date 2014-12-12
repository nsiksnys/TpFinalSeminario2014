package frgp.seminario.cine.findItem.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import frgp.seminario.cine.dataAccess.DataAccess;
import frgp.seminario.cine.model.Asiento;

@Service("AsientoFindItem")
public class AsientoFindItem {
	@Autowired
	DataAccess dataAccess;
	
	@SuppressWarnings("unchecked")
	public ArrayList<Asiento> getAll()
	{
		return (ArrayList<Asiento>) dataAccess.getAll(Asiento.class);
	}
	
	@SuppressWarnings("unchecked")
	public Asiento findByFilaColumna(String fila, String columna)
	{
		ArrayList<Asiento> todos = (ArrayList<Asiento>) dataAccess.getAll(Asiento.class);
		
		for (Asiento item : todos)
		{
			if (item.getFila().equals(fila) && item.getColumna().equals(columna))
				return item;
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Asiento findByAsiento(Asiento item)
	{
		ArrayList<Asiento> todos = (ArrayList<Asiento>) dataAccess.getAll(Asiento.class);
		
		for (Asiento registro : todos)
		{
			if (registro.equals(item))
				return registro;
		}
		
		return null;
	}
}
