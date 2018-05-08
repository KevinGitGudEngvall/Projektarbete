package se.group.projektarbete.data;

import javax.persistence.*;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade={CascadeType.DETACH, CascadeType.PERSIST})
    List<User> users;

    protected Team(){}

    public Team(String name, Boolean active) {
        this.name = name;
        this.active = active;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUserList(User user) {
        users.add(user);
        user.setTeam(this);

    }

}
