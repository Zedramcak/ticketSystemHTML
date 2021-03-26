package eu.adamzrc.ticketSystemHTML.controllers;

import eu.adamzrc.ticketSystemHTML.models.Ticket;
import eu.adamzrc.ticketSystemHTML.models.User;
import eu.adamzrc.ticketSystemHTML.service.ITicketService;
import eu.adamzrc.ticketSystemHTML.service.IUserService;
import eu.adamzrc.ticketSystemHTML.models.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by Adam Zrcek on 23.03.2021
 */
@Controller
@RequestMapping(path = "/tickets")
public class TicketController {

    private final String DIRECTORY = "tickets/";

    @Autowired
    private ITicketService ticketService;
    @Autowired
    private IUserService userService;

    @GetMapping(path = "")
    public String showTickets(Model model){
        model.addAttribute("tickets", ticketService.findAll());
        return DIRECTORY+"showAll";
    }

    @GetMapping(path = "/add")
    public String createNewTicket(Model model){
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("users", userService.findAll());
        return DIRECTORY+"new";
    }

    @PostMapping(path="/add")
    public String createNewTicket(Ticket ticket){
        ticket.setDateCreated(Date.valueOf(LocalDate.now()));
        ticket.setFinishedDate(null);
        ticket.setStatus(Status.NEW);
        ticketService.saveTicket(ticket);
        return DIRECTORY+"saved";
    }

    @GetMapping(path = "/{id}")
    public String showTicket(@PathVariable Long id, Model model){
        Ticket ticket = ticketService.findTicket(id);
        model.addAttribute(ticket);
        return DIRECTORY+"show";
    }

    @PostMapping(path = "/{id}/delete")
    public String deleteTicket(@PathVariable Long id){
        ticketService.deleteTicket(ticketService.findTicket(id));
        return DIRECTORY+"deleted";
    }

}
