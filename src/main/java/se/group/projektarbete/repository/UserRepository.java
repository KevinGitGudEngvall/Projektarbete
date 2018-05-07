package se.group.projektarbete.repository;

import org.springframework.data.repository.CrudRepository;
import se.group.projektarbete.data.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
