package com.salesianostriana.dam.clasesproyecto.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.clasesproyecto.model.Categoria;
import com.salesianostriana.dam.clasesproyecto.model.Producto;
import com.salesianostriana.dam.clasesproyecto.servicios.CategoriaServicio;
import com.salesianostriana.dam.clasesproyecto.servicios.LineaDeVentaServicio;
import com.salesianostriana.dam.clasesproyecto.servicios.ProductoServicio;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Controller
public class ProductoController {

	private final CategoriaServicio categoriaServicio;
	
	private final ProductoServicio productoServicio;
	
	private final LineaDeVentaServicio lineaDeVentaServicio;
	
	@GetMapping({"categorias/{id}/productos"})
	public String listado(Model model,  @PathVariable Long id) {
		
		List <Producto> productos =new ArrayList<Producto>();
		
			for (Producto pr : productoServicio.findByCategoria(id)) {
				productos.add(pr);
			}
			
			model.addAttribute("productos",productos);
		
		
		
		
		return "Productos";
	}
	
	@GetMapping( "/productosAdmin")
	public String gestionProductos(Model model) {

		List<Producto> productos = new ArrayList<Producto>();
		
			for (Producto pr : productoServicio.findAll()) {
				
				productos.add(pr);
			}

			model.addAttribute("productos", productos);
		
		

		return "ProductosAdmin";
	}
	
	
	@GetMapping("/productosAdmin/nuevo")
	public String mostrarFormularioProducto(Model model) {
		model.addAttribute("producto", new Producto());
		return "AgregarProducto";
	}
	

	@PostMapping("/productosAdmin/nuevo/submit")
	public String procesarFormularioProducto(@ModelAttribute("producto") Producto pr) {
		productoServicio.add(pr);
		return "redirect:/productosAdmin";
	}
	
	@GetMapping("/productosAdmin/borrar/{id}")
	public String borrar(@PathVariable("id") long id) {
		productoServicio.deleteById(id);
		return "redirect:/productosAdmin";
	}
	
	 @ModelAttribute("categorias")
	 public List<Categoria> categorias(){
		 return categoriaServicio.findAll();
	 }
}
