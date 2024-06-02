package ar.edu.unlam.pb2.paquetetest;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.pb2.dominio.*;

public class TestBase {

	private Restaurante restaurante;

	@Before
	public void init() {
		this.restaurante = new Restaurante("Italia");
	}

	@Test
	public void queSePuedaCrearUnaReservaCliente() {
		Reserva reserva = new Reserva(1, LocalDate.of(2024, 5, 20), LocalTime.of(16, 0));
		Cliente cliente = new Cliente(1, "Juan");

		ReservaCliente reservaCliente = new ReservaCliente(reserva, cliente);

		assertNotNull(reservaCliente);
	}

	@Test
	public void queUnClientePuedaHacerUnaReserva()
			throws ClienteNoEncontradoException, ReservaNoEncontradaException, ReservaClienteDuplicadoException, MesaNoEncontrada, MesaYaAsignadaAReserva {
		Reserva reserva = new Reserva(1, LocalDate.of(2024, 5, 20), LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		Cliente cliente = new Cliente(1, "Juan");
		restaurante.agregarCliente(cliente);

		Boolean reservaRealizada = restaurante.realizarReserva(reserva, cliente,null);

		assertTrue(reservaRealizada);
	}

	@Test(expected = ClienteNoEncontradoException.class)
	public void dadoQueUnClientePuedeHacerUnaReservaSiNoSeLoEncuentraQueSeLanceLaExceptionAdecuada()
			throws ClienteNoEncontradoException, ReservaNoEncontradaException, ReservaClienteDuplicadoException, MesaNoEncontrada, MesaYaAsignadaAReserva {
		Reserva reserva = new Reserva(1, LocalDate.of(2024, 5, 20), LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		Cliente cliente = new Cliente(1, "Juan");

		restaurante.realizarReserva(reserva, cliente,null);
	}

	@Test(expected = ReservaNoEncontradaException.class)
	public void dadoQueUnClientePuedeHacerUnaReservaSiNoSeEncuentraLaReservaQueSeLanceLaExceptionAdecuada()
			throws ClienteNoEncontradoException, ReservaNoEncontradaException, ReservaClienteDuplicadoException, MesaNoEncontrada, MesaYaAsignadaAReserva {
		Reserva reserva = new Reserva(1, LocalDate.of(2024, 5, 20), LocalTime.of(16, 0));
		Cliente cliente = new Cliente(1, "Juan");
		restaurante.agregarCliente(cliente);

		restaurante.realizarReserva(reserva, cliente,null);
	}

	@Test(expected = ReservaClienteDuplicadoException.class)
	public void dadoQueUnClientePuedeHacerUnaReservaQueNoPuedaHaberLaMismaReservaClienteDosVeces()
			throws ClienteNoEncontradoException, ReservaNoEncontradaException, ReservaClienteDuplicadoException, MesaNoEncontrada, MesaYaAsignadaAReserva {
		Reserva reserva = new Reserva(1, LocalDate.of(2024, 5, 20), LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		Cliente cliente = new Cliente(1, "Juan");
		restaurante.agregarCliente(cliente);

		restaurante.realizarReserva(reserva, cliente,null);
		restaurante.realizarReserva(reserva, cliente,null);
	}
	
	//-----------------------PARTE MESA -------------------------------

	@Test
	public void queSePuedaCrearUnaMesa() {
		Integer numero = 1;
		Mesa mesa = new Mesa(numero);

		assertNotNull(mesa);
	}

	@Test
	public void queSePuedaAgregarUnaMesaAlRestaurente() {
		Mesa mesa = new Mesa(1);

		Boolean mesaAgregada = restaurante.agregarMesa(mesa);

		assertTrue(mesaAgregada);
	}
	
	@Test
	public void queSePuedaBuscarUnaMesa() {
		Mesa mesa = new Mesa(1);
		restaurante.agregarMesa(mesa);
		
		Mesa mesaObtenida = restaurante.buscarMesa(mesa);
		
		assertEquals(mesa,mesaObtenida);
	}
	
	@Test
	public void queSePuedaObtenerUnaReservaEntreLasReservasClientesDelRestaurante() throws ClienteNoEncontradoException, ReservaNoEncontradaException, ReservaClienteDuplicadoException, MesaNoEncontrada, MesaYaAsignadaAReserva {
		Cliente cliente = new Cliente(1,"Juan");
		restaurante.agregarCliente(cliente);
		Reserva reserva = new Reserva(1,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		restaurante.realizarReserva(reserva,cliente,null);
		
		Reserva reservaObtenida = restaurante.obtenerReservaDeReservasClientes(reserva);
		
		assertEquals(reserva,reservaObtenida);
	}

	@Test
	public void queSePuedaAsignarUnaMesaAUnaReserva() throws ClienteNoEncontradoException, ReservaNoEncontradaException, ReservaClienteDuplicadoException, MesaNoEncontrada, MesaYaAsignadaAReserva {
		Mesa mesa = new Mesa(1);
		restaurante.agregarMesa(mesa);
		Cliente cliente = new Cliente(1,"Juan");
		restaurante.agregarCliente(cliente);
		Reserva reserva = new Reserva(1,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		restaurante.realizarReserva(reserva,cliente,mesa);
		
		Reserva reservaObtenida = restaurante.obtenerReservaDeReservasClientes(reserva);
		
		assertEquals(mesa,reservaObtenida.getMesa());
	}
	
	@Test (expected = MesaNoEncontrada.class)
	public void dadoQueSePuedeAsignarUnaMesaAUnaReservaSiNoSeEncuentraLaMesaQueLanceException() throws ClienteNoEncontradoException, ReservaNoEncontradaException, ReservaClienteDuplicadoException, MesaNoEncontrada, MesaYaAsignadaAReserva {
		Mesa mesa = new Mesa(1);
		Cliente cliente = new Cliente(1,"Juan");
		restaurante.agregarCliente(cliente);
		Reserva reserva = new Reserva(1,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		restaurante.realizarReserva(reserva,cliente,mesa);
	}
	
	@Test (expected = MesaYaAsignadaAReserva.class)
	public void dadoQueSePuedeAsignarUnaMesaAUnaReservaSiEsaReservaYaTieneAsignadaUnaMesaQueLanceException() throws ClienteNoEncontradoException, ReservaNoEncontradaException, ReservaClienteDuplicadoException, MesaNoEncontrada, MesaYaAsignadaAReserva {
		Mesa mesa = new Mesa(1);
		restaurante.agregarMesa(mesa);
		Cliente cliente = new Cliente(1,"Juan");
		restaurante.agregarCliente(cliente);
		Reserva reserva = new Reserva(1,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		restaurante.realizarReserva(reserva,cliente,mesa);
		restaurante.realizarReserva(reserva,cliente,mesa);
	}

	@Test
	public void queUnaMesaTengaAsignadaVariosClientes() {
	}
	
	
	//----------------------------------- PARTE MESA FIN --------------------

	@Test
	public void queSePuedaBuscarUnaReservaCliente()
			throws ClienteNoEncontradoException, ReservaNoEncontradaException, ReservaClienteDuplicadoException, MesaNoEncontrada, MesaYaAsignadaAReserva {
		Cliente cliente = new Cliente(1, "Juan");
		restaurante.agregarCliente(cliente);
		Reserva reserva = new Reserva(1, LocalDate.of(2024, 5, 20), LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		restaurante.realizarReserva(reserva, cliente,null);

		ReservaCliente rcEncontrada = restaurante.buscarUnaReservaCliente(cliente, reserva);

		assertEquals(cliente, rcEncontrada.getCliente());
		assertEquals(reserva, rcEncontrada.getReserva());
	}

	@Test
	public void queSePuedaCrearUnPedido()
			throws ClienteNoEncontradoException, ReservaNoEncontradaException, ReservaClienteDuplicadoException, MesaNoEncontrada, MesaYaAsignadaAReserva {
		Cliente cliente = new Cliente(1, "Juan");
		restaurante.agregarCliente(cliente);
		Reserva reserva = new Reserva(1, LocalDate.of(2024, 5, 20), LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		restaurante.realizarReserva(reserva, cliente,null);
		Mesero mesero = new Mesero(15, "pepe", LocalDate.of(2009, 1, 5));
		ReservaCliente rcEncontrada = restaurante.buscarUnaReservaCliente(cliente, reserva);

		Pedido pedido = new Pedido(rcEncontrada, mesero);

		assertNotNull(pedido);
	}

	@Test
	public void queUnMeseroTomeUnPedido() throws ClienteNoEncontradoException, ReservaNoEncontradaException,
			PedidoDuplicadoException, EmpleadoNoEncontradoException, PedidoYaTomado, ReservaClienteDuplicadoException,
			ReservaClienteNoEncontrado, MesaNoEncontrada, MesaYaAsignadaAReserva {
		Mesero mesero = new Mesero(15, "pepe", LocalDate.of(2009, 1, 5));
		restaurante.agregarEmpleado(mesero);
		Cliente cliente = new Cliente(1, "Juan");
		restaurante.agregarCliente(cliente);
		Reserva reserva = new Reserva(1, LocalDate.of(2024, 5, 20), LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		restaurante.realizarReserva(reserva, cliente,null);

		Boolean pedidoTomadoPorMesero = restaurante.queUnMeseroTomeUnPedido(reserva, cliente, mesero);

		assertTrue(pedidoTomadoPorMesero);
	}

	@Test(expected = EmpleadoNoEncontradoException.class)
	public void dadoQueUnMeseroTomaUnPedidoSiNoSeLoEncuentraQueSeLanceUnaExceptionAdecuada()
			throws ClienteNoEncontradoException, ReservaNoEncontradaException, EmpleadoNoEncontradoException,
			ReservaClienteDuplicadoException, PedidoDuplicadoException, PedidoYaTomado, ReservaClienteNoEncontrado, MesaNoEncontrada, MesaYaAsignadaAReserva {
		Mesero mesero = new Mesero(15, "pepe", LocalDate.of(2009, 1, 5));
		Cliente cliente = new Cliente(1, "Juan");
		restaurante.agregarCliente(cliente);
		Reserva reserva = new Reserva(1, LocalDate.of(2024, 5, 20), LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		restaurante.realizarReserva(reserva, cliente,null);

		restaurante.queUnMeseroTomeUnPedido(reserva, cliente, mesero);
	}

	@Test(expected = ReservaClienteNoEncontrado.class)
	public void dadoQueUnMeseroTomaUnPedidoSiNoSeEncuentraElPedidoQueLanceException()
			throws ClienteNoEncontradoException, ReservaNoEncontradaException, ReservaClienteDuplicadoException,
			EmpleadoNoEncontradoException, PedidoDuplicadoException, PedidoYaTomado, ReservaClienteNoEncontrado, MesaNoEncontrada, MesaYaAsignadaAReserva {
		Mesero mesero = new Mesero(15, "pepe", LocalDate.of(2009, 1, 5));
		restaurante.agregarEmpleado(mesero);
		Cliente cliente = new Cliente(1, "Juan");
		restaurante.agregarCliente(cliente);
		Cliente cliente2 = new Cliente(2, "Enrique");
		restaurante.agregarCliente(cliente2);
		Reserva reserva = new Reserva(1, LocalDate.of(2024, 5, 20), LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		restaurante.realizarReserva(reserva, cliente,null);

		restaurante.queUnMeseroTomeUnPedido(reserva, cliente2, mesero);
	}

//	@Test (expected = PedidoYaTomado.class)
//	public void dadoQueUnMeseroTomaUnPedidoSiEsePedidoYaFueTomadoPorOtroMeseroQueLanceException() throws ClienteNoEncontradoException, ReservaNoEncontradaException, PedidoDuplicadoException, EmpleadoNoEncontradoException, PedidoYaTomado, ReservaClienteDuplicadoException, ReservaClienteNoEncontrado {
//		Mesero mesero = new Mesero(15,"pepe",LocalDate.of(2009, 1, 5));
//		Mesero mesero2 = new Mesero(19,"Lucas",LocalDate.of(2009, 1, 5));
//		restaurante.agregarEmpleado(mesero);
//		restaurante.agregarEmpleado(mesero2);
//		Cliente cliente = new Cliente(1,"Juan");
//		restaurante.agregarCliente(cliente);
//		Reserva reserva = new Reserva(1,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0));
//		restaurante.agregarReserva(reserva);
//		restaurante.realizarReserva(reserva,cliente);
//		
//		restaurante.queUnMeseroTomeUnPedido(reserva,cliente,mesero);
//		restaurante.queUnMeseroTomeUnPedido(reserva,cliente,mesero2);
//	}

	@Test(expected = PedidoDuplicadoException.class)
	public void dadoQueUnMeseroTomaLaReservaDeUnClienteQueNoSeDeLaMismaCombinacionDeReservaClienteMeseroDosVeces()
			throws ClienteNoEncontradoException, ReservaNoEncontradaException, PedidoYaTomado,
			EmpleadoNoEncontradoException, PedidoDuplicadoException, ReservaClienteDuplicadoException,
			ReservaClienteNoEncontrado, MesaNoEncontrada, MesaYaAsignadaAReserva {
		Mesero mesero = new Mesero(15, "pepe", LocalDate.of(2009, 1, 5));
		restaurante.agregarEmpleado(mesero);
		Cliente cliente = new Cliente(1, "Juan");
		restaurante.agregarCliente(cliente);
		Reserva reserva = new Reserva(1, LocalDate.of(2024, 5, 20), LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		restaurante.realizarReserva(reserva, cliente,null);

		restaurante.queUnMeseroTomeUnPedido(reserva, cliente, mesero);
		restaurante.queUnMeseroTomeUnPedido(reserva, cliente, mesero);
	}

	@Test
	public void queSePuedaAsignarEmpleadosAUnEncargado()
			throws EmpleadoNoEncontradoException, EmpleadoYaAsignadoAEncargado {
		Encargado encargado = new Encargado(15, "pepe", LocalDate.of(2009, 1, 5));
		Mesero mesero = new Mesero(15, "pepe", LocalDate.of(2009, 1, 5));
		Mesero mesero2 = new Mesero(12, "lucas", LocalDate.of(2008, 5, 5));
		restaurante.agregarEmpleado(encargado);
		restaurante.agregarEmpleado(mesero);
		restaurante.agregarEmpleado(mesero2);

		Boolean empleadoAsignadoAEncargado = restaurante.asignarEmpleadoAEncargado(encargado, mesero);
		restaurante.asignarEmpleadoAEncargado(encargado, mesero2);
		Empleado empleado = restaurante.buscarUnEmpleado(encargado.getCodigo());

		assertTrue(empleadoAsignadoAEncargado);
		assertEquals(2, ((Encargado) empleado).getEmpleadosACargo().size());
	}

	@Test(expected = EmpleadoNoEncontradoException.class)
	public void dadoQueSePuedeAsignarEmpleadosAUnEncargadoSiNoSeEncuentraElEncargadoQueSeLanceException()
			throws EmpleadoNoEncontradoException, EmpleadoYaAsignadoAEncargado {
		Encargado encargado = new Encargado(15, "pepe", LocalDate.of(2009, 1, 5));
		Mesero mesero = new Mesero(16, "pepe", LocalDate.of(2009, 1, 5));
		restaurante.agregarEmpleado(mesero);

		restaurante.asignarEmpleadoAEncargado(encargado, mesero);
	}

	@Test(expected = EmpleadoYaAsignadoAEncargado.class)
	public void dadoQueSePuedeAsignarEmpleadosAUnEncargadoSiUnEmpleadoYaFueAsignadoAEseEncargadoQueLanceException()
			throws EmpleadoNoEncontradoException, EmpleadoYaAsignadoAEncargado {
		Encargado encargado = new Encargado(15, "pepe", LocalDate.of(2009, 1, 5));
		restaurante.agregarEmpleado(encargado);
		Mesero mesero = new Mesero(16, "pepe", LocalDate.of(2009, 1, 5));
		restaurante.agregarEmpleado(mesero);

		restaurante.asignarEmpleadoAEncargado(encargado, mesero);
		restaurante.asignarEmpleadoAEncargado(encargado, mesero);
	}

}
