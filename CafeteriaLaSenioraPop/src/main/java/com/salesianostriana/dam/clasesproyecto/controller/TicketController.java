package com.salesianostriana.dam.clasesproyecto.controller;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.salesianostriana.dam.clasesproyecto.model.Categoria;
import com.salesianostriana.dam.clasesproyecto.model.LineaDeVenta;
import com.salesianostriana.dam.clasesproyecto.model.Producto;
import com.salesianostriana.dam.clasesproyecto.servicios.LineaDeVentaServicio;
import com.salesianostriana.dam.clasesproyecto.servicios.ProductoServicio;
import com.salesianostriana.dam.clasesproyecto.servicios.TicketServicio;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor

@Controller
public class TicketController {

	@Autowired
	private TicketServicio ticketServicio;

	@Autowired
	private ProductoServicio productoServicio;
	
	@Autowired
	private LineaDeVentaServicio lineaDeVentaServicio;

	

	@GetMapping("/carrito")
	public String showCarrito(Model model) {
		
		List <LineaDeVenta> lineaDeVenta = new ArrayList<LineaDeVenta>();
		for (LineaDeVenta ticket : lineaDeVenta) {
			lineaDeVenta.add(ticket);
		}
		model.addAttribute("lineaDeVenta", lineaDeVenta);
		
		return "ticket";
	}

	@GetMapping("/productoACarrito/{id}")
	public String productoACarrito(@PathVariable("id") Long id, Model model) {

		ticketServicio.addLineaDeVenta(lineaDeVentaServicio.findById(id));

		return "redirect:/carrito";
	}

	@GetMapping("/borrarProducto/{id}")
	public String removeProductFromCart(@PathVariable("id") Long id) {

		TicketServicio.removeProducto(productoServicio.findById(id));
		return "redirect:/carrito";
	}

	@ModelAttribute("total_carrito")
	public Double totalCarrito() {
		double total = 0.0;
		List <LineaDeVenta> carrito = TicketServicio.getProductosCarrito();
		
		if (carrito != null) {
			for (Producto p : carrito.keySet()) {
				total += p.getPrecio() * carrito.get(p);
			}
			return total;
		}

		return 0.0;
	}

=======
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.salesianostriana.dam.clasesproyecto.model.Categoria;
import com.salesianostriana.dam.clasesproyecto.model.Producto;
import com.salesianostriana.dam.clasesproyecto.servicios.ProductoServicio;
import com.salesianostriana.dam.clasesproyecto.servicios.TicketServicio;

public class TicketController {

	@Autowired
	TicketServicio ticketServicio;
	@Autowired
	ProductoServicio productoServicio;

	@GetMapping("private/mostrarTicket") // Se encarga de mostrar todo lo que esté añadido al carrito, en mi caso será
											// igual
	public String showCarrito(Model model) {

		model.addAttribute("products", ticketServicio.getProductsCarrito());

		return "/private/Ticket";
	}

	@GetMapping("private/productoACarrito/{id}") // añade un producto al carrito
	public String productoACarrito(@PathVariable("id") long id, Model model) {		

		Optional<Producto> comprobar = productoServicio.findById(id);

		if (comprobar!=null) {
			ticketServicio.addProducto(comprobar.get());// se hará igual
			return "redirect:/private/mostrarTicket";
		} else {
			// No existe ningún categoria con el Id proporcionado.
			// Redirigimos hacia el listado.
			return "redirect:/";
		}
	}

	@GetMapping("/borrarProducto/{id}")
	public String removeProductFromCart(@PathVariable("id") Long id) {

		ticketServicio.removeProducto(productoServicio.findById(id));
		return "redirect:/carrito";
	}

	/*
	 * @ModelAttribute("total_carrito") public Double totalCarrito () {
	 * 
	 * Map <Producto,Integer> carrito=ticketServicio.getProductsCarrito(); double
	 * total=0.0; if (carrito !=null) { for (Producto p: carrito.keySet()) {
	 * total+=p.getPvp()*carrito.get(p); } return total; }
	 * 
	 * return 0.0; }
	 */
>>>>>>> 792f3da16602f6dae92d622851f024529e428142
}
