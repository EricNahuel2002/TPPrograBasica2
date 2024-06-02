package ar.edu.unlam.pb2.dominio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;


public class Restaurante implements IRestaurante {
	private String nombre;
	private List<Empleado> empleados;
	private List<Cliente> clientes;
	private List<Reserva> reservas;
	private List<ReservaCliente> reservasClientes;
	private List<Pedido> pedidos;
	private List<Mesa> mesas;

	public Restaurante(String nombreRestaurante) {
		this.nombre = nombreRestaurante;
		this.empleados = new ArrayList<>();
		this.clientes = new ArrayList<>();
		this.reservas = new ArrayList<>();
		this.reservasClientes = new ArrayList<>();
		this.pedidos = new ArrayList<>();
		this.mesas = new ArrayList<>();
	}
	

	public Empleado buscarUnEmpleado(Integer codigo) {
		for (Empleado empleado : empleados) {
			if (empleado.getCodigo().equals(codigo)) {
				return empleado;
			}
		}
		return null;
	}
	// cantidad de veces que un cliente fue al restaurante
	// coleccion de clientes
	// coleccion de empleados


	public Boolean agregarEmpleado(Empleado empleado) {
	    Empleado empleadoEncontrado = this.buscarUnEmpleado(empleado.getCodigo());
	    if (empleadoEncontrado == null) {
	        return empleados.add(empleado);
	    }
	    return false;
	}
	
	@Override
	public Boolean despedirUnEmpleado(Integer codigo) {
		Empleado empleado = this.buscarUnEmpleado(codigo);
		if (empleado != null) {
			return empleados.remove(empleado);
		}
		return false;
	}
	
	@Override
	public Boolean agregarCliente(Cliente cliente) {
		return this.clientes.add(cliente);
	}


	public Boolean agregarReserva(Reserva reserva) {
		return reservas.add(reserva);
	}


//	public Boolean agregarReservaCliente(ReservaCliente reservaCliente) {
//
//		Boolean reservaClienteAgregada = reservasClientes.add(reservaCliente);
//
//		if(reservaClienteAgregada) {
//			Mesero mesero = (Mesero)reservaCliente.getMesero();
//			mesero.incrementarCantidadPedidos();
//		}
//
//		return reservaClienteAgregada;
//	}

	@Override
	public Cliente buscarUnCliente(Integer id) {
		for (Cliente cliente : clientes) {
			if (cliente.getNumero().equals(id)) {
				return cliente;
			}
		}
		return null;
	}
	
	// metodos calculara sueldo

	public Boolean cargarMeseroACargoDelEncargado(Empleado encargado, Empleado mesero) {
		if(buscarUnEmpleado(encargado.getCodigo()) != null && buscarUnEmpleado(mesero.getCodigo()) != null) {
			return ((Encargado) encargado).asignarEmpleado(mesero);
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


	public Boolean realizarReserva(Reserva reserva, Cliente cliente, Mesa mesa)
			throws ClienteNoEncontradoException, ReservaNoEncontradaException, ReservaClienteDuplicadoException, MesaNoEncontrada, MesaYaAsignadaAReserva {
		Cliente clienteEncontrado = validacionClienteNoEncontrado(cliente);
		Reserva reservaEncontrada = validacionReservaNoEncontrada(reserva);
		ReservaCliente rc = validacionReservaClienteDuplicado(clienteEncontrado, reservaEncontrada);
		Mesa mesaEncontrada = validacionMesaNoEncontrada(mesa);
		rc = new ReservaCliente(reservaEncontrada, clienteEncontrado,mesaEncontrada);
		return reservasClientes.add(rc);
	}

	private Mesa validacionMesaNoEncontrada(Mesa mesa) throws MesaNoEncontrada {
		Mesa mesaEncontrada = this.buscarMesa(mesa);
		if(mesaEncontrada == null) {
			throw new MesaNoEncontrada();
		}
		return mesaEncontrada;
	}

	private ReservaCliente validacionReservaClienteDuplicado(Cliente clienteEncontrado, Reserva reservaEncontrada)
			throws ReservaClienteDuplicadoException, MesaYaAsignadaAReserva {
		ReservaCliente rc = this.buscarReservaCliente(reservaEncontrada, clienteEncontrado);
		if (rc != null) {
			if(rc.getReserva().getMesa() != null) {
				throw new MesaYaAsignadaAReserva();
			}
			throw new ReservaClienteDuplicadoException();
		}
		return rc;
	}

	private Reserva validacionReservaNoEncontrada(Reserva reserva) throws ReservaNoEncontradaException {
		Reserva reservaEncontrada = this.buscarReserva(reserva.getId());
		if (reservaEncontrada == null) {
			throw new ReservaNoEncontradaException();
		}
		return reservaEncontrada;
	}

	private Cliente validacionClienteNoEncontrado(Cliente cliente) throws ClienteNoEncontradoException {
		Cliente clienteEncontrado = this.buscarUnCliente(cliente.getNumero());
		if (clienteEncontrado == null) {
			throw new ClienteNoEncontradoException();
		}
		return clienteEncontrado;
	}

	private ReservaCliente buscarReservaCliente(Reserva reservaEncontrada, Cliente clienteEncontrado) {
		for (ReservaCliente pd : reservasClientes) {
			if (pd.getReserva().equals(reservaEncontrada) && pd.getCliente().equals(clienteEncontrado)) {
				return pd;
			}
		}
		return null;
	}

	private Pedido buscarPedido(ReservaCliente reservaClienteEncontrada, Empleado empleadoEncontrado) {
		for (Pedido pd : pedidos) {
			if (pd.getReservaCliente().equals(reservaClienteEncontrada) && pd.getMesero().equals(empleadoEncontrado)) {
				return pd;
			}
		}
		return null;
	}

	private Reserva buscarReserva(Integer id) {
		for (Reserva reserva : reservas) {
			if (reserva.getId().equals(id)) {
				return reserva;
			}
		}
		return null;
	}


	public List<Empleado> obtenerEncargados() {
		List<Empleado> encargados = new ArrayList<>();
		for (Empleado empleado : empleados) {
			if (empleado instanceof Encargado) {
				encargados.add(empleado);
			}
		}
		return encargados;
	}

	public List<Reserva> obtenerHistorialDeReservasDeUnCliente(Cliente cliente) {
		List<Reserva> reservasDeUnCliente = new ArrayList<>();
		for (ReservaCliente rc : reservasClientes) {
			if (rc.getCliente().equals(cliente)) {
				reservasDeUnCliente.add(rc.getReserva());
			}
		}
		return reservasDeUnCliente;
	}

	// reserva n----n clientes sale clase intermedia
	// en


	public Boolean queUnMeseroTomeUnPedido(Reserva reserva, Cliente cliente, Empleado mesero)
			throws EmpleadoNoEncontradoException, PedidoDuplicadoException, ReservaNoEncontradaException,
			ClienteNoEncontradoException, ReservaClienteNoEncontrado {
		validacionReservaNoEncontrada(reserva);
		validacionClienteNoEncontrado(cliente);
		validacionEmpleadoNoEncontrado(mesero);
		ReservaCliente rcEncontrada = validacionReservaClienteNoEncontrado(reserva, cliente);
		Pedido pedido = this.buscarPedido(rcEncontrada, mesero);
		if (pedido == null) {
			((Mesero) mesero).incrementarCantidadPedidos();
			return pedidos.add(new Pedido(rcEncontrada, mesero));
		}
		if (pedido.getMesero().equals(mesero)) {
			throw new PedidoDuplicadoException();
		}
		return false;
	}

	private ReservaCliente validacionReservaClienteNoEncontrado(Reserva reserva, Cliente cliente)
			throws ReservaClienteNoEncontrado {
		ReservaCliente rcEncontrada = this.buscarUnaReservaCliente(cliente, reserva);
		if (rcEncontrada == null) {
			throw new ReservaClienteNoEncontrado();
		}
		return rcEncontrada;
	}

	private void validacionEmpleadoNoEncontrado(Empleado empleado) throws EmpleadoNoEncontradoException {
		Empleado empleadoEncontrado = this.buscarUnEmpleado(empleado.getCodigo());
		if (empleadoEncontrado == null) {
			throw new EmpleadoNoEncontradoException();
		}
	}


	public Boolean asignarEmpleadoAEncargado(Empleado encargado, Empleado mesero)
			throws EmpleadoNoEncontradoException, EmpleadoYaAsignadoAEncargado {
		validacionEmpleadoNoEncontrado(encargado);
		validacionEmpleadoNoEncontrado(mesero);
		validacionEmpleadoYaAsignadoAEncargado(encargado, mesero);
		return ((Encargado) encargado).asignarEmpleado(mesero);
	}

	private void validacionEmpleadoYaAsignadoAEncargado(Empleado encargado, Empleado mesero)
			throws EmpleadoYaAsignadoAEncargado {
		for (Empleado empleado : ((Encargado) encargado).getEmpleadosACargo()) {
			if (empleado.equals(mesero)) {
				throw new EmpleadoYaAsignadoAEncargado();
			}
		}
	}

	public ReservaCliente buscarUnaReservaCliente(Cliente cliente, Reserva reserva) {
		for (ReservaCliente rc : reservasClientes) {
			if (rc.getCliente().equals(cliente) && rc.getReserva().equals(reserva)) {
				return rc;
			}
		}
		return null;
	}
	
	public Mesa buscarMesa(Mesa mesa) {
		for(Mesa m: mesas) {
			if(m.equals(mesa)) {
				return m;
			}
		}
		return null;
	}

	public Boolean agregarMesa(Mesa mesa) {
		return mesas.add(mesa);
	}

	@Override
	public List<Empleado> obtenerListaDeEmpleadosOrdenadoDeMayorAMenorPorSueldo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Empleado> obtenerListaDeEncargadosOrdenadoDeMayorAMenorPorSueldo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Empleado> obtenerListaDeMeserosOrdenadoDeMayorAMenorPorSueldo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Empleado> obtenerListaDeCajerosOrdenadoDeMayorAMenorPorSueldo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Empleado obtenerElMeseroDelMes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashSet<Cliente> obtenerLaCantidadDeClientesQueFueronAlRestaurante() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double calcularElSueldoEnBaseALaAntiguedadDeUnEmpleado(Empleado empleado) {
		// TODO Auto-generated method stub
		return null;
	}

	public Reserva obtenerReservaDeReservasClientes(Reserva reserva) {
		for (ReservaCliente rc : reservasClientes) {
			if (rc.getReserva().equals(reserva)) {
				return rc.getReserva();
			}
		}
		return null;
	}


}
