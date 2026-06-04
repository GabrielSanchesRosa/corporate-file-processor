package br.com.gsr.corporate_file_processor.controller.response;

import br.com.gsr.corporate_file_processor.enums.FileProcessStatusEnum;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@JsonPropertyOrder({"id", "fileName", "receivedAt", "processedAt", "status", "totalRecords", "successRecords", "failedRecords"})
public class FileProcessResponse {

    private UUID id;
    private String fileName;
    private LocalDateTime receivedAt;
    private LocalDateTime processedAt;
    private FileProcessStatusEnum status;
    private Integer totalRecords;
    private Integer successRecords;
    private Integer failedRecords;
}
