package eu.adamzrc.ticketSystemHTML.controllers;

import eu.adamzrc.ticketSystemHTML.models.User;
import eu.adamzrc.ticketSystemHTML.service.ITicketService;
import eu.adamzrc.ticketSystemHTML.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    // == constructors ==

    // == public methods ==
    @GetMapping(path = "")
    public ModelAndView showUsers(){
        ModelAndView modelAndView = new ModelAndView(DIRECTORY+"showAll");
        modelAndView.addObject("users", userService.findAll());
        return modelAndView;
    }

    @GetMapping(path = "/{id}")
    public ModelAndView showUser(@PathVariable Long id){
        ModelAndView model = new ModelAndView(DIRECTORY+"show");
        User user = userService.findUser(id);
        model.addObject(user);
        model.addObject("tickets", user.getTickets());
        model.addObject("createdTickets", ticketService.findTicketCreatedByUser(user));
        return model;
    }


    @GetMapping(path = "/add")
    public ModelAndView addNewUser(){
        ModelAndView model = new ModelAndView(DIRECTORY+"new");
        model.addObject("user", new User());
        return model;
    }

    @PostMapping(path = "/add")
    public String addNewUser(User user){
        User newUser = userService.saveUser(user);
        return "redirect:/user/"+  + newUser.getUserId();
    }

    @GetMapping(path = "/edit/{id}")
    public ModelAndView editUserPage(@PathVariable Long id){
        ModelAndView model = new ModelAndView(DIRECTORY+"edit");
        model.addObject("user", userService.findUser(id));
        return model;
    }

    @PostMapping(path = "/edit/{id}")
    public String editUser(@PathVariable Long id, User user){
        userService.updateUser(id, user);
        return "redirect:/user/" + user.getUserId();
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
    public void addAttributes(Model model){
        model.addAttribute("userSigned", getSignedUser());
    }

    private User getSignedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByUsername(authentication.getName());
    }
}
