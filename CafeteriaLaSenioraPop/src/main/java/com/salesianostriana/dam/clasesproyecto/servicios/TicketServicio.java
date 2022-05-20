package com.salesianostriana.dam.clasesproyecto.servicios;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.salesianostriana.dam.clasesproyecto.model.LineaDeVenta;
import com.salesianostriana.dam.clasesproyecto.model.Mesa;
import com.salesianostriana.dam.clasesproyecto.model.Producto;
import com.salesianostriana.dam.clasesproyecto.model.Ticket;
import com.salesianostriana.dam.clasesproyecto.repositories.ProductoRepository;
import com.salesianostriana.dam.clasesproyecto.repositories.TicketRepository;
import com.salesianostriana.dam.clasesproyecto.servicios.base.ServicioBaseImpl;

@Service

@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TicketServicio extends ServicioBaseImpl<Ticket, Long, TicketRepository> {
	@Autowired
	private LineaDeVentaServicio lineaDeVentaServicio;
	
	@Autowired
	private ProductoRepository productoRepositorio;
	
	private Map<Producto, Integer> products = new HashMap<>();

	public Map<Producto, Integer> getProductsCarrito() {
		return Collections.unmodifiableMap(products);
	}
	
	
	public void addProducto (Producto p) {
		if (products.containsKey(p)) {
			products.replace(p, products.get(p)+1);
		}else {
			products.put(p, 1);
		}
	}

	public void removeProducto(Optional<Producto> optional) {// elimina un producto
		if (products.containsKey(optional.get())) {// comprueba si existeel producto en el map
			if (products.get(optional.get()) > 1)// comprueba si hay más de un productod el mismo tipo
				products.replace(optional.get(), products.get(optional.get()) - 1);// le resta 1
			else if (products.get(optional.get()) == 1) {
				products.remove(optional.get());// si no, lo elimina
			}
		}

	}
	public void cerrarTicket(Mesa mesa) {
		List<LineaDeVenta> listaLineasDeVenta =new ArrayList<LineaDeVenta>();
		Ticket ticket;
		double total=0;
		
		for (Map.Entry<Producto, Integer> lineaDeVenta : products.entrySet()) {//
			
			
			listaLineasDeVenta.add(
					LineaDeVenta.builder()
					.producto(lineaDeVenta.getKey())
					.cantidad(lineaDeVenta.getValue())
					.subtotal(lineaDeVenta.getKey().getPrecio() * lineaDeVenta.getValue())
					.build()
					);
			
			total+=(lineaDeVenta.getKey().getPrecio() * lineaDeVenta.getValue());
		}
		total=this.descuento(total);
		//build del ticket
		ticket = Ticket.builder()
		.fecha(LocalDate.now())
		.hora(LocalTime.now())
		.mesa(mesa.getNumero())
		.total(total)		
		.build();
		
		if(!listaLineasDeVenta.isEmpty()) {
			this.save(ticket);
			for (LineaDeVenta lineaDeVenta : listaLineasDeVenta) {
				lineaDeVenta.addToTicket(ticket);
				lineaDeVentaServicio.save(lineaDeVenta);
				
			}
			productoRepositorio.flush();
			products.clear();
			
		}
		
		
	}
	
	public double descuento(double total) {
		double cantidadLimite=100;
		double descuento=10;
		int divisor=100;
		if(total>=cantidadLimite) {
			return total-(descuento*(total/divisor));
		}
		return total;
	}

}
