package br.com.gsr.corporate_file_processor.service;

import br.com.gsr.corporate_file_processor.model.FileProcess;
import br.com.gsr.corporate_file_processor.repository.FileProcessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileProcessService {

    private final FileProcessRepository fileProcessRepository;

    public List<FileProcess> findAll() {
        return fileProcessRepository.findAll();
    }

    public Optional<FileProcess> findById(UUID id) {
        return fileProcessRepository.findById(id);
    }
}
