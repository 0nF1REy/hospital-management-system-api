package dev.alanryan.hospitalmanagementsystem.api.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder @AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Schema(description = "Modelo que representa um Paciente no sistema")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do paciente (gerado automaticamente)", example = "1")
    private Long id;

    @Schema(description = "Nome completo do paciente", example = "Izaya Orihara")
    private String name;

    @Schema(description = "Gênero do paciente", example = "Masculino")
    private String gender;

    @Schema(description = "Idade do paciente", example = "25")
    private int age;
}
