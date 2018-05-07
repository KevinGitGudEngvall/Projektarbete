package se.group.projektarbete.repository;

import org.springframework.data.repository.CrudRepository;
import se.group.projektarbete.data.Issue;

public interface IssueRepository extends CrudRepository<Issue, Long> {
}
