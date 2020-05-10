package com.carrito.backend.dto;

import java.sql.Timestamp;

public class RequestInitCarro {

	private String nombreUsuario;
	private String documentoUsuario;
	private Timestamp fechaCreacion;

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getDocumentoUsuario() {
		return documentoUsuario;
	}

	public void setDocumentoUsuario(String documentoUsuario) {
		this.documentoUsuario = documentoUsuario;
	}

	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

}
