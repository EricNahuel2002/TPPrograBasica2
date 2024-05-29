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
	

	public Empleado buscarUnEmpleado(Integer codigo) {
		for(Empleado empleado: empleados) {
			if(empleado.getCodigo().equals(codigo)) {
				return empleado;
			}
		}
		return null;
	}

	public Boolean agregarEmpleado(Empleado empleado) {
	    Empleado empleadoEncontrado = this.buscarUnEmpleado(empleado.getCodigo());
	    if (empleadoEncontrado == null) {
	        return empleados.add(empleado);
	    }
	    return false;
	}


	public Boolean despedirUnEmpleado(Integer codigo) {
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


	public Cliente buscarUnCliente(Integer numero) {
		for(Cliente cliente: clientes) {
			if(cliente.getNumero().equals(numero)) {
				return cliente;
			}
		}
		return null;
	}

	@Override
	// falta crear una interfaz 
	public List<Empleado> obtenerListaDeTodosLosEmpleadosOrdenadoDeMayorAMenorPorSueldo() {
		Collections.sort(empleados,(o1,o2) -> o2.getSueldo().compareTo(o1.getSueldo()));
		return this.empleados;
	}

	
}
