package com.healthcare.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RequiredArgsConstructor
@Component
public class MailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Value("${email.username}")
    private String email;

    public void sendMail(String[] sendTo, String subject){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email);
        message.setTo(sendTo);
        message.setSubject(subject);

        Context context = new Context();
        //context.setVariable("var", listaDTO);
        String content = templateEngine.process("mailTemplate", context);

        message.setText(content);

        javaMailSender.send(message);
    }
}
