package dev.sumana.portfolio;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/documents")
public class ApiController {

    public record CreateRequest(@NotBlank String name, @NotBlank String text) {
    }

    public record QuestionRequest(@NotBlank String question) {
    }

    private final DocumentRecordRepository repository;
    private final GeminiClient gemini;

    public ApiController(DocumentRecordRepository repository, GeminiClient gemini) {
        this.repository = repository;
        this.gemini = gemini;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DocumentRecord create(@Valid @RequestBody CreateRequest request) {
        return repository.save(new DocumentRecord(request.name(), request.text()));
    }

    @GetMapping
    public List<DocumentRecord> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public DocumentRecord get(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
    }

    @PostMapping("/{id}/ask")
    public Map<String, String> ask(@PathVariable Long id,
            @Valid @RequestBody QuestionRequest request) {
        return Map.of("answer", gemini.answer(get(id).getText(), request.question()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        repository.deleteById(id);
    }
}
