package dev.sumana.portfolio.notes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RecordNoteRepository extends JpaRepository<RecordNote,Long> {
 List<RecordNote> findByResourceTypeAndResourceIdOrderByCreatedAtDesc(String resourceType,Long resourceId);
}