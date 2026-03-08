package dev.alanryan.hospitalmanagementsystem.api.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder @AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Schema(description = "Modelo que representa o agendamento de uma consulta")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do agendamento", example = "1")
    private Long id;

    @Schema(description = "ID do paciente vinculado à consulta", example = "1")
    private Long patientId;

    @Schema(description = "ID do médico vinculado à consulta", example = "1")
    private Long doctorId;

    @Schema(description = "Data e hora da consulta agendada", example = "2023-12-25 10:30")
    private String date;
}
