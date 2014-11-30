package frgp.seminario.cine.findItem.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import frgp.seminario.cine.dataAccess.DataAccess;
import frgp.seminario.cine.model.Funcion;
import frgp.seminario.cine.model.Reserva;
import frgp.seminario.cine.model.Cartelera;
import frgp.seminario.cine.repository.Repository;

@Service("ReservaFindItem")
public class ReservaFindItem {
	@Autowired
	DataAccess dataAccess;
	
	@Autowired
	@Qualifier("CarteleraRepository")
	Repository<Cartelera> carteleras;
	
	/**
	 * Determina si existe un registro con las mismas caracteristicas en la base de datos
	 * @param item Reserva que buscamos en la base
	 * @return true si existe un registro, false si no
	 **/
	@SuppressWarnings("unchecked")
	public Long getIdByObject(Reserva item){
		ArrayList<Reserva> todos = (ArrayList<Reserva>) dataAccess.getAll(Reserva.class);
		
		for (Reserva registro : todos) {
			if (registro.equals(item))
				return registro.getId();
		}
		return (long) 0;
	}

	
	@SuppressWarnings("unchecked")
	public ArrayList<Reserva> findByClienteEmail(String email){
		ArrayList<Reserva> todos = (ArrayList<Reserva>) dataAccess.getAll(Reserva.class);
		ArrayList<Reserva> respuesta = new ArrayList<Reserva>(); 
		
		for (Reserva item : todos) {
			if (item.getCliente().getEmail().equals(email))
				respuesta.add(item);
		}
		return respuesta;
	}
	
	@SuppressWarnings("unchecked")
	public boolean findActiveByClienteEmailBoolean(String email){
		ArrayList<Reserva> todos = (ArrayList<Reserva>) dataAccess.getAll(Reserva.class);
		ArrayList<Reserva> respuesta = new ArrayList<Reserva>(); 
		
		for (Reserva item : todos) {
			if (item.getCliente().getEmail().equals(email) && item.isActivo())
				respuesta.add(item);
		}
		
		if (respuesta.isEmpty())
			return false;
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Reserva> findByClienteDNI(int dni){
		ArrayList<Reserva> todos = (ArrayList<Reserva>) dataAccess.getAll(Reserva.class);
		ArrayList<Reserva> respuesta = new ArrayList<Reserva>(); 
		
		for (Reserva item : todos) {
			if (item.getCliente().getDni() == dni)
				respuesta.add(item);
		}
		return respuesta;
	}
	
	public boolean findActiveByPeliculaBoolean(Long idPelicula){
		ArrayList<Reserva> todos = getAllEnabled();
		
		for (Reserva item: todos)
			if (item.getFuncion().getPelicula().getId() == idPelicula)
				return true;
		return false;
	}
		
	@SuppressWarnings("unchecked")
	public boolean findByIdBoolean(int id){
		ArrayList<Reserva> todos = (ArrayList<Reserva>) dataAccess.getAll(Reserva.class);
		
		for (Reserva item : todos) {
			if (item.getId() == id)
				return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	//@Override
	public ArrayList<Reserva> getAllByFlag(boolean flag) {
		ArrayList<Reserva> todos = (ArrayList<Reserva>) dataAccess.getAll(Reserva.class);
		ArrayList<Reserva> rta = new ArrayList<Reserva>();
		
		for (Reserva item : todos) {
			if (item.isActivo() == flag)
				rta.add(item);
		}
		return rta;
	}
	
	/**
	 * Devuelve todos las peliculas activas
	 * @return un ArrayList con los registros
	 **/
	public ArrayList<Reserva> getAllEnabled(){
		return getAllByFlag(true);
	}
	
	/**
	 * Devuelve todos las peliculas desactivadas
	 * @return un ArrayList con los registros
	 **/
	public ArrayList<Reserva> getAllDisabled(){
		return getAllByFlag(false);
	}


	public boolean findActiveByFuncionesCarteleraBoolean(Long idCartelera) {
		ArrayList<Reserva> todos = getAllEnabled();
		Cartelera registro = carteleras.get(Cartelera.class, idCartelera);
		
		for (Reserva item: todos){
			for (Funcion funcion : registro.getFunciones()){
				if (item.getFuncion().getId() == funcion.getId())
					return true;
			}
		}
		return false;
	}

}