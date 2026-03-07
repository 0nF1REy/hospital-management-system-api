package dev.alanryan.hospitalmanagementsystem.api.controllers;

import dev.alanryan.hospitalmanagementsystem.api.models.Patient;
import dev.alanryan.hospitalmanagementsystem.api.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("patients")
@RequiredArgsConstructor
public class PatientController {

    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    private final PatientService patientService;

    @GetMapping
    public Page<Patient> getAllPatients(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "2") int size) {
        logger.info("Requisição GET: Buscando todos os pacientes.");
        return patientService.getAllPatients(page, size);
    }

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        logger.info("Requisição POST: Criando novo paciente.");
        return patientService.createPatient(patient);
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        logger.info("Requisição GET: Buscando paciente com ID {}", id);
        return patientService.getPatientById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        logger.info("Requisição DELETE: Removendo paciente com ID {}", id);
        patientService.deletePatient(id);
    }

    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        logger.info("Requisição PUT: Atualizando paciente com ID {}", id);
        return patientService.updatePatient(id, patient);
    }
}
