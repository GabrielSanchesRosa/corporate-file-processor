package br.com.gsr.corporate_file_processor.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UploadResponse {

    private List<String> accepted;
    private int count;
}
