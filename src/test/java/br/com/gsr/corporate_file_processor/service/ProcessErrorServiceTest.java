package br.com.gsr.corporate_file_processor.service;

import br.com.gsr.corporate_file_processor.model.ProcessError;
import br.com.gsr.corporate_file_processor.repository.ProcessErrorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProcessErrorServiceTest {

    @Mock
    private ProcessErrorRepository processErrorRepository;

    @InjectMocks
    private ProcessErrorService processErrorService;

    @Test
    void shouldReturnAllProcessErrors() {
        ProcessError processError = ProcessError.builder()
                .id(UUID.randomUUID())
                .lineNumber(2)
                .errorMessage("Field 'name' is required")
                .createdAt(LocalDateTime.now())
                .build();

        when(processErrorRepository.findAll()).thenReturn(List.of(processError));

        List<ProcessError> result = processErrorService.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Field 'name' is required", result.get(0).getErrorMessage());
    }

    @Test
    void shouldReturnEmptyListWhenNoErrors() {
        when(processErrorRepository.findAll()).thenReturn(List.of());

        List<ProcessError> result = processErrorService.findAll();

        assertTrue(result.isEmpty());
    }
}
