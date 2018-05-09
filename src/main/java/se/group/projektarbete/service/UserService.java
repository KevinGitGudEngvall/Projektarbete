package se.group.projektarbete.service;

import org.springframework.stereotype.Service;
import se.group.projektarbete.data.User;
import se.group.projektarbete.repository.UserRepository;
import se.group.projektarbete.repository.WorkItemRepository;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public final class UserService {

    private UserRepository userRepository;
    private WorkItemRepository workItemRepository;

    private AtomicLong userNumbers;

    public UserService(UserRepository userRepository, WorkItemRepository workItemRepository) {
        this.userRepository = userRepository;
        this.workItemRepository = workItemRepository;
        userNumbers = new AtomicLong(this.userRepository.getHighestUserNumber().orElse(1000L));
    }

    public User createUser(User user) {
        validateUser(user);
        return userRepository.save(new User(user.getFirstName(), user.getLastName(),
                user.getUserName(), userNumbers.incrementAndGet()));

    }

    public Optional<User> getUserByUsernumber(Long userNumber) {
        return userRepository.findUserByuserNumber(userNumber);
    }

    public Boolean updateUser(Long userNumber, User user) {
        validateUser(user);
        if (userRepository.findUserByuserNumber(userNumber).isPresent()) {
            Optional<User> users = userRepository.findUserByuserNumber(userNumber);
            users.get().updateUser(user);
            userRepository.save(users.get());
            return true;
        }
        return false;
    }

    public Boolean inactivateUser(Long userNumber) {
        if (userRepository.findUserByuserNumber(userNumber).isPresent()) {
            Optional<User> users = userRepository.findUserByuserNumber(userNumber);
            users.get().setToInactive(users.get());
            userRepository.save(users.get());
            return true;
        }
        return false;
    }

    private void validateUser(User user) {
        if (user.getUserName().length() < 10) {
            throw new InvalidInputException("Username cannot be shorter than 10 characters");
        }
    }
}
