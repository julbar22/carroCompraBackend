package com.carrito.backend.services;

import java.sql.Date;
import java.sql.Timestamp;

import com.carrito.backend.dto.CarroCompras;
import com.carrito.backend.dto.RequestValorCompra;
import com.carrito.backend.dto.ResponseValorTotal;
import com.carrito.backend.dto.RespuestaGenerica;

public interface CarritoService {

	CarroCompras crearCarrito(String user, String documento, Date fechaCreacion);

	ResponseValorTotal valorCompras(RequestValorCompra requestCompra);

	RespuestaGenerica guardarCompra(RequestValorCompra requestCompra);
	
	RespuestaGenerica eliminarCompra(Long idCompra);

}
