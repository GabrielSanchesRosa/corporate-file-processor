package br.com.gsr.corporate_file_processor.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Customer {

    private UUID id;
    private String name;
    private String document;
    private String email;
}
