package br.com.gsr.corporate_file_processor.controller;

import br.com.gsr.corporate_file_processor.controller.response.UploadResponse;
import br.com.gsr.corporate_file_processor.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
public class UploadController {

    private final FileUploadService fileUploadService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UploadResponse> upload(@RequestParam("files") List<MultipartFile> files) {
        List<String> accepted = fileUploadService.upload(files);
        return ResponseEntity.accepted().body(new UploadResponse(accepted, accepted.size()));
    }
}
