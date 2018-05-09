package se.group.projektarbete.service;

import org.springframework.stereotype.Service;
import se.group.projektarbete.data.Issue;
import se.group.projektarbete.data.WorkItem;
import se.group.projektarbete.repository.IssueRepository;
import se.group.projektarbete.repository.UserRepository;
import se.group.projektarbete.repository.WorkItemRepository;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public final class WorkItemService {

    private final WorkItemRepository workItemRepository;
    private final IssueRepository issueRepository;

    public WorkItemService(WorkItemRepository workItemRepository, IssueRepository issueRepository) {
        this.workItemRepository = workItemRepository;
        this.issueRepository = issueRepository;
    }
    public boolean removeWorkItem(Long id) {
        if (workItemRepository.existsById(id)) {
            workItemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void addIssueToWorkItem(Long id, Issue issue){
        workItemRepository.findById(id).ifPresent(w -> {
            issue.setWorkItem(w);
            issueRepository.save(issue);
        });
    }

    public WorkItem createWorkItem(WorkItem workItem) {
       // Exception hanterare för att se till att ett workitem har all nödvändig input
        return workItemRepository.save(workItem);
    }
}
