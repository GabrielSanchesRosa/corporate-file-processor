package br.com.gsr.corporate_file_processor.controller;

import br.com.gsr.corporate_file_processor.controller.mapper.ProcessErrorControllerMapper;
import br.com.gsr.corporate_file_processor.controller.response.ProcessErrorResponse;
import br.com.gsr.corporate_file_processor.service.ProcessErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/errors")
@RequiredArgsConstructor
public class ProcessErrorController {

    private final ProcessErrorService processErrorService;
    private final ProcessErrorControllerMapper processErrorControllerMapper;

    @GetMapping
    public List<ProcessErrorResponse> findAll() {
        return processErrorControllerMapper.toResponse(processErrorService.findAll());
    }
}
