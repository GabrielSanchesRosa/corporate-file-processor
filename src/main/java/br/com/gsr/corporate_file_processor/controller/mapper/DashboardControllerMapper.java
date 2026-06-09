package br.com.gsr.corporate_file_processor.controller.mapper;

import br.com.gsr.corporate_file_processor.controller.response.DashboardResponse;
import br.com.gsr.corporate_file_processor.model.Dashboard;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DashboardControllerMapper {

    DashboardResponse toResponse(Dashboard dashboard);
}
