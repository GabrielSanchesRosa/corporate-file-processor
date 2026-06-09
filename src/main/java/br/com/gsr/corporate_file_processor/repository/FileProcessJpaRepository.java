package br.com.gsr.corporate_file_processor.repository;

import br.com.gsr.corporate_file_processor.entity.FileProcessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FileProcessJpaRepository extends JpaRepository<FileProcessEntity, UUID> {

    @Query(value = "SELECT SUM(success_records) FROM file_process", nativeQuery = true)
    Long sumSuccessRecords();

    @Query(value = "SELECT AVG(EXTRACT(EPOCH FROM (processed_at - received_at))) FROM file_process WHERE processed_at IS NOT NULL", nativeQuery = true)
    Double avgProcessingTime();
}
