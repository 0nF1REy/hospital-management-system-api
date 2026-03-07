package dev.alanryan.hospitalmanagementsystem.api.service;

import dev.alanryan.hospitalmanagementsystem.api.models.Appointment;
import dev.alanryan.hospitalmanagementsystem.api.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);
    private final AppointmentRepository appointmentRepository;
    private final RestClient restClient = RestClient.create();

    public Page<Appointment> getAllAppointments(int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            return appointmentRepository.findAll(pageable);
        } catch (Exception e) {
            logger.error("Erro ao buscar consultas: {}", e.getMessage());
            return null;
        }
    }

    public Appointment getAppointmentById(Long id) {
        try {
            return appointmentRepository.findById(id).orElse(null);
        } catch (Exception e) {
            logger.error("Erro ao buscar consulta ID {}: {}", id, e.getMessage());
            return null;
        }
    }

    public Appointment createAppointment(Appointment appointment) {
        try {
            Appointment savedAppointment = appointmentRepository.save(appointment);

            sendWebhook(savedAppointment);

            return savedAppointment;
        } catch (Exception e) {
            logger.error("Erro ao criar consulta: {}", e.getMessage());
            return null;
        }
    }

    private void sendWebhook(Appointment appointment) {
        String webhookUrl = "http://localhost:8081/api/v1/webhook";
        try {
            restClient.post()
                    .uri(webhookUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(appointment)
                    .retrieve()
                    .toBodilessEntity();
            logger.info("Webhook enviado com sucesso para a consulta ID: {}", appointment.getId());
        } catch (Exception e) {
            logger.error("Falha ao enviar webhook: {}", e.getMessage());
        }
    }

    public void deleteAppointment(Long id) {
        try {
            appointmentRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Erro ao deletar consulta ID {}: {}", id, e.getMessage());
        }
    }

    public Appointment updateAppointment(Long id, Appointment updatedApp) {
        try {
            Optional<Appointment> existing = appointmentRepository.findById(id);
            if (existing.isPresent()) {
                Appointment a = existing.get();
                a.setDate(updatedApp.getDate());
                a.setPatientId(updatedApp.getPatientId());
                a.setDoctorId(updatedApp.getDoctorId());
                return appointmentRepository.save(a);
            }
            return null;
        } catch (Exception e) {
            logger.error("Erro ao atualizar consulta ID {}: {}", id, e.getMessage());
            return null;
        }
    }
}
