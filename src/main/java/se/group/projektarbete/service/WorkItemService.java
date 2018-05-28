package se.group.projektarbete.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se.group.projektarbete.data.Issue;
import se.group.projektarbete.data.User;
import se.group.projektarbete.data.WorkItem;
import se.group.projektarbete.data.workitemenum.Status;
import se.group.projektarbete.repository.IssueRepository;
import se.group.projektarbete.repository.UserRepository;
import se.group.projektarbete.repository.WorkItemRepository;
import se.group.projektarbete.service.exceptions.BadIssueException;
import se.group.projektarbete.service.exceptions.BadTeamException;
import se.group.projektarbete.service.exceptions.BadUserException;
import se.group.projektarbete.service.exceptions.BadWorkitemException;

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

    public void addIssueToWorkItem(Long id, Issue issue) {
        validateWorkItem(id);
        workItemRepository.findById(id).ifPresent(w -> {
            w.setStatus(Status.UNSTARTED);
            issue.setWorkItem(w);
            w.setIssue(issue);
            issueRepository.save(issue);
            workItemRepository.save(w);
        });
    }

    public WorkItem createWorkItem(WorkItem workItem) {
        if (workItem.getName() == null || workItem.getDescription() == null) {
            throw new BadWorkitemException("All required values for the workItem has not been assigned");
        }
        return workItemRepository.save(new WorkItem(workItem.getName(), workItem.getDescription()));
    }

    public boolean setStatusOnWorkItem(Long id, String status) {
        Optional<WorkItem> workItems = workItemRepository.findById(id);
        if (workItems.isPresent()) {
            validate(status);
            workItems.get().setStatus(Status.valueOf(status));
            workItemRepository.save(workItems.get());
            return true;
        }
        return false;
    }

    public Optional<WorkItem> getWorkItem(Long id) {
        return workItemRepository.findById(id);
    }

    //public Page<WorkItem> getAllWorkItems(Pageable pageable) { return workItemRepository.findAll(pageable); }

    public boolean deleteWorkItem(Long id) {
        if (workItemRepository.existsById(id)) {
            workItemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void addWorkItemByUserId(Long workItemId, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<WorkItem> workItem = workItemRepository.findById(workItemId);
        if (!user.isPresent()) {
            throw new BadUserException("No User with that id");
        } else if (!workItem.isPresent()) {
            throw new BadWorkitemException("No Workitem with that id");
        } else if (user.get().getWorkItems().size() > 4) {
            throw new BadWorkitemException("To many Workitems for that user");
        } else if (!user.get().getActive()) {
            throw new BadUserException("User is not active");
        }
        user.get().setWorkItems(workItem.get());
        workItemRepository.save(workItem.get());
    }

    public List<WorkItem> getAllWorkItemsByTeamId(Long teamId) {
        List<User> users = userRepository.findUsersByTeamId(teamId);
        if (users.isEmpty()) {
            throw new BadTeamException("No users for team with id: " + teamId);
        }
        return workItemRepository.findAll().stream()
                .filter(w -> users.stream().anyMatch(u -> u.getWorkItems().stream().anyMatch(wu -> wu.getId().equals(w.getId()))))
                .collect(Collectors.toList());
    }

    public List<WorkItem> getAllWorkItemsByUserId(Long userId) {
        List<WorkItem> workItems = workItemRepository.findWorkItemsByUserId(userId);
        if (workItems.isEmpty()) {
            throw new BadWorkitemException("No workitems for user with id: " + userId);
        }
        return workItems;
    }

    public List<WorkItem> getAllWorkItemsByDescription(String description) {
        List<WorkItem> workItems = workItemRepository.findAll().stream()
                .filter(w -> w.getDescription().contains(description))
                .collect(Collectors.toList());
        if (workItems.isEmpty()) {
            throw new BadWorkitemException("No workitems with description: " + description);
        }
        return workItems;
    }

    public List<WorkItem> getAllWorkItemsWithIssues() {
        List<WorkItem> workItems = workItemRepository.findAll().stream()
                .filter(w -> issueRepository.findAll().stream()
                        .anyMatch(i -> i.getWorkItem().getId().equals(w.getId()))).collect(Collectors.toList());
        if (workItems.isEmpty()) {
            throw new BadWorkitemException("No workitems found with issues");
        }
        return workItems;
    }

    public List<WorkItem> getAllWorkItemsByStatus(Status status) {
        List<WorkItem> workItems = workItemRepository.findWorkItemsByStatus(status);
        if (workItems.isEmpty()) {
            throw new BadWorkitemException("No workitems with status: " + status);
        }
        return workItems;
    }

    private void validateWorkItem(Long id) {
        if (!workItemRepository.findById(id).isPresent()) {
            throw new BadWorkitemException("No workitem was found with id: " + id);
        }
        if (!workItemRepository.findById(id).get().getStatus().toString().equals("DONE")) {
            throw new BadIssueException("Cant add an issue to a workitem that is not DONE");
        }
    }

    private void validate(String status) {
        if (!status.equals("STARTED") && !status.equals("UNSTARTED") && !status.equals("DONE")) {
            throw new BadWorkitemException("status=? , do not contain DONE, STARTED or UNSTARTED");
        }
    }
}