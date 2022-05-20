package com.salesianostriana.dam.clasesproyecto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.salesianostriana.dam.clasesproyecto.model.LineaDeVenta;
import com.salesianostriana.dam.clasesproyecto.model.Producto;
import com.salesianostriana.dam.clasesproyecto.servicios.LineaDeVentaServicio;
import com.salesianostriana.dam.clasesproyecto.servicios.TicketServicio;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class LineaDeVentaController {
	
	@Autowired
	private LineaDeVentaServicio lineaDeVentaServicio;
	@Autowired
	private TicketServicio ticketServicio;
	
	@GetMapping({"/admin/mostrarRegistro/{id}/detallesVenta"})
	public String listado(Model model,  @PathVariable Long id) {
		
		List <LineaDeVenta> lineasDeVenta =new ArrayList<LineaDeVenta>();
		
			for (LineaDeVenta ln : lineaDeVentaServicio.findByTicket(id)) {
				lineasDeVenta.add(ln);
			}
			
			model.addAttribute("lineasDeVenta",lineasDeVenta);
		
		
		
		
		return "admin/DetallesVenta";
	}
	
	@ModelAttribute("total_del_carrito")
	public Double totalCarrito() {

		Map<Producto, Integer> carrito = lineaDeVentaServicio.getProductsCarrito();
		double total = 0.0;
		if (carrito != null) {
			for (Producto p : carrito.keySet()) {
				total += p.getPrecio()* carrito.get(p);
			}
			
			return total;
		}

		return 0.0;
	}
	
	
	@ModelAttribute("total_descuento")
	public Double totalConDescuento() {

		Map<Producto, Integer> carrito = lineaDeVentaServicio.getProductsCarrito();
		double total = 0.0;
		if (carrito != null) {
			for (Producto p : carrito.keySet()) {
				total += p.getPrecio() * carrito.get(p);
			}
			
			return ticketServicio.descuento(total);
		}

		return 0.0;
	}
	
}
