package eu.adamzrc.ticketSystemHTML.controllers;

import eu.adamzrc.ticketSystemHTML.models.User;
import eu.adamzrc.ticketSystemHTML.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

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

    // == constructors ==

    // == public methods ==
    @GetMapping(path = "")
    public String showUsers(Model model){
        model.addAttribute("users", userService.findAll());
        return DIRECTORY+"showAll";
    }

    @GetMapping(path = "/{id}")
    public String showUser(@PathVariable Long id, Model model){
        User user = userService.findUser(id);
        model.addAttribute(user);
        model.addAttribute("tickets", user.getTickets());
        return DIRECTORY+"show";
    }

    @GetMapping(path = "/add")
    public String addNewUser(Model model){
        model.addAttribute("user", new User());
        return DIRECTORY+"new";
    }

    @PostMapping(path = "/add")
    public String addNewUser(User user){
        user.setUserName(
                user.getFirstName().replace("[^a-zA-Z","").toLowerCase().charAt(0) +
                user.getLastName().replace("[^a-zA-Z","").toLowerCase().substring(0,3) +
                user.getBirthDate().toString().substring(2,4)
        );
        userService.saveUser(user);
        return DIRECTORY+"saved";
    }

    @GetMapping(path = "/edit/{id}")
    public String editUser(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.findUser(id));
        return DIRECTORY+"edit";
    }

    @PostMapping(path = "/edit/{id}")
    public String editUser(@PathVariable Long id, User user){
        userService.updateUser(id, user);
        return DIRECTORY+"edited";
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteUser(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.findUser(id));
        return DIRECTORY+"delete";
    }

    @PostMapping(path = "/delete/{id}")
    public String deleteUser(@PathVariable Long id, User user){
        user.setUserId(id);
        userService.deleteUser(user);
        return DIRECTORY+"deleted";
    }

    // == private methods ==
}
