package com.joaoeduardo.penabrancadelivery_backend.config;


import com.joaoeduardo.penabrancadelivery_backend.user.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor

public class EmailService {

    private final JavaMailSender emailSender;
    @Value("${MAIL_USER}")
    private String mailUser;

    private String verifyURL = "http://localhost:8080/users/verify?code=";

    public void sendVerificationEmail(User user) throws IOException, MessagingException {

        String content = "<h1>DEU CERTO [[NAME]] [NAME]!</h1>";

        String senderName = "Pena Branca Delivery";

        String subject = "Pena Branca Delivery - Please Verify Your Registration";

//        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/templates/email_template.html")));
//        // Substitui o placeholder ${message} pelo conteúdo da mensagem
//        content = content.replace("${message}", message);

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(mailUser, senderName);
        helper.setTo(user.getEmail());
        helper.setSubject(subject);

        content = content.replace("[[NAME]]", user.getName());

        String verifyURL = this.verifyURL+user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        emailSender.send(message);

    }

}