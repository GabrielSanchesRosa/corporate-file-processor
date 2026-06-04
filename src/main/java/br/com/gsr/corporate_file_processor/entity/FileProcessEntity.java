package br.com.gsr.corporate_file_processor.entity;

import br.com.gsr.corporate_file_processor.enums.FileProcessStatusEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "file_process")
public class FileProcessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "file_name", length = 255, nullable = false)
    private String fileName;

    @Column(name = "received_at", nullable = false)
    private LocalDateTime receivedAt;

    @Column(name = "processed_at", nullable = true)
    private LocalDateTime processedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50, nullable = false)
    private FileProcessStatusEnum status;

    @Column(name = "total_records", nullable = true)
    private Integer totalRecords;

    @Column(name = "success_records", nullable = true)
    private Integer successRecords;

    @Column(name = "failed_records", nullable = true)
    private Integer failedRecords;
}
