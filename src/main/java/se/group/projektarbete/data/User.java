package se.group.projektarbete.data;

import se.group.projektarbete.data.workitemenum.Status;

import javax.persistence.*;
import java.util.ArrayList;
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
    private Boolean active = true;


    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.PERSIST}, mappedBy = "user")
    private List<WorkItem> workItems;

    @ManyToOne
    private Team team;

    protected User() {
    }

    public User(String firstName, String lastName, String userName, Long userNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.userNumber = userNumber;
        workItems = new ArrayList<>();
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

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<WorkItem> getWorkItems() {
        return this.workItems;
    }

    public void setWorkItems(WorkItem workItem) {
        workItems.add(workItem);
        workItem.setUser(this);
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void updateUser(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.userName = user.getUserName();
        this.active = user.getActive();
    }

    public void setWorkItemsToUnstarted(List<WorkItem> workItems) {
        for (int i = 0; i < workItems.size(); i++) {
            workItems.get(i).setStatus(Status.UNSTARTED);
        }
    }
}