package com.salesianostriana.dam.clasesproyecto.servicios;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.salesianostriana.dam.clasesproyecto.model.Producto;
import com.salesianostriana.dam.clasesproyecto.model.Ticket;
import com.salesianostriana.dam.clasesproyecto.repositories.TicketRepository;
import com.salesianostriana.dam.clasesproyecto.servicios.base.ServicioBaseImpl;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TicketServicio extends ServicioBaseImpl<Ticket, Long, TicketRepository> {

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

}
