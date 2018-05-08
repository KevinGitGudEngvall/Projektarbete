package se.group.projektarbete.data;

import javax.persistence.*;
import java.util.List;

@Entity
public final class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private Long userNumber;
    @Column(nullable = false)
    private Boolean active;

    @OneToMany
    List<WorkItem> workItems;

    @ManyToOne
    private Team team;

    protected User(){}

    public User(String firstName, String lastName, String userName, Long userNumber, Boolean active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.userNumber = userNumber;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public Long getUserNumber() {
        return userNumber;
    }

    public Boolean getActive() {
        return active;
    }

    public List<WorkItem> getWorkItems() {
        return workItems;
    }

    public Team getTeam() {
        return team;
    }
}

