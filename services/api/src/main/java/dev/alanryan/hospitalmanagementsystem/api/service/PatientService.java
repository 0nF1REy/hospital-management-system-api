package dev.alanryan.hospitalmanagementsystem.api.service;

import dev.alanryan.hospitalmanagementsystem.api.models.Patient;
import dev.alanryan.hospitalmanagementsystem.api.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {

    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

    private final PatientRepository patientRepository;

    public Page<Patient> getAllPatients(int page, int size) {
        try  {
            logger.info("Entrou na camada de serviço: Buscando todos os pacientes");
            Pageable pageable = PageRequest.of(page, size);
            return patientRepository.findAll(pageable);
        } catch(Exception e) {
            logger.error("Ocorreu um erro ao buscar todos os pacientes: {}", e.getMessage());
            return null;
        }
    }

    public Patient getPatientById(Long id) {
        try {
            Optional<Patient> patient = patientRepository.findById(id);
            return patient.orElse(null);
        } catch(Exception e) {
            logger.error("Ocorreu um erro ao buscar o paciente com ID {} : {}", id, e.getMessage());
            return null;
        }
    }

    public Patient createPatient(Patient patient) {
        try {
            logger.info("Criando novo paciente: {}", patient.getName());
            return patientRepository.save(patient);
        } catch(Exception e) {
            logger.error("Ocorreu um erro ao criar um paciente: {}", e.getMessage(), e);
            return null;
        }
    }

    public void deletePatient(Long id) {
        try {
            logger.info("Deletando paciente com ID: {}", id);
            patientRepository.deleteById(id);
        } catch(Exception e) {
            logger.error("Ocorreu um erro ao deletar o paciente com ID {} : {}", id, e.getMessage(), e);
        }
    }

    public Patient updatePatient(Long id, Patient updatedPatient) {
        try {
            Optional<Patient> existingPatient = patientRepository.findById(id);
            if(existingPatient.isPresent()) {
                Patient p = existingPatient.get();
                p.setName(updatedPatient.getName());
                p.setAge(updatedPatient.getAge());
                p.setGender(updatedPatient.getGender());

                logger.info("Atualizando paciente com ID: {}", id);
                return patientRepository.save(p);
            } else {
                logger.error("Paciente com ID {} não encontrado para atualização", id);
                return null;
            }
        } catch(Exception e) {
            logger.error("Ocorreu um erro ao atualizar o paciente com ID {} : {}", id, e.getMessage(), e);
            return null;
        }
    }
}
