package se.group.projektarbete.service;

import org.springframework.stereotype.Service;
import se.group.projektarbete.data.Team;
import se.group.projektarbete.data.User;
import se.group.projektarbete.data.WorkItem;
import se.group.projektarbete.data.workitemenum.Status;
import se.group.projektarbete.repository.TeamRepository;
import se.group.projektarbete.repository.UserRepository;
import se.group.projektarbete.repository.WorkItemRepository;
import se.group.projektarbete.service.exceptions.BadTeamException;
import se.group.projektarbete.service.exceptions.BadUserException;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public final class UserService {

    private final UserRepository userRepository;
    private final WorkItemRepository workItemRepository;
    private final TeamRepository teamRepository;
    private final AtomicLong userNumbers;

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

    public Optional<User> getUserByUserNumber(Long userNumber) {
        return userRepository.findUserByuserNumber(userNumber);
    }

    public boolean updateUser(Long userNumber, User user) {
        validateUser(user);
        Optional<User> users = userRepository.findUserByuserNumber(userNumber);
        if (users.isPresent()) {
            users.get().setFirstName(user.getFirstName());
            users.get().setLastName(user.getLastName());
            users.get().setUserName(user.getUserName());
            users.get().setActive(user.getActive());
            userRepository.save(users.get());
            return true;
        }
        return false;
    }

    public boolean inactivateUser(Long userNumber) {
        Optional<User> users = userRepository.findUserByuserNumber(userNumber);
        if (users.isPresent()) {
            users.get().setActive(false);
            userRepository.save(users.get());
            setWorkItemsToUnstarted(workItemRepository.findAllByUser(users.get()));
            return true;
        }
        return false;
    }

    public List<User> findUsersByFirstNameAndLastNameAndUserName(String firstName, String lastName, String userName) {
        return userRepository.findAll().stream()
                .filter(u ->
                        firstName != null && firstName.equalsIgnoreCase(u.getFirstName()) ||
                                lastName != null && lastName.equalsIgnoreCase(u.getLastName()) ||
                                userName != null && userName.equalsIgnoreCase(u.getUserName()))
                .collect(Collectors.toList());
    }

    public List<User> getAllUsersInTeamByTeamName(String teamName) {
        Optional<Team> team = teamRepository.findByName(teamName);
        if (team.isPresent()) {
            return userRepository.getAllByTeamId(team.get().getId());
        }
        throw new BadTeamException("No team with team name: " + teamName);
    }

    private void setWorkItemsToUnstarted(List<WorkItem> workItems) {
        if (!workItems.isEmpty()) {
            for (int i = 0; i < workItems.size(); i++) {
                workItems.get(i).setStatus(Status.UNSTARTED);
            }
            saveWorkItems(workItems);
        }
    }

    private void saveWorkItems(List<WorkItem> workItems) {
        for (WorkItem workItem : workItems) {
            workItemRepository.save(workItem);
        }
    }

    private void validateUser(User user) {
        if (user.getFirstName() == null || user.getLastName() == null || user.getUserName() == null) {
            throw new BadUserException("All required values for the user has not been assigned");
        }
        if (user.getUserName().length() < 10) {
            throw new BadUserException("Username cannot be shorter than 10 characters");
        }
    }
}
