package se.group.projektarbete.data;


import se.group.projektarbete.data.workitemenum.Status;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

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
    @XmlTransient
    private User user;

    @OneToOne
    @XmlTransient
    private Issue issue;

    protected WorkItem() {
    }


    public WorkItem(String name, String description) {
        this.name = name;
        this.description = description;
        status = Status.UNSTARTED;
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

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }
}
