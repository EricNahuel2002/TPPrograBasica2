package ar.edu.unlam.pb2.paquetetest;

import java.util.Comparator;

import ar.edu.unlam.pb2.dominio.Empleado;

public class OrdenDescendiente implements Comparator<Empleado> {

	@Override
	public int compare(Empleado o1, Empleado o2) {
		return o2.getCodigo().compareTo(o1.getCodigo());
	}

}
