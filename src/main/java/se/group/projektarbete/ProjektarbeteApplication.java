package se.group.projektarbete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.group.projektarbete.data.Issue;
import se.group.projektarbete.data.Team;
import se.group.projektarbete.data.User;
import se.group.projektarbete.data.WorkItem;
import se.group.projektarbete.data.workitemenum.Status;
import se.group.projektarbete.repository.IssueRepository;
import se.group.projektarbete.repository.TeamRepository;
import se.group.projektarbete.repository.UserRepository;
import se.group.projektarbete.repository.WorkItemRepository;
import se.group.projektarbete.service.IssueService;
import se.group.projektarbete.service.TeamService;
import se.group.projektarbete.service.UserService;

@SpringBootApplication
public class ProjektarbeteApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjektarbeteApplication.class, args);
    }
}
