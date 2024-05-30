package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;
import java.time.Period;

public abstract class Empleado {

	private Integer codigo;
	private String nombre;
	private Integer horasTrabajadas;
	private Double valorHora;
	private Double sueldo;
	private LocalDate anioIngreso;

	
	
	public Empleado(Integer codigo, String nombre, LocalDate anioIngreso) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.horasTrabajadas = 0;
		this.valorHora = 0.0;
		this.sueldo = 0.0;
		this.anioIngreso = anioIngreso;
	}
	
	public abstract Double calcularSueldo();

	public Integer calcularAniosDeAntiguedad() {
		LocalDate fechaActual = LocalDate.now();
		Period periodo = Period.between(anioIngreso, fechaActual);
        return periodo.getYears();
	}

	public Integer getHorasTrabajadas() {
		return horasTrabajadas;
	}

	public void setHorasTrabajadas(Integer horasTrabajadas) {
		this.horasTrabajadas = horasTrabajadas;
	}

	public Double getValorHora() {
		return valorHora;
	}

	public void setValorHora(Double valorHora) {
		this.valorHora = valorHora;
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
