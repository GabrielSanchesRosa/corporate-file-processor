package br.com.gsr.corporate_file_processor.repository;

import br.com.gsr.corporate_file_processor.entity.CustomerEntity;
import br.com.gsr.corporate_file_processor.model.Customer;
import br.com.gsr.corporate_file_processor.repository.mapper.CustomerRepositoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;
    private final CustomerRepositoryMapper customerRepositoryMapper;

    public Customer save(Customer customer) {
        CustomerEntity entity = customerRepositoryMapper.toEntity(customer);

        Customer saved = customerRepositoryMapper.toModel(customerJpaRepository.save(entity));

        return saved;
    }
}
