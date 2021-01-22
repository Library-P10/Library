package com.api.library.service.impl;

import com.api.library.service.contract.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * Cette méthode envoie le message pré configurer
     * @param argTo
     * @param argFirst
     * @param argLast
     * @param argTitle
     */
    public void sendMessage(String argTo, String argFirst, String argLast, String argTitle){
        SimpleMailMessage mailMessage = new SimpleMailMessage(mailRecoveryModel());
        String text = String.format(Objects.requireNonNull(mailMessage.getText()),argFirst, argLast, argTitle);
        mailMessage.setTo(argTo);
        mailMessage.setText(text);
        javaMailSender.send(mailMessage);
    }

    public SimpleMailMessage mailRecoveryModel(){
        SimpleMailMessage writer = new SimpleMailMessage();
        writer.setTo("%s");
        writer.setFrom("559e1d13c45051");
        writer.setSubject("Livre disponible à la récupération - Bibliothèque d'OC-City");
        writer.setText("Bonjour, %s %s" +
                "\n\nVous aviez réservé le livre \"%s\" qui a été mis en attente d'un retour." +
                "\nVous pouvez dès à présent venir récupérer le livre qui est réservé pour vous à la suite d'un retour." +
                "\nVous avez 48h pour venir le chercher, passer ce délai, votre réservation est annulée" +
                "\n\n\nBibliothèque d'OC-City" +
                "\n\n\n\nCeci est un message automatique, ne pas répondre à ce mail.");

        return writer;
    }
}
