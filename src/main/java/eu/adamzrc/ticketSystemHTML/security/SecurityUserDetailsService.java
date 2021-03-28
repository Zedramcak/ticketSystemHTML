package eu.adamzrc.ticketSystemHTML.security;

import eu.adamzrc.ticketSystemHTML.models.User;
import eu.adamzrc.ticketSystemHTML.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Adam Zrcek on 27.03.2021
 */
@Service
public interface SecurityUserDetailsService{
    User findUserByUsername(String username);
    void saveUser(User user);
}
