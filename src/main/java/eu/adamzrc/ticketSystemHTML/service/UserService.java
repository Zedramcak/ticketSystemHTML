package eu.adamzrc.ticketSystemHTML.service;

import eu.adamzrc.ticketSystemHTML.models.User;
import eu.adamzrc.ticketSystemHTML.repositories.TicketRepository;
import eu.adamzrc.ticketSystemHTML.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.LocalDateTime;
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
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    // == constructors ==

    // == public methods ==


    @Override
    public User findByUsername(String username) {
        return repository.findUserByUsername(username);
    }

    @Override
    public Page<User> getUsersPageable(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    public void deleteUser(User user) {
        repository.delete(user);
    }

    @Override
    public void deleteUserById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setUsername(generateUsername(user));
        return repository.save(user);
    }

    @Override
    public User findUser(Long id) {
        if (repository.findById(id).isPresent()){
            return repository.findById(id).get();
        }
        return null;
    }

    @Override
    public void updateUser(Long id, User updatedUser){
        updatedUser.setUserId(id);
        updatedUser.setUsername(findUser(id).getUsername());
        updatedUser.setActive(1);
        updatedUser.setPassword(findUser(id).getPassword());
        repository.save(updatedUser);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    // == private methods ==
    private String generateUsername(User user){
        String firstName = Normalizer.normalize(user.getFirstName(), Normalizer.Form.NFD).replaceAll("[\\p{InCombiningDiacriticalMarks}]","").replaceAll("[^a-zA-Z]","").toLowerCase().substring(0,2);
        String lastName = Normalizer.normalize(user.getLastName(), Normalizer.Form.NFD).replaceAll("[\\p{InCombiningDiacriticalMarks}]","").replaceAll("[^a-zA-Z]","").toLowerCase().substring(0,3);

        return firstName + lastName + LocalDateTime.now().getSecond();
    }
}
