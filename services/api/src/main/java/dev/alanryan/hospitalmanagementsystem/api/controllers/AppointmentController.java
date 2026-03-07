package dev.alanryan.hospitalmanagementsystem.api.controllers;

import dev.alanryan.hospitalmanagementsystem.api.models.Appointment;
import dev.alanryan.hospitalmanagementsystem.api.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping
    public Page<Appointment> getAllAppointments(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "2") int size) {
        return appointmentService.getAllAppointments(page, size);
    }

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointmentService.createAppointment(appointment);
    }

    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }

    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable Long id, @RequestBody Appointment app) {
        return appointmentService.updateAppointment(id, app);
    }
}
