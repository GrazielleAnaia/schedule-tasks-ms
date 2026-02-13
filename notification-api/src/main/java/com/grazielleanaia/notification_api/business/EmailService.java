package com.grazielleanaia.notification_api.business;


import com.grazielleanaia.notification_api.business.dto.TaskDTO;
import com.grazielleanaia.notification_api.infrastructure.exception.EmailException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor

public class EmailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Value("${send.mail.remetent}")
    private String remetent;

    @Value("${send.mail.remetentName}")
    private String remetentName;

    public void sendEmail(TaskDTO taskDTO) {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
            mimeMessageHelper.setFrom(new InternetAddress(remetent, remetentName));
            mimeMessageHelper.setTo(InternetAddress.parse(taskDTO.getCustomerEmail()));
            mimeMessageHelper.setSubject("Task Notification");

            Context context = new Context();
            context.setVariable("taskName", taskDTO.getTaskName());
            context.setVariable("eventDate", taskDTO.getEventDate());
            context.setVariable("description", taskDTO.getDescription());

            String template = templateEngine.process("notification", context);
            mimeMessageHelper.setText(template, true);
            javaMailSender.send(message);

        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new EmailException("Error to send email", e.getCause());
        }
    }

}
