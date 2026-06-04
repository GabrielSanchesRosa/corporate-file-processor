package br.com.gsr.corporate_file_processor.repository.mapper;

import br.com.gsr.corporate_file_processor.entity.CustomerEntity;
import br.com.gsr.corporate_file_processor.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerRepositoryMapper {

    Customer toModel(CustomerEntity customerEntity);
    CustomerEntity toEntity(Customer customer);
}
