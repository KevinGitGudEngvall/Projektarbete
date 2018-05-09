package se.group.projektarbete.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import se.group.projektarbete.data.Issue;
import se.group.projektarbete.repository.IssueRepository;

@Service
@Component
public final class IssueService {

    private final IssueRepository repository;

    public IssueService(IssueRepository repository) {
        this.repository = repository;

    }

    public Issue createIssue(Issue issue) {
        return repository.save(issue);

    }
}


