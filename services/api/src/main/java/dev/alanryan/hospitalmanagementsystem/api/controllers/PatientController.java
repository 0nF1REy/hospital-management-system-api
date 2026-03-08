package dev.alanryan.hospitalmanagementsystem.api.controllers;

import dev.alanryan.hospitalmanagementsystem.api.models.Patient;
import dev.alanryan.hospitalmanagementsystem.api.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("patients")
@RequiredArgsConstructor
@Tag(name = "1. Pacientes", description = "Endpoints para gerenciamento de cadastro de pacientes")
public class PatientController {

    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    private final PatientService patientService;

    @GetMapping
    @Operation(summary = "Listar todos os pacientes", description = "Retorna uma lista paginada de todos os pacientes registrados", operationId = "1_getAll")
    @ApiResponse(responseCode = "200", description = "Lista recuperada com sucesso")
    public Page<Patient> getAllPatients(
            @Parameter(description = "Número da página (inicia em 0)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Quantidade de registros por página") @RequestParam(defaultValue = "2") int size) {
        logger.info("Requisição GET: Buscando todos os pacientes.");
        return patientService.getAllPatients(page, size);
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo paciente", description = "Cria um novo registro de paciente no banco de dados", operationId = "2_create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Paciente criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos")
    })
    public Patient createPatient(@RequestBody Patient patient) {
        logger.info("Requisição POST: Criando novo paciente.");
        return patientService.createPatient(patient);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar paciente por ID", description = "Retorna os detalhes de um paciente específico baseado no ID", operationId = "3_getById")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente encontrado"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    })
    public Patient getPatientById(@Parameter(description = "ID do paciente", example = "1") @PathVariable Long id) {
        logger.info("Requisição GET: Buscando paciente com ID {}", id);
        return patientService.getPatientById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover um paciente", description = "Exclui permanentemente o registro de um paciente do sistema", operationId = "5_delete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    })
    public void deletePatient(@Parameter(description = "ID do paciente a ser removido") @PathVariable Long id) {
        logger.info("Requisição DELETE: Removendo paciente com ID {}", id);
        patientService.deletePatient(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar dados do paciente", description = "Modifica as informações de um paciente existente", operationId = "4_update")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    })
    public Patient updatePatient(
            @Parameter(description = "ID do paciente a ser atualizado") @PathVariable Long id,
            @RequestBody Patient patient) {
        logger.info("Requisição PUT: Atualizando paciente com ID {}", id);
        return patientService.updatePatient(id, patient);
    }
}
