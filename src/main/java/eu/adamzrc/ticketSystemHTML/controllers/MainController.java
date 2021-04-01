package eu.adamzrc.ticketSystemHTML.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.adamzrc.ticketSystemHTML.models.apiNamesDay.Svatek;
import eu.adamzrc.ticketSystemHTML.models.User;
import eu.adamzrc.ticketSystemHTML.service.TicketService;
import eu.adamzrc.ticketSystemHTML.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Adam Zrcek on 27.03.2021
 */
@Controller
public class MainController {

    @Autowired
    private UserService userService;
    @Autowired
    private TicketService ticketService;

    Logger logger = LoggerFactory.getLogger(MainController.class);

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
        model.addObject("subordinates", getSignedUser().getSubordinates());
        return model;
    }

    @ModelAttribute
    public void addAttributes(Model model){
        model.addAttribute("userSigned", getSignedUser());

//        try{
//            ObjectMapper mapper = new ObjectMapper();
//            JsonNode jsonNode = mapper.readTree(new URL("https://api.abalin.net/today?country=cz&timezone=Europe%2FPrague"));
//            Svatek svatky = mapper.convertValue(jsonNode, Svatek.class);
//
//            model.addAttribute("svatek", svatky.getData().getNamedays().getCz());
//            logger.info("Namesday added");
//        }catch (IOException e){
//            logger.error(e.getMessage());
//        }


    }

    private User getSignedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByUsername(authentication.getName());
    }
}
