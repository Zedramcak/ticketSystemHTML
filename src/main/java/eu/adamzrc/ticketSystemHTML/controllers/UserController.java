package eu.adamzrc.ticketSystemHTML.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.adamzrc.ticketSystemHTML.models.User;
import eu.adamzrc.ticketSystemHTML.models.apiNamesDay.Svatek;
import eu.adamzrc.ticketSystemHTML.service.IRoleService;
import eu.adamzrc.ticketSystemHTML.service.ITicketService;
import eu.adamzrc.ticketSystemHTML.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Adam Zrcek on 23.03.2021
 */
@Controller
@RequestMapping(path = "/user")
public class UserController {
    // == constants ==
    private final String DIRECTORY = "users/";
    // == fields ==
    @Autowired
    private IUserService userService;
    @Autowired
    private ITicketService ticketService;
    @Autowired
    private IRoleService roleService;

    // == constructors ==

    // == public methods ==
    @GetMapping(path = "")
    public ModelAndView showUsers(){
        ModelAndView modelAndView = new ModelAndView(DIRECTORY+"showAll");
        modelAndView.addObject("users", userService.findAll());
        return modelAndView;
    }

    @GetMapping(path = "/{username}")
    public ModelAndView showUser(@PathVariable String username){
        ModelAndView model = new ModelAndView(DIRECTORY+"show");
        User user = userService.findByUsername(username);
        model.addObject(user);
        model.addObject("tickets", user.getTickets());
        model.addObject("createdTickets", ticketService.findTicketCreatedByUser(user));
        model.addObject("subordinates", user.getSubordinates());
        return model;
    }


    @GetMapping(path = "/add")
    public ModelAndView addNewUser(){
        ModelAndView model = new ModelAndView(DIRECTORY+"new");
        model.addObject("roles", roleService.findAll());
        model.addObject("user", new User());
        model.addObject("managers", userService.findAll());
        return model;
    }

    @PostMapping(path = "/add")
    public String addNewUser(User user){
        user.setActive(1);
        User newUser = userService.saveUser(user);
        return "redirect:/user/"+ newUser.getUsername();
    }

    @GetMapping(path = "/edit/{id}")
    public ModelAndView editUserPage(@PathVariable Long id){
        ModelAndView model = new ModelAndView(DIRECTORY+"edit");
        model.addObject("roles", roleService.findAll());
        model.addObject("user", userService.findUser(id));
        model.addObject("managers", userService.findAll());
        return model;
    }

    @PostMapping(path = "/edit/{id}")
    public String editUser(@PathVariable Long id, User user){
        userService.updateUser(id, user);
        return "redirect:/user/" + user.getUsername();
    }

    @GetMapping(path = "/delete/{id}")
    public ModelAndView deleteUserPage(@PathVariable Long id){
        ModelAndView model = new ModelAndView(DIRECTORY+"delete");
        model.addObject("user", userService.findUser(id));
        return model;
    }

    @PostMapping(path = "/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
        return "redirect:/user";
    }

    @ModelAttribute
    public void addAttributes(Model model) throws IOException {
        model.addAttribute("userSigned", getSignedUser());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(new URL("https://api.abalin.net/today?country=cz&timezone=Europe%2FPrague"));
        Svatek svatky = mapper.convertValue(jsonNode, Svatek.class);

        model.addAttribute("svatek", svatky.getData().getNamedays().getCz());
    }

    private User getSignedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByUsername(authentication.getName());
    }
}
