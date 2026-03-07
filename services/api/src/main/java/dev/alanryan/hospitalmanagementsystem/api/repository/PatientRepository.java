package dev.alanryan.hospitalmanagementsystem.api.repository;

import dev.alanryan.hospitalmanagementsystem.api.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {}
