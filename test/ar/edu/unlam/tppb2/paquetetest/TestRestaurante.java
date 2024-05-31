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
	public void queUnClientePuedaHacerUnaReserva() {
		Reserva reserva = new Reserva(1,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		Cliente cliente = new Cliente(1,"Juan");
		restaurante.agregarCliente(cliente);
		
		Boolean reservaRealizada = restaurante.realizarPedido(reserva,cliente);
		
		assertTrue(reservaRealizada);
	}
	
	@Test
	public void dadoQueUnClientePuedeHacerUnaReservaSiNoSeLoEncuentraQueSeLanceLaExceptionAdecuada() {
		
	}
	@Test
	public void dadoQueUnClientePuedeHacerUnaReservaSiNoSeEncuentraLaReservaQueSeLanceLaExceptionAdecuada() {
		
	}
	@Test
	public void dadoQueUnClientePuedeHacerUnaReservaQueNoPuedaHaberElMismoPedidoDosVeces() {
		
	}
	
	@Test
	public void queSePuedaAsignarUnaMesaAUnaReserva() {
		
	}
	
	@Test
	public void queUnaMesaTengaAsignadaVariosClientes() {
		
	}
	
	@Test
	public void queUnMeseroTomeLaReservaDeUnCliente() {
		Empleado mesero = new Mesero(15,"pepe",LocalDate.of(2009, 1, 5));
		restaurante.agregarEmpleado(mesero);
		Cliente cliente = new Cliente(1,"Juan");
		restaurante.agregarCliente(cliente);
		Reserva reserva = new Reserva(1,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		restaurante.realizarPedido(reserva,cliente);
		
		Boolean reservaClienteTomadaPorMesero = restaurante.queUnMeseroTomeLaReservaDeUnCliente(reserva,cliente,mesero);
		
		assertTrue(reservaClienteTomadaPorMesero);
	}
	@Test
	public void dadoQueUnMeseroTomaLaReservaDeUnClienteSiNoSeLoEncuentraQueSeLanceUnaExceptionAdecuada() {
		
	}
	
	@Test
	public void dadoQueUnMeseroTomaLaReservaDeUnClienteQueNoHayaElMismoPedidoDosVeces() {
		
	}
	
	
	
	@Test
	public void queSePuedaAsignarEmpleadosAUnEncargado() {
		Empleado encargado = new Encargado(15,"pepe",LocalDate.of(2009, 1, 5));
		Empleado mesero = new Mesero(15,"pepe",LocalDate.of(2009, 1, 5));
		Empleado mesero2 = new Mesero(12,"lucas",LocalDate.of(2008, 5, 5));
		restaurante.agregarEmpleado(encargado);
		restaurante.agregarEmpleado(mesero);
		restaurante.agregarEmpleado(mesero2);
		
		Boolean empleadoAsignadoAEncargado = restaurante.asignarEmpleadoAEncargado(encargado,mesero);
		restaurante.asignarEmpleadoAEncargado(encargado,mesero2);
		Empleado empleado = restaurante.buscarUnEmpleado(encargado.getCodigo());
		
		assertTrue(empleadoAsignadoAEncargado);
		assertEquals(2,((Encargado) empleado).getEmpleadosACargo().size());
	}
	
	public void dadoQueSePuedeAsignarEmpleadosAUnEncargadoSiNoSeEncuentraElEncargadoQueSeLanceException() {
		
	}
	
	public void dadoQueSePuedeAsignarEmpleadosAUnEncargadoSiUnEmpleadoYaFueAsignadoAEseEncargadoQueLanceException() {
		
	}
	
	
}
