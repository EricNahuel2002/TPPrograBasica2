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
	private static Integer CANTIDAD_MAXIMA_EMPLEADOS = 2;
	
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


	public Cliente buscarUnCliente(Integer numero) throws ClienteNoEncontradoException {
		for(Cliente cliente: clientes) {
			if(cliente.getNumero().equals(numero)) {
				return cliente;
			}
		}
		throw new ClienteNoEncontradoException();
	}
	
	// metodos calculara sueldo

	public Boolean cargarMeseroACargoDelEncargado(Empleado encargado, Empleado mesero) throws EmpleadoNoEncontradoException {
		if(buscarUnEmpleado(encargado.getCodigo()) != null && buscarUnEmpleado(mesero.getCodigo()) != null) {
			return ((Encargado) encargado).asignarEmpleado(mesero);
		}
		return false;
		
	}


	public void cargarValorHoraDeUnEmpleado(Integer idEmpleado, Double valorHora) throws EmpleadoNoEncontradoException {

		Empleado empleado = buscarUnEmpleado(idEmpleado);

		if(empleado != null) {
			empleado.setValorHora(valorHora);
		}

	}

	public void cargarHorasTrabajadasDeUnEmpleado(Integer idEmpleado, Integer horasTrabajadas) throws EmpleadoNoEncontradoException {

		Empleado empleado = buscarUnEmpleado(idEmpleado);

		if(empleado != null) {
			empleado.setHorasTrabajadas(horasTrabajadas);
		}

	}

	public Boolean cargarSueldoEmpleado(Integer idEmpleado) throws EmpleadoNoEncontradoException {
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
	
	@Override
	public Double calcularElSueldoEnBaseALaAntiguedadDeUnEmpleado(Empleado empleado) {
		// falta empezar
		return null;
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


	@Override
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

	@Override
	public HashSet<Cliente> obtenerLaCantidadDeClientesQueFueronAlRestaurante(){
		HashSet<Cliente> clientesNoRepetidos = new HashSet<Cliente>();
		clientesNoRepetidos.addAll(this.clientes);
		return clientesNoRepetidos;
		
	}


	@Override
	public List<Empleado> obtenerListaDeEncargadosOrdenadoDeMayorAMenorPorSueldo() {
		List<Empleado> listaEncargados = new ArrayList<>();
		for (Empleado empleado : empleados) {
			if (empleado instanceof Encargado) {
				listaEncargados.add(empleado);
				}
		}
		obtenerListaOrdenaDeEmpleadosSueldoDescendente(listaEncargados);
		return listaEncargados;
	}

	private void obtenerListaOrdenaDeEmpleadosSueldoDescendente(List<Empleado> lista) {
		Collections.sort(lista,(o1,o2) -> o2.getSueldo().compareTo(o1.getSueldo()));	
	}


	@Override
	public List<Empleado> obtenerListaDeMeserosOrdenadoDeMayorAMenorPorSueldo() {
		List<Empleado> listaDeMeseros = new ArrayList<>();
		for (Empleado empleado : empleados) {
			if (empleado instanceof Mesero) {
				listaDeMeseros.add(empleado);
			}
		}
		obtenerListaOrdenaDeEmpleadosSueldoDescendente(listaDeMeseros);
		return listaDeMeseros;
	}

	@Override
	public List<Empleado> obtenerListaDeCajerosOrdenadoDeMayorAMenorPorSueldo() {
		List<Empleado> listaDeCajeros = new ArrayList<>();
		for (Empleado empleado : empleados) {
			if (empleado instanceof Cajero) {
				listaDeCajeros.add(empleado);
			}
		}
		obtenerListaOrdenaDeEmpleadosSueldoDescendente(listaDeCajeros);
		return listaDeCajeros;
	}

	@Override
	public Empleado obtenerElMeseroDelMes() throws NoHayEmpleadoDelMesException {
		Empleado meseroMes = null;
		int cp = -1;
		for (Empleado e : empleados) {
			if (e instanceof Mesero) {
				Mesero m = (Mesero)e;
				
				if ( m.getCantidadPedidos() > cp  ) {
					cp = m.getCantidadPedidos();
					meseroMes = m;
					return meseroMes;
					
				} 
			}
			 
		}
		throw new NoHayEmpleadoDelMesException();
		
	}
	
	public Boolean queUnMeseroPuedaTomarMasDeUnPedido(Reserva reserva, Cliente cliente, Empleado mesero)
			throws EmpleadoNoEncontradoException, PedidoDuplicadoException, ReservaNoEncontradaException,
			ClienteNoEncontradoException, ReservaClienteNoEncontrado {
		
		validacionReservaNoEncontrada(reserva);
		validacionClienteNoEncontrado(cliente);
		validacionEmpleadoNoEncontrado(mesero);
		
		ReservaCliente rcEncontrada = validacionReservaClienteNoEncontrado(reserva, cliente);
		
		Pedido pedido = this.buscarPedido(rcEncontrada, mesero);
		if (pedido == null) {
			for (Empleado e : empleados) {
				if (e instanceof Mesero) {
					Mesero m = (Mesero)e;
		        m.incrementarCantidadPedidos();
				}
			return pedidos.add(new Pedido(rcEncontrada, mesero));
		}
		
		}
		return false;
	}


// metodos reservas
	
	public Boolean realizarReserva(Reserva reserva, Cliente cliente)
			throws ClienteNoEncontradoException, ReservaNoEncontradaException, ReservaClienteDuplicadoException, MesaNoEncontrada, MesaYaAsignadaAReserva {
		
		Cliente clienteEncontrado = validacionClienteNoEncontrado(cliente);
		Reserva reservaEncontrada = validacionReservaNoEncontrada(reserva);
		ReservaCliente rc = validacionReservaClienteDuplicado(clienteEncontrado, reservaEncontrada);
		
		rc = new ReservaCliente(reservaEncontrada, clienteEncontrado,null);
		return reservasClientes.add(rc);
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
			throw new EmpleadoNoEncontradoException("El empleado no existe");
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


	public Reserva obtenerReservaDeReservasClientes(Reserva reserva) {
		for (ReservaCliente rc : reservasClientes) {
			if (rc.getReserva().equals(reserva)) {
				return rc.getReserva();
			}
		}
		return null;
	}



}
