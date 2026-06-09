package br.com.gsr.corporate_file_processor.service;

import br.com.gsr.corporate_file_processor.model.ProcessError;
import br.com.gsr.corporate_file_processor.repository.ProcessErrorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcessErrorService {

    private final ProcessErrorRepository processErrorRepository;

    public List<ProcessError> findAll() {
        return processErrorRepository.findAll();
    }
}
