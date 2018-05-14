package se.group.projektarbete.data;

import javax.persistence.*;

@Entity
public final class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @OneToOne
    private WorkItem workItem;

    protected Issue(){}

    public Issue(String description, WorkItem workItem) {
        this.description = description;
        this.workItem = workItem;
    }
    public void setWorkItem (WorkItem workItem){
        this.workItem = workItem;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public WorkItem getWorkItem() {
        return workItem;
    }

    public void updateIssue(Issue issue, WorkItem workItem) {
        this.description = issue.getDescription();
        workItem.setIssue(issue);
    }
}
