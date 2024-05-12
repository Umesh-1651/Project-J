package com.emailsender.sendemail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
@Service
public class sendEmail{
    @Autowired
    private JavaMailSender sender;
    public void send(String to, String subject, String body) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setFrom("umarohi828@gmail.com");
        smm.setTo(to);
        smm.setSubject(subject);
        smm.setText(body);
        sender.send(smm);
    }
}