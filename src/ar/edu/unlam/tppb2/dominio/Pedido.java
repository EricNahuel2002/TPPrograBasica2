package ar.edu.unlam.tppb2.dominio;

import java.util.Objects;

public class Pedido {
	
	private Reserva pedido;
	private Cliente cliente;
	private Empleado mesero;
	//que una pedido tenga una COLECCION DE MESEROS

	public Pedido(Reserva pedido, Cliente cliente) {
		this.pedido = pedido;
		this.cliente = cliente;
	}

	public Reserva getPedido() {
		return pedido;
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
		return Objects.hash(cliente, pedido);
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
		return Objects.equals(cliente, other.cliente) && Objects.equals(pedido, other.pedido);
	}
}
