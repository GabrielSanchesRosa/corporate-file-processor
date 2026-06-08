package br.com.gsr.corporate_file_processor.notification;

import br.com.gsr.corporate_file_processor.config.FileProcessorProperties;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailNotificationService {

    private final JavaMailSender mailSender;
    private final FileProcessorProperties properties;

    public void sendErrorNotification(String fileName, int failedRecords) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true ,"UTF-8");

            helper.setTo(properties.getNotificationEmail());
            helper.setSubject("[ALERT] Processing errors detected - " + fileName);
            helper.setText("""
                    <h3>Processing Alert</h3>
                    <p>File <strong>%s</strong> was processed with <strong>%d filed record(s)</strong>.</p>
                    <p>Please check the <code>/errors</code> endpoint for details.</p>
                    """.formatted(fileName, failedRecords), true);

            mailSender.send(message);
            log.info("Error notification sent for file: {}", fileName);
        } catch (Exception e) {
            log.error("Failed to send error notification for file: {}", fileName, e);
        }
    }

    public void sendProcessingSummary(String fileName, int total, int success, int failed) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true ,"UTF-8");

            helper.setTo(properties.getNotificationEmail());
            helper.setSubject("[SUMMARY] Processing completed - " + fileName);
            helper.setText("""
                    <h3>Processing Summary</h3>
                    <p>File <strong>%s</strong> has been processed.</p>
                    <ul>
                        <li>Total records: <strong>%d</strong></li>
                        <li>Success: <strong>%d</strong></li>
                        <li>Failed: <strong>%d</strong></li>
                    </ul>
                    """.formatted(fileName, total, success, failed), true);

            mailSender.send(message);
            log.info("Processing summary sent for file: {}", fileName);
        } catch (Exception e) {
            log.error("Failed to send processing summary for file: {}", fileName, e);
        }
    }
}
