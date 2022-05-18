package com.salesianostriana.dam.clasesproyecto.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LineaDeVenta {

	@Id
	@GeneratedValue
	private long id;
	@OneToOne
	private Producto producto;
	private int cantidad;
	private double subtotal;
	@ManyToOne
	private Ticket ticket;
	
	
	public void addToTicket(Ticket ticket) {
		this.ticket=ticket;
		ticket.getLineaDeVenta().add(this);
	}
	
	public void removeFromTicket(Ticket ticket) {
		ticket.getLineaDeVenta().remove(this);
		this.ticket = null;
		
	}
	
}
