package com.carrito.backend.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carrito.backend.dto.CarroCompras;
import com.carrito.backend.dto.RequestInitCarro;
import com.carrito.backend.dto.RequestValorCompra;
import com.carrito.backend.dto.ResponseValorTotal;
import com.carrito.backend.dto.RespuestaGenerica;
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
		return carritoService.crearCarrito(requestCarro.getNombreUsuario(), requestCarro.getDocumentoUsuario(),
				requestCarro.getFechaCreacion());
	}

	@CrossOrigin
	@RequestMapping(value = "/consultarTotal", method = RequestMethod.POST)
	public ResponseValorTotal crearCarrito(@RequestBody RequestValorCompra requestValor) {
		return carritoService.valorCompras(requestValor);
	}

	@CrossOrigin
	@RequestMapping(value = "/comprar", method = RequestMethod.POST)
	public RespuestaGenerica comprarCarrito(@RequestBody RequestValorCompra requestValor) {
		return carritoService.guardarCompra(requestValor);
	}

	@CrossOrigin
	@RequestMapping(value = "/eliminarCarrito/{idCarro}", method = RequestMethod.DELETE)
	public RespuestaGenerica eliminarCarritoCarrito(@PathVariable Long idCarro) {
		return carritoService.eliminarCompra(idCarro);
	}

}
