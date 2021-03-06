package com.salesianostriana.dam.clasesproyecto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.clasesproyecto.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	List<Categoria> findByNombreContainsIgnoreCase(String nombre);

	
	
}
