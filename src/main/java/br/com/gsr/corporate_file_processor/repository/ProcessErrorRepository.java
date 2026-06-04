package br.com.gsr.corporate_file_processor.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProcessErrorRepository {

    private final ProcessErrorJpaRepository processErrorJpaRepository;
}
