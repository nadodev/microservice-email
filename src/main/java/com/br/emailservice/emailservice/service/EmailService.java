package com.br.emailservice.emailservice.service;

import com.br.emailservice.emailservice.model.Email;
import com.br.emailservice.emailservice.repository.EmailRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class EmailService {

    private final EmailRepository emailRepository;

    public EmailService(JavaMailSender mailSender, EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public void enviarEmail(String destinatario, String assunto, String mensagem) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(destinatario);
        email.setSubject(assunto);
        email.setText(mensagem);

        //mailSender.send(email);

        Email novoEmail = new Email();
        novoEmail.setDestinatario(destinatario);
        novoEmail.setAssunto(assunto);
        novoEmail.setMensagem(mensagem);
        novoEmail.setEnviadoEm(LocalDateTime.now());

        emailRepository.save(novoEmail);
    }
}