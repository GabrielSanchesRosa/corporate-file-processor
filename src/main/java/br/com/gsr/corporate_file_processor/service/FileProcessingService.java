package br.com.gsr.corporate_file_processor.service;

import br.com.gsr.corporate_file_processor.dto.CsvRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileProcessingService {

    private final CsvReaderService csvReaderService;
    private final CsvRecordValidator csvRecordValidator;

    public void process(Path filePath) throws IOException {
        log.info("Starting processing of file: {}", filePath.getFileName());

        List<CsvRecord> records = csvReaderService.read(filePath);

        for (CsvRecord record : records) {
            List<String> errors = csvRecordValidator.validate(record);

            if (errors.isEmpty()) {
                log.info("Valid record - line {}: {}", record.getLineNumber(), record.getName());
            } else {
                log.warn("Invalid record - line {}: {}", record.getLineNumber(), errors);
            }
        }

        log.info("Finished processing of file: {}", filePath.getFileName());
    }
}
