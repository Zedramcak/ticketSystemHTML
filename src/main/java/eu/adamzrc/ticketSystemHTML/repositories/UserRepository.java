package eu.adamzrc.ticketSystemHTML.repositories;

import eu.adamzrc.ticketSystemHTML.models.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findUserByUsername(String username);
}
