package dev.alanryan.hospitalnotificationservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
@Slf4j
@Tag(name = "Webhook Receiver", description = "Endpoint receptor de notificações externas do ecossistema hospitalar")
public class WebhookController {

    @PostMapping
    @Operation(
            summary = "Receber notificação de agendamento",
            description = "Consome os dados de consultas enviados pela API Principal (HMS) após a persistência de um agendamento."
    )
    @ApiResponse(responseCode = "200", description = "Notificação recebida e processada com sucesso pelos logs")
    public void receiveWebhook(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "JSON contendo os detalhes do Appointment",
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Exemplo de Agendamento",
                                    summary = "Payload enviado pelo HMS",
                                    value = "{\"id\": 1, \"patientId\": 2, \"doctorId\": 1, \"date\": \"2023-11-10 14:30\"}"
                            )
                    )
            )
            @RequestBody Object payload) {

        log.info("NOVA NOTIFICAÇÃO RECEBIDA!");
        log.info("Payload recebido: {}", payload);
    }
}
