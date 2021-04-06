package eu.adamzrc.ticketSystemHTML.repositories;

import eu.adamzrc.ticketSystemHTML.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByRole(String role);
}
