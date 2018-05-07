package se.group.projektarbete.repository;

import org.springframework.data.repository.CrudRepository;
import se.group.projektarbete.data.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {
}
