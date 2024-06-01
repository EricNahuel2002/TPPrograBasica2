package ar.edu.unlam.tppb2.paquetetest;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;
import ar.edu.unlam.tppb2.dominio.*;
public class TestRestaurante {
	
	private Restaurante restaurante;
	
	@Before
	public void init() {
		this.restaurante = new Restaurante("Italia");
	}
	
	@Test
	public void queUnClientePuedaHacerUnaReserva() throws ClienteNoEncontradoException, ReservaNoEncontradaException, PedidoDuplicadoException {
		Reserva reserva = new Reserva(1,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		Cliente cliente = new Cliente(1,"Juan");
		restaurante.agregarCliente(cliente);
		
		Boolean reservaRealizada = restaurante.realizarPedido(reserva,cliente);
		
		assertTrue(reservaRealizada);
	}
	
	@Test (expected = ClienteNoEncontradoException.class)
	public void dadoQueUnClientePuedeHacerUnaReservaSiNoSeLoEncuentraQueSeLanceLaExceptionAdecuada() throws ClienteNoEncontradoException, ReservaNoEncontradaException, PedidoDuplicadoException {
		Reserva reserva = new Reserva(1,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		Cliente cliente = new Cliente(1,"Juan");
		
		restaurante.realizarPedido(reserva,cliente);
	}
	@Test (expected = ReservaNoEncontradaException.class)
	public void dadoQueUnClientePuedeHacerUnaReservaSiNoSeEncuentraLaReservaQueSeLanceLaExceptionAdecuada() throws ClienteNoEncontradoException, ReservaNoEncontradaException, PedidoDuplicadoException {
		Reserva reserva = new Reserva(1,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0));
		Cliente cliente = new Cliente(1,"Juan");
		restaurante.agregarCliente(cliente);
		
		restaurante.realizarPedido(reserva,cliente);
	}
	@Test (expected = PedidoDuplicadoException.class)
	public void dadoQueUnClientePuedeHacerUnaReservaQueNoPuedaHaberElMismoPedidoDosVeces() throws ClienteNoEncontradoException, ReservaNoEncontradaException, PedidoDuplicadoException {
		Reserva reserva = new Reserva(1,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		Cliente cliente = new Cliente(1,"Juan");
		restaurante.agregarCliente(cliente);
		
		restaurante.realizarPedido(reserva,cliente);
		restaurante.realizarPedido(reserva,cliente);
	}
	
	@Test
	public void queSePuedaAsignarUnaMesaAUnaReserva() {
		
	}
	
	@Test
	public void queUnaMesaTengaAsignadaVariosClientes() {
		
	}
	
	@Test
	public void queUnMeseroTomeUnPedido() throws ClienteNoEncontradoException, ReservaNoEncontradaException, PedidoDuplicadoException, EmpleadoNoEncontradoException, PedidoYaTomado {
		Mesero mesero = new Mesero(15,"pepe",LocalDate.of(2009, 1, 5));
		restaurante.agregarEmpleado(mesero);
		Cliente cliente = new Cliente(1,"Juan");
		restaurante.agregarCliente(cliente);
		Reserva reserva = new Reserva(1,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		restaurante.realizarPedido(reserva,cliente);
		
		Boolean reservaClienteTomadaPorMesero = restaurante.queUnMeseroTomeUnPedido(reserva,cliente,mesero);
		
		assertTrue(reservaClienteTomadaPorMesero);
	}
	
	@Test (expected = EmpleadoNoEncontradoException.class)
	public void dadoQueUnMeseroTomaLaReservaDeUnClienteSiNoSeLoEncuentraQueSeLanceUnaExceptionAdecuada() throws ClienteNoEncontradoException, ReservaNoEncontradaException, PedidoDuplicadoException, EmpleadoNoEncontradoException, PedidoYaTomado {
		Mesero mesero = new Mesero(15,"pepe",LocalDate.of(2009, 1, 5));
		Cliente cliente = new Cliente(1,"Juan");
		restaurante.agregarCliente(cliente);
		Reserva reserva = new Reserva(1,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		restaurante.realizarPedido(reserva,cliente);
		
		restaurante.queUnMeseroTomeUnPedido(reserva,cliente,mesero);
	}
	
	@Test (expected = PedidoYaTomado.class)
	public void dadoQueUnMeseroTomaUnPedidoSiEsePedidoYaFueTomadoPorOtroMeseroQueLanceException() throws ClienteNoEncontradoException, ReservaNoEncontradaException, PedidoDuplicadoException, EmpleadoNoEncontradoException, PedidoYaTomado {
		Mesero mesero = new Mesero(15,"pepe",LocalDate.of(2009, 1, 5));
		Mesero mesero2 = new Mesero(19,"Lucas",LocalDate.of(2009, 1, 5));
		restaurante.agregarEmpleado(mesero);
		restaurante.agregarEmpleado(mesero2);
		Cliente cliente = new Cliente(1,"Juan");
		restaurante.agregarCliente(cliente);
		Reserva reserva = new Reserva(1,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		restaurante.realizarPedido(reserva,cliente);
		
		restaurante.queUnMeseroTomeUnPedido(reserva,cliente,mesero);
		restaurante.queUnMeseroTomeUnPedido(reserva,cliente,mesero2);
	}
	
	
	@Test (expected = PedidoDuplicadoException.class)
	public void dadoQueUnMeseroTomaLaReservaDeUnClienteQueNoSeDeLaMismaCombinacionDeReservaClienteMeseroDosVeces() throws ClienteNoEncontradoException, ReservaNoEncontradaException, PedidoYaTomado, EmpleadoNoEncontradoException, PedidoDuplicadoException {
		Mesero mesero = new Mesero(15,"pepe",LocalDate.of(2009, 1, 5));
		restaurante.agregarEmpleado(mesero);
		Cliente cliente = new Cliente(1,"Juan");
		restaurante.agregarCliente(cliente);
		Reserva reserva = new Reserva(1,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		restaurante.realizarPedido(reserva,cliente);
		
		restaurante.queUnMeseroTomeUnPedido(reserva,cliente,mesero);
		restaurante.queUnMeseroTomeUnPedido(reserva,cliente,mesero);
	}
	
	
	@Test
	public void queSePuedaAsignarEmpleadosAUnEncargado() throws EmpleadoNoEncontradoException, EmpleadoYaAsignadoAEncargado {
		Encargado encargado = new Encargado(15,"pepe",LocalDate.of(2009, 1, 5));
		Mesero mesero = new Mesero(15,"pepe",LocalDate.of(2009, 1, 5));
		Mesero mesero2 = new Mesero(12,"lucas",LocalDate.of(2008, 5, 5));
		restaurante.agregarEmpleado(encargado);
		restaurante.agregarEmpleado(mesero);
		restaurante.agregarEmpleado(mesero2);
		
		Boolean empleadoAsignadoAEncargado = restaurante.asignarEmpleadoAEncargado(encargado,mesero);
		restaurante.asignarEmpleadoAEncargado(encargado,mesero2);
		Empleado empleado = restaurante.buscarUnEmpleado(encargado.getCodigo());
		
		assertTrue(empleadoAsignadoAEncargado);
		assertEquals(2,((Encargado) empleado).getEmpleadosACargo().size());
	}
	
	
	@Test (expected = EmpleadoNoEncontradoException.class)
	public void dadoQueSePuedeAsignarEmpleadosAUnEncargadoSiNoSeEncuentraElEncargadoQueSeLanceException() throws EmpleadoNoEncontradoException, EmpleadoYaAsignadoAEncargado {
		Encargado encargado = new Encargado(15,"pepe",LocalDate.of(2009, 1, 5));
		Mesero mesero = new Mesero(16,"pepe",LocalDate.of(2009, 1, 5));
		restaurante.agregarEmpleado(mesero);
		
		restaurante.asignarEmpleadoAEncargado(encargado,mesero);
	}
	
	@Test (expected = EmpleadoYaAsignadoAEncargado.class)
	public void dadoQueSePuedeAsignarEmpleadosAUnEncargadoSiUnEmpleadoYaFueAsignadoAEseEncargadoQueLanceException() throws EmpleadoNoEncontradoException, EmpleadoYaAsignadoAEncargado {
		Encargado encargado = new Encargado(15,"pepe",LocalDate.of(2009, 1, 5));
		restaurante.agregarEmpleado(encargado);
		Mesero mesero = new Mesero(16,"pepe",LocalDate.of(2009, 1, 5));
		restaurante.agregarEmpleado(mesero);
		
		restaurante.asignarEmpleadoAEncargado(encargado,mesero);
		restaurante.asignarEmpleadoAEncargado(encargado,mesero);
	}
	
	
}
