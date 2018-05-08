package se.group.projektarbete.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import se.group.projektarbete.data.User;
import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT MAX(userNumber) from User")
    Optional<Long> getHighestUserNumber();
    Optional<User> findUserByuserNumber(Long id);
}
