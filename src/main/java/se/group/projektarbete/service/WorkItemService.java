package se.group.projektarbete.service;

import org.springframework.stereotype.Service;
import se.group.projektarbete.data.Issue;
import se.group.projektarbete.data.User;
import se.group.projektarbete.data.WorkItem;
import se.group.projektarbete.data.workitemenum.Status;
import se.group.projektarbete.repository.IssueRepository;
import se.group.projektarbete.repository.UserRepository;
import se.group.projektarbete.repository.WorkItemRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public final class WorkItemService {

    private final WorkItemRepository workItemRepository;
    private final IssueRepository issueRepository;
    private final UserRepository userRepository;

    public WorkItemService(WorkItemRepository workItemRepository, IssueRepository issueRepository, UserRepository userRepository) {
        this.workItemRepository = workItemRepository;
        this.issueRepository = issueRepository;
        this.userRepository = userRepository;
    }

    public boolean removeWorkItem(Long id) {
        if (workItemRepository.existsById(id)) {
            workItemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void addIssueToWorkItem(Long id, Issue issue) {
        workItemRepository.findById(id).ifPresent(w -> {
            w.setIssue(issue);
            w.setStatus(Status.UNSTARTED);
            workItemRepository.save(w);
            issue.setWorkItem(w);
            issueRepository.save(issue);
        });
    }

    public WorkItem createWorkItem(WorkItem workItem) {
        // Exception hanterare för att se till att ett workitem har all nödvändig input
        return workItemRepository.save(workItem);
    }


    public void addWorkItemByUserId(Long workItemId, Long userId) {
        List<WorkItem> workItems = workItemRepository.findAll().stream().filter(w -> w.getUser().getId().equals(userId))
                .collect(Collectors.toList());

        Optional<User> user = userRepository.findById(userId);
        Optional<WorkItem> workItem = workItemRepository.findById(workItemId);

        if (!user.isPresent()) {
            throw new InvalidInputException("No User with that id");

        } else if (!workItem.isPresent()) {
            throw new InvalidInputException("No Workitem with that id");

        } else if (workItems.stream().anyMatch(w -> w.getId().equals(workItemId))) {
            throw new InvalidInputException("That workitem is alerady assigned to that user.");

        } else if (workItems.size() > 4) {
            throw new InvalidInputException("To many Workitems for that user");

        } else if (!user.get().getActive()) {
            throw new InvalidInputException("user is not active");
        }

        user.get().setWorkItems(workItem.get());
        workItemRepository.save(workItem.get());
    }


    public List<WorkItem> findAllWorkItemsByTeamId(Long teamId) {
        List<User> users = userRepository.findUsersByTeamId(teamId);
        if (users.isEmpty()) {
            throw new InvalidInputException("No workitems for that teamid.");
        }
        return workItemRepository.findAll().stream()
                .filter(w -> w.getUser().getId().equals(users.listIterator().next().getId()))
                .collect(Collectors.toList());
    }

    public List<WorkItem> findAllWorkItemsByUserId(Long userId) {
        List<WorkItem> workItems = workItemRepository.findAll().stream()
                .filter(w -> w.getUser().getId().equals(userId))
                .collect(Collectors.toList());
        if (workItems.isEmpty()) {
            throw new InvalidInputException("No workitems for that userid.");
        }
        return workItems;
    }

    public List<WorkItem> findAllWorkItemsByDescription(String description) {
        List<WorkItem> workItems = workItemRepository.findAll().stream()
                .filter(w -> w.getDescription().contains(description))
                .collect(Collectors.toList());
        if (workItems.isEmpty()) {
            throw new InvalidInputException("No workitems with that description");
        }
        return workItems;
    }

    public List<WorkItem> getAllWorkItemsWithIssues() {

        List<WorkItem> workItems = workItemRepository.findAll().stream()
                .filter(w -> w.getIssue().getId() != null)
                .collect(Collectors.toList());
        if(workItems.isEmpty()){
            throw new InvalidInputException(("No issues"));

        }

        return workItems;

    }
}




