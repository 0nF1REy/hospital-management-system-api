package dev.alanryan.hospitalmanagementsystem.api.repository;

import dev.alanryan.hospitalmanagementsystem.api.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {}
