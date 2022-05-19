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
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.clasesproyecto.model.Categoria;
import com.salesianostriana.dam.clasesproyecto.model.Producto;
import com.salesianostriana.dam.clasesproyecto.servicios.CategoriaServicio;
import com.salesianostriana.dam.clasesproyecto.servicios.ProductoServicio;
import com.salesianostriana.dam.clasesproyecto.servicios.TicketServicio;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class TicketController {

	@Autowired
	private TicketServicio ticketServicio;
	@Autowired
	private ProductoServicio productoServicio;
	
	@Autowired
	private CategoriaServicio categoriaServicio;

	@GetMapping("private/mostrarTicket") // Se encarga de mostrar todo lo que esté añadido al carrito, en mi caso será
											// igual
	public String showCarrito(Model model) {
		
		List<Categoria> categorias = new ArrayList<Categoria>();

		for (Categoria cat : categoriaServicio.findAll()) {

			categorias.add(cat);
		}

		model.addAttribute("categorias", categorias);
		

		model.addAttribute("products", ticketServicio.getProductsCarrito());

		return "/private/Ticket";
	}

	@GetMapping("private/productoACarrito/{id}") // añade un producto al carrito
	public String productoACarrito(@PathVariable("id") long id, Model model) {

		Optional<Producto> comprobar = productoServicio.findById(id);

		if (comprobar.isPresent()) {
			ticketServicio.addProducto(comprobar.get());// se hará igual
			return "redirect:/private/mostrarTicket";
		} else {
			// No existe ningún categoria con el Id proporcionado.
			// Redirigimos hacia el listado.
			return "redirect:/";
		}
	}

	@GetMapping("/private/borrarProducto/{id}")
	public String removeProductFromCart(@PathVariable("id") Long id) {

		ticketServicio.removeProducto(productoServicio.findById(id));
		return "redirect:/private/mostrarTicket";
	}
	
	
	@GetMapping("/private/cerrarTicket")
	public String checkout(@RequestParam("mesa") int mesa) {		
		
			ticketServicio.cerrarTicket(mesa);
			return "redirect:/private/categorias";
		
	}

	@ModelAttribute("total_carrito")
	public Double totalCarrito() {

		Map<Producto, Integer> carrito = ticketServicio.getProductsCarrito();
		double total = 0.0;
		if (carrito != null) {
			for (Producto p : carrito.keySet()) {
				total += p.getPrecio() * carrito.get(p);
			}
			return total;
		}

		return 0.0;
	}
	
	

}
