package com.salesianostriana.dam.clasesproyecto.servicios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.salesianostriana.dam.clasesproyecto.model.LineaDeVenta;
import com.salesianostriana.dam.clasesproyecto.model.Producto;
import com.salesianostriana.dam.clasesproyecto.model.Ticket;
import com.salesianostriana.dam.clasesproyecto.repositories.TicketRepository;
import com.salesianostriana.dam.clasesproyecto.servicios.base.ServicioBaseImpl;

@Service
@Scope (value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TicketServicio extends ServicioBaseImpl<Ticket, Long, TicketRepository>{
	
	
	private  List<LineaDeVenta> ticket= new ArrayList <LineaDeVenta> ();
	
	public  List<LineaDeVenta> getProductosCarrito() {
		// TODO Auto-generated method stub
		return Collections.unmodifiableList(ticket);
	}

	public void addLineaDeVenta (Optional<LineaDeVenta> optional) {
		
		ticket.add(optional.get());
	}

	public void removeProducto(Optional<Producto> p) {
		ticket.remove(p);
		
	}

	
}
