package dev.alanryan.hospitalmanagementsystem.api.controllers;

import dev.alanryan.hospitalmanagementsystem.api.models.Appointment;
import dev.alanryan.hospitalmanagementsystem.api.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("appointments")
@RequiredArgsConstructor
@Tag(name = "3. Consultas", description = "Endpoints para gestão de agendamentos e integrações de notificações")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping
    @Operation(summary = "Listar todos os agendamentos", description = "Retorna uma lista paginada de todas as consultas marcadas no sistema")
    @ApiResponse(responseCode = "200", description = "Lista recuperada com sucesso")
    public Page<Appointment> getAllAppointments(
            @Parameter(description = "Número da página (inicia em 0)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Quantidade de agendamentos por página") @RequestParam(defaultValue = "2") int size) {
        return appointmentService.getAllAppointments(page, size);
    }

    @PostMapping
    @Operation(
            summary = "Agendar uma nova consulta",
            description = "Cria um novo agendamento e dispara automaticamente uma notificação Webhook para o serviço especializado de notificações"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Consulta agendada e notificação enviada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados do agendamento inválidos")
    })
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointmentService.createAppointment(appointment);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar agendamento por ID", description = "Retorna os detalhes de uma consulta específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento encontrado"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    })
    public Appointment getAppointmentById(@Parameter(description = "ID do agendamento", example = "1") @PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover agendamento", description = "Exclui permanentemente o registro de uma consulta do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    })
    public void deleteAppointment(@Parameter(description = "ID do agendamento a ser removido") @PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar agendamento", description = "Modifica os dados de uma consulta existente (ex: alteração de data ou médico)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    })
    public Appointment updateAppointment(
            @Parameter(description = "ID do agendamento a ser atualizado") @PathVariable Long id,
            @RequestBody Appointment app) {
        return appointmentService.updateAppointment(id, app);
    }
}
