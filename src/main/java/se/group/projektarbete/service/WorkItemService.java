package se.group.projektarbete.service;

import org.springframework.stereotype.Service;
import se.group.projektarbete.data.WorkItem;
import se.group.projektarbete.repository.UserRepository;
import se.group.projektarbete.repository.WorkItemRepository;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public final class WorkItemService {

    private final WorkItemRepository workItemRepository;

    public WorkItemService(WorkItemRepository workItemRepository) {
        this.workItemRepository = workItemRepository;
    }
    public boolean removeWorkItem(Long id) {
        if (workItemRepository.existsById(id)) {
            workItemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public WorkItem createWorkItem(WorkItem workItem) {
       // Exception hanterare för att se till att ett workitem har all nödvändig input
        return workItemRepository.save(workItem);
    }
}
