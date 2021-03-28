package eu.adamzrc.ticketSystemHTML.service;

import eu.adamzrc.ticketSystemHTML.models.Role;
import eu.adamzrc.ticketSystemHTML.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Adam Zrcek on 28.03.2021
 */
@Service
public class RoleService implements IRoleService{
    @Autowired
    RoleRepository repository;

    @Override
    public Role findRoleByName(String name) {
        return repository.findByRole(name);
    }
    // == constants ==

    // == fields ==

    // == constructors ==

    // == public methods ==

    // == private methods ==
}
