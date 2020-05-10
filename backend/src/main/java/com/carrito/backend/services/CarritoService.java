package com.carrito.backend.services;

import java.sql.Timestamp;

import com.carrito.backend.dto.CarroCompras;

public interface CarritoService {

	CarroCompras crearCarrito(String user,String documento,Timestamp fechaCreacion);

}
