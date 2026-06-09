package br.com.gsr.corporate_file_processor.service;

import br.com.gsr.corporate_file_processor.model.Dashboard;
import br.com.gsr.corporate_file_processor.repository.FileProcessRepository;
import br.com.gsr.corporate_file_processor.repository.ProcessErrorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DashboardServiceTest {

    @Mock
    private FileProcessRepository fileProcessRepository;

    @Mock
    private ProcessErrorRepository processErrorRepository;

    @InjectMocks
    private DashboardService dashboardService;

    @Test
    void shouldReturnDashboardWithCorrectMetrics() {
        when(fileProcessRepository.count()).thenReturn(10L);
        when(processErrorRepository.countErrors()).thenReturn(5L);
        when(fileProcessRepository.avgProcessingTime()).thenReturn(0.5);
        when(fileProcessRepository.sumSuccessRecords()).thenReturn(100L);

        Dashboard result = dashboardService.getDashboard();

        assertEquals(10L, result.getTotalFiles());
        assertEquals(5L, result.getTotalErrors());
        assertEquals(0.5, result.getAverageProcessingTimeInSeconds());
        assertEquals(100L, result.getTotalRecordsProcessed());
    }
}
