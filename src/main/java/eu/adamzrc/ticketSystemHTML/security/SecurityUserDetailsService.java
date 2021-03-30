package eu.adamzrc.ticketSystemHTML.security;

import eu.adamzrc.ticketSystemHTML.models.User;
import org.springframework.stereotype.Service;

/**
 * Created by Adam Zrcek on 27.03.2021
 */
@Service
public interface SecurityUserDetailsService{
    User findUserByUsername(String username);
    void saveUser(User user);
}
