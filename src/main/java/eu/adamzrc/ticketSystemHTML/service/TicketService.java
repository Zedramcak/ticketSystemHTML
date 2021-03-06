package eu.adamzrc.ticketSystemHTML.service;

import eu.adamzrc.ticketSystemHTML.models.Ticket;
import eu.adamzrc.ticketSystemHTML.models.User;
import eu.adamzrc.ticketSystemHTML.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService implements ITicketService{
    @Autowired
    private TicketRepository repository;

    @Override
    public List<Ticket> findTicketCreatedByUser(User user) {
        return repository.findAllByCreatedByUser(user);
    }

    @Override
    public Ticket findTicket(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void saveTicket(Ticket ticket) {
        repository.save(ticket);
    }

    @Override
    public void updateTicket(Long id, Ticket ticket) {
        ticket.setId(id);
        repository.save(ticket);
    }

    @Override
    public void deleteTicket(Ticket ticket) {
        repository.delete(ticket);
    }

    @Override
    public List<Ticket> findAll() {
        return (List<Ticket>) repository.findAll();
    }
}
