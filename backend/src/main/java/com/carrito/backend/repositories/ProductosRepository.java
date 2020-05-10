package com.carrito.backend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.carrito.backend.entities.Producto;

public interface ProductosRepository extends CrudRepository<Producto, Long> {

}
