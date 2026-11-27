package dev.sumana.portfolio.audit;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "activity_events")
public class ActivityEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String eventType;

    @Column(nullable = false)
    private String resourceType;

    private Long resourceId;

    @Column(nullable = false)
    private Instant createdAt;

    protected ActivityEvent() {
    }

    public ActivityEvent(String eventType, String resourceType, Long resourceId) {
        this.eventType = eventType;
        this.resourceType = resourceType;
        this.resourceId = resourceId;
        this.createdAt = Instant.now();
    }

    public Long getId() { return id; }
    public String getEventType() { return eventType; }
    public String getResourceType() { return resourceType; }
    public Long getResourceId() { return resourceId; }
    public Instant getCreatedAt() { return createdAt; }
}
