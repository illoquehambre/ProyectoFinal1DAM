package com.salesianostriana.dam.clasesproyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.clasesproyecto.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{

}
