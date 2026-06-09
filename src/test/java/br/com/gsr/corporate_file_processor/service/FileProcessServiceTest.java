package br.com.gsr.corporate_file_processor.service;

import br.com.gsr.corporate_file_processor.enums.FileProcessStatusEnum;
import br.com.gsr.corporate_file_processor.model.FileProcess;
import br.com.gsr.corporate_file_processor.repository.FileProcessRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FileProcessServiceTest {

    @Mock
    private FileProcessRepository fileProcessRepository;

    @InjectMocks
    private FileProcessService fileProcessService;

    @Test
    void shouldReturnAllFileProcesses() {
        FileProcess fileProcess = FileProcess.builder()
                .id(UUID.randomUUID())
                .fileName("test.csv")
                .status(FileProcessStatusEnum.COMPLETED)
                .build();

        when(fileProcessRepository.findAll()).thenReturn(List.of(fileProcess));

        List<FileProcess> result = fileProcessService.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("test.csv", result.get(0).getFileName());
    }

    @Test
    void shouldReturnFileProcessById() {
        UUID id = UUID.randomUUID();
        FileProcess fileProcess = FileProcess.builder()
                .id(id)
                .fileName("test.csv")
                .status(FileProcessStatusEnum.COMPLETED)
                .build();

        when(fileProcessRepository.findById(id)).thenReturn(Optional.of(fileProcess));

        Optional<FileProcess> result = fileProcessService.findById(id);

        assertTrue(result.isPresent());
        assertEquals("test.csv", result.get().getFileName());
    }

    @Test
    void shouldReturnEmptyWhenFileProcessNotFound() {
        UUID id = UUID.randomUUID();
        when(fileProcessRepository.findById(id)).thenReturn(Optional.empty());

        Optional<FileProcess> result = fileProcessService.findById(id);

        assertTrue(result.isEmpty());
    }
}
