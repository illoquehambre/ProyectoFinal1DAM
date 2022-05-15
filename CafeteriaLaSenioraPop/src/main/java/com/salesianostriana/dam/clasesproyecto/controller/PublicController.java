package com.salesianostriana.dam.clasesproyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicController {

	@GetMapping("/index")
	public String welcome() {
		return "index";
	}


	@GetMapping("/")
	public String principal() {
		return "paginaPrincipal";
	}

	@GetMapping("/error")
	public String error() {
		return "error";
	}
}
