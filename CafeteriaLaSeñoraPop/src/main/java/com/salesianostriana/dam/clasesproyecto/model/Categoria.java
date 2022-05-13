package com.salesianostriana.dam.clasesproyecto.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class Categoria {
	
	
	@Id
	@GeneratedValue
	private long id;
	private String nombre;
	//Especificar tipo de enlace de Bd(oneToMany, manyToOne...)
	@Builder.Default
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany (mappedBy="categoria", fetch= FetchType.EAGER)
	private List <Producto> productos = new ArrayList<>();
	
	
	public Categoria(String nombre, List<Producto> productos) {
		super();
		this.nombre = nombre;
		this.productos = productos;
	}
	
	
		
}
