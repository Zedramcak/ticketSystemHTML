package eu.adamzrc.ticketSystemHTML.service;

import eu.adamzrc.ticketSystemHTML.models.Role;
import eu.adamzrc.ticketSystemHTML.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleService implements IRoleService{
    @Autowired
    RoleRepository repository;

    @Override
    public List<Role> findAll() {
        return (List<Role>) repository.findAll();
    }

    @Override
    public Role findRoleByName(String name) {
        return repository.findByRole(name);
    }
}
