package com.carrito.backend.services;

import java.sql.Timestamp;

public interface CarritoService {

	String crearCarrito(String user,String documento,Timestamp fechaCreacion);

}
