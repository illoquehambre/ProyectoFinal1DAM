package com.salesianostriana.dam.clasesproyecto.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.clasesproyecto.model.Categoria;
import com.salesianostriana.dam.clasesproyecto.model.Producto;
import com.salesianostriana.dam.clasesproyecto.repositories.ProductoRepository;
import com.salesianostriana.dam.clasesproyecto.servicios.base.ServicioBaseImpl;

@Service
public class ProductoServicio extends ServicioBaseImpl<Producto, Long, ProductoRepository>{
	
	public List <Producto> findByCategoria(Long id){
		return this.repositorio.findByCategoriaId(id);
	}
	
	public List<Producto> buscarPorNombre(String cadena) {
		return repositorio.findByNombreContainsIgnoreCase(cadena);
	}

	public int numeroProductosCategoria(Categoria categoria) {
		return repositorio.findNumProductosByCategoria(categoria);
	}

	



	
}
