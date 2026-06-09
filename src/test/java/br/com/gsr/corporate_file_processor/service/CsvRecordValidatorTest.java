package br.com.gsr.corporate_file_processor.service;

import br.com.gsr.corporate_file_processor.dto.CsvRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class CsvRecordValidatorTest {

    private final CsvRecordValidator validator = new CsvRecordValidator();

    @Test
    void shouldReturnNoErrorsWhenRecordIsValid() {
        CsvRecord record = CsvRecord.builder()
                .name("John Smith")
                .document("12345678901")
                .email("john@example.com")
                .build();

        List<String> errors = validator.validate(record);

        assertTrue(errors.isEmpty());
    }

    @Test
    void shouldReturnErrorWhenNameIsBlank() {
        CsvRecord record = CsvRecord.builder()
                .name("")
                .document("12345678901")
                .email("john@example.com")
                .build();

        List<String> errors = validator.validate(record);

        assertFalse(errors.isEmpty());
        assertTrue(errors.contains("Field 'name' is required"));
    }

    @Test
    void shouldReturnErrorWhenDocumentIsNull() {
        CsvRecord record = CsvRecord.builder()
                .name("John Smith")
                .document(null)
                .email("john@example.com")
                .build();

        List<String> errors = validator.validate(record);

        assertFalse(errors.isEmpty());
        assertTrue(errors.contains("Field 'document' is required"));
    }

    @Test
    void shouldReturnErrorWhenEmailFormatIsInvalid() {
        CsvRecord record = CsvRecord.builder()
                .name("John Smith")
                .document("12345678901")
                .email("not-an-email")
                .build();

        List<String> errors = validator.validate(record);

        assertFalse(errors.isEmpty());
        assertTrue(errors.contains("Field 'email' has invalid format"));
    }
}
