package com.carrito.backend.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.carrito.backend.dto.TipoCarroCompras;

@Entity
@Table(name = "compras")
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Timestamp fechaCompra;

	private float valorTotal;
	
	private TipoCarroCompras tipoCarro;

	@JoinColumn(name = "usuario_id")
	@ManyToOne
	private Usuario usuario;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "compra")
	private List<DetalleCompra> detalleCompra;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Timestamp fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<DetalleCompra> getDetalleCompra() {
		return detalleCompra;
	}

	public void setDetalleCompra(List<DetalleCompra> detalleCompra) {
		this.detalleCompra = detalleCompra;
	}

	public TipoCarroCompras getTipoCarro() {
		return tipoCarro;
	}

	public void setTipoCarro(TipoCarroCompras tipoCarro) {
		this.tipoCarro = tipoCarro;
	}

}
