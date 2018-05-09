package se.group.projektarbete.data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

@Entity
public final class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean active;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "team", cascade={CascadeType.DETACH, CascadeType.PERSIST})
    List<User> users;

    protected Team(){}

    public Team(String name, Boolean active) {
        this.name = name;
        this.active = active;
        this.users = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getActive() {
        return active;
    }

    @XmlTransient
    public List<User> getUsers() {
        return users;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setUser(User user) {
        users.add(user);
        user.setTeam(this);
    }

    public void updateTeam(Team team){
        this.name = team.getName();
        this.active = team.getActive();
    }
}
