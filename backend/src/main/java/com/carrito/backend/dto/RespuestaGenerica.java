package com.carrito.backend.dto;

public class RespuestaGenerica {
	public String codigo;
	public String descripcion;

	public RespuestaGenerica(String codigo,String descripcion) {
		this.codigo =codigo;
		this.descripcion =descripcion;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
