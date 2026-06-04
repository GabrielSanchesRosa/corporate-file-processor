package br.com.gsr.corporate_file_processor.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Dashboard {

    private Long totalFiles;
    private Long totalErrors;
    private Double averageProcessingTimeInSeconds;
    private Long totalRecordsProcessed;
}
