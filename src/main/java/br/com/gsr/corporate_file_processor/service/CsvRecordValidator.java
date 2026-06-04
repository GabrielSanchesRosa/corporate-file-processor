package br.com.gsr.corporate_file_processor.service;

import br.com.gsr.corporate_file_processor.dto.CsvRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CsvRecordValidator {

    public List<String> validate(CsvRecord csvRecord) {
        List<String> errors = new ArrayList<>();

        if (csvRecord.getName() == null || csvRecord.getName().isBlank()) {
            errors.add("Field 'name' is required");
        }

        if (csvRecord.getDocument() == null || csvRecord.getDocument().isBlank()) {
            errors.add("Field 'document' is required");
        }

        if (csvRecord.getEmail() != null && !csvRecord.getEmail().isBlank()) {
            if (!csvRecord.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                errors.add("Field 'email' has invalid format");
            }
        }

        return errors;
    }
}
