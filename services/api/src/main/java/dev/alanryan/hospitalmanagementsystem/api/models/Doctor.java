package dev.alanryan.hospitalmanagementsystem.api.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder @AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Schema(description = "Modelo que representa um Médico do corpo clínico")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do médico", example = "1")
    private Long id;

    @Schema(description = "Nome completo do médico", example = "Dr. Kenzo Tenma")
    private String name;

    @Schema(description = "Especialidade médica do profissional", example = "Neurocirurgia")
    private String speciality;
}
