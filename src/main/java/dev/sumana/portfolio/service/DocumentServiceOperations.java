package dev.sumana.portfolio.service;

import dev.sumana.portfolio.DocumentRecord;
import java.util.List;

public interface DocumentServiceOperations {
    List<DocumentRecord> findAll();
    DocumentRecord findById(Long id);
}
