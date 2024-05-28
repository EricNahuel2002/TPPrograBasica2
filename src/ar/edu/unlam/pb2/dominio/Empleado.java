package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;

public class Empleado {
	
	
	private Integer codigo;
	private String nombre;
	private Double sueldo;
	private LocalDate anioIngreso;

	
	
	public Empleado(Integer codigo, String nombre, LocalDate anioIngreso) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.sueldo = 0.0;
		this.anioIngreso = anioIngreso;
	}
	
	
}
