package dev.sumana.portfolio.audit;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/activity")
public class ActivityController {

    private final ActivityService service;

    public ActivityController(ActivityService service) {
        this.service = service;
    }

    @GetMapping
    public List<ActivityEvent> recent() {
        return service.recent();
    }

    @GetMapping("/{resourceType}/{resourceId}")
    public List<ActivityEvent> history(@PathVariable String resourceType,
            @PathVariable Long resourceId) {
        return service.history(resourceType, resourceId);
    }
}
