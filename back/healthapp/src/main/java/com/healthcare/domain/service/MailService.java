package com.healthcare.domain.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.healthcare.domain.model.entity.Appointment;

@RequiredArgsConstructor
@Component
public class MailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Value("${email.username}")
    private String email;

    public void sendMail(String[] sendTo, String subject, Appointment appointment) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        Context context = new Context();
        context.setVariable("medic", appointment.getMedic());
        context.setVariable("patient", appointment.getPatient());
        context.setVariable("appointment", appointment);
        context.setVariable("status", appointment.getStatus().toString());
        helper.setFrom(email);
        helper.setTo(sendTo);
        helper.setSubject(subject);
        String content = templateEngine.process("mailTemplate", context);
        helper.setText(content, true);
        javaMailSender.send(message);
    }
}
