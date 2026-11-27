package dev.sumana.portfolio.notes;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
@Service
public class RecordNoteService {
 private final RecordNoteRepository repository;
 public RecordNoteService(RecordNoteRepository repository){this.repository=repository;}
 @Transactional(readOnly=true)
 public List<RecordNote> list(String type,Long id){validate(type,id);return repository.findByResourceTypeAndResourceIdOrderByCreatedAtDesc(type,id);}
 @Transactional
 public RecordNote add(String type,Long id,String body){validate(type,id);if(body==null||body.isBlank()||body.length()>4000)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid note");return repository.save(new RecordNote(type,id,body.trim()));}
 @Transactional
 public void delete(Long id){if(!repository.existsById(id))throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Note not found");repository.deleteById(id);}
 private void validate(String type,Long id){if(type==null||!type.matches("[a-zA-Z][a-zA-Z0-9_-]{0,63}")||id==null||id<=0)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid resource");}
}