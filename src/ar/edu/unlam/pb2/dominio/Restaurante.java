package ar.edu.unlam.pb2.dominio;

import java.util.ArrayList;
import java.util.List;

public class Restaurante {
	private String nombre;
	private List<Empleado> empleados;
	private List<Cliente> clientes;
	private List<Reserva> reservas;
	private List<ReservaCliente> reservasClientes;

	public Restaurante(String nombreRestaurante) {
		this.nombre = nombreRestaurante;
		this.empleados = new ArrayList<>();
		this.clientes = new ArrayList<>();
		this.reservas = new ArrayList<>();
		this.reservasClientes = new ArrayList<>();
	}
	

	public Empleado buscarUnEmpleado(Integer codigo) throws EmpleadoNoEncontradoException {
		for(Empleado empleado: empleados) {
			if(empleado.getCodigo().equals(codigo)) {
				return empleado;
			}
		}
		throw new EmpleadoNoEncontradoException();
	}

	public Boolean agregarEmpleado(Empleado empleado) throws EmpleadoDuplicadoException {
	    if (empleados.contains(empleado)) {
	        throw new EmpleadoDuplicadoException();
	    }
	    return empleados.add(empleado);
	}




	public Boolean despedirUnEmpleado(Integer codigo) throws EmpleadoNoEncontradoException {
		Empleado empleado = this.buscarUnEmpleado(codigo);
		if(empleado != null) {
			return empleados.remove(empleado);
		}
		return false;
	}

	public Boolean agregarCliente(Cliente cliente) {
		return this.clientes.add(cliente);
	}



	public Boolean agregarReserva(Reserva reserva) {
		return reservas.add(reserva);
	}


	public Boolean agregarReservaCliente(ReservaCliente reservaCliente) {
		return reservasClientes.add(reservaCliente);
	}


	public Cliente buscarUnCliente(Integer numero) throws ClienteNoEncontradoException {
		for(Cliente cliente: clientes) {
			if(cliente.getNumero().equals(numero)) {
				return cliente;
			}
		}
		throw new ClienteNoEncontradoException();
	}


	
}
