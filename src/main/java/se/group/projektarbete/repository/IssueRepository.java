package se.group.projektarbete.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import se.group.projektarbete.data.Issue;

import java.util.List;

public interface IssueRepository extends PagingAndSortingRepository<Issue, Long> {

    List<Issue> findAll();

    @Override
    Page<Issue> findAll(Pageable pageable);

}