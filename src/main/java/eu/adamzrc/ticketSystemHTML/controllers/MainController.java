package eu.adamzrc.ticketSystemHTML.controllers;

import eu.adamzrc.ticketSystemHTML.models.User;
import eu.adamzrc.ticketSystemHTML.service.TicketService;
import eu.adamzrc.ticketSystemHTML.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Adam Zrcek on 27.03.2021
 */
@Controller
public class MainController {

    @Autowired
    private UserService userService;
    @Autowired
    private TicketService ticketService;

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/")
    public ModelAndView home(){
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addObject("success", "LOGIN SUCCESSFUL");
        model.setViewName("index");
        return model;
    }

    @GetMapping("/myProfile")
    public ModelAndView showSignedUserProfile(){
        ModelAndView model = new ModelAndView("users/show");
        User signedUser = getSignedUser();
        model.addObject("user", signedUser);
        model.addObject("tickets", signedUser.getTickets());
        model.addObject("createdTickets", ticketService.findTicketCreatedByUser(signedUser));
        return model;
    }

    @ModelAttribute
    public void addAttributes(Model model){
        model.addAttribute("userSigned", getSignedUser());
    }

    private User getSignedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByUsername(authentication.getName());
    }
}
