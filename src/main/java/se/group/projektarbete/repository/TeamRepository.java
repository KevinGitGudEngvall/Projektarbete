package se.group.projektarbete.repository;

import org.springframework.data.repository.CrudRepository;
import se.group.projektarbete.data.Team;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Long> {

    List<Team> findAll();
}
