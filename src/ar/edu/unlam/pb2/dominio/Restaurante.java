package ar.edu.unlam.pb2.dominio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.HashSet;

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
	// cantidad de veces que un cliente fue al restaurante
	// coleccion de clientes
	// coleccion de empleados

	public Boolean agregarEmpleado(Empleado mesero) {
		Empleado empleadoEncontrado = this.buscarUnEmpleado(mesero.getCodigo());
		if (empleadoEncontrado == null) {
			return empleados.add(mesero);
		}
		return false;
	}

	public Empleado buscarUnEmpleado(Integer codigo) {
		for (Empleado empleado : empleados) {
			if (empleado.getCodigo().equals(codigo)) {
				return empleado;
			}
		}
		return null;
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

	@Override
	public Cliente buscarUnCliente(Integer id) {
		for (Cliente cliente : clientes) {
			if (cliente.getNumero().equals(id)) {
				return cliente;
			}
		}
		return null;
	}

	@Override
	public Boolean agregarReserva(Reserva reserva) {
		return reservas.add(reserva);
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

	public Boolean agregarReservaCliente(ReservaCliente rc) {
		return reservasClientes.add(rc);
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

//	public Boolean queUnMeseroTomeUnPedido(Reserva reserva, Cliente cliente, Empleado mesero)
//			throws EmpleadoNoEncontradoException, PedidoDuplicadoException, ReservaNoEncontradaException,
//			ClienteNoEncontradoException, PedidoYaTomado {
//		validacionReservaNoEncontrada(reserva);
//		validacionClienteNoEncontrado(cliente);
//		validacionEmpleadoNoEncontrado(mesero);
//		ReservaCliente pedido = this.buscarPedido(reserva, cliente);
//		if (pedido.getMesero() == null) {
//			pedido.setMesero(mesero);
//			((Mesero) mesero).incrementarCantidadDePedidosTomados();
//			return true;
//		}
//		if (pedido.getMesero() != null) {
//			if (pedido.getMesero().equals(mesero)) {
//				throw new PedidoDuplicadoException();
//			}
//			throw new PedidoYaTomado();
//		}
//		return false;
//	}

	public Boolean queUnMeseroTomeUnPedido(Reserva reserva, Cliente cliente, Empleado mesero)
			throws EmpleadoNoEncontradoException, PedidoDuplicadoException, ReservaNoEncontradaException,
			ClienteNoEncontradoException, ReservaClienteNoEncontrado {
		validacionReservaNoEncontrada(reserva);
		validacionClienteNoEncontrado(cliente);
		validacionEmpleadoNoEncontrado(mesero);
		ReservaCliente rcEncontrada = validacionReservaClienteNoEncontrado(reserva, cliente);
		Pedido pedido = this.buscarPedido(rcEncontrada, mesero);
		if (pedido == null) {
			((Mesero) mesero).incrementarCantidadDePedidosTomados();
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

	public Boolean pagarSueldoAUnEmpleado(Empleado empleado, Double sueldo) {
		Empleado empleadoObtenido = this.buscarUnEmpleado(empleado.getCodigo());
		if (empleadoObtenido != null) {
			empleadoObtenido.cobrarSueldo(sueldo);
			return true;
		}
		return false;
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
