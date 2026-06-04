package br.com.gsr.corporate_file_processor.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FileProcessRepository {

    private final FileProcessJpaRepository fileProcessJpaRepository;
}
