package com.carrito.backend.dto;

public class ResponseValorTotal {

	private float valorSinDescuentos;
	private float descuentos;
	private float valorTotal;

	public ResponseValorTotal() {
		this.descuentos = 0;
		this.valorSinDescuentos = 0;
		this.valorTotal = 0;
	}

	public float getValorSinDescuentos() {
		return valorSinDescuentos;
	}

	public void setValorSinDescuentos(float valorSinDescuentos) {
		this.valorSinDescuentos = valorSinDescuentos;
	}

	public float getDescuentos() {
		return descuentos;
	}

	public void setDescuentos(float descuentos) {
		this.descuentos = descuentos;
	}

	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

}
