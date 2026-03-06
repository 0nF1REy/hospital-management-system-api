package dev.alanryan.hospitalmanagementsystem.api.service;

import dev.alanryan.hospitalmanagementsystem.api.models.Doctor;
import dev.alanryan.hospitalmanagementsystem.api.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private static final Logger logger = LoggerFactory.getLogger(DoctorService.class);
    private final DoctorRepository doctorRepository;

    public Page<Doctor> getAllDoctors(int page, int size) {
        try {
            logger.info("Camada de serviço: Buscando todos os médicos");
            Pageable pageable = PageRequest.of(page, size);
            return doctorRepository.findAll(pageable);
        } catch (Exception e) {
            logger.error("Erro ao buscar médicos: {}", e.getMessage());
            return null;
        }
    }

    public Doctor getDoctorById(Long id) {
        try {
            return doctorRepository.findById(id).orElse(null);
        } catch (Exception e) {
            logger.error("Erro ao buscar médico com ID {}: {}", id, e.getMessage());
            return null;
        }
    }

    public Doctor createDoctor(Doctor doctor) {
        try {
            logger.info("Criando novo médico: {}", doctor.getName());
            return doctorRepository.save(doctor);
        } catch (Exception e) {
            logger.error("Erro ao criar médico: {}", e.getMessage());
            return null;
        }
    }

    public void deleteDoctor(Long id) {
        try {
            logger.info("Deletando médico com ID: {}", id);
            doctorRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Erro ao deletar médico com ID {}: {}", id, e.getMessage());
        }
    }

    public Doctor updateDoctor(Long id, Doctor updatedDoctor) {
        try {
            Optional<Doctor> existing = doctorRepository.findById(id);
            if (existing.isPresent()) {
                Doctor d = existing.get();
                d.setName(updatedDoctor.getName());
                d.setSpeciality(updatedDoctor.getSpeciality());
                logger.info("Atualizando médico com ID: {}", id);
                return doctorRepository.save(d);
            }
            logger.error("Médico com ID {} não encontrado", id);
            return null;
        } catch (Exception e) {
            logger.error("Erro ao atualizar médico com ID {}: {}", id, e.getMessage());
            return null;
        }
    }
}
