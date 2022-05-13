package com.salesianostriana.dam.clasesproyecto;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesianostriana.dam.clasesproyecto.model.Categoria;
import com.salesianostriana.dam.clasesproyecto.model.Producto;
import com.salesianostriana.dam.clasesproyecto.servicios.CategoriaServicio;
import com.salesianostriana.dam.clasesproyecto.servicios.ProductoServicio;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Datos {
	
	@Autowired
	private CategoriaServicio categoriaServicio;
	
	@Autowired
	private ProductoServicio productoServicio;
	
	
	
	

	
	@PostConstruct
	public void run() {
		Categoria cat = Categoria.builder().nombre("Ensaladas").build();
		categoriaServicio.save(cat);
		Categoria leche = Categoria.builder().nombre("Leche").build();
		categoriaServicio.save(leche);
		Categoria lechemerengada = Categoria.builder().nombre("Leche").build();
		categoriaServicio.save(lechemerengada);
		Categoria lecheNatada = Categoria.builder().nombre("Leche").build();
		categoriaServicio.save(lecheNatada);
		Producto pr = Producto.builder().nombre("CEsar").precio(41).cantidadDisponible(847).url("https://t2.rg.ltmcdn.com/es/posts/3/2/6/ensalada_de_verduras_variadas_57623_orig.jpg").categoria(cat).build();
		productoServicio.save(pr);
		Producto pr2 = Producto.builder().nombre("Carbonara").precio(41).cantidadDisponible(847).url("https://t2.rg.ltmcdn.com/es/posts/3/2/6/ensalada_de_verduras_variadas_57623_orig.jpg").categoria(cat).build();
		productoServicio.save(pr2);
		Producto pr1 = Producto.builder().nombre("Boloñesa").precio(41).cantidadDisponible(847).
				url("https://t2.rg.ltmcdn.com/es/posts/3/2/6/ensalada_de_verduras_variadas_57623_orig.jpg").categoria(lechemerengada).build();
		productoServicio.save(pr1);
	}
	
	
}
