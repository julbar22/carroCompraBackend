package com.carrito.backend.repositories;

import java.sql.Timestamp;

import org.springframework.data.repository.CrudRepository;

import com.carrito.backend.entities.UsuarioHistoria;

public interface UsuarioHistoriaRepository extends CrudRepository<UsuarioHistoria, Long> {
	
	UsuarioHistoria findTop1ByUsuarioIdAndFechaInicio(Long usuarioId, Timestamp fechInicio);
	

}
