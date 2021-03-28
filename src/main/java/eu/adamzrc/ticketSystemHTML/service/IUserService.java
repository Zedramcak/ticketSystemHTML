package eu.adamzrc.ticketSystemHTML.service;

import eu.adamzrc.ticketSystemHTML.models.Ticket;
import eu.adamzrc.ticketSystemHTML.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Adam Zrcek on 23.03.2021
 */
public interface IUserService {
    Page<User> getUsersPageable(Pageable page);
    User findByUsername(String username);
    User findUser(Long id);
    void deleteUserById(Long id);
    void deleteUser(User user);
    User saveUser(User user);
    void updateUser(Long id, User user);
    List<User> findAll();
}
