package eu.adamzrc.ticketSystemHTML.repositories;

import eu.adamzrc.ticketSystemHTML.models.Project;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Adam Zrcek on 01.04.2021
 */
public interface ProjectRepository extends CrudRepository<Project, Long> {
}
