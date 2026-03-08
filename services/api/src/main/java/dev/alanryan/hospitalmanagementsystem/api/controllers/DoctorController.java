package dev.alanryan.hospitalmanagementsystem.api.controllers;

import dev.alanryan.hospitalmanagementsystem.api.models.Doctor;
import dev.alanryan.hospitalmanagementsystem.api.service.DoctorService;
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
@RequestMapping("doctors")
@RequiredArgsConstructor
@Tag(name = "2. Médicos", description = "Endpoints para gerenciamento do corpo clínico do hospital")
public class DoctorController {

    private static final Logger logger = LoggerFactory.getLogger(DoctorController.class);
    private final DoctorService doctorService;

    @GetMapping
    @Operation(summary = "Listar todos os médicos", description = "Retorna uma lista paginada de todos os médicos cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista recuperada com sucesso")
    public Page<Doctor> getAllDoctors(
            @Parameter(description = "Número da página (inicia em 0)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Quantidade de registros por página") @RequestParam(defaultValue = "2") int size) {
        logger.info("Requisição GET: Buscando médicos.");
        return doctorService.getAllDoctors(page, size);
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo médico", description = "Cria um novo registro de médico no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Médico cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos")
    })
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        logger.info("Requisição POST: Criando médico {}.", doctor.getName());
        return doctorService.createDoctor(doctor);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar médico por ID", description = "Retorna os detalhes de um médico específico baseado no ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Médico encontrado"),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado")
    })
    public Doctor getDoctorById(@Parameter(description = "ID do médico", example = "1") @PathVariable Long id) {
        logger.info("Requisição GET: Buscando médico com ID {}", id);
        return doctorService.getDoctorById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover um médico", description = "Exclui o registro de um médico do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Médico removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado")
    })
    public void deleteDoctor(@Parameter(description = "ID do médico a ser removido") @PathVariable Long id) {
        logger.info("Requisição DELETE: Removendo médico com ID {}", id);
        doctorService.deleteDoctor(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar dados do médico", description = "Modifica as informações de um médico existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Médico atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado")
    })
    public Doctor updateDoctor(
            @Parameter(description = "ID do médico a ser atualizado") @PathVariable Long id,
            @RequestBody Doctor doctor) {
        logger.info("Requisição PUT: Atualizando médico com ID {}", id);
        return doctorService.updateDoctor(id, doctor);
    }
}
