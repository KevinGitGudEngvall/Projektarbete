package se.group.projektarbete.repository;

import org.springframework.data.repository.CrudRepository;
import se.group.projektarbete.data.WorkItem;

public interface WorkItemRepository extends CrudRepository<WorkItem, Long> {
}
