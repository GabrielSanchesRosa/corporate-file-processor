package br.com.gsr.corporate_file_processor.notification;

import br.com.gsr.corporate_file_processor.config.FileProcessorProperties;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmailNotificationServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private FileProcessorProperties properties;

    @Mock
    private FileProcessorProperties.Directories directories;

    @InjectMocks
    private EmailNotificationService emailNotificationService;

    @Test
    void shouldSendErrorNotification() throws Exception {
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        when(properties.getNotificationEmail()).thenReturn("test@example.com");

        emailNotificationService.sendErrorNotification("file.csv", 3);

        verify(mailSender, times(1)).send(mimeMessage);
    }

    @Test
    void shouldSendProcessingSummary() throws Exception {
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        when(properties.getNotificationEmail()).thenReturn("test@example.com");

        emailNotificationService.sendProcessingSummary("file.csv", 10, 7, 3);

        verify(mailSender, times(1)).send(mimeMessage);
    }

    @Test
    void shouldLogErrorWhenEmailFails() {
        when(mailSender.createMimeMessage()).thenThrow(new RuntimeException("SMTP error"));

        assertDoesNotThrow(() -> emailNotificationService.sendErrorNotification("file.csv", 3));
    }
}
