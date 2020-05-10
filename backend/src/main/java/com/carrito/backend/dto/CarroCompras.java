package com.carrito.backend.dto;

public class CarroCompras {

	private TipoCarroCompras tipo;
	private String descripcion;
	private Long idCarrito;

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

	public Long getIdCarrito() {
		return idCarrito;
	}

	public void setIdCarrito(Long idCarrito) {
		this.idCarrito = idCarrito;
	}

}
