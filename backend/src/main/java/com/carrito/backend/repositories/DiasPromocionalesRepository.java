package com.carrito.backend.repositories;

import java.sql.Date;

import org.springframework.data.repository.CrudRepository;

import com.carrito.backend.entities.DiasPromocionales;

public interface DiasPromocionalesRepository extends CrudRepository<DiasPromocionales, Long> {

	DiasPromocionales findOneByFecha(Date fecha);
}
