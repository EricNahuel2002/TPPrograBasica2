package ar.edu.unlam.pb2.dominio;

import java.util.HashSet;
import java.util.List;

public interface IRestaurante {
	Boolean agregarEmpleado(Empleado empleado) throws EmpleadoDuplicadoException;
	Boolean despedirUnEmpleado(Integer codigo) throws EmpleadoNoEncontradoException;
	Empleado buscarUnEmpleado(Integer codigo) throws EmpleadoNoEncontradoException;
	List<Empleado> obtenerListaDeEmpleadosOrdenadosDescendentePorSueldo();
	List<Empleado> obtenerListaDeEncargadosOrdenadoDeMayorAMenorPorSueldo();
	List<Empleado> obtenerListaDeMeserosOrdenadoDeMayorAMenorPorSueldo();
	List<Empleado> obtenerListaDeCajerosOrdenadoDeMayorAMenorPorSueldo();
	Empleado obtenerElMeseroDelMes() throws NoHayEmpleadoDelMesException;
	HashSet<Cliente> obtenerLaCantidadDeClientesQueFueronAlRestaurante();
	Boolean agregarCliente(Cliente cliente);
	Cliente buscarUnCliente(Integer id) throws ClienteNoEncontradoException;
	Double calcularElSueldoEnBaseALaAntiguedadDeUnEmpleado(Empleado empleado);
	Boolean agregarReserva(Reserva reserva);
}
