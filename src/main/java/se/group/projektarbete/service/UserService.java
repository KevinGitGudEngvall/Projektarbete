package se.group.projektarbete.service;

import org.springframework.stereotype.Service;
import se.group.projektarbete.data.User;
import se.group.projektarbete.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
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
        validateUser(user);
        return userRepository.save(new User(user.getFirstName(), user.getLastName(),
                user.getUserName(), userNumbers.incrementAndGet(), true));

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
            //users.get().setWorkItems(Status.UNSTARTED);
            userRepository.save(users.get());
            return true;
        }
        return false;
    }

    public List<User> getUserByFirstNameAndOrLastNameAndOrUserName(String searchString,String searchString2, String searchString3) {
        List<User> users = userRepository.findAll();
        List<User> searchResult = new ArrayList<>();
        List<String> searchStringList = new ArrayList<>();

        searchStringList.add(searchString);
        searchStringList.add(searchString2);
        searchStringList.add(searchString3);
        for (int j = 0; j<searchStringList.size(); j++)
            for(int i = 0; i < users.size(); i++){

                if(users.get(i).getFirstName().equalsIgnoreCase(searchStringList.get(j))){
                    searchResult.add(users.get(i));
                }

                else if (users.get(i).getLastName().equalsIgnoreCase(searchStringList.get(j))){
                    searchResult.add(users.get(i));
                }

                else if (users.get(i).getUserName().equalsIgnoreCase(searchStringList.get(j))){
                    searchResult.add(users.get(i));
                }

                else if(users.get(i).getFirstName().equalsIgnoreCase(searchStringList.get(j)) && users.get(i).getLastName().equalsIgnoreCase(searchStringList.get(j))){
                    searchResult.add(users.get(i));
                }

                else if(users.get(i).getFirstName().equalsIgnoreCase(searchStringList.get(j)) && users.get(i).getUserName().equalsIgnoreCase(searchStringList.get(j))){
                    searchResult.add(users.get(i));
                }

                else if(users.get(i).getLastName().equalsIgnoreCase(searchStringList.get(j)) && users.get(i).getUserName().equalsIgnoreCase(searchStringList.get(j))){
                    searchResult.add(users.get(i));
                }
        }
        return searchResult;
    }

    private void validateUser(User user) {
        if (user.getUserName().length() < 10) {
            throw new InvalidInputException("Username cannot be shorter than 10 characters");
        }
    }
}
