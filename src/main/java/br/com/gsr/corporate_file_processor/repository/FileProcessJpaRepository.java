package br.com.gsr.corporate_file_processor.repository;

import br.com.gsr.corporate_file_processor.entity.FileProcessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FileProcessJpaRepository extends JpaRepository<FileProcessEntity, UUID> {
}
