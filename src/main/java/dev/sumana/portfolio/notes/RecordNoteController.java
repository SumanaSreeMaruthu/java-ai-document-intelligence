package dev.sumana.portfolio.notes;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/notes")
public class RecordNoteController {
 public record AddNoteRequest(@NotBlank @Size(max=4000) String body){}
 private final RecordNoteService service;
 public RecordNoteController(RecordNoteService service){this.service=service;}
 @GetMapping("/{type}/{id}")
 public List<RecordNote> list(@PathVariable String type,@PathVariable Long id){return service.list(type,id);}
 @PostMapping("/{type}/{id}")
 @ResponseStatus(HttpStatus.CREATED)
 public RecordNote add(@PathVariable String type,@PathVariable Long id,@Valid @RequestBody AddNoteRequest request){return service.add(type,id,request.body());}
 @DeleteMapping("/{id}")
 @ResponseStatus(HttpStatus.NO_CONTENT)
 public void delete(@PathVariable Long id){service.delete(id);}
}