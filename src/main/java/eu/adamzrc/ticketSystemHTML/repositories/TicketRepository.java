package eu.adamzrc.ticketSystemHTML.repositories;

import eu.adamzrc.ticketSystemHTML.models.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Adam Zrcek on 23.03.2021
 */
public interface TicketRepository extends CrudRepository<Ticket, Long> {
}
