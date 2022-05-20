package com.salesianostriana.dam.clasesproyecto.model;

import java.time.LocalDate;
import java.time.LocalTime;
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

public class Ticket {
	
	@Id
	@GeneratedValue
	private long id;
	
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany (mappedBy="ticket", fetch= FetchType.EAGER)
	@Builder.Default
	private List <LineaDeVenta> lineaDeVenta = new ArrayList<LineaDeVenta>();	
	private LocalDate fecha;
	private LocalTime hora;
	//Indicar relación de la BD
	private double total;
	private int mesa;

	
	
	
	
	
	
	
	
	
		
}
