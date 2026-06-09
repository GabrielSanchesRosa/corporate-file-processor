package br.com.gsr.corporate_file_processor.service;

import br.com.gsr.corporate_file_processor.model.Dashboard;
import br.com.gsr.corporate_file_processor.repository.FileProcessRepository;
import br.com.gsr.corporate_file_processor.repository.ProcessErrorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final FileProcessRepository fileProcessRepository;
    private final ProcessErrorRepository processErrorRepository;

    public Dashboard getDashboard() {
        Dashboard dashboard = Dashboard.builder()
                .totalFiles(fileProcessRepository.count())
                .totalErrors(processErrorRepository.countErrors())
                .averageProcessingTimeInSeconds(fileProcessRepository.avgProcessingTime())
                .totalRecordsProcessed(fileProcessRepository.sumSuccessRecords())
                .build();

        return dashboard;
    }
}
