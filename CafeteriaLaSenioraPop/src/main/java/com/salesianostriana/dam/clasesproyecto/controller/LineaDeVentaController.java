package com.salesianostriana.dam.clasesproyecto.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.salesianostriana.dam.clasesproyecto.model.LineaDeVenta;
import com.salesianostriana.dam.clasesproyecto.servicios.LineaDeVentaServicio;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class LineaDeVentaController {
	
	@Autowired
	private LineaDeVentaServicio lineaDeventaServicio;
	
	@GetMapping({"/admin/mostrarRegistro/{id}/detallesVenta"})
	public String listado(Model model,  @PathVariable Long id) {
		
		List <LineaDeVenta> lineasDeVenta =new ArrayList<LineaDeVenta>();
		
			for (LineaDeVenta ln : lineaDeventaServicio.findByTicket(id)) {
				lineasDeVenta.add(ln);
			}
			
			model.addAttribute("lineasDeVenta",lineasDeVenta);
		
		
		
		
		return "admin/DetallesVenta";
	}
	
}
