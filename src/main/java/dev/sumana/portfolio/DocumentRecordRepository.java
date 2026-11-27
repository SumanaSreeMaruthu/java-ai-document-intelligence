package dev.sumana.portfolio;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRecordRepository extends JpaRepository<DocumentRecord, Long> {
    java.util.List<DocumentRecord> findByNameContainingIgnoreCase(String value);
}
