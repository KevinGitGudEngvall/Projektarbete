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

    @OneToMany(fetch = FetchType.EAGER)
    List<WorkItem> workItems;

    @ManyToOne
    private Team team;

    protected User() {
    }

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

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void updateUser(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.userName = user.getUserName();
        this.userNumber = user.getUserNumber();
        this.active = user.getActive();
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setWorkItems(WorkItem workItem) {
        workItems.add(workItem);
        workItem.setUser(this);
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}

