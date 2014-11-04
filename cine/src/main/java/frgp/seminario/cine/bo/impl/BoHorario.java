package frgp.seminario.cine.bo.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import frgp.seminario.cine.findItem.impl.HorarioFindItem;
import frgp.seminario.cine.model.Horario;
import frgp.seminario.cine.repository.impl.HorarioRepository;

//Funciones pertenecientes a la logica de negocios
@Service("BoHorario") //agrego el nombre del bean, para que al momento de llamar al Autowired pueda aclarar cual quiero
public class BoHorario {
	@Autowired
	@Qualifier("HorarioRepository") //aclaro cual es el bean a inyectar
	HorarioRepository repository;
	
	@Autowired
	@Qualifier("HorarioFindItem") //aclaro cual es el bean a inyectar
	HorarioFindItem busqueda;
	
	public Horario get(Object id){
		return repository.get(Horario.class, id);
	}
	
	public ArrayList<Horario> listarTodos(){
		return repository.getAll(Horario.class);
	}
}
