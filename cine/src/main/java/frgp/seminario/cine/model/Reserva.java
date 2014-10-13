package frgp.seminario.cine.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	@ManyToOne(cascade=CascadeType.ALL, fetch= FetchType.EAGER)
	private Cliente cliente;
	
	@Column(nullable=false)
	@ManyToOne(cascade=CascadeType.ALL, fetch= FetchType.EAGER)
	private Funcion funcion;
	
	@Column(nullable=false)
	private int cantidadEntradas;
	
	@Transient
	private Entrada entrada;
	
	@Column(nullable=false)
	@OneToMany(mappedBy="reserva")
	private List<Asiento> asientos;
	
	@Column(nullable=false)
	@ManyToOne(cascade=CascadeType.ALL, fetch= FetchType.EAGER)
	private Promocion promo;
	
	@Column(nullable=false)
	private Date fechaReserva;
	
	@Column(nullable=false)
	private Date fechaCreacion;
	
	@Column(nullable=false)
	private float importe;
	
	@Column(nullable=false)
	private boolean activo;

	public Reserva(Long id, Cliente cliente, Funcion funcion,
			int cantidadEntradas, Entrada entrada, List<Asiento> asientos,
			Promocion promo, Date fechaReserva, Date fechaCreacion,
			float importe, boolean activo) {
		this.id = id;
		this.cliente = cliente;
		this.funcion = funcion;
		this.cantidadEntradas = cantidadEntradas;
		this.entrada = entrada;
		this.asientos = asientos;
		this.promo = promo;
		this.fechaReserva = fechaReserva;
		this.fechaCreacion = fechaCreacion;
		this.importe = importe;
		this.activo = activo;
	}

	public Reserva(Cliente cliente, Funcion funcion, int cantidadEntradas,
			Entrada entrada, List<Asiento> asientos, Promocion promo,
			Date fechaReserva, Date fechaCreacion, float importe) {
		this.cliente = cliente;
		this.funcion = funcion;
		this.cantidadEntradas = cantidadEntradas;
		this.entrada = entrada;
		this.asientos = asientos;
		this.promo = promo;
		this.fechaReserva = fechaReserva;
		this.fechaCreacion = fechaCreacion;
		this.importe = importe;
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

	public int getCantidadEntradas() {
		return cantidadEntradas;
	}

	public void setCantidadEntradas(int cantidadEntradas) {
		this.cantidadEntradas = cantidadEntradas;
	}

	public Entrada getEntrada() {
		return entrada;
	}

	public void setEntrada(Entrada entrada) {
		this.entrada = entrada;
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

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
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
		
		if (cantidadEntradas != item.getCantidadEntradas())
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
