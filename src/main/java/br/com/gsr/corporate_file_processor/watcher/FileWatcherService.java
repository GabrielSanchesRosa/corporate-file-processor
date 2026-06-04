package br.com.gsr.corporate_file_processor.watcher;

import br.com.gsr.corporate_file_processor.config.FileProcessorProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.nio.file.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileWatcherService implements ApplicationListener<ApplicationReadyEvent>{

    private final FileProcessorProperties properties;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Path inputDir = Path.of(properties.getDirectories().getInput());
        Path processedDir = Path.of(properties.getDirectories().getProcessed());
        Path errorDir = Path.of(properties.getDirectories().getError());

        try {
            Files.createDirectories(inputDir);
            Files.createDirectories(processedDir);
            Files.createDirectories(errorDir);

            WatchService watchService = FileSystems.getDefault().newWatchService();
            inputDir.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

            Thread.ofVirtual().start(() -> {
               WatchKey key;

               try {
                   while ((key = watchService.take()) != null) {
                       for (WatchEvent<?> watchEvent : key.pollEvents()) {
                           Path fileName = (Path) watchEvent.context();
                           log.info("New file detected: {}", fileName);
                       }
                       key.reset();
                   }
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
