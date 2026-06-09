package br.com.gsr.corporate_file_processor.service;

import br.com.gsr.corporate_file_processor.dto.CsvRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CsvReaderServiceTest {

    private final CsvReaderService csvReaderService = new CsvReaderService();

    @Test
    void shouldParseValidCsvFile() throws IOException {
        Path tempFile = Files.createTempFile("test", ".csv");
        Files.writeString(tempFile, "name;document;email\nJohn Smith;12345678901;john@example.com\n");

        List<CsvRecord> records = csvReaderService.read(tempFile);

        assertEquals(1, records.size());
        assertEquals("John Smith", records.get(0).getName());
        assertEquals("12345678901", records.get(0).getDocument());
        assertEquals("john@example.com", records.get(0).getEmail());
        assertEquals(2, records.get(0).getLineNumber());

        Files.deleteIfExists(tempFile);
    }

}
