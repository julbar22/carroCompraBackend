package com.carrito.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.carrito.backend.entities.Producto;
import com.carrito.backend.services.ProductosService;

@RestController
@RequestMapping("/carroCompras")
@EnableAutoConfiguration
public class ProductosController {

	@Autowired
	private ProductosService productoService;

	@CrossOrigin
	@RequestMapping(value = "/productos", method = RequestMethod.GET)
	public List<Producto> crearCarrito() {
		return productoService.listaProductos();
	}

}
