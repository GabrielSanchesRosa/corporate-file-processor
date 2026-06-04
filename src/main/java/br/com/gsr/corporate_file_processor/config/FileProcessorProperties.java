package br.com.gsr.corporate_file_processor.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "file-processor")
public class FileProcessorProperties {

    private Directories directories;

    @Data
    public static class Directories {
        private String input;
        private String processed;
        private String error;
    }
}
