package dev.sumana.portfolio.notes;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
@ExtendWith(MockitoExtension.class)
class RecordNoteServiceTest {
 @Mock RecordNoteRepository repository;
 @InjectMocks RecordNoteService service;
 @Test void listReturnsNotes(){when(repository.findByResourceTypeAndResourceIdOrderByCreatedAtDesc("document",1L)).thenReturn(List.of());assertTrue(service.list("document",1L).isEmpty());}
 @Test void blankNoteRejected(){assertThrows(ResponseStatusException.class,()->service.add("document",1L," "));}
}