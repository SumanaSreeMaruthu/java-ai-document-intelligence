package dev.sumana.portfolio;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/documents")
public class ApiController {

    public record Request(@NotBlank String documentName) {
    }

    public record Response(String id, String status, String result, Instant createdAt) {
    }

    private final Map<String, Response> items = new ConcurrentHashMap<>();

    @PostMapping
    public Response create(@Valid @RequestBody Request request) {
        String id = UUID.randomUUID().toString();
        Response response = new Response(
                id,
                "CREATED",
                "Received: " + request.documentName(),
                Instant.now());
        items.put(id, response);
        return response;
    }

    @GetMapping
    public Collection<Response> list() {
        return items.values();
    }

    @GetMapping("/{id}")
    public Response get(@PathVariable String id) {
        Response response = items.get(id);
        if (response == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return response;
    }
}
