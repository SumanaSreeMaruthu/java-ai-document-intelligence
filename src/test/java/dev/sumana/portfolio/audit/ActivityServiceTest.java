package dev.sumana.portfolio.audit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ActivityServiceTest {

    @Mock
    private ActivityEventRepository repository;

    @InjectMocks
    private ActivityService service;

    @Test
    void recentReturnsNewestEvents() {
        when(repository.findTop50ByOrderByCreatedAtDesc()).thenReturn(List.of());
        assertEquals(0, service.recent().size());
    }

    @Test
    void historyUsesResourceTypeAndId() {
        when(repository.findByResourceTypeAndResourceIdOrderByCreatedAtDesc("Case", 10L))
                .thenReturn(List.of());
        service.history("Case", 10L);
        verify(repository).findByResourceTypeAndResourceIdOrderByCreatedAtDesc("Case", 10L);
    }
}
