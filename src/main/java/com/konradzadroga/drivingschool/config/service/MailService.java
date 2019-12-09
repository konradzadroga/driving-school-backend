package com.konradzadroga.drivingschool.config.service;

import com.konradzadroga.drivingschool.config.message.request.SignUpDTO;
import com.konradzadroga.drivingschool.rest_api.user.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private JavaMailSender sender;
    @Value("${spring.mail.username}")
    private String myEmailAddress;

    public MailService(JavaMailSender sender) {
        this.sender = sender;
    }

    public void registrationSuccessful(SignUpDTO signUpDTO) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(signUpDTO.getEmail());
        mail.setFrom(myEmailAddress);
        mail.setSubject("Witaj " + signUpDTO.getName() + " " + signUpDTO.getSurname());
        mail.setText("Dziękujemy za rejestrację na naszej stronie");
        sender.send(mail);
    }

    public void sendContactMessage(UserDTO user, String content) {
        SimpleMailMessage mailToSender = new SimpleMailMessage();
        SimpleMailMessage mailToReceiver = new SimpleMailMessage();
        StringBuilder senderMail = new StringBuilder();
        StringBuilder receiverMail = new StringBuilder();

        senderMail.append("Dziękujemy za kontakt z nami. \n \n Twoja wiadomość: \n")
                .append(content)
                .append("\n \n Skontaktujemy się z Tobą w tej sprawie w najbliższym czasie.");

        receiverMail.append("Wiadomość od użytkownika: ")
                .append(user.getUsername())
                .append(", ")
                .append(user.getEmail())
                .append(", o treści: \n")
                .append(content);

        mailToSender.setTo(user.getEmail());
        mailToSender.setFrom(myEmailAddress);
        mailToSender.setSubject("Dziękujemy za skontaktowanie się z nami");
        mailToSender.setText(senderMail.toString());

        mailToReceiver.setTo(myEmailAddress);
        mailToReceiver.setFrom(myEmailAddress);
        mailToReceiver.setSubject("Wiadomość od użytkownika: " + user.getUsername());
        mailToReceiver.setText(receiverMail.toString());

        sender.send(mailToSender);
        sender.send(mailToReceiver);
    }



}
