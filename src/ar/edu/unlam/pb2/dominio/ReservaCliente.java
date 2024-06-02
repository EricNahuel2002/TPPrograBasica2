package ar.edu.unlam.pb2.dominio;

import java.util.Objects;

public class ReservaCliente {
	
	private Reserva reserva;
	private Cliente cliente;

	public ReservaCliente(Reserva reserva, Cliente cliente) {
		this.reserva = reserva;
		this.cliente = cliente;
	}

	public ReservaCliente(Reserva reserva, Cliente cliente, Mesa mesa) {
		this.reserva = reserva;
		this.cliente = cliente;
		this.reserva.setMesa(mesa);
	}

	public ReservaCliente(Reserva reserva, Cliente cliente, Empleado mesero) {
		
	}

	public Reserva getReserva() {
		return reserva;
	}

	public Cliente getCliente() {
		return cliente;
	}

	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, reserva);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReservaCliente other = (ReservaCliente) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(reserva, other.reserva);
	}

}
