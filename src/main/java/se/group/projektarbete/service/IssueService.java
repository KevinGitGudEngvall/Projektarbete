package se.group.projektarbete.service;


import org.springframework.stereotype.Service;
import se.group.projektarbete.data.Issue;
import se.group.projektarbete.repository.IssueRepository;
import se.group.projektarbete.repository.WorkItemRepository;



@Service
public final class IssueService {

    private final IssueRepository issueRepository;
    private final WorkItemRepository workItemRepository;

    public IssueService(IssueRepository issueRepository, WorkItemRepository workItemRepository) {
        this.issueRepository = issueRepository;
        this.workItemRepository = workItemRepository;
    }

    public Issue createIssue(Issue issue) {
        return issueRepository.save(issue);

    }

    public Boolean updateIssue(Long id, Issue issue) {
        if(issueRepository.findById(id).isPresent()) {
            issue.updateIssue(issue);
            return true;
        }
        return false;
    }

}


