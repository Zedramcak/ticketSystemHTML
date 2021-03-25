package eu.adamzrc.ticketSystemHTML.service;

import eu.adamzrc.ticketSystemHTML.models.User;
import eu.adamzrc.ticketSystemHTML.repositories.TicketRepository;
import eu.adamzrc.ticketSystemHTML.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Adam Zrcek on 23.03.2021
 */
@Service
public class UserService implements IUserService{
    // == fields ==
    @Autowired
    private UserRepository repository;
    @Autowired
    private TicketRepository ticketRepository;
    // == constructors ==

    // == public methods ==

    @Override
    public void deleteUser(User user) {
        repository.delete(user);
    }

    @Override
    public void saveUser(User user) {
        repository.save(user);
    }

    @Override
    public User findUser(Long id) {
        if (repository.findById(id).isPresent()){
            return repository.findById(id).get();
        }
        return null;
    }

    @Override
    public void updateUser(Long id, User user){
        user.setUserId(id);
        repository.save(user);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    // == private methods ==
}
