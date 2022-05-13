package com.salesianostriana.dam.clasesproyecto.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.clasesproyecto.model.LineaDeVenta;
import com.salesianostriana.dam.clasesproyecto.model.Producto;
import com.salesianostriana.dam.clasesproyecto.repositories.LineaDeVentaRepository;
import com.salesianostriana.dam.clasesproyecto.servicios.base.ServicioBaseImpl;

@Service
public class LineaDeVentaServicio extends ServicioBaseImpl<LineaDeVenta, Long, LineaDeVentaRepository>{

	public List <LineaDeVenta> findByTicket(Long id){
		return this.repositorio.findByTicketId(id);
	}
}
