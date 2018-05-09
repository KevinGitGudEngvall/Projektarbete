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

@SpringBootApplication
public class ProjektarbeteApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProjektarbeteApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;

	@Autowired
	WorkItemRepository workItemRepository;

	@Autowired
	TeamRepository teamRepository;

	@Autowired
	TeamService teamService;

	@Override
	public void run(String... args) throws Exception {

		User user1 = userRepository.save(new User("Senad", "Hasic","SenadHasic", 1000L));
		User user2 = userRepository.save(new User("Kevin", "Engvall","KevinEngvall", 1001L));
		User user3 = userRepository.save(new User("Semi", "Turdean","SemiTurdean", 1002L));
		User user4 = userRepository.save(new User("Ian", "Lord","IanLord", 1003L));

		WorkItem workItem1 = workItemRepository.save(new WorkItem("W1", "W1", Status.UNSTARTED, user1));
		WorkItem workItem2 = workItemRepository.save(new WorkItem("W2", "W2", Status.UNSTARTED, user2));
		WorkItem workItem3 = workItemRepository.save(new WorkItem("W3", "W3", Status.UNSTARTED, user3));
		WorkItem workItem4 = workItemRepository.save(new WorkItem("W4", "W4", Status.UNSTARTED, user4));

		Team team1 = teamRepository.save(new Team("a-team", true));
		user1.setTeam(team1);
		userRepository.save(user1);
	}

}
