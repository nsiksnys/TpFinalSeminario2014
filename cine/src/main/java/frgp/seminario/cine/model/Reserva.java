package frgp.seminario.cine.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;

@Entity
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch= FetchType.EAGER)
	private Cliente cliente;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch= FetchType.EAGER)
	private Funcion funcion;
	
	@OneToMany//(mappedBy="reserva")
	private List<Asiento> asientos;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch= FetchType.EAGER)
	private Promocion promo;
	
	@Column(nullable=false)
	private Date fechaReserva;
	
	@Column(nullable=false)
	private Date fechaCreacion;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "Reserva_Precios")
	@MapKeyColumn//(name="idPrecio")
	@Column(name="cantidad")
	private Map<Precio, Integer> precios;
	
	@Column(nullable=false)
	private float importe;
	
	@Column(nullable=false)
	private String codigo;
	
	@Column(nullable=false)
	private boolean activo;
	
	public Reserva()
	{
		//constructor vacio
	}

	public Reserva(Long id, Cliente cliente, Funcion funcion, List<Asiento> asientos,
			Promocion promo, Date fechaReserva, Date fechaCreacion, Map<Precio, Integer> precios,
			float importe, boolean activo) {
		this.id = id;
		this.cliente = cliente;
		this.funcion = funcion;
		this.asientos = asientos;
		this.promo = promo;
		this.fechaReserva = fechaReserva;
		this.fechaCreacion = fechaCreacion;
		this.precios = precios;
		this.importe = importe;
		this.activo = activo;
	}

	public Reserva(Cliente cliente, Funcion funcion, List<Asiento> asientos, Promocion promo,
			Date fechaReserva, Date fechaCreacion, Map<Precio, Integer> precios, float importe) {
		this.cliente = cliente;
		this.funcion = funcion;
		this.asientos = asientos;
		this.promo = promo;
		this.fechaReserva = fechaReserva;
		this.fechaCreacion = fechaCreacion;
		this.precios = precios;
		this.importe = importe;
		this.activo = true;
	}

	public Reserva(Cliente cliente, Funcion funcion, List<Asiento> asientos, Promocion promo,
			Date fechaReserva, Date fechaCreacion, Map<Precio, Integer> precios, float importe, String codigo) {
		this.cliente = cliente;
		this.funcion = funcion;
		this.asientos = asientos;
		this.promo = promo;
		this.fechaReserva = fechaReserva;
		this.fechaCreacion = fechaCreacion;
		this.precios = precios;
		this.importe = importe;
		this.codigo = codigo;
		this.activo = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcion getFuncion() {
		return funcion;
	}

	public void setFuncion(Funcion funcion) {
		this.funcion = funcion;
	}

	public List<Asiento> getAsientos() {
		return asientos;
	}

	public void setAsientos(List<Asiento> asientos) {
		this.asientos = asientos;
	}

	public Promocion getPromo() {
		return promo;
	}

	public void setPromo(Promocion promo) {
		this.promo = promo;
	}

	public Date getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	//@ElementCollection
	public Map<Precio, Integer> getPrecios() {
		return precios;
	}

	public void setPrecios(Map<Precio, Integer> precios) {
		this.precios = precios;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public boolean equals(Reserva item)
	{
		if (!cliente.equals(item.getCliente()))
			return false;
		
		if (!funcion.equals(item.getFuncion()))
			return false;
				
		if (!isMismaLista(asientos, item.getAsientos()))
			return false;
		
		if(!promo.equals(item.getPromo()))
			return false;
		
		if (fechaCreacion.compareTo(item.getFechaCreacion()) !=0)
			return false;
		
		if (fechaReserva.compareTo(item.getFechaReserva()) != 0)
			return false;
		
		if (importe != item.getImporte())
			return false;
		
		return true;
	}
	
	public boolean isMismaLista(List<Asiento> thisAsientos, List<Asiento> itemAsientos)
	{
		int cuenta = 0;
		for (int i = 0; i < thisAsientos.size(); i++) {// uso un for tradicional para evitar que busque un null en elementos
			for (int j = 0; j < thisAsientos.size(); j++) {
				if (thisAsientos.get(i).equals(itemAsientos.get(j)))//comparo
					cuenta++;
			}
		}
		if (cuenta == thisAsientos.size())
			return true;
		return false;
	}
}
