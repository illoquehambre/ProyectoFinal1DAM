package com.salesianostriana.dam.clasesproyecto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.clasesproyecto.model.LineaDeVenta;

public interface LineaDeVentaRepository extends JpaRepository<LineaDeVenta, Long>{

	public List<LineaDeVenta> findByTicketId(Long id);
	

}
