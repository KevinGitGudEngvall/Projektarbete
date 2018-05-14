package se.group.projektarbete.data;



import se.group.projektarbete.data.workitemenum.Status;

import javax.persistence.*;

@Entity
public final class WorkItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private User user;

    @OneToOne
    private Issue issue;

    protected WorkItem(){}


    public WorkItem(String name, String description, Status status, User user) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

    public void setIssue(Issue issue){
        this.issue = issue;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
