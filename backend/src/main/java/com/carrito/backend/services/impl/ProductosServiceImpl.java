package com.carrito.backend.services.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrito.backend.dto.CarroCompras;
import com.carrito.backend.dto.TipoCarroCompras;
import com.carrito.backend.entities.DiasPromocionales;
import com.carrito.backend.entities.Producto;
import com.carrito.backend.entities.Usuario;
import com.carrito.backend.entities.UsuarioHistoria;
import com.carrito.backend.repositories.ComprasRepository;
import com.carrito.backend.repositories.DiasPromocionalesRepository;
import com.carrito.backend.repositories.ProductosRepository;
import com.carrito.backend.repositories.UsuarioHistoriaRepository;
import com.carrito.backend.repositories.UsuarioRepository;
import com.carrito.backend.services.CarritoService;
import com.carrito.backend.services.ProductosService;

@Service
public class ProductosServiceImpl implements ProductosService {

	@Autowired
	private ProductosRepository productosRepo;

	@Override
	public List<Producto> listaProductos() {
		List<Producto> productos = new ArrayList<>();
		productosRepo.findAll().forEach(producto -> productos.add(producto));
		return productos;
	}

}
