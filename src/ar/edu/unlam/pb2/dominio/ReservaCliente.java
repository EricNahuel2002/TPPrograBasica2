package ar.edu.unlam.pb2.dominio;

import java.util.Objects;

public class ReservaCliente {
	
	private Reserva reserva;
	private Cliente cliente;
	private Empleado mesero;


	public ReservaCliente(Reserva reserva, Cliente cliente) {
		this.reserva = reserva;
		this.cliente = cliente;
	}

	public Reserva getPedido() {
		return reserva;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setMesero(Empleado mesero) {
		this.mesero = mesero;
	}

	public Empleado getMesero() {
		return mesero;
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
