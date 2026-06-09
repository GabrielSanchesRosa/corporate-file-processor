package br.com.gsr.corporate_file_processor.controller.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"totalFiles", "totalErrors", "averageProcessingTimeInSeconds", "totalRecordsProcessed"})
public class DashboardResponse {

    private Long totalFiles;
    private Long totalErrors;
    private Double averageProcessingTimeInSeconds;
    private Long totalRecordsProcessed;
}
