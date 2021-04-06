package eu.adamzrc.ticketSystemHTML.security;

import eu.adamzrc.ticketSystemHTML.models.User;
import org.springframework.stereotype.Service;

@Service
public interface SecurityUserDetailsService{
    User findUserByUsername(String username);
    void saveUser(User user);
}
