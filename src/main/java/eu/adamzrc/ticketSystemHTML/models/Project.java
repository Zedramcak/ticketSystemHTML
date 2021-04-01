package eu.adamzrc.ticketSystemHTML.models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Adam Zrcek on 01.04.2021
 */
@Entity
public class Project {

    @Id
    @GeneratedValue
    private Long projectId;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "leaderId")
    private User projectLeader;

    @OneToMany(mappedBy = "project")
    private List<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team assignedTeam;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getProjectLeader() {
        return projectLeader;
    }

    public void setProjectLeader(User projectLeader) {
        this.projectLeader = projectLeader;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Team getAssignedTeam() {
        return assignedTeam;
    }

    public void setAssignedTeam(Team assignedTeam) {
        this.assignedTeam = assignedTeam;
    }
}
