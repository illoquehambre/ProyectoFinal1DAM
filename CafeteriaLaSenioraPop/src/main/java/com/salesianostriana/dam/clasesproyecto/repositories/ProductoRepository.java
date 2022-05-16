package com.salesianostriana.dam.clasesproyecto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.clasesproyecto.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{
	
	public List <Producto> findByCategoriaId(Long id);

	public List<Producto> findByNombreContainsIgnoreCase(String nombre);
}
