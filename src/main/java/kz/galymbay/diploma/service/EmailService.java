package kz.galymbay.diploma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String to, String subject, String body) {
//        MimeMessage message = javaMailSender.createMimeMessage();

//        MimeMessage message = javaMailSender.createMimeMessage();
//
//        try {
//            message.setSubject(subject);
//            MimeMessageHelper helper;
//            helper = new MimeMessageHelper(message, true);
//            helper.setTo(to);
//            helper.setText(body, true);
//            javaMailSender.send(message);
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);
    }
}

