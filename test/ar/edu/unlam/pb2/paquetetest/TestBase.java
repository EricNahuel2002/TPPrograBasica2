package ar.edu.unlam.pb2.paquetetest;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

import ar.edu.unlam.pb2.dominio.Cajero;
import ar.edu.unlam.pb2.dominio.Empleado;
import ar.edu.unlam.pb2.dominio.Encargado;
import ar.edu.unlam.pb2.dominio.Mesero;



public class TestBase {

	
	@Test
	public void queUnEncargadoSeaUnaInstanciaDeLaClaseEncargado() {
		
	    Empleado encargado = new Encargado(15, "pepe", LocalDate.of(2009, 1, 5));

	    assertTrue(encargado instanceof Encargado);
	}
	
	
	@Test
	public void queUnEncargadoSeaUnaSubClaseDeEmpleado() {
		
	    Empleado encargado = new Encargado(15, "pepe", LocalDate.of(2009, 1, 5));

	    assertTrue(encargado instanceof Empleado);
	}
	
	
	
	@Test
	public void queUnCajeroSeaUnaInstanciaDeLaClaseCajero() {
		
		Empleado cajero = new Cajero(13,"Enrique",LocalDate.of(2005, 1, 1));

	    assertTrue(cajero instanceof Cajero);
	}
	
	
	@Test
	public void queUnCajeroSeaUnaSubClaseDeEmpleado() {
		
		Empleado cajero = new Cajero(13,"Enrique",LocalDate.of(2005, 1, 1));

	    assertTrue(cajero instanceof Empleado);
	}
	
	@Test
	public void queUnMeseroSeaUnaInstanciaDeLaClaseMesero() {
		
		Empleado mesero = new Mesero(12,"Juan",LocalDate.of(2015, 5, 6));

	    assertTrue(mesero instanceof Mesero);
	}
	
	
	@Test
	public void queUnMeseroSeaUnaSubClaseDeEmpleado() {
		Empleado mesero = new Mesero(12,"Juan",LocalDate.of(2015, 5, 6));

	    assertTrue(mesero instanceof Empleado);
	}
	
	
	
	
	
	
	
	
	
}
