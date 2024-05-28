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
	
	
	
	public Integer getCodigo() {
		return codigo;
	}



	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public Double getSueldo() {
		return sueldo;
	}



	public void setSueldo(Double sueldo) {
		this.sueldo = sueldo;
	}



	public LocalDate getAnioIngreso() {
		return anioIngreso;
	}



	public void setAnioIngreso(LocalDate anioIngreso) {
		this.anioIngreso = anioIngreso;
	}

}
