package eu.adamzrc.ticketSystemHTML.controllers;

import eu.adamzrc.ticketSystemHTML.models.User;
import eu.adamzrc.ticketSystemHTML.service.IRoleService;
import eu.adamzrc.ticketSystemHTML.service.ITicketService;
import eu.adamzrc.ticketSystemHTML.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Adam Zrcek on 23.03.2021
 */
@Controller
@RequestMapping(path = "/users")
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
    @GetMapping()
    public ModelAndView showUsers(@RequestParam("page") Optional<Integer> page,
                                  @RequestParam("size") Optional<Integer> size,
                                  @RequestParam("orderBy")Optional<String> order){
        ModelAndView modelAndView = new ModelAndView(DIRECTORY+"showAll");
        Page<User> userPage = userService.getUsersPageable(page.orElse(1)-1, size.orElse(10), order.orElse("lastName"));
//        modelAndView.addObject("users", userService.findAll());
        modelAndView.addObject("usersPage", userPage);
        modelAndView.addObject("order", order.orElse("lastName"));

        int totalPages = userPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }

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
        return "redirect:/users/"+ newUser.getUsername();
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
        return "redirect:/users/" + user.getUsername();
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
        return "redirect:/users";
    }

    @ModelAttribute
    public void addAttributesToAllModels(Model model) {
        model.addAttribute("userSigned", getSignedUser());
    }

    private User getSignedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByUsername(authentication.getName());
    }
}
