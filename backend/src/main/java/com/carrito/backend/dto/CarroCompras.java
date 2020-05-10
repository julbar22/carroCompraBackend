package com.carrito.backend.dto;

public class CarroCompras {

	private TipoCarroCompras tipo;
	private String descripcion;

	public TipoCarroCompras getTipo() {
		return tipo;
	}

	public void setTipo(TipoCarroCompras tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
