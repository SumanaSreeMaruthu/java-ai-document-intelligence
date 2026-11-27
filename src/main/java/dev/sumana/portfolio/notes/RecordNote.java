package dev.sumana.portfolio.notes;
import jakarta.persistence.*;
import java.time.Instant;
@Entity
@Table(name="record_notes")
public class RecordNote {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String resourceType;
 @Column(nullable=false) private Long resourceId;
 @Column(nullable=false,length=4000) private String body;
 @Column(nullable=false) private Instant createdAt;
 protected RecordNote(){}
 public RecordNote(String type,Long id,String body){this.resourceType=type;this.resourceId=id;this.body=body;this.createdAt=Instant.now();}
 public Long getId(){return id;}
 public String getResourceType(){return resourceType;}
 public Long getResourceId(){return resourceId;}
 public String getBody(){return body;}
 public Instant getCreatedAt(){return createdAt;}
}