package dev.sumana.portfolio;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/documents")
public class ApiController {

    public record CreateRequest(@NotBlank String name, @NotBlank String text) {}
    public record QuestionRequest(@NotBlank String question) {}

    private final DocumentService service;

    public ApiController(DocumentService service) {
        this.service = service;
    }

    @GetMapping
    public List<DocumentRecord> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public DocumentRecord get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DocumentRecord create(@Valid @RequestBody CreateRequest request) {
        return service.create(request);
    }

    @PostMapping("/{id}/ask")
    public Map<String, String> ask(@PathVariable Long id, @Valid @RequestBody QuestionRequest request) {
        return Map.of("answer", service.ask(id, request.question()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
    @GetMapping("/search")
    public List<DocumentRecord> search(@RequestParam(required = false) String query) {
        return service.search(query);
    }
}
