package br.com.gsr.corporate_file_processor.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ProcessError {

    private UUID id;
    private FileProcess fileProcess;
    private Integer lineNumber;
    private String errorMessage;
    private LocalDateTime createdAt;
}
