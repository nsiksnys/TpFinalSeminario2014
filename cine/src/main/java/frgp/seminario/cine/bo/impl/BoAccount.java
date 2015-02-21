package frgp.seminario.cine.bo.impl;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import frgp.seminario.cine.account.Account;
import frgp.seminario.cine.account.AccountRepository;
import frgp.seminario.cine.bo.BusinessObject;
import frgp.seminario.cine.model.Cliente;
import frgp.seminario.cine.signup.SignupForm;
import frgp.seminario.cine.utils.FechaUtils;

//Funciones pertenecientes a la logica de negocios
@Service("BoAccount") //agrego el nombre del bean, para que al momento de llamar al Autowired pueda aclarar cual quiero
public class BoAccount implements BusinessObject<Account, SignupForm> {
	@Autowired
	AccountRepository accounts;
	
	@Autowired
	BoCliente clientes;
	
	@Autowired
	FechaUtils utils;
	
	@Inject
	private PasswordEncoder passwordEncoder;
		
	
	/**
	 * Recupera un registro Account activo de la base de datos
	 * @param email email de la cuenta
	 * @return un Account con la cuenta, o null si no lo encontro
	 */
	@Override
	public Account get(Object email) {
		return accounts.findByEmail((String) email);
	}
	
	/**
	 * Recupera un registro Cliente activo de la base de datos
	 * @param email email de la cuenta
	 * @return un Account con la cuenta, o null si no lo encontro
	 */
	public Cliente getCliente(Object email){
		return clientes.get(email);
	}
	
	/**
	 * Recupera un registro Account de la base de datos, sin importar si esta activo o no
	 * @param email email de la cuenta
	 * @return un Account con la cuenta, o null si no lo encontro
	 */
	public Account getActiveOrInactive(Object email){
		return accounts.getActiveOrInactive(email);
	}
	
	public boolean getActiveBoolean(Object email)
	{
		//si encontro un registro 
		if (this.get(email) != null){
			return true;
		}
		
		return false;
	}

	
	public boolean getActiveOrInactiveBoolean(Object email)
	{
		//si encontro un registro 
		if (this.getActiveOrInactive(email) != null){
			return true;
		}
		
		return false;
	}
	@Override
	public boolean guardar(Account registro) {
		try
		{
			accounts.save(registro);
		}
		catch (PersistenceException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean guardar(Cliente registro){
		return clientes.guardar(registro);
	}

	@Override
	public boolean modificar(Account registro) {
		Account registroActual = this.get(registro.getEmail());
		
		if (registroActual.equals(registro))
			return true;
		
		if (getRoles().containsKey(registro.getRole())){
			registroActual.setRole(registro.getRole());
		}
		
		//si se cambia a cliente, devuelve falso porque no se puede (genera un RollbackException)
		if (registro.getRole().equals("C")){
				return false;
		}
		
		return accounts.merge(registroActual);
	}
	
	/**
	 * Usado para cambiar los datos del usuario logueado (/usuario/actual)
	 * @param formulario
	 * @return el resultado del merge del registro
	 */
	public boolean modificar(SignupForm formulario) {
		Account registro = this.get(formulario.getEmail());
		
		//si el mail corresponde a un cliente, se pasa a la funcion propia de esa clase
		if (registro.getRole().equals("C"))
			return clientes.modificar(formulario);
		
		//guardo los cambios posibles
		if (formulario.getNombre() != "")
			registro.setNombre(formulario.getNombre());
		
		if (formulario.getNombre() != "")
			registro.setApellido(formulario.getApellido());
		
		if (formulario.getFechaNacimiento() != null)
			registro.setFechaNacimiento(utils.getFechaFormatoDiaMesAnio(formulario.getFechaNacimiento()));
		
		if (formulario.getPreguntaSeguridad() != "")
			registro.setPreguntaSeguridad(formulario.getPreguntaSeguridad());
		
		if (formulario.getRespuestaSeguridad() != "")
			registro.setRespuestaSeguridad(passwordEncoder.encode(formulario.getRespuestaSeguridad()));

		return accounts.merge(registro);
	}

	@Deprecated
	public boolean modificar(Cliente registro){
		if (!(registro instanceof frgp.seminario.cine.model.Cliente))
			return false;
		
		return clientes.modificar(registro);
	}

	@Override
	public boolean desactivar(Account registro) {
		//Si el registro corresponde a un cliente, se pasa a la funcion de BoCliente
		if (registro.getRole().equals("C"))
			return clientes.desactivar(clientes.get(registro.getEmail()));
		
		if (registro.isActive())
			registro.setActive(false);
		
		return accounts.merge(registro);
	}
	
	public boolean activar(Account registro) {
		if (!registro.isActive())
			registro.setActive(true);
		
		return accounts.merge(registro);
	}

	@Override
	public ArrayList<Account> listarTodos() {
		return accounts.getAll();
	}

	@Override
	public boolean verificar(Account registro) {
		if (!(registro instanceof frgp.seminario.cine.account.Account))
			return false;
		
		if (!accounts.getIdByObject(registro).equals(""))
			return false;
		
		return true;
	}

	@Override
	public Account formToEntity(SignupForm formulario) {
		//parametros no nulleables
		Account entity = new Account(	Long.parseLong(formulario.getDni()), 
										formulario.getNombre(), 
										formulario.getApellido(), 
										formulario.getEmail(),
										formulario.getPassword(),
										formulario.getRole());
		//parametros nulleables
		if (formulario.getSexo() != null)
			entity.setSexo(formulario.getSexo());
		
		if (formulario.getFechaNacimiento() != null)
			entity.setFechaNacimiento(utils.getFechaFormatoDiaMesAnio(formulario.getFechaNacimiento()));
		
		if (formulario.getPreguntaSeguridad() != null)
			entity.setPreguntaSeguridad(formulario.getPreguntaSeguridad());
		
		if (formulario.getRespuestaSeguridad() != null)
			entity.setRespuestaSeguridad(formulario.getRespuestaSeguridad());
		
		return entity;
	}
	
	public Cliente formToClienteEntity(SignupForm formulario) {
        return clientes.formToEntity(formulario);
	}
	
	public Account formToEntityModificar(SignupForm formulario) {
		//parametros no nulleables
		Account entity = new Account(	Long.parseLong(formulario.getDni()), 
										formulario.getNombre(), 
										formulario.getApellido(), 
										formulario.getEmail(),
										"",//como es una modificacion el password no va
										formulario.getRole());
		//parametros nulleables
		if (formulario.getSexo() != null)
			entity.setSexo(formulario.getSexo());
		
		if (formulario.getFechaNacimiento() != null)
			entity.setFechaNacimiento(utils.getFechaFormatoDiaMesAnio(formulario.getFechaNacimiento()));
		
		if (formulario.getPreguntaSeguridad() != null)
			entity.setPreguntaSeguridad(formulario.getPreguntaSeguridad());
		
		if (formulario.getRespuestaSeguridad() != null)
			entity.setRespuestaSeguridad(formulario.getRespuestaSeguridad());
		
		return entity;
	}
	
	public Cliente formToClienteEntityModificar(SignupForm formulario)
	{
		return clientes.formToEntityModificar(formulario);
	}
	
	public SignupForm entityToForm(Account registro){
		SignupForm formulario = new SignupForm();
		
		//parametros no nulleables
		formulario.setDni(registro.getDni().toString());
		formulario.setNombre(registro.getNombre());
		formulario.setApellido(registro.getApellido());
		formulario.setEmail(registro.getEmail());
		formulario.setRole(getRoles().get(registro.getRole()));
		
		//parametros nulleables
		if (registro.getSexo() != null)
			formulario.setSexo(registro.getSexo());
		
		if (registro.getFechaNacimiento() != null)
		formulario.setFechaNacimiento(utils.getFormatoDiaMesAnio(registro.getFechaNacimiento()));
		
		if (registro.getPreguntaSeguridad() != null)
		formulario.setPreguntaSeguridad(registro.getPreguntaSeguridad());
		
		if (registro.getRespuestaSeguridad() != null)
		formulario.setRespuestaSeguridad(registro.getRespuestaSeguridad());
		
		return formulario;
	}
	
	public HashMap<String, String> getRoles(){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("A", "Administrador");
		map.put("C", "Cliente");
		map.put("G", "Gerente");
		return map;
	}
	
	public HashMap<String, String> getSexos(){
		HashMap<String, String> map = new HashMap<String, String>(2);
		map.put("F", "Femenino");
		map.put("M", "Masculino");
		
		return map;
	}

/**
 * Verifica que la respuesta de seguridad enviada coincida con la que esta en el registro de usuario
 * @param email mail de la cuenta
 * @param dni dni de la cuenta
 * @param respuesta respuesta de seguridad ingresada en el formulario
 * @return true si la respuesta coincide, false si no
 */
	public boolean verificarRespuestaSeguridad(String email, Long dni, String respuesta) {
		Account cuenta = accounts.getActiveOrInactive(email);
		
		//si no se encontro el registro en la base de datos o el dni no coincide
		if (cuenta == null || !cuenta.getDni().equals(dni))
		{
			return false;
		}
		
		//si la respuesta de seguridad submiteada coincide
		if (passwordEncoder.matches(respuesta, cuenta.getRespuestaSeguridad()))
		{
			return true;
		}
		
		return false;
	}

/**
 * Recupera una cuenta, ya sea por estar inactiva o por no recordar la contraseña
 * @param email mail de la cuenta
 * @param dni dni de la cuenta
 * @param respuesta respuesta de seguridad ingresada en el formulario
 * @return true si el merge del registro fue exitoso, false si no
 */
	public boolean recuperar(String email, Long dni, String respuesta)
	{
		//si la verificacion es exitosa
		if (verificarRespuestaSeguridad(email, dni, respuesta))
		{
			return activar(getActiveOrInactive(email));
		}
		
		return false;
	}
}
