package ar.edu.unlam.pb2.paquetetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.pb2.dominio.Cajero;
import ar.edu.unlam.pb2.dominio.Cliente;
import ar.edu.unlam.pb2.dominio.Empleado;
import ar.edu.unlam.pb2.dominio.Encargado;
import ar.edu.unlam.pb2.dominio.Mesero;
import ar.edu.unlam.pb2.dominio.Reserva;
import ar.edu.unlam.pb2.dominio.ReservaCliente;
import ar.edu.unlam.pb2.dominio.Restaurante;



public class TestBase {
	
	private static final String NOMBRE_RESTAURANTE = "Luigi";
	private Restaurante restaurante;

	@Before
	public void init() {
		this.restaurante = new Restaurante(NOMBRE_RESTAURANTE);
	}
	
	@Test
	public void queUnEncargadoSeaUnaInstanciaDeLaClaseEncargado() {
		
	    Empleado encargado = new Encargado(001, "Santi", LocalDate.of(2003, 10, 10));

	    assertTrue(encargado instanceof Encargado);
	}
	
	
	@Test
	public void queUnEncargadoSeaUnaSubClaseDeEmpleado() {
		
	    Empleado encargado = new Encargado(001, "Santi", LocalDate.of(2003, 10, 10));

	    assertTrue(encargado instanceof Empleado);
	}
	
	
	
	@Test
	public void queUnCajeroSeaUnaInstanciaDeLaClaseCajero() {
		
		Empleado cajero = new Cajero(002, "Ana", LocalDate.of(1998, 12, 19));

	    assertTrue(cajero instanceof Cajero);
	}
	
	
	@Test
	public void queUnCajeroSeaUnaSubClaseDeEmpleado() {
		
		Empleado cajero = new Cajero(002, "Ana", LocalDate.of(1998, 12, 19));

	    assertTrue(cajero instanceof Empleado);
	}
	
	@Test
	public void queUnMeseroSeaUnaInstanciaDeLaClaseMesero() {
		
		Empleado mesero = new Mesero(003, "Pedro", LocalDate.of(2005, 05, 12));

	    assertTrue(mesero instanceof Mesero);
	}
	
	
	@Test
	public void queUnMeseroSeaUnaSubClaseDeEmpleado() {
		Empleado mesero = new Mesero(003, "Pedro", LocalDate.of(2005, 05, 12));

	    assertTrue(mesero instanceof Empleado);
	}
	
	
	
	@Test
	public void queSePuedaAgregarUnEmpleadoAlRestaurante() {
		
		Empleado mesero = new Mesero(003, "Pedro", LocalDate.of(2005, 05, 12));
		
		Boolean empleadoAgregado = restaurante.agregarEmpleado(mesero);
		
		assertTrue(empleadoAgregado);
	
	}
	
	@Test
	public void queNoSePuedaAgregarUnEmpleadoConElMismoCodigo() {

		Boolean seAgregoEmpleado = restaurante.agregarEmpleado(new Mesero(003, "Pedro", LocalDate.of(2005, 05, 12)));
		Boolean seAgregoEmpleado2 = restaurante.agregarEmpleado(new Mesero(003, "Ana", LocalDate.of(1998, 12, 19)));
		
		assertTrue(seAgregoEmpleado);
		assertFalse(seAgregoEmpleado2);
	}
	
	@Test
	public void queSePuedaDespedirAUnEmpleado() {
		
		Empleado mesero = new Mesero(003, "Pedro", LocalDate.of(2005, 05, 12));
		
		restaurante.agregarEmpleado(mesero);
		
		Boolean empleadoDespedido = restaurante.despedirUnEmpleado(mesero.getCodigo());
		
		assertTrue(empleadoDespedido);
	}
	
	@Test
	public void queSePuedaAgregarUnClienteAlRestaurante() {
		
		Cliente cliente = new Cliente(001 ,"Bautista");
		
		Boolean seAgregoAlCliente = restaurante.agregarCliente(cliente);
		
		assertTrue(seAgregoAlCliente);
	}
	
	@Test
	public void queSePuedaAgregarUnaReservaAlRestaurante() {

		Reserva reserva = new Reserva(001, LocalDate.of(2024, 5, 28), LocalTime.of(21, 0));
		
		Boolean seAgregoReserva = restaurante.agregarReserva(reserva);
		
		assertTrue(seAgregoReserva);
	}
	
	@Test
	public void queSePuedaAgregarUnaReservaClienteAlRestaurante() { 
		
		Reserva reserva = new Reserva(001, LocalDate.of(2024, 5, 28), LocalTime.of(21, 0));
		
		Cliente cliente = new Cliente(001, "Bautista");
		
		Empleado mesero = new Mesero(003, "Pedro", LocalDate.of(2005, 05, 12));
		
		restaurante.agregarEmpleado(mesero);
		
		ReservaCliente reservaCliente = new ReservaCliente(reserva, cliente, mesero);
		
		Boolean seAgregoReservaCliente = restaurante.agregarReservaCliente(reservaCliente);
		
		assertTrue(seAgregoReservaCliente);
	}
	
	
	@Test
	public void queSePuedaBuscarUnEmpleadoPorSuId() {
		
		Empleado empleadoABuscar = new Mesero(003, "Pedro", LocalDate.of(2005, 05, 12));
		
		restaurante.agregarEmpleado(empleadoABuscar);
		
		Empleado empleadoObtenido = restaurante.buscarUnEmpleado(empleadoABuscar.getCodigo());
		
		assertEquals(empleadoABuscar, empleadoObtenido);
		
	}
	
	@Test
	public void queSePuedaBuscarUnClientePorSuNumero() {
		
		Cliente clienteABuscar = new Cliente(001, "Bautista");
		
		restaurante.agregarCliente(clienteABuscar);
		
		Cliente clienteObtenido = restaurante.buscarUnCliente(clienteABuscar.getNumero());
		
		assertEquals(clienteABuscar, clienteObtenido);
	}
	
	@Test
	public void queSePuedaCalcularElSueldoDeUnEncargado() {

		Empleado encargado = new Encargado(123, "Julieta", LocalDate.of(2000,5,19));
		restaurante.agregarEmpleado(encargado);

		Empleado meseroA = new Mesero(189, "Flor", LocalDate.of(2007,7,30));
		restaurante.agregarEmpleado(meseroA);
		Empleado meseroB = new Mesero(7457, "Juana", LocalDate.of(2015,7,30));
		restaurante.agregarEmpleado(meseroB);
		Empleado meseroC = new Mesero(59, "Flor", LocalDate.of(2008,7,30));
		restaurante.agregarEmpleado(meseroC);

		restaurante.cargarMeseroACargoDelEncargado(encargado, meseroA);
		restaurante.cargarMeseroACargoDelEncargado(encargado, meseroB);
		restaurante.cargarMeseroACargoDelEncargado(encargado, meseroC);
		restaurante.cargarValorHoraDeUnEmpleado(123, 100.0);
		restaurante.cargarHorasTrabajadasDeUnEmpleado(123, 20); 
		restaurante.cargarSueldoEmpleado(123);

		Double sueldoObtenido = restaurante.obtenerSueldoDeUnEmpleado(123);
		Double sueldoDeseado = 20 * 100.0 + encargado.calcularAniosDeAntiguedad()*5000 + 3*500;

		assertEquals(sueldoDeseado, sueldoObtenido);

	}
	
	@Test
	public void queSePuedaCalcularElSueldoDeUnMesero() {
		Empleado mesero = new Mesero(112, "Juanito", LocalDate.of(2020,6,2));
		restaurante.agregarEmpleado(mesero);
		restaurante.cargarValorHoraDeUnEmpleado(112, 100.0);
		restaurante.cargarHorasTrabajadasDeUnEmpleado(112, 20);

		Cliente cliente = new Cliente(584, "nombreCliente");
		restaurante.agregarCliente(cliente);

		Reserva reserva = new Reserva(8546,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);

		ReservaCliente reservaCliente = new ReservaCliente(reserva, cliente, mesero);
		restaurante.agregarReservaCliente(reservaCliente);

		Reserva reserva2 = new Reserva(854,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0)); 
		restaurante.agregarReserva(reserva2);

		ReservaCliente reservaCliente2 = new ReservaCliente(reserva2, cliente, mesero);
		restaurante.agregarReservaCliente(reservaCliente2);

		restaurante.cargarSueldoEmpleado(112);

		Double sueldoObtenido = restaurante.obtenerSueldoDeUnEmpleado(112);
		Double sueldoDeseado = 20 * 100.0 + mesero.calcularAniosDeAntiguedad()*1000 + 100;

		assertEquals(sueldoDeseado, sueldoObtenido);
	}
	
}
