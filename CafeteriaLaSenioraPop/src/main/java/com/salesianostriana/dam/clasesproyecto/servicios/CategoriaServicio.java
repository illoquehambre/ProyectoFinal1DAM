package com.salesianostriana.dam.clasesproyecto.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.clasesproyecto.model.Categoria;
import com.salesianostriana.dam.clasesproyecto.model.Producto;
import com.salesianostriana.dam.clasesproyecto.repositories.CategoriaRepository;
import com.salesianostriana.dam.clasesproyecto.servicios.base.ServicioBaseImpl;

@Service
public class CategoriaServicio extends ServicioBaseImpl<Categoria, Long, CategoriaRepository>{

	public List<Categoria> buscarPorNombre(String nombre) {
		
		return this.repositorio.findByNombreContainsIgnoreCase(nombre);
	}


	
}
