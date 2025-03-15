package com.br.emailservice.emailservice.rabbitmq;

import com.br.emailservice.emailservice.service.EmailService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class RabbitMQConsumer {

    private final EmailService emailService;

    public RabbitMQConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "email_queue")
    public void receberMensagem(Map<String, String> mensagem) {
        try {
            String destinatario = mensagem.get("destinatario");
            String assunto = mensagem.get("assunto");
            String mensagemTexto = mensagem.get("mensagem");

            if (destinatario == null || destinatario.isBlank()) {
                throw new IllegalArgumentException("Destinatário não pode ser nulo ou vazio");
            }
            if (assunto == null || assunto.isBlank()) {
                throw new IllegalArgumentException("Assunto não pode ser nulo ou vazio");
            }
            if (mensagemTexto == null || mensagemTexto.isBlank()) {
                throw new IllegalArgumentException("Mensagem não pode ser nula ou vazia");
            }

            emailService.enviarEmail(destinatario, assunto, mensagemTexto);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar a mensagem", e);
        }
    }
}
