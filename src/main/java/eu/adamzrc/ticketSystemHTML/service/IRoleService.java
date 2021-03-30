package eu.adamzrc.ticketSystemHTML.service;

import eu.adamzrc.ticketSystemHTML.models.Role;

import java.util.List;

/**
 * Created by Adam Zrcek on 28.03.2021
 */
public interface IRoleService {
    Role findRoleByName(String name);
    List<Role> findAll();
}
