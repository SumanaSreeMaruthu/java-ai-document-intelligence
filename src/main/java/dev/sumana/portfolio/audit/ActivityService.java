package dev.sumana.portfolio.audit;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActivityService {

    private final ActivityEventRepository repository;

    public ActivityService(ActivityEventRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ActivityEvent record(String action, String resourceType, Long resourceId) {
        return repository.save(new ActivityEvent(action, resourceType, resourceId));
    }

    @Transactional(readOnly = true)
    public List<ActivityEvent> recent() {
        return repository.findTop50ByOrderByCreatedAtDesc();
    }

    @Transactional(readOnly = true)
    public List<ActivityEvent> history(String resourceType, Long resourceId) {
        return repository.findByResourceTypeAndResourceIdOrderByCreatedAtDesc(
                resourceType, resourceId);
    }
}
