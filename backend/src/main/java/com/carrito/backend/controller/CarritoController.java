package com.carrito.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.carrito.backend.dto.CarroCompras;
import com.carrito.backend.dto.RequestInitCarro;
import com.carrito.backend.services.CarritoService;

@RestController
@RequestMapping("/carroCompras")
@EnableAutoConfiguration
public class CarritoController {

	@Autowired
	private CarritoService carritoService;

	@CrossOrigin
	@RequestMapping(value = "/crear", method = RequestMethod.POST)
	public CarroCompras crearCarrito(@RequestBody RequestInitCarro requestCarro) {

		System.out.println("esta llegando");
		return carritoService.crearCarrito(requestCarro.getNombreUsuario(), requestCarro.getDocumentoUsuario(),
				requestCarro.getFechaCreacion());
	}

}
