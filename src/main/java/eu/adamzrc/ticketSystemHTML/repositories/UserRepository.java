package eu.adamzrc.ticketSystemHTML.repositories;

import eu.adamzrc.ticketSystemHTML.models.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Adam Zrcek on 23.03.2021
 */
//TODO check pagable option
public interface UserRepository extends CrudRepository<User, Long> {
}
