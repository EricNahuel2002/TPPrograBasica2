package ar.edu.unlam.pb2.dominio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

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

		Boolean reservaClienteAgregada = reservasClientes.add(reservaCliente);

		if(reservaClienteAgregada) {
			Mesero mesero = (Mesero)reservaCliente.getMesero();
			mesero.incrementarCantidadPedidos();
		}

		return reservaClienteAgregada;
	}


	public Cliente buscarUnCliente(Integer numero) {
		for(Cliente cliente: clientes) {
			if(cliente.getNumero().equals(numero)) {
				return cliente;
			}
		}
		return null;
	}
	
	// metodos calculara sueldo

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

	public Double obtenerSueldoDeUnEmpleado(Integer idEmpleado) {
		Empleado empleado = buscarUnEmpleado(idEmpleado);

		if(empleado != null) {
			return empleado.getSueldo();
		}

		return -1.0;

	}

//>>>>>>> e2b761878c31bf6f1521a3a617659efa0ea693e3
//<<<<<<< HEAD
	
	// metodos obtener 
	public TreeSet<Empleado> obtenerListaDeEmpleadosOrdenadosAscendenteSinQueSeRepitaElCodigo() {
		TreeSet <Empleado> listaEmpleadosOrdenado = new TreeSet<>();
		
		listaEmpleadosOrdenado.addAll(this.empleados);
		
		return listaEmpleadosOrdenado;
	}
	
	public TreeSet<Empleado> obtenerListaDeEmpleadosOrdenadosPorUnOrdenEnEspecifico( Comparator <Empleado> OrdenDescendiente) {
		
		TreeSet<Empleado> listaNoRepetida = new TreeSet <Empleado> (OrdenDescendiente);
		listaNoRepetida.addAll(this.empleados);
		return listaNoRepetida;
	}

	
	public List<Empleado> obtenerListaDeEmpleadosOrdenadosDescendentePorSueldo() {
		Collections.sort(empleados,(o1,o2) -> o2.getSueldo().compareTo(o1.getSueldo()));
		return this.empleados;
	}


	public List<Empleado> obtenerListaDeMeseros() {
		List<Empleado> listaMeseros = new ArrayList<>();
		for (Empleado empleado : empleados) {
			if (empleado instanceof Mesero) {
				listaMeseros.add(empleado);
			}
		}
		return listaMeseros;
	}
	
	public List<Empleado> obtenerListaDeMeserosPorAntiguedadDescendente() {
		List<Empleado> listaMeseros = obtenerListaDeMeseros();
		Collections.sort(listaMeseros,(o1,o2)-> o2.calcularAniosDeAntiguedad().compareTo(o1.calcularAniosDeAntiguedad()));
		return listaMeseros;
	}


	public HashSet<Cliente> obtenerCantidadDeClientes(){
		HashSet<Cliente> clientesNoRepetidos = new HashSet<Cliente>();
		clientesNoRepetidos.addAll(this.clientes);
		return clientesNoRepetidos;
		
		
		
	}

}
