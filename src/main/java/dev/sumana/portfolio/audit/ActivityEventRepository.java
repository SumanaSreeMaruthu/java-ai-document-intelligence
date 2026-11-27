package dev.sumana.portfolio.audit;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityEventRepository extends JpaRepository<ActivityEvent, Long> {

    List<ActivityEvent> findTop50ByOrderByCreatedAtDesc();

    List<ActivityEvent> findByResourceTypeAndResourceIdOrderByCreatedAtDesc(
            String resourceType, Long resourceId);
}
