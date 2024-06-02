package ar.edu.unlam.pb2.dominio;

import java.util.Objects;

public class Pedido {

	private ReservaCliente reservaCliente;
	private Empleado mesero;

	public Pedido(ReservaCliente rcEncontrada, Empleado mesero) {
		this.reservaCliente = rcEncontrada;
		this.mesero = mesero;
	}

	public ReservaCliente getReservaCliente() {
		return reservaCliente;
	}
	
	public Empleado getMesero() {
		return mesero;
	}

	@Override
	public int hashCode() {
		return Objects.hash(mesero, reservaCliente);
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
		return Objects.equals(mesero, other.mesero) && Objects.equals(reservaCliente, other.reservaCliente);
	}

}
