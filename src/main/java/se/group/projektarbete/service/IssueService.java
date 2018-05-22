package se.group.projektarbete.service;

import org.springframework.stereotype.Service;
import se.group.projektarbete.data.Issue;
import se.group.projektarbete.repository.IssueRepository;

import java.util.Optional;

@Service
public final class IssueService {

    private final IssueRepository issueRepository;

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public boolean updateIssue(Long id, Issue issue) {
        if (issueRepository.findById(id).isPresent()) {
            Optional<Issue> result = issueRepository.findById(id);
            result.get().setDescription(issue.getDescription());
            issueRepository.save(result.get());
            return true;
        }
        return false;
    }
}