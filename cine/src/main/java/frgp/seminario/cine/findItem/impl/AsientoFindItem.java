package frgp.seminario.cine.findItem.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	public String getAsientoReservaString(Long idReserva) {
		Object[] lista = dataAccess.getCustomQueryResult("SELECT asientos_id FROM Reserva JOIN Reserva_Asiento ON Reserva.id = Reserva_Asiento.reserva_id WHERE Reserva.id=" + idReserva).toArray();
		//String rta ="";
		
		//for(Object item : lista)
		//	rta += rta.concat(item + " ");
		return Arrays.toString(lista)	;	
		//return rta;
	}
	
	public Object[] findAsientoIdByFuncion(Long idFuncion,String fecha)
	{
		return dataAccess.getCustomQueryResult("SELECT asientos_id FROM Reserva JOIN Reserva_Asiento ON Reserva.id = Reserva_Asiento.Reserva_id WHERE Reserva.funcion_id =" + idFuncion + " AND Reserva.fechaReserva LIKE '" + fecha + "%'").toArray();
	}
		
	/**
	 * Devuelve la cantidad de asientos reservados para una funcion determinada
	 * @param idFuncion
	 * @param statusReserva boolean
	 * @return int con la cantidad hallada
	 */
	@SuppressWarnings("unused")
	public int getCantidadAsientoByFuncionStatusReserva(Long idFuncion, boolean statusReserva)
	{
		Object[] lista = dataAccess.getCustomQueryResult("SELECT asientos_id FROM Reserva JOIN Reserva_Asiento ON Reserva.id = Reserva_Asiento.reserva_id WHERE Reserva.funcion_id=" + idFuncion + " AND Reserva.activo=" + statusReserva).toArray();
		int cantidad = 0;
		
		for(Object item : lista)
			cantidad++;
		
		return cantidad;
	}
	//TODO: Este codigo esta repetido
//	public int getCantidadAsientosByFuncionMailReserva(String mail,Long idfuncion)
//	{
//		return dataAccess.getCustomQueryResult("SELECT asientos_id FROM Reserva JOIN Reserva_Asiento ON Reserva.id = Reserva_Asiento.Reserva_id WHERE Reserva.cliente_email = '" + mail + "' AND Reserva.funcion_id =" +   idfuncion ).size();
//	}
	
	
	
}
