package br.com.gsr.corporate_file_processor.service;

import br.com.gsr.corporate_file_processor.dto.CsvRecord;
import br.com.gsr.corporate_file_processor.enums.FileProcessStatusEnum;
import br.com.gsr.corporate_file_processor.model.Customer;
import br.com.gsr.corporate_file_processor.model.FileProcess;
import br.com.gsr.corporate_file_processor.model.ProcessError;
import br.com.gsr.corporate_file_processor.repository.CustomerRepository;
import br.com.gsr.corporate_file_processor.repository.FileProcessRepository;
import br.com.gsr.corporate_file_processor.repository.ProcessErrorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileProcessingService {

    private final CsvReaderService csvReaderService;
    private final CsvRecordValidator csvRecordValidator;
    private final FileProcessRepository fileProcessRepository;
    private final CustomerRepository customerRepository;
    private final ProcessErrorRepository processErrorRepository;

    public void process(Path filePath) throws IOException {
        log.info("Starting processing of file: {}", filePath.getFileName());

        int successRecords = 0;
        int failedRecords = 0;

        FileProcess fileProcess = buildFileProcess(filePath);

        FileProcess savedFile = fileProcessRepository.save(fileProcess);

        List<CsvRecord> records = csvReaderService.read(filePath);

        savedFile.setStatus(FileProcessStatusEnum.PROCESSING);

        fileProcessRepository.update(savedFile);

        for (CsvRecord record : records) {
            List<String> errors = csvRecordValidator.validate(record);

            if (errors.isEmpty()) {
                log.info("Valid record - line {}: {}", record.getLineNumber(), record.getName());

                Customer customer = buildCustomer(record);

                customerRepository.save(customer);
                successRecords++;
            } else {
                String msg = String.format("Invalid record - line %d: %s", record.getLineNumber(), errors);
                log.warn(msg);

                ProcessError processError = buildProcessError(record, savedFile, msg);

                processErrorRepository.save(processError);
                failedRecords++;
            }
        }

        savedFile.setProcessedAt(LocalDateTime.now());

        savedFile.setStatus(
                successRecords == 0 ? FileProcessStatusEnum.FAILED :
                failedRecords > 0 ? FileProcessStatusEnum.COMPLETED_WITH_ERRORS :
                FileProcessStatusEnum.COMPLETED);

        savedFile.setTotalRecords(records.size());
        savedFile.setSuccessRecords(successRecords);
        savedFile.setFailedRecords(failedRecords);

        fileProcessRepository.update(savedFile);

        log.info("Finished processing of file: {}", filePath.getFileName());
    }

    private FileProcess buildFileProcess(Path filePath) {
        FileProcess f = FileProcess.builder()
                .fileName(filePath.getFileName().toString())
                .receivedAt(LocalDateTime.now())
                .status(FileProcessStatusEnum.RECEIVED)
                .build();

        return f;
    }

    private Customer buildCustomer(CsvRecord csvRecord) {
        Customer c = Customer.builder()
                .name(csvRecord.getName())
                .document(csvRecord.getDocument())
                .email(csvRecord.getEmail())
                .build();

        return c;
    }

    private ProcessError buildProcessError(CsvRecord csvRecord, FileProcess file, String errorMessage) {
        ProcessError p = ProcessError.builder()
                .fileProcess(file)
                .lineNumber(csvRecord.getLineNumber())
                .errorMessage(errorMessage)
                .createdAt(LocalDateTime.now())
                .build();

        return p;
    }
}
