package ar.edu.unlam.pb2.dominio;

public class Pedido {
	
	private ReservaCliente reservaCliente;
	private Empleado mesero;
	
	public Pedido(ReservaCliente reservaCliente, Empleado mesero) {
		this.reservaCliente = reservaCliente;
		this.mesero = mesero;
	}

	public Empleado getMesero() {
		return mesero;
	}

	public void setMesero(Empleado mesero) {
		this.mesero = mesero;
	}
	
	

}
