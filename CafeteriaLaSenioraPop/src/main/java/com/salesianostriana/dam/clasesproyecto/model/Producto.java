package com.salesianostriana.dam.clasesproyecto.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producto {
	
	
	
	@Id
	@GeneratedValue
	private long id;	
	private String nombre;
	private double precio;
	private int cantidad;
	private String url;
	//Especificar el tipo de conexion de BD (mnayToOne, OneToMany...)
	@ManyToOne
	
	private Categoria categoria;
	
	
	public Producto(String nombre, double precio, int cantidad, String url, Categoria categoria) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.cantidad = cantidad;
		this.url = url;
		this.categoria = categoria;
	}
}
