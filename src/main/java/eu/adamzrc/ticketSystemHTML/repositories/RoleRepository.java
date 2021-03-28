package eu.adamzrc.ticketSystemHTML.repositories;

import eu.adamzrc.ticketSystemHTML.models.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Adam Zrcek on 28.03.2021
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByRole(String role);
}
