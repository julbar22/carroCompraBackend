package com.carrito.backend.services.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrito.backend.dto.CarroCompras;
import com.carrito.backend.dto.RequestValorCompra;
import com.carrito.backend.dto.TipoCarroCompras;
import com.carrito.backend.entities.Compra;
import com.carrito.backend.entities.DiasPromocionales;
import com.carrito.backend.entities.Usuario;
import com.carrito.backend.entities.UsuarioHistoria;
import com.carrito.backend.repositories.ComprasRepository;
import com.carrito.backend.repositories.DiasPromocionalesRepository;
import com.carrito.backend.repositories.UsuarioHistoriaRepository;
import com.carrito.backend.repositories.UsuarioRepository;
import com.carrito.backend.services.CarritoService;

@Service
public class CarritoServiceImpl implements CarritoService {

	@Autowired
	private UsuarioRepository usuarioRepo;

	@Autowired
	private UsuarioHistoriaRepository usuarioHistoryRepo;

	@Autowired
	private ComprasRepository comprasRepository;

	@Autowired
	private DiasPromocionalesRepository diasPromocionalesRepo;

	@Override
	public CarroCompras crearCarrito(String nombre, String documento, Timestamp fechaCreacion) {
		Usuario usuario = usuarioRepo.findTop1ByDocumentoNumeroAndNombre(documento, nombre);
		if (Objects.isNull(usuario)) {
			usuario = createUsuario(nombre, documento);
			return isDiaPromocional(fechaCreacion) ? crearCarrito(usuario, fechaCreacion, TipoCarroCompras.PROMOCIONAL)
					: crearCarrito(usuario, fechaCreacion, TipoCarroCompras.NORMAL);
		} else {
			if (isVIP(fechaCreacion, usuario.getId())) {
				return crearCarrito(usuario, fechaCreacion, TipoCarroCompras.VIP);
			} else {
				return isDiaPromocional(fechaCreacion)
						? crearCarrito(usuario, fechaCreacion, TipoCarroCompras.PROMOCIONAL)
						: crearCarrito(usuario, fechaCreacion, TipoCarroCompras.NORMAL);
			}
		}
	}

	public CarroCompras crearCarrito(Usuario usuario, Timestamp fechaCompra, TipoCarroCompras tipoCarro) {
		CarroCompras carro = new CarroCompras();
		carro.setTipo(tipoCarro);
		Compra compra = saveCarrito(usuario, tipoCarro, fechaCompra);
		carro.setIdCarrito(compra.getId());
		return carro;
	}

	public Compra saveCarrito(Usuario usuario, TipoCarroCompras tipoCarro, Timestamp fechaCompra) {
		Compra compra = new Compra();
		compra.setFechaCompra(fechaCompra);
		compra.setTipoCarro(tipoCarro);
		compra.setUsuario(usuario);
		compra.setValorTotal(0);
		return comprasRepository.save(compra);
	}

	public Usuario createUsuario(String nombre, String documento) {
		Usuario usuario = new Usuario();
		usuario.setDocumentoNumero(documento);
		usuario.setNombre(nombre);
		usuario = usuarioRepo.save(usuario);
		return usuario;
	}

	public boolean isVIP(Timestamp fecha, Long idUsuario) {
		Date date = new Date(fecha.getTime());
		if (isVIPMesPasado(date, idUsuario)) {
			return true;
		} else {
			return comprasSuperioresMesPasado(fecha);
		}
	}

	public boolean isVIPMesPasado(Date fecha, Long idUsuario) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) - 1, 1, 0, 0);
		Timestamp fechaInicioMes = new Timestamp(calendar.getTimeInMillis());
		UsuarioHistoria historiaMes = usuarioHistoryRepo.findTop1ByUsuarioIdAndFechaInicio(idUsuario, fechaInicioMes);
		return Objects.isNull(historiaMes) ? false : historiaMes.getVip();
	}

	public boolean comprasSuperioresMesPasado(Date fecha) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) - 1, 1, 0, 0);
		Timestamp fechaInicioMes = new Timestamp(calendar.getTimeInMillis());
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		Timestamp fechaFinMes = new Timestamp(calendar.getTimeInMillis());
		float totalCompras = comprasRepository.sumValorByFechas(fechaInicioMes, fechaFinMes);
		return totalCompras > 10000 ? true : false;
	}

	public boolean isDiaPromocional(Timestamp fecha) {
		java.sql.Date date = new java.sql.Date(fecha.getTime());
		DiasPromocionales diaPromocion = diasPromocionalesRepo.findOneByFecha(date);
		return Objects.isNull(diaPromocion) ? false : true;
	}

	@Override
	public void valorCompras(RequestValorCompra requestCompra) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void guardarCompra(RequestValorCompra requestCompra) {
		// TODO Auto-generated method stub
		
	}

}
