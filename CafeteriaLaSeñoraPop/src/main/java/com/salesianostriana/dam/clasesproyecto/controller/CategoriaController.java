package com.salesianostriana.dam.clasesproyecto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.clasesproyecto.model.Categoria;
import com.salesianostriana.dam.clasesproyecto.servicios.CategoriaServicio;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CategoriaController {

	private final CategoriaServicio categoriaServicio;
	
	@GetMapping({ "/", "/carta" })
	public String carta(Model model,  Optional<String> optional) {

		List<Categoria> categorias = new ArrayList<Categoria>();
		
			for (Categoria cat : categoriaServicio.findAll()) {
				
				categorias.add(cat);
			}

			model.addAttribute("categorias", categorias);
		
		

		return "Carta2";
	}
	
	
	@GetMapping( "/categorias")
	public String listado(Model model,  Optional<String> optional) {

		List<Categoria> categorias = new ArrayList<Categoria>();
		
			for (Categoria cat : categoriaServicio.findAll()) {
				
				categorias.add(cat);
			}

			model.addAttribute("categorias", categorias);
		
		

		return "Categorias";
	}
	
	@GetMapping( "/categoriasAdmin")
	public String gestionCategorias(Model model,  Optional<String> optional) {

		List<Categoria> categorias = new ArrayList<Categoria>();
		
			for (Categoria cat : categoriaServicio.findAll()) {
				
				categorias.add(cat);
			}

			model.addAttribute("categorias", categorias);
		
		

		return "CategoriasAdmin";
	}
	
	
	@GetMapping("/categoriasAdmin/nuevo")
	public String mostrarFormularioCategoria(Model model) {
		model.addAttribute("categoria", new Categoria());
		return "AgregarCategoria";
	}
	

	@PostMapping("/categoriasAdmin/nuevo/submit")
	public String procesarFormularioCategoria(@ModelAttribute("categoria") Categoria cat) {
		categoriaServicio.add(cat);
		return "redirect:/categoriasAdmin";
	}
	
	@GetMapping("/categoriasAdmin/borrar/{id}")
	public String borrar(@PathVariable("id") long id) {
		categoriaServicio.deleteById(id);
		return "redirect:/categoriasAdmin";
	}
	
	
	@GetMapping("/categoriasAdmin/{id}/editar")
	public String mostrarFormularioEdicion(@PathVariable("id") long id, Model model) {
		
		//Buscamos al categoria por id y recordemos que el método findById del servicio, devuelve el objeto buscado o null si no se encuentra.
		 
		
		Optional<Categoria> comprobar = categoriaServicio.findById(id);
		
		if (comprobar.isPresent()) {
			model.addAttribute("categoria", comprobar.get());
			return "AgregarCategoria";
		} else {
			// No existe ningún categoria con el Id proporcionado.
			// Redirigimos hacia el listado.
			return "redirect:/categoriasAdmin";
		}
		
		
	}
	
	/**
	 * Método que procesa la respuesta del formulario al editar
	 */
	@PostMapping("categoriasAdmin/{id}/editar/submit")
	public String procesarFormularioEdicion(@ModelAttribute("categoria") Categoria cat) {
		categoriaServicio.edit(cat);
		return "redirect:/categoriasAdmin";//Volvemos a redirigir la listado a través del controller para pintar la lista actualizada con la modificación hecha
	}
	
	
	
}
