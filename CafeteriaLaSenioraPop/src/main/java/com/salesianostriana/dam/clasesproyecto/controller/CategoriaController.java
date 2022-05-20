package com.salesianostriana.dam.clasesproyecto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.clasesproyecto.model.Categoria;
import com.salesianostriana.dam.clasesproyecto.servicios.CategoriaServicio;
import com.salesianostriana.dam.clasesproyecto.servicios.ProductoServicio;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller

public class CategoriaController {
	@Autowired
	private CategoriaServicio categoriaServicio;
	@Autowired
	private ProductoServicio productoServicio;

	

	@GetMapping({ "/private", "/private/categorias" })
	public String listado(Model model) {

		List<Categoria> categorias = new ArrayList<Categoria>();

		for (Categoria cat : categoriaServicio.findAll()) {

			categorias.add(cat);
		}

		model.addAttribute("categorias", categorias);

		return "private/Categorias";
	}

	@GetMapping("/admin/buscarCat")
	public String buscarAdmin(Model model, @RequestParam String nombre) {

		model.addAttribute("categorias", categoriaServicio.buscarPorNombre(nombre));

		return "admin/CategoriasAdmin";

	}

	@GetMapping("/private/buscarCat")
	public String buscarPrivate(Model model, @RequestParam String nombre) {

		model.addAttribute("categorias", categoriaServicio.buscarPorNombre(nombre));

		return "private/Categorias";

	}

	@GetMapping({ "/admin", "/admin/categoriasAdmin" })
	public String gestionCategorias(Model model, Optional<String> optional) {

		List<Categoria> categorias = new ArrayList<Categoria>();

		for (Categoria cat : categoriaServicio.findAll()) {

			categorias.add(cat);
		}

		model.addAttribute("categorias", categorias);

		return "admin/CategoriasAdmin";
	}

	@GetMapping("/admin/categoriasAdmin/nuevo")
	public String mostrarFormularioCategoria(Model model) {
		model.addAttribute("categoria", new Categoria());
		return "admin/AgregarCategoria";
	}

	@PostMapping("/admin/categoriasAdmin/nuevo/submit")
	public String procesarFormularioCategoria(@ModelAttribute("categoria") Categoria cat) {
		categoriaServicio.add(cat);
		return "redirect:/admin/categoriasAdmin";
	}

	@GetMapping("/admin/categoriasAdmin/borrar/{id}")
	public String borrar(@PathVariable("id") long id) {
		// Comprobar si tiene productos dentro

		Optional<Categoria> categoria = categoriaServicio.findById(id);

		if (categoria.isPresent()) {

			if (productoServicio.numeroProductosCategoria(categoria.get()) == 0) {
				categoriaServicio.delete(categoria.get());
			} else {

				// Se ha agregado el parámetro error con valor true a la ruta
				return "redirect:/admin/categoriasAdmin/?error=true";
			}

		}

		return "redirect:/admin/categoriasAdmin/";

		/*
		 * categoriaServicio.deleteById(id); return "redirect:/admin/categoriasAdmin";
		 */
	}

	@GetMapping("/admin/categoriasAdmin/{id}/editar")
	public String mostrarFormularioEdicion(@PathVariable("id") long id, Model model) {

		// Buscamos al categoria por id y recordemos que el método findById del
		// servicio, devuelve el objeto buscado o null si no se encuentra.

		Optional<Categoria> comprobar = categoriaServicio.findById(id);

		if (comprobar.isPresent()) {
			model.addAttribute("categoria", comprobar.get());
			return "admin/AgregarCategoria";
		} else {
			// No existe ningún categoria con el Id proporcionado.
			// Redirigimos hacia el listado.
			return "redirect:/admin/categoriasAdmin";
		}

	}

	/**
	 * Método que procesa la respuesta del formulario al editar
	 */
	@PostMapping("/admin/categoriasAdmin/{id}/editar/submit")
	public String procesarFormularioEdicion(@ModelAttribute("categoria") Categoria cat) {
		categoriaServicio.edit(cat);
		return "redirect:/admin/categoriasAdmin";// Volvemos a redirigir la listado a través del controller para pintar
													// la lista actualizada con la modificación hecha
	}

}
