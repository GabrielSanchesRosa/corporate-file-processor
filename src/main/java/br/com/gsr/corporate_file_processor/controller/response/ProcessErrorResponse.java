package br.com.gsr.corporate_file_processor.controller.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@JsonPropertyOrder({"id", "lineNumber", "errorMessage", "createdAt", "fileProcess"})
public class ProcessErrorResponse {

    private UUID id;
    private Integer lineNumber;
    private String errorMessage;
    private LocalDateTime createdAt;
    private FileProcessResponse fileProcess;
}
