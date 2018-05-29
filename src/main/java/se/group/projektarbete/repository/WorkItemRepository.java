package se.group.projektarbete.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import se.group.projektarbete.data.User;
import se.group.projektarbete.data.WorkItem;
import se.group.projektarbete.data.workitemenum.Status;

import java.util.List;

public interface WorkItemRepository extends PagingAndSortingRepository<WorkItem, Long> {

    List<WorkItem> findAllByUser(User user);

    List<WorkItem> findAll();

    @Override
    Page<WorkItem> findAll(Pageable pageable);

    List<WorkItem> findWorkItemsByUserId(Long userId);

    List<WorkItem> findWorkItemsByStatus(Status status);
}