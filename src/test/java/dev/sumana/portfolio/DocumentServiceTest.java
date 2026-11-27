package dev.sumana.portfolio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
class DocumentServiceTest {

    @Mock
    private DocumentRecordRepository repository;

    @Mock
    private GeminiClient gemini;

    @InjectMocks
    private DocumentService service;

    @Test
    void listReturnsRepositoryResults() {
        when(repository.findAll()).thenReturn(List.of());
        assertEquals(0, service.list().size());
    }

    @Test
    void getThrowsWhenRecordIsMissing() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> service.get(99L));
    }
}
