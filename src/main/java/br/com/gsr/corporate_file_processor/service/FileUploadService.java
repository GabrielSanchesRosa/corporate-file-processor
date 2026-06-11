package br.com.gsr.corporate_file_processor.service;

import br.com.gsr.corporate_file_processor.config.FileProcessorProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final FileProcessorProperties properties;

    public List<String> upload(List<MultipartFile> files) {
        List<String> accepted = new ArrayList<>();
        Path inputDir = Path.of(properties.getDirectories().getInput());

        for (MultipartFile file : files) {
            try {
                String fileName = file.getOriginalFilename();
                Path destination = inputDir.resolve(fileName);
                Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
                accepted.add(fileName);
                log.info("File uploaded to input directory: {}", fileName);
            } catch (IOException e) {
                log.error("Failed to upload file: {}", file.getOriginalFilename(), e);
            }
        }

        return accepted;
    }
}
