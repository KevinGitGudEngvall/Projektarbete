package se.group.projektarbete.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Issue> getAllIssues(Pageable pageable) { return issueRepository.findAll(pageable); }

    public boolean updateIssue(Long id, Issue issue) {
        Optional<Issue> result = issueRepository.findById(id);
        if (result.isPresent()) {
            result.get().setDescription(issue.getDescription());
            issueRepository.save(result.get());
            return true;
        }
        return false;
    }
}