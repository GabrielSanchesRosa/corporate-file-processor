package br.com.gsr.corporate_file_processor.controller;

import br.com.gsr.corporate_file_processor.controller.mapper.FileProcessControllerMapper;
import br.com.gsr.corporate_file_processor.controller.response.FileProcessResponse;
import br.com.gsr.corporate_file_processor.service.FileProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileProcessController {

    private final FileProcessService fileProcessService;
    private final FileProcessControllerMapper fileProcessControllerMapper;

    @GetMapping
    public List<FileProcessResponse> findAll() {
        List<FileProcessResponse> response = fileProcessControllerMapper.toResponse(fileProcessService.findAll());

        return response;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FileProcessResponse> findById(@PathVariable UUID id) {
        return fileProcessService.findById(id)
                .map(fileProcessControllerMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
