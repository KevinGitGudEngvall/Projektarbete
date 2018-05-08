package se.group.projektarbete.service;

import org.springframework.stereotype.Service;
import se.group.projektarbete.data.User;
import se.group.projektarbete.repository.UserRepository;

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

        User newUser = userRepository.save(new  User(user.getFirstName(), user.getLastName(),
                user.getUserName(), userNumbers.incrementAndGet(), true));

        return newUser;


    }

    public User updateUser(User user) {

        return null;

    }
}
