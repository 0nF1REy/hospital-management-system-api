package dev.alanryan.hospitalnotificationservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
@Slf4j
public class WebhookController {

    @PostMapping
    public void receiveWebhook(@RequestBody Object payload) {
        log.info("NOVA NOTIFICAÇÃO RECEBIDA!");
        log.info("Payload recebido: {}", payload);
    }
}
