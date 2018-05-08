package se.group.projektarbete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.group.projektarbete.data.User;
import se.group.projektarbete.data.WorkItem;
import se.group.projektarbete.data.workitemenum.Status;
import se.group.projektarbete.repository.UserRepository;
import se.group.projektarbete.repository.WorkItemRepository;

@SpringBootApplication
public class ProjektarbeteApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProjektarbeteApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;

	@Autowired
	WorkItemRepository workItemRepository;

	@Override
	public void run(String... args) throws Exception {
		User user1 = userRepository.save(new User("1", "1","1", 1000L, true));
		User user2 = userRepository.save(new User("2", "2","2", 1001L, true));
		User user3 = userRepository.save(new User("3", "3","3", 1002L, true));
		User user4 = userRepository.save(new User("4", "4","4", 1003L, true));

		WorkItem workItem1 = workItemRepository.save(new WorkItem("W1", "W1", Status.UNSTARTED, user1));
		WorkItem workItem2 = workItemRepository.save(new WorkItem("W2", "W2", Status.UNSTARTED, user2));
		WorkItem workItem3 = workItemRepository.save(new WorkItem("W3", "W3", Status.UNSTARTED, user3));
		WorkItem workItem4 = workItemRepository.save(new WorkItem("W4", "W4", Status.UNSTARTED, user4));
	}
}
