package com.restaurant.rewards.infrastructure.messaging;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendRewardEmail(String toEmail, String customerName, int points, double cashback) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("restaurant.rewards.lab@gmail.com"); // Cambia si usas otro correo
        message.setTo(toEmail);
        message.setSubject("¡Has ganado nuevas recompensas en tu restaurante favorito!");
        message.setText("Hola " + customerName + ",\n\n" +
                "¡Gracias por tu visita! Acabamos de procesar tu última transacción.\n\n" +
                "Has ganado:\n" +
                "- " + points + " Puntos ⭐\n" +
                "- S/ " + String.format("%.2f", cashback) + " de Cashback 💰\n\n" +
                "¡Te esperamos pronto para que disfrutes tus beneficios!\n\n" +
                "Atentamente,\nEl equipo del Restaurante");

        mailSender.send(message);
    }
}
