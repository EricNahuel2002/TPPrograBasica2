package ar.edu.unlam.pb2.dominio;

import java.util.Objects;

public class Pedido {
	
	private Reserva reserva;
	private Cliente cliente;
	private Empleado mesero;
	//que una pedido tenga una COLECCION DE MESEROS

	public Pedido(Reserva reserva, Cliente cliente) {
		this.reserva = reserva;
		this.cliente = cliente;
	}

	public Reserva getReserva() {
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
		return Objects.hash(cliente, mesero, reserva);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(mesero, other.mesero)
				&& Objects.equals(reserva, other.reserva);
	}

	
}
