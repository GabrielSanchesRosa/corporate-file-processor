package br.com.gsr.corporate_file_processor.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "process_error")
public class ProcessErrorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "file_process_id", nullable = false)
    private FileProcessEntity fileProcess;

    @Column(name = "line_number", nullable = false)
    private Integer lineNumber;

    @Column(name = "error_message", length = 1000, nullable = false)
    private String errorMessage;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
