package eu.adamzrc.ticketSystemHTML.service;

import eu.adamzrc.ticketSystemHTML.models.Ticket;
import eu.adamzrc.ticketSystemHTML.models.User;

import java.util.List;

/**
 * Created by Adam Zrcek on 23.03.2021
 */
public interface IUserService {
    User findUser(Long id);
    void deleteUser(User user);
    void saveUser(User user);
    void updateUser(Long id, User user);
    List<User> findAll();
}
