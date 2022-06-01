package com.salesianostriana.dam.clasesproyecto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesianostriana.dam.clasesproyecto.model.LineaDeVenta;
import com.salesianostriana.dam.clasesproyecto.model.Producto;

public interface LineaDeVentaRepository extends JpaRepository<LineaDeVenta, Long>{

	public List<LineaDeVenta> findByTicketId(Long id);
	
	@Query("select count(ln) from LineaDeVenta ln where ln.producto = ?1")
	public int findNumLineasDeVentaByProducto(Producto producto);

}
