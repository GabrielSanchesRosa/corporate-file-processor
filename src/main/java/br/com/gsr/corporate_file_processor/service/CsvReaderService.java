package br.com.gsr.corporate_file_processor.service;

import br.com.gsr.corporate_file_processor.dto.CsvRecord;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Service
public class CsvReaderService {

    public List<CsvRecord> read(Path filePath) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Files.newInputStream(filePath), StandardCharsets.UTF_8))) {
            bufferedReader.mark(1);

            if (bufferedReader.read() != '\uFEFF') {
                bufferedReader.reset();
            }

            CsvToBean<CsvRecord> csvToBean = new CsvToBeanBuilder<CsvRecord>(bufferedReader)
                    .withType(CsvRecord.class)
                    .withSeparator(';')
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<CsvRecord> records = csvToBean.parse();

            IntStream.range(0, records.size()).forEach(i -> records.get(i).setLineNumber(i + 2));

            return records;
        }
    }
}
