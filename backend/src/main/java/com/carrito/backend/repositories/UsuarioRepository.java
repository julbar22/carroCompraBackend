package com.carrito.backend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.carrito.backend.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	// TODO: se asume que existe un solo usuario por numero de documento
	Usuario findTop1ByDocumentoNumeroAndNombre(String documentoNumero, String nombre);
	
	
	

}
