package ar.edu.unlam.pb2.dominio;

import java.util.ArrayList;
import java.util.List;

public class Restaurante {
	private String nombre;
	private List<Empleado> empleados;
	private List<Cliente> clientes;
	private List<Reserva> reservas;
	private List<ReservaCliente> reservasClientes;
	private List<Pedido> pedidos;

	public Restaurante(String nombreRestaurante) {
		this.nombre = nombreRestaurante;
		this.empleados = new ArrayList<>();
		this.clientes = new ArrayList<>();
		this.reservas = new ArrayList<>();
		this.reservasClientes = new ArrayList<>();
		this.pedidos = new ArrayList<>();
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

		Boolean reservaClienteAgregada = reservasClientes.add(reservaCliente);

		return reservaClienteAgregada;
	}

	public Boolean agregarPedido(Pedido pedido) {
		Boolean pedidoAgregado = pedidos.add(pedido);
		
		if(pedidoAgregado) {
			Mesero mesero = (Mesero)pedido.getMesero();
			mesero.incrementarCantidadPedidos();
		}
		
		return pedidoAgregado;
	}


	public Cliente buscarUnCliente(Integer numero) {
		for(Cliente cliente: clientes) {
			if(cliente.getNumero().equals(numero)) {
				return cliente;
			}
		}
		return null;
	}


	public Boolean cargarMeseroACargoDelEncargado(Empleado encargado, Empleado mesero) {
		if(buscarUnEmpleado(encargado.getCodigo()) != null && buscarUnEmpleado(mesero.getCodigo()) != null) {
			return ((Encargado) encargado).agregarMeseroACargo(mesero);
		}
		return false;
		
	}


	public void cargarValorHoraDeUnEmpleado(Integer idEmpleado, Double valorHora) {

		Empleado empleado = buscarUnEmpleado(idEmpleado);

		if(empleado != null) {
			empleado.setValorHora(valorHora);
		}

	}

	public void cargarHorasTrabajadasDeUnEmpleado(Integer idEmpleado, Integer horasTrabajadas) {

		Empleado empleado = buscarUnEmpleado(idEmpleado);

		if(empleado != null) {
			empleado.setHorasTrabajadas(horasTrabajadas);
		}

	}

	public Boolean cargarSueldoEmpleado(Integer idEmpleado) {
		Empleado empleado = buscarUnEmpleado(idEmpleado);
		Boolean cargado = false;

		if(empleado != null) {
			Double sueldo = empleado.calcularSueldo();
			empleado.setSueldo(sueldo);
			cargado = true;
		}
		return cargado;
	}

	public Double obtenerSueldoDeUnEmpleado(Integer idEmpleado) throws EmpleadoNoEncontradoException {
		Empleado empleado = buscarUnEmpleado(idEmpleado);

		if (empleado != null) {
            return empleado.getSueldo();
        }

        throw new EmpleadoNoEncontradoException("Empleado con ID " + idEmpleado + " no existe.");
    }
	
}
