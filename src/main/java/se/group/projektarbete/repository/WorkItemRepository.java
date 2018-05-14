package se.group.projektarbete.repository;

import org.springframework.data.repository.CrudRepository;
import se.group.projektarbete.data.User;
import se.group.projektarbete.data.WorkItem;
import se.group.projektarbete.data.workitemenum.Status;

import java.util.List;
import java.util.Optional;

public interface WorkItemRepository extends CrudRepository<WorkItem, Long> {

    List<WorkItem> findAllByUser(User user);
    List<WorkItem> findAll();
}
