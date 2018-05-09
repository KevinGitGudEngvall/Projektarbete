package se.group.projektarbete.service;

import org.springframework.stereotype.Service;
import se.group.projektarbete.data.Team;
import se.group.projektarbete.data.User;
import se.group.projektarbete.data.WorkItem;
import se.group.projektarbete.repository.TeamRepository;
import se.group.projektarbete.repository.UserRepository;
import se.group.projektarbete.repository.WorkItemRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public final class UserService {

    private UserRepository userRepository;
    private WorkItemRepository workItemRepository;
    private TeamRepository teamRepository;
    private AtomicLong userNumbers;

    public UserService(UserRepository userRepository, WorkItemRepository workItemRepository, TeamRepository teamRepository) {
        this.userRepository = userRepository;
        this.workItemRepository = workItemRepository;
        this.teamRepository = teamRepository;
        userNumbers = new AtomicLong(this.userRepository.getHighestUserNumber().orElse(1000L));
    }

    public User createUser(User user) {
        validateUser(user);
        return userRepository.save(new User(
                user.getFirstName(),
                user.getLastName(),
                user.getUserName(),
                userNumbers.incrementAndGet()));
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
            users.get().setActive(false);
            userRepository.save(users.get());
            List<WorkItem> workItems = workItemRepository.findAllByUser(users.get());
            setWorkItemsToUnstarted(workItems, users.get());
            return true;
        }
        return false;
    }

    public List<User> findUsersByFirstNameAndLastNameAndUserName(String firstName, String lastName, String userName) {
        List<User> users;
        if (lastName == null && userName == null) {
            users = userRepository.getUsersByFirstName(firstName);
        } else if (userName == null)
            users = userRepository.getUsersByFirstNameAndLastName(firstName, lastName);
        else {
            users = userRepository.getUsersByFirstNameAndLastNameAndUserName(firstName, lastName, userName);
        }
        if (users.isEmpty()) {
            throw new InvalidInputException("No users with those parameters.");
        }
        return users;
    }

    public List<User> findAllUsersAtTeamByTeamName(String teamName) {
        Optional<Team> team = teamRepository.findByName(teamName);

       if(team.isPresent()) {
            return userRepository.getAllByTeamId(team.get().getId());
        }
        throw new InvalidInputException("No team with that name: " + teamName);
    }

    private void setWorkItemsToUnstarted(List<WorkItem> workItems, User user) {
        if (!workItems.isEmpty()) {
            user.setWorkItemsToUnstarted(workItems);
            saveWorkItems(workItems);
        }
    }

    private void saveWorkItems(List<WorkItem> workItems) {
        for (int i = 0; i < workItems.size(); i++) {
            workItemRepository.save(workItems.get(i));
        }
    }

    private void validateUser(User user) {
        if (user.getUserName().length() < 10) {
            throw new InvalidInputException("Username cannot be shorter than 10 characters");
        }
    }
}
