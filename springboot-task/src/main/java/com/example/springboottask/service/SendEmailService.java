package com.example.springboottask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.time.LocalDateTime;


@Service
public class SendEmailService {

    @Autowired
    private JavaMailSenderImpl sender;

    public void sendSimpleEmail(String subject,String text,String from,String to){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setText(text);
        message.setFrom(from);
        message.setTo(to);
        sender.send(message);
    }

    public void sendMultipartEmail(String subject, String text, String from, String to, Boolean html, String attachmentFileName, File file) throws MessagingException {
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.setSubject(subject);
        helper.setText(text,html);
        helper.setFrom(from);
        helper.setTo(to);
        helper.addAttachment(attachmentFileName,file);
        sender.send(mimeMessage);
    }
}
