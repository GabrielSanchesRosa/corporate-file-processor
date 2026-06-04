package br.com.gsr.corporate_file_processor.repository.mapper;

import br.com.gsr.corporate_file_processor.entity.ProcessErrorEntity;
import br.com.gsr.corporate_file_processor.model.ProcessError;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProcessErrorRepositoryMapper {

    ProcessError toModel(ProcessErrorEntity processErrorEntity);
    List<ProcessError> toModel(List<ProcessErrorEntity> processErrorEntity);

    ProcessErrorEntity toEntity(ProcessError processError);
}
