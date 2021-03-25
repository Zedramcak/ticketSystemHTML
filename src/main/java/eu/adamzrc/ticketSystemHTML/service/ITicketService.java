package eu.adamzrc.ticketSystemHTML.service;

import eu.adamzrc.ticketSystemHTML.models.Ticket;
import eu.adamzrc.ticketSystemHTML.models.User;

import java.util.List;

/**
 * Created by Adam Zrcek on 23.03.2021
 */
public interface ITicketService{
    List<Ticket> findTicketCreatedByUser(User user);
    Ticket findTicket(Long id);
    void saveTicket(Ticket ticket);
    void updateTicket(Long id, Ticket ticket);
    void deleteTicket(Ticket ticket);
    List<Ticket> findAll();
}
