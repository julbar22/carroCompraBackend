package com.carrito.backend.dto;

public class ProductosComprados {

	private Long id;

	private int cantidad;

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long idProducto) {
		this.id = idProducto;
	}

}
