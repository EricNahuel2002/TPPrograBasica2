package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public abstract class Empleado implements Comparable<Empleado> {

	private Integer codigo;
	private String nombre;
	private Integer horasTrabajadas;
	private Double valorHora;
	private Double sueldo;
	private LocalDate anioIngreso;
	private Double valorPorAnioAntiguedad;

	
	
	public Empleado(Integer codigo, String nombre, LocalDate anioIngreso) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.horasTrabajadas = 0;
		this.valorHora = 0.0;
		this.sueldo = 0.0;
		this.anioIngreso = anioIngreso;
		this.valorPorAnioAntiguedad = 0.0;
	}
	
	public abstract Double calcularSueldo();
//>>>>>>> e2b761878c31bf6f1521a3a617659efa0ea693e3
	
	@Override
	public int compareTo(Empleado o) {
		return this.getCodigo().compareTo(o.codigo);
	}
	
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
	
	

	public Double getValorPorAnioAntiguedad() {
		return valorPorAnioAntiguedad;
	}

	public void setValorPorAnioAntiguedad(Double valorPorAnioAntiguedad) {
		this.valorPorAnioAntiguedad = valorPorAnioAntiguedad;
	}

	@Override
	public int hashCode() {
		return Objects.hash(anioIngreso, codigo, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return Objects.equals(anioIngreso, other.anioIngreso) && Objects.equals(codigo, other.codigo)
				&& Objects.equals(nombre, other.nombre);
	}
	
	

}
