package eu.adamzrc.ticketSystemHTML.service;

import eu.adamzrc.ticketSystemHTML.models.Role;

import java.util.List;

public interface IRoleService {
    Role findRoleByName(String name);
    List<Role> findAll();
}
