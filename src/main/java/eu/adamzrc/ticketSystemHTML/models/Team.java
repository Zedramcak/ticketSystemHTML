package eu.adamzrc.ticketSystemHTML.models;

import javax.persistence.*;


@Entity
public class Team {

    @Id
    @GeneratedValue
    private Long teamId;

    private String name;

    private String leader;

    @ManyToOne
    @JoinColumn(name = "leaderId")
    private User teamLeader;

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public User getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(User teamLeader) {
        this.teamLeader = teamLeader;
    }
}
