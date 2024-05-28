package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Encargado extends Empleado {

	
	private List<Empleado> empleadosACargo;
	private static final Double PORCIENTO_A_SUBIR_ANTES = 0.40;
	private static final Double PORCIENTO_A_SUBIR_DESPUES = 0.35;
	private final Double PRIMA_POR_CANTIDAD_DE_EMPLEADOS_A_CARGO = 100.0;

	public Encargado(Integer codigo, String nombre, LocalDate anioIngreso) {
		super(codigo, nombre, anioIngreso);
		this.empleadosACargo = new ArrayList<>();
	}
	
	
	
	
}
