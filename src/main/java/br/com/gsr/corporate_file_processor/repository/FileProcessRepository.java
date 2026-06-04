package br.com.gsr.corporate_file_processor.repository;

import br.com.gsr.corporate_file_processor.entity.FileProcessEntity;
import br.com.gsr.corporate_file_processor.model.FileProcess;
import br.com.gsr.corporate_file_processor.repository.mapper.FileProcessRepositoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FileProcessRepository {

    private final FileProcessJpaRepository fileProcessJpaRepository;
    private final FileProcessRepositoryMapper fileProcessRepositoryMapper;

    public FileProcess save(FileProcess fileProcess) {
        FileProcessEntity entity = fileProcessRepositoryMapper.toEntity(fileProcess);

        FileProcess saved = fileProcessRepositoryMapper.toModel(fileProcessJpaRepository.save(entity));

        return saved;
    }

    public void update(FileProcess savedFile) {
        FileProcessEntity entity = fileProcessRepositoryMapper.toEntity(savedFile);

        fileProcessJpaRepository.save(entity);
    }
}
