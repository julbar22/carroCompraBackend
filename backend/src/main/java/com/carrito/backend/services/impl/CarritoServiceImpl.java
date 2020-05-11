package com.carrito.backend.services.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.annotation.DeterminableImports;
import org.springframework.stereotype.Service;

import com.carrito.backend.dto.CarroCompras;
import com.carrito.backend.dto.ProductosComprados;
import com.carrito.backend.dto.RequestValorCompra;
import com.carrito.backend.dto.ResponseValorTotal;
import com.carrito.backend.dto.RespuestaGenerica;
import com.carrito.backend.dto.TipoCarroCompras;
import com.carrito.backend.entities.Compra;
import com.carrito.backend.entities.DetalleCompra;
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

	@Autowired
	private ProductosRepository productoRepository;

	@Override
	public CarroCompras crearCarrito(String nombre, String documento, java.sql.Date fechaCreacion) {
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

	public CarroCompras crearCarrito(Usuario usuario, java.sql.Date fechaCompra, TipoCarroCompras tipoCarro) {
		CarroCompras carro = new CarroCompras();
		carro.setTipo(tipoCarro);
		Compra compra = saveCarrito(usuario, tipoCarro, fechaCompra);
		carro.setIdCarrito(compra.getId());
		return carro;
	}

	public Compra saveCarrito(Usuario usuario, TipoCarroCompras tipoCarro, java.sql.Date fechaCompra) {
		Compra compra = new Compra();
		compra.setFechaCompra(new Timestamp(fechaCompra.getTime()));
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

	public boolean isVIP(java.sql.Date fecha, Long idUsuario) {
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
		Float totalCompras = comprasRepository.sumValorByFechas(fechaInicioMes, fechaFinMes);
		return totalCompras != null && totalCompras > 10000 ? true : false;
	}

	/**
	 * 
	 * @param date
	 * la fecha viene con formato yyyy/mm/dd
	 */
	public boolean isDiaPromocional(java.sql.Date date) {
		//TODO arreglar zona horaria
		date.setTime(date.getTime() + (3 * 60 * 60 * 1000));
		DiasPromocionales diaPromocion = diasPromocionalesRepo.findOneByFecha(date);
		return Objects.isNull(diaPromocion) ? false : true;
	}

	@Override
	public RespuestaGenerica guardarCompra(RequestValorCompra requestCompra) {

		Optional<Compra> carritoOptional = comprasRepository.findById(requestCompra.getIdCompra());
		if (carritoOptional.isPresent()) {
			Compra carritoCompra = carritoOptional.get();
			int cantidadProductos = CantidadDeProductos(requestCompra.getProductos());
			float totalCompra = CalcularTotal(requestCompra.getProductos());
			float descuento = CalcularDescuentos(cantidadProductos, totalCompra, requestCompra.getProductos(),
					carritoCompra.getTipoCarro());
			float totalConDescuento = totalCompra - descuento > 0 ? totalCompra - descuento : 0;
			carritoCompra.setValorTotal(totalConDescuento);
			List<DetalleCompra> detalleProductos = new ArrayList<>();
			for (ProductosComprados producto : requestCompra.getProductos()) {
				Optional<Producto> productoOptional = productoRepository.findById(producto.getId());
				if (productoOptional.isPresent()) {
					DetalleCompra detalleCompra = new DetalleCompra();
					detalleCompra.setProducto(productoOptional.get());
					detalleCompra.setCantidad(producto.getCantidad());
					detalleCompra.setCompra(carritoCompra);
					detalleProductos.add(detalleCompra);
				}
			}
			carritoCompra.setDetalleCompra(detalleProductos);
			comprasRepository.save(carritoCompra);
			return new RespuestaGenerica("200", "Compra realizada");

		}

		return new RespuestaGenerica("500", "Error en la compra");

	}

	public Float CalcularDescuentos(int productosComprados, float totalCompra, List<ProductosComprados> productos,
			TipoCarroCompras tipoCarro) {

		if (productosComprados == 5) {
			return (float) (totalCompra * 0.2);
		} else if (productosComprados > 10) {
			if (tipoCarro.equals(TipoCarroCompras.VIP)) {
				return ((float) 700 + productoMasBarato(productos));
			} else if (tipoCarro.equals(TipoCarroCompras.PROMOCIONAL)) {
				return (float) 500;
			} else {
				return (float) 200;
			}
		} else {
			return (float) 0;
		}
	}

	public Float productoMasBarato(List<ProductosComprados> productos) {
		List<Float> valorProductos = new ArrayList<>();
		for (ProductosComprados producto : productos) {
			Optional<Producto> productoOptional = productoRepository.findById(producto.getId());
			if (productoOptional.isPresent()) {
				valorProductos.add(productoOptional.get().getValor());
			}
		}
		return valorProductos.stream().min(Float::compare).get();
	}

	public int CantidadDeProductos(List<ProductosComprados> productos) {
		int productosComprados = 0;
		for (ProductosComprados producto : productos) {
			productosComprados += producto.getCantidad();
		}

		return productosComprados;
	}

	public float CalcularTotal(List<ProductosComprados> productos) {
		float totalCompra = 0;
		for (ProductosComprados producto : productos) {
			Optional<Producto> productoOptional = productoRepository.findById(producto.getId());
			if (productoOptional.isPresent()) {
				totalCompra += (productoOptional.get().getValor() * producto.getCantidad());
			}
		}
		return totalCompra;
	}

	@Override
	public ResponseValorTotal valorCompras(RequestValorCompra requestCompra) {
		ResponseValorTotal valorTotal = new ResponseValorTotal();
		Optional<Compra> carritoOptional = comprasRepository.findById(requestCompra.getIdCompra());
		if (carritoOptional.isPresent()) {
			int cantidadProductos = CantidadDeProductos(requestCompra.getProductos());
			float totalCompra = CalcularTotal(requestCompra.getProductos());
			float descuento = CalcularDescuentos(cantidadProductos, totalCompra, requestCompra.getProductos(),
					carritoOptional.get().getTipoCarro());

			valorTotal.setDescuentos(descuento);
			float totalConDescuento = totalCompra - descuento > 0 ? totalCompra - descuento : 0;
			valorTotal.setValorTotal(totalConDescuento);
			valorTotal.setValorSinDescuentos(totalCompra);
		}

		return valorTotal;
	}

	@Override
	public RespuestaGenerica eliminarCompra(Long idCompra) {
		Optional<Compra> carritoOptional = comprasRepository.findById(idCompra);
		if (carritoOptional.isPresent()) {
			comprasRepository.delete(carritoOptional.get());
			return new RespuestaGenerica("200", "Compra eliminada");
		} else {
			return new RespuestaGenerica("500", "Error eliminando");
		}

	}

}
