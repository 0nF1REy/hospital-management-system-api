package dev.alanryan.hospitalmanagementsystem.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI hospitalApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hospital Management System API")
                        .description("API principal para gestão hospitalar com integração de Webhooks.")
                        .version("v0.3.0")
                        .contact(new Contact()
                                .name("Alan Ryan da Silva Domingues")
                                .url("https://alan-ryan.vercel.app/"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .tags(Arrays.asList(
                        new Tag().name("1. Pacientes").description("Endpoints para gerenciamento de cadastro de pacientes"),
                        new Tag().name("2. Médicos").description("Endpoints para gerenciamento do corpo clínico do hospital"),
                        new Tag().name("3. Consultas").description("Endpoints para gestão de agendamentos e integrações de notificações"),
                        new Tag().name("4. Financeiro").description("Endpoints para gestão de faturamento e contas hospitalares")
                ));
    }
}
