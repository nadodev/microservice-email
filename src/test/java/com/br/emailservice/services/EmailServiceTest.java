package com.br.emailservice.services;


import com.br.emailservice.emailservice.model.Email;
import com.br.emailservice.emailservice.repository.EmailRepository;
import com.br.emailservice.emailservice.service.EmailService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private EmailRepository emailRepository;

    @InjectMocks
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEnviarEmail() {
        String destinatario = "nadojba@hotmail.com";
        String assunto = "Teste";
        String mensagem = "Olá, isso é um teste!";

        String emailEsperado = "nadojba@hotmail.com";

        emailService.enviarEmail(destinatario, assunto, mensagem);

        verify(emailRepository, times(1)).save(any(Email.class));

        Email emailSalvo = new Email();
        emailSalvo.setDestinatario(destinatario);
        emailSalvo.setAssunto(assunto);
        emailSalvo.setMensagem(mensagem);
        emailSalvo.setEnviadoEm(LocalDateTime.now());

        assertEquals(destinatario, emailSalvo.getDestinatario());
        assertEquals(assunto, emailSalvo.getAssunto());
        assertEquals(mensagem, emailSalvo.getMensagem());
        assertEquals(emailEsperado, emailSalvo.getDestinatario());
    }
}
