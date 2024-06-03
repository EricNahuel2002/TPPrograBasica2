package ar.edu.unlam.pb2.paquetetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.pb2.dominio.Cajero;
import ar.edu.unlam.pb2.dominio.Cliente;
import ar.edu.unlam.pb2.dominio.ClienteNoEncontradoException;
import ar.edu.unlam.pb2.dominio.Empleado;
import ar.edu.unlam.pb2.dominio.EmpleadoDuplicadoException;
import ar.edu.unlam.pb2.dominio.EmpleadoNoEncontradoException;
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
	public void queSePuedaAgregarUnEmpleadoAlRestaurante() throws EmpleadoDuplicadoException, EmpleadoNoEncontradoException {

	    Empleado mesero = new Mesero(003, "Pedro", LocalDate.of(2005, 05, 12));


	    Boolean empleadoAgregado = restaurante.agregarEmpleado(mesero);


	    assertTrue(empleadoAgregado);
	}


	

	@Test
	public void queAlAgregarUnEmpleadoQueYaEstaAgregadoLanceUnaException() throws EmpleadoDuplicadoException, EmpleadoNoEncontradoException {
	    
	    restaurante.agregarEmpleado(new Mesero(003, "Pedro", LocalDate.of(2005, 05, 12)));
	   
	    try {
	        restaurante.agregarEmpleado(new Mesero(003, "Ana", LocalDate.of(1998, 12, 19)));
	      
	    } catch (EmpleadoDuplicadoException e) {
	        
	    }
	}

	
	
	@Test
	public void queSePuedaDespedirAUnEmpleado() throws EmpleadoDuplicadoException, EmpleadoNoEncontradoException {
		
		Empleado mesero = new Mesero(003, "Pedro", LocalDate.of(2005, 05, 12));
		
		restaurante.agregarEmpleado(mesero);
		
		Boolean empleadoDespedido = restaurante.despedirUnEmpleado(mesero.getCodigo());
		
		assertTrue(empleadoDespedido);
	}
	
	@Test(expected = EmpleadoNoEncontradoException.class)
	public void queAlDespedirUnEmpleadoNoExistenteLanceUnaException() throws EmpleadoNoEncontradoException {

	    Integer numeroEmpleadoNoExistente = 999; 
	    
	    restaurante.despedirUnEmpleado(numeroEmpleadoNoExistente);
	    

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
		
		ReservaCliente reservaCliente = new ReservaCliente(reserva, cliente);
		
		Boolean seAgregoReservaCliente = restaurante.agregarReservaCliente(reservaCliente);
		
		assertTrue(seAgregoReservaCliente);
	}
	
	
	@Test
	public void queSePuedaBuscarUnEmpleadoPorSuId() throws EmpleadoDuplicadoException, EmpleadoNoEncontradoException {

	    Empleado empleadoABuscar = new Mesero(003, "Pedro", LocalDate.of(2005, 05, 12));
	    restaurante.agregarEmpleado(empleadoABuscar);


	    Empleado empleadoObtenido = restaurante.buscarUnEmpleado(empleadoABuscar.getCodigo());



	    assertEquals(empleadoABuscar, empleadoObtenido); 
	}
	
	@Test(expected = EmpleadoNoEncontradoException.class)
	public void queAlBuscarUnEmpleadoQueNoExistenteLanceUnaException() throws EmpleadoNoEncontradoException {

	    Integer numeroEmpleadoNoExistente = 999; 
	    
	    restaurante.buscarUnEmpleado(numeroEmpleadoNoExistente);
	    

	}
	

	
	@Test
	public void queSePuedaBuscarUnClientePorSuNumero() throws ClienteNoEncontradoException {
		
		Cliente clienteABuscar = new Cliente(001, "Bautista");
		
		restaurante.agregarCliente(clienteABuscar);
		
		Cliente clienteObtenido = restaurante.buscarUnCliente(clienteABuscar.getNumero());
		
		assertEquals(clienteABuscar, clienteObtenido);
	}
	
	@Test(expected = ClienteNoEncontradoException.class)
	public void queAlBuscarUnClienteNoExistenteLanceUnaException() throws ClienteNoEncontradoException {

	    Integer numeroClienteNoExistente = 999; 
	    
	    restaurante.buscarUnCliente(numeroClienteNoExistente);
	    

	}

	
	
}
