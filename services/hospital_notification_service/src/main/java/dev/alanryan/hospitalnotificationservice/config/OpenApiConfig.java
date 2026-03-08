package dev.alanryan.hospitalnotificationservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI notificationApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hospital Notification Service")
                        .description("Microserviço especializado no recebimento, processamento e log de Webhooks disparados pelo ecossistema hospitalar.")
                        .version("v0.1.0")
                        .contact(new Contact()
                                .name("Alan Ryan da Silva Domingues")
                                .url("https://alan-ryan.vercel.app/"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}
