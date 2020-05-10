package com.carrito.backend.dto;

import java.util.List;

public class RequestValorCompra {

	private Long idCompra;
	private List<ProductosComprados> productos;

	public Long getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
	}

	public List<ProductosComprados> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductosComprados> productos) {
		this.productos = productos;
	}
}
