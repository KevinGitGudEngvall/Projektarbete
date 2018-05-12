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

		User user1 = userRepository.save(new User("Senad", "Hasic","SenadHasic", 1000L));
		User user2 = userRepository.save(new User("Ian", "McLovin","IanMcLovin", 1001L));
		User user3 = userRepository.save(new User("Kevin", "Engvall","KevinEngvall", 1002L));
		User user4 = userRepository.save(new User("Semi", "Turdean","SemiTurdean", 1003L));
		User user5 = userRepository.save(new User("Johan", "Chefen","JohanChefen", 1004L));
		User user6 = userRepository.save(new User("Java", "Pro","JavaPro", 1005L));
		User user7 = userRepository.save(new User("SQL", "Pro","SQLPro", 1006L));
		User user8 = userRepository.save(new User("C", "Pro","CPro", 1007L));

		WorkItem workItem1 = workItemRepository.save(new WorkItem("WorkItem number one", "WorkItemNumberOne", Status.STARTED, user1));
		WorkItem workItem2 = workItemRepository.save(new WorkItem("W2", "W2", Status.STARTED, user1));
		WorkItem workItem3 = workItemRepository.save(new WorkItem("W3", "W3", Status.UNSTARTED, user1));
		WorkItem workItem4 = workItemRepository.save(new WorkItem("W4", "W4", Status.UNSTARTED, user1));
		WorkItem workItem5 = workItemRepository.save(new WorkItem("W5", "W5", Status.UNSTARTED, user2));
		WorkItem workItem6 = workItemRepository.save(new WorkItem("W6", "W6", Status.UNSTARTED, user2));
		WorkItem workItem7 = workItemRepository.save(new WorkItem("W7", "W7", Status.UNSTARTED, user2));
		WorkItem workItem8 = workItemRepository.save(new WorkItem("W8", "W8", Status.UNSTARTED, user2));

		Team team = teamRepository.save(new Team("dev", true));
		Team team2 = teamRepository.save(new Team("production", true));

		team.setUser(user1);
		team2.setUser(user2);
		team2.setUser(user3);
		user3.setActive(false);
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		teamRepository.save(team);
	}


}
