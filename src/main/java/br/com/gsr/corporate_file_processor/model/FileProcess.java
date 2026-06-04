package br.com.gsr.corporate_file_processor.model;

import br.com.gsr.corporate_file_processor.enums.FileProcessStatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class FileProcess {

    private UUID id;
    private String fileName;
    private LocalDateTime receivedAt;
    private LocalDateTime processedAt;
    private FileProcessStatusEnum status;
    private Integer totalRecords;
    private Integer successRecords;
    private Integer failedRecords;
}
