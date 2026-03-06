package dev.alanryan.hospitalmanagementsystem.api.service;

import dev.alanryan.hospitalmanagementsystem.api.models.Bill;
import dev.alanryan.hospitalmanagementsystem.api.repository.BillRepository;
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
public class BillService {

    private static final Logger logger = LoggerFactory.getLogger(BillService.class);
    private final BillRepository billRepository;

    public Page<Bill> getAllBills(int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            return billRepository.findAll(pageable);
        } catch (Exception e) {
            logger.error("Erro ao buscar contas: {}", e.getMessage());
            return null;
        }
    }

    public Bill getBillById(Long id) {
        try {
            return billRepository.findById(id).orElse(null);
        } catch (Exception e) {
            logger.error("Erro ao buscar conta ID {}: {}", id, e.getMessage());
            return null;
        }
    }

    public Bill createBill(Bill bill) {
        try {
            return billRepository.save(bill);
        } catch (Exception e) {
            logger.error("Erro ao criar conta: {}", e.getMessage());
            return null;
        }
    }

    public void deleteBill(Long id) {
        try {
            billRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Erro ao deletar conta ID {}: {}", id, e.getMessage());
        }
    }

    public Bill updateBill(Long id, Bill updatedBill) {
        try {
            Optional<Bill> existing = billRepository.findById(id);
            if (existing.isPresent()) {
                Bill b = existing.get();
                b.setAmount(updatedBill.getAmount());
                b.setStatus(updatedBill.getStatus());
                b.setPatientId(updatedBill.getPatientId());
                return billRepository.save(b);
            }
            return null;
        } catch (Exception e) {
            logger.error("Erro ao atualizar conta ID {}: {}", id, e.getMessage());
            return null;
        }
    }
}
