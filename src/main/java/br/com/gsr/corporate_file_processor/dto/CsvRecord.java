package br.com.gsr.corporate_file_processor.dto;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CsvRecord {

    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName(column = "document")
    private String document;

    @CsvBindByName(column = "email")
    private String email;

    @CsvIgnore
    private Integer lineNumber;
}
