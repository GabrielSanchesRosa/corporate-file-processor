package br.com.gsr.corporate_file_processor.repository;

import br.com.gsr.corporate_file_processor.entity.ProcessErrorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProcessErrorJpaRepository extends JpaRepository<ProcessErrorEntity, UUID> {
}
