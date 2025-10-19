package com.example.shoppingcart.common;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@AllArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendSimpleEmail(String to, String subject, String body) {
        log.info("Sending email to: {}", to);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("shoppingcart@gmail.com");

        mailSender.send(message);
        log.info("Email sent successfully");
    }

    public void sendHtmlEmail(String to, String subject, String htmlBody) throws MessagingException {
        log.info("Sending email to: {}", to);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        helper.setFrom("shoppingcart@gmail.com");

        mailSender.send(message);
        log.info("Email sent successfully");
    }

    public void sentEmailWithAttachment(String to, String subject, String body, File file) throws MessagingException {
        log.info("Sending email with attachment to: {}", to);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true);
        helper.setFrom("shoppingcart@gmail.com");

        helper.addAttachment(file.getName(), file);
        mailSender.send(message);
        log.info("Email with attachment sent successfully");
    }

}
