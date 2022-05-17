package com.salesianostriana.dam.clasesproyecto.controller;

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

}
