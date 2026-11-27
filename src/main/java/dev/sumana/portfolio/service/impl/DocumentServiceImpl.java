package dev.sumana.portfolio.service.impl;

import dev.sumana.portfolio.DocumentRecord;
import dev.sumana.portfolio.DocumentService;
import dev.sumana.portfolio.service.DocumentServiceOperations;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImpl implements DocumentServiceOperations {

    private final DocumentService delegate;

    public DocumentServiceImpl(DocumentService delegate) {
        this.delegate = delegate;
    }

    @Override
    public List<DocumentRecord> findAll() {
        return delegate.list();
    }

    @Override
    public DocumentRecord findById(Long id) {
        return delegate.get(id);
    }
}
