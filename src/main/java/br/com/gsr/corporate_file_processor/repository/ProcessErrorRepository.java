package br.com.gsr.corporate_file_processor.repository;

import br.com.gsr.corporate_file_processor.entity.ProcessErrorEntity;
import br.com.gsr.corporate_file_processor.model.ProcessError;
import br.com.gsr.corporate_file_processor.repository.mapper.ProcessErrorRepositoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProcessErrorRepository {

    private final ProcessErrorJpaRepository processErrorJpaRepository;
    private final ProcessErrorRepositoryMapper processErrorRepositoryMapper;

    public ProcessError save(ProcessError processError) {
        ProcessErrorEntity entity = processErrorRepositoryMapper.toEntity(processError);

        ProcessError saved = processErrorRepositoryMapper.toModel(processErrorJpaRepository.save(entity));

        return saved;
    }
}
