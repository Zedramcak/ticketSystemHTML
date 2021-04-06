package eu.adamzrc.ticketSystemHTML.repositories;

import eu.adamzrc.ticketSystemHTML.models.Ticket;
import eu.adamzrc.ticketSystemHTML.models.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TicketRepository extends PagingAndSortingRepository<Ticket, Long> {
    List<Ticket> findAllByCreatedByUser(User user);
}
