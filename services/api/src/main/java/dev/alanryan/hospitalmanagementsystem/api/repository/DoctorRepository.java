package dev.alanryan.hospitalmanagementsystem.api.repository;

import dev.alanryan.hospitalmanagementsystem.api.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {}
