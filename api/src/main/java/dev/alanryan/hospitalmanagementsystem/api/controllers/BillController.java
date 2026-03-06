package dev.alanryan.hospitalmanagementsystem.api.controllers;

import dev.alanryan.hospitalmanagementsystem.api.models.Bill;
import dev.alanryan.hospitalmanagementsystem.api.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bills")
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;

    @GetMapping
    public Page<Bill> getAllBills(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "2") int size) {
        return billService.getAllBills(page, size);
    }

    @PostMapping
    public Bill createBill(@RequestBody Bill bill) {
        return billService.createBill(bill);
    }

    @GetMapping("/{id}")
    public Bill getBillById(@PathVariable Long id) {
        return billService.getBillById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBill(@PathVariable Long id) {
        billService.deleteBill(id);
    }

    @PutMapping("/{id}")
    public Bill updateBill(@PathVariable Long id, @RequestBody Bill bill) {
        return billService.updateBill(id, bill);
    }
}
