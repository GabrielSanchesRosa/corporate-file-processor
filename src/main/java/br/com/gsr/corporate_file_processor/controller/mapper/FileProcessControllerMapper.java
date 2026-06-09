package br.com.gsr.corporate_file_processor.controller.mapper;

import br.com.gsr.corporate_file_processor.controller.response.FileProcessResponse;
import br.com.gsr.corporate_file_processor.model.FileProcess;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FileProcessControllerMapper {

    FileProcessResponse toResponse(FileProcess fileProcess);
    List<FileProcessResponse> toResponse(List<FileProcess> fileProcess);
}
