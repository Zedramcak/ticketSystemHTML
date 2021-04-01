package eu.adamzrc.ticketSystemHTML.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.adamzrc.ticketSystemHTML.models.Ticket;
import eu.adamzrc.ticketSystemHTML.models.User;
import eu.adamzrc.ticketSystemHTML.models.apiNamesDay.Svatek;
import eu.adamzrc.ticketSystemHTML.service.ITicketService;
import eu.adamzrc.ticketSystemHTML.service.IUserService;
import eu.adamzrc.ticketSystemHTML.models.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
    public String createNewTicket(Ticket ticket, Model model){
        ticket.setDateCreated(Date.valueOf(LocalDate.now()));
        ticket.setFinishedDate(null);
        ticket.setStatus(Status.NEW);
        ticket.setCreatedByUser(userService.findUser(getSignedUser().getUserId()));
        ticketService.saveTicket(ticket);
        model.addAttribute(ticket);
        return DIRECTORY+"show";
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

    @PostMapping(path = "/{id}/editStatus")
    public String editTicket(@PathVariable Long id, Model model){
        Ticket ticket = ticketService.findTicket(id);
        switch(ticket.getStatus()){
            case NEW:
                ticket.setStatus(Status.IN_PROGRESS);
                break;
            case IN_PROGRESS:
                ticket.setStatus(Status.FINISHED);
                ticket.setAssignedUser(ticket.getCreatedByUser());
                ticket.setFinishedDate(Date.valueOf(LocalDate.now()));
                break;
            default:
                break;
        }
        ticketService.saveTicket(ticket);
        model.addAttribute(ticket);
        return "redirect:";
    }

    @ModelAttribute
    public void addAttributes(Model model) throws IOException {
        model.addAttribute("userSigned", getSignedUser());

//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode jsonNode = mapper.readTree(new URL("https://api.abalin.net/today?country=cz&timezone=Europe%2FPrague"));
//        Svatek svatky = mapper.convertValue(jsonNode, Svatek.class);
//
//        model.addAttribute("svatek", svatky.getData().getNamedays().getCz());
    }

    private User getSignedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByUsername(authentication.getName());
    }

}
