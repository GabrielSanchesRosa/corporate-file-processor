package br.com.gsr.corporate_file_processor.controller.mapper;

import br.com.gsr.corporate_file_processor.controller.response.ProcessErrorResponse;
import br.com.gsr.corporate_file_processor.model.ProcessError;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProcessErrorControllerMapper {

    ProcessErrorResponse toResponse(ProcessError processError);
    List<ProcessErrorResponse> toResponse(List<ProcessError> processError);
}
