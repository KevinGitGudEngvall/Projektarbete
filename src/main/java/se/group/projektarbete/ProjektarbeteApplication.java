package se.group.projektarbete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.group.projektarbete.data.Team;
import se.group.projektarbete.data.User;
import se.group.projektarbete.data.WorkItem;
import se.group.projektarbete.data.workitemenum.Status;
import se.group.projektarbete.repository.TeamRepository;
import se.group.projektarbete.repository.UserRepository;
import se.group.projektarbete.repository.WorkItemRepository;
import se.group.projektarbete.service.TeamService;
import se.group.projektarbete.service.UserService;

@SpringBootApplication
public class ProjektarbeteApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProjektarbeteApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@Autowired
	WorkItemRepository workItemRepository;

	@Autowired
	TeamRepository teamRepository;

	@Autowired
	TeamService teamService;

	@Override
	public void run(String... args) throws Exception {

		User user1 = userRepository.save(new User("1", "1","1", 1000L));
		User user2 = userRepository.save(new User("2", "2","2", 1001L));
		User user3 = userRepository.save(new User("3", "3","3", 1002L));
		User user4 = userRepository.save(new User("4", "4","4", 1003L));

		WorkItem workItem1 = workItemRepository.save(new WorkItem("W1", "W1", Status.STARTED, user1));
		WorkItem workItem2 = workItemRepository.save(new WorkItem("W2", "W2", Status.STARTED, user1));
		WorkItem workItem3 = workItemRepository.save(new WorkItem("W3", "W3", Status.UNSTARTED, user3));
		WorkItem workItem4 = workItemRepository.save(new WorkItem("W4", "W4", Status.UNSTARTED, user4));

		Team team = teamRepository.save(new Team("dev", true));

		team.setUser(user1);
		team.setUser(user2);

		userRepository.save(user1);
		userRepository.save(user2);
		teamRepository.save(team);
	}


}
