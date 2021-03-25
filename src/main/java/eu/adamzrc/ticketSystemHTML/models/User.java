package eu.adamzrc.ticketSystemHTML.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by Adam Zrcek on 23.03.2021
 */
@Entity
public class User {
    // == constants ==

    // == fields ==
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(nullable = false, length = 32)
    private String firstName;

    @Column(nullable = false, length = 32)
    private String lastName;

    private Date birthDate;

    private String userName;

    @OneToMany(mappedBy = "assignedUser")
    private List<Ticket> tickets;

    // == constructors ==

    // == public methods ==

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    // == private methods ==
}
