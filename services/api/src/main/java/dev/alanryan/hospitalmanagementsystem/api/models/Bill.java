package dev.alanryan.hospitalmanagementsystem.api.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder @AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Schema(description = "Modelo que representa uma fatura ou conta hospitalar")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único da fatura", example = "1")
    private Long id;

    @Schema(description = "ID do paciente responsável pelo pagamento", example = "1")
    private Long patientId;

    @Schema(description = "Valor total da cobrança", example = "1500.50")
    private double amount;

    @Schema(
            description = "Status atual da fatura",
            example = "PENDING",
            allowableValues = {"PENDING", "PAID", "CANCELLED"}
    )
    private String status;
}
