package se.group.projektarbete.data;

import javax.persistence.*;

@Entity
public final class Issue {
///Skriver nåt
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String description;

    @OneToOne
    private WorkItem workItem;

    private Long Ian;

    protected Issue(){}

    public Issue(String description, WorkItem workItem) {
        this.description = description;
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
}
