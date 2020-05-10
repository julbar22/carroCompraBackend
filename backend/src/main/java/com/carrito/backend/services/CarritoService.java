package com.carrito.backend.services;

import java.sql.Timestamp;

import com.carrito.backend.dto.CarroCompras;
import com.carrito.backend.dto.RequestValorCompra;

public interface CarritoService {

	CarroCompras crearCarrito(String user, String documento, Timestamp fechaCreacion);

	void valorCompras(RequestValorCompra requestCompra);

	void guardarCompra(RequestValorCompra requestCompra);

}
