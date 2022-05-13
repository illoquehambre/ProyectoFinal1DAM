package com.salesianostriana.dam.clasesproyecto.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.salesianostriana.dam.clasesproyecto.model.LineaDeVenta;
import com.salesianostriana.dam.clasesproyecto.servicios.LineaDeVentaServicio;
import com.salesianostriana.dam.clasesproyecto.servicios.TicketServicio;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller

public class LineaDeVentaController {

	/*private final TicketServicio ticketServicio;
	private final LineaDeVentaServicio lineaDeVentaServicio;
	
	@GetMapping({"/ticket"})
	public String carrito(Model model,  @PathVariable Long id) {
		
		List <LineaDeVenta> lineaDeVenta =new ArrayList<LineaDeVenta>();
		
			for (LineaDeVenta lv : lineaDeVentaServicio.findByTicket(id)) {
				lineaDeVenta.add(lv);
			}
			
			model.addAttribute("lineaDeVenta",lineaDeVenta);
		
		
		
		
		return "Productos";
	}
	*/
}
