package br.com.gsr.corporate_file_processor.service;

import br.com.gsr.corporate_file_processor.config.FileProcessorProperties;
import br.com.gsr.corporate_file_processor.dto.CsvRecord;
import br.com.gsr.corporate_file_processor.enums.FileProcessStatusEnum;
import br.com.gsr.corporate_file_processor.model.FileProcess;
import br.com.gsr.corporate_file_processor.notification.EmailNotificationService;
import br.com.gsr.corporate_file_processor.repository.CustomerRepository;
import br.com.gsr.corporate_file_processor.repository.FileProcessRepository;
import br.com.gsr.corporate_file_processor.repository.ProcessErrorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FileProcessingServiceTest {

    @Mock
    private FileProcessorProperties properties;

    @Mock
    private FileProcessorProperties.Directories directories;

    @Mock
    private CsvReaderService csvReaderService;

    @Mock
    private CsvRecordValidator csvRecordValidator;

    @Mock
    private FileProcessRepository fileProcessRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ProcessErrorRepository processErrorRepository;

    @Mock
    private EmailNotificationService emailNotificationService;

    @InjectMocks
    private FileProcessingService fileProcessingService;

    @Test
    void shouldProcessFileWithAllValidRecords() throws IOException {
        Path tempFile = Files.createTempFile("test", ".csv");

        CsvRecord record = CsvRecord.builder()
                .name("John Smith")
                .document("12345678901")
                .email("john@example.com")
                .lineNumber(2)
                .build();

        FileProcess savedFileProcess = FileProcess.builder()
                        .id(UUID.randomUUID())
                        .fileName(tempFile.getFileName().toString())
                        .status(FileProcessStatusEnum.RECEIVED)
                        .build();

        when(csvReaderService.read(tempFile)).thenReturn(List.of(record));
        when(csvRecordValidator.validate(record)).thenReturn(List.of());
        when(fileProcessRepository.save(any())).thenReturn(savedFileProcess);
        when(properties.getDirectories()).thenReturn(directories);
        when(directories.getProcessed()).thenReturn(tempFile.getParent().toString());

        fileProcessingService.process(tempFile);

        verify(customerRepository, times(1)).save(any());
        verify(processErrorRepository, never()).save(any());
        verify(fileProcessRepository, times(2)).update(any());
    }

    @Test
    void shouldProcessFileWithAllInvalidRecords() throws Exception {
        Path tempFile = Files.createTempFile("test", ".csv");

        CsvRecord record = CsvRecord.builder()
                .name("")
                .document(null)
                .email("invalid")
                .lineNumber(2)
                .build();

        FileProcess savedFileProcess = FileProcess.builder()
                .id(UUID.randomUUID())
                .fileName(tempFile.getFileName().toString())
                .status(FileProcessStatusEnum.RECEIVED)
                .build();

        when(csvReaderService.read(tempFile)).thenReturn(List.of(record));
        when(csvRecordValidator.validate(record)).thenReturn(List.of("Field 'name' is required", "Field 'document' is required"));
        when(fileProcessRepository.save(any())).thenReturn(savedFileProcess);
        when(properties.getDirectories()).thenReturn(directories);
        when(directories.getError()).thenReturn(tempFile.getParent().toString());

        fileProcessingService.process(tempFile);

        verify(customerRepository, never()).save(any());
        verify(processErrorRepository, times(1)).save(any());
        verify(emailNotificationService, times(1)).sendErrorNotification(any(), anyInt());
        verify(fileProcessRepository, times(2)).update(any());
    }

    @Test
    void shouldProcessFileWithMixedRecords() throws Exception {
        Path tempFile = Files.createTempFile("test", ".csv");

        CsvRecord validRecord = CsvRecord.builder()
                .name("John Smith")
                .document("1234567890")
                .email("john@example.com")
                .lineNumber(2)
                .build();

        CsvRecord invalidRecord = CsvRecord.builder()
                .name("")
                .document("null")
                .lineNumber(3)
                .build();

        FileProcess savedFileProcess = FileProcess.builder()
                .id(UUID.randomUUID())
                .fileName(tempFile.getFileName().toString())
                .status(FileProcessStatusEnum.RECEIVED)
                .build();

        when(csvReaderService.read(tempFile)).thenReturn(List.of(validRecord, invalidRecord));
        when(csvRecordValidator.validate(validRecord)).thenReturn(List.of());
        when(csvRecordValidator.validate(invalidRecord)).thenReturn(List.of("Field 'name' is required"));
        when(fileProcessRepository.save(any())).thenReturn(savedFileProcess);
        when(properties.getDirectories()).thenReturn(directories);
        when(directories.getProcessed()).thenReturn(tempFile.getParent().toString());

        fileProcessingService.process(tempFile);

        verify(customerRepository, times(1)).save(any());
        verify(processErrorRepository, times(1)).save(any());
        verify(emailNotificationService, times(1)).sendErrorNotification(any(), anyInt());
        verify(emailNotificationService, times(1)).sendProcessingSummary(any(), anyInt(), anyInt(), anyInt());
        verify(fileProcessRepository, times(2)).update(any());
    }
}
