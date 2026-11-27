package dev.sumana.portfolio;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(readOnly = true)
public class DocumentService {

    private final DocumentRecordRepository repository;
    private final GeminiClient gemini;

    public DocumentService(DocumentRecordRepository repository, GeminiClient gemini) {
        this.repository = repository;
        this.gemini = gemini;
    }

    public List<DocumentRecord> list() {
        return repository.findAll();
    }

    public DocumentRecord get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Record not found"));
    }

    @Transactional
    public DocumentRecord create(ApiController.CreateRequest request) {
        return repository.save(new DocumentRecord(request.name(), request.text()));
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record not found");
        }
        repository.deleteById(id);
    }

    public String ask(Long id, String question) {
        return gemini.answer(get(id).getText(), question);
    }
    public List<DocumentRecord> search(String value) {
        if (value == null || value.isBlank()) {
            return repository.findAll();
        }
        return repository.findByNameContainingIgnoreCase(value.trim());
    }
}
