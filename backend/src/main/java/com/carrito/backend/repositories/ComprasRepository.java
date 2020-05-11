package com.carrito.backend.repositories;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.carrito.backend.entities.Compra;

public interface ComprasRepository extends CrudRepository<Compra, Long> {

	@Query("SELECT SUM(c.valorTotal) from Compra c Where c.fechaCompra >= ?1 and c.fechaCompra <= ?2")
	Float sumValorByFechas(Timestamp fechaInicio, Timestamp fechaFin);

}
