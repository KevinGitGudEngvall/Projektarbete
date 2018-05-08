package se.group.projektarbete.service;

import org.springframework.stereotype.Service;
import se.group.projektarbete.data.User;
import se.group.projektarbete.repository.UserRepository;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public final class UserService {

    private UserRepository userRepository;

    private AtomicLong userNumbers;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        userNumbers = new AtomicLong(this.userRepository.getHighestUserNumber().orElse(1000L));
    }

    public User createUser(User user) {

        return userRepository.save(new  User(user.getFirstName(), user.getLastName(),
                user.getUserName(), userNumbers.incrementAndGet(), true));



    }

    public Optional<User> getUserByUsernumber(Long id) {
        if(userRepository.findUserByuserNumber(id).isPresent()) {
            System.out.println("hej");
            return userRepository.findUserByuserNumber(id);
        }
       // throw new InvalidInputException("No user by that usernumber!");
        return null;
    }

    public User updateUser(User user) {

        return null;

    }

    private void validateUser(User user) {
        if(user.getUserName().length() < 10) {
            // throw new InvalidUserException("Username cannot be shorter than 10 characters");
        }
    }
}
