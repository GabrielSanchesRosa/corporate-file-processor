package br.com.gsr.corporate_file_processor.repository.mapper;

import br.com.gsr.corporate_file_processor.entity.FileProcessEntity;
import br.com.gsr.corporate_file_processor.model.FileProcess;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FileProcessRepositoryMapper {

    FileProcess toModel(FileProcessEntity fileProcessEntity);
    FileProcessEntity toEntity(FileProcess fileProcess);
}
