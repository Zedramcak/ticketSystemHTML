package eu.adamzrc.ticketSystemHTML.repositories;

import eu.adamzrc.ticketSystemHTML.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Adam Zrcek on 23.03.2021
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
}
