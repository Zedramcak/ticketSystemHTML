package eu.adamzrc.ticketSystemHTML.models;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Adam Zrcek on 23.03.2021
 */
@Entity
public class Ticket {
    // == constants ==

    // == fields ==
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "authorUserId", nullable = false)
    private User createdByUser;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User assignedUser;

    private Date dateCreated;

    private Date dueDate;

    private Date finishedDate;

    @Enumerated
    @Column(columnDefinition = "smallint")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;

    // == constructors ==

    // == public methods ==

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(Date finishedDate) {
        this.finishedDate = finishedDate;
    }

    public User getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(User createdByUser) {
        this.createdByUser = createdByUser;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    // == private methods ==
}
