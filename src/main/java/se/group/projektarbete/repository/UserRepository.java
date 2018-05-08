package se.group.projektarbete.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import se.group.projektarbete.data.User;
<<<<<<< HEAD
=======

import java.util.List;
>>>>>>> 39b73c7ce10cca3edea2581baa64f8bf978e6b0e
import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT MAX(userNumber) from User")
    Optional<Long> getHighestUserNumber();
<<<<<<< HEAD
    Optional<User> findUserByuserNumber(Long id);
=======

   Optional<User> findUserByuserNumber(Long id);

   List<User> findAll();




>>>>>>> 39b73c7ce10cca3edea2581baa64f8bf978e6b0e
}
