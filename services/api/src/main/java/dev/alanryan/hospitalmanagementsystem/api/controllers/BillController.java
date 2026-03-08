package dev.alanryan.hospitalmanagementsystem.api.controllers;

import dev.alanryan.hospitalmanagementsystem.api.models.Bill;
import dev.alanryan.hospitalmanagementsystem.api.service.BillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bills")
@RequiredArgsConstructor
@Tag(name = "4. Financeiro", description = "Endpoints para gestão de faturamento e contas hospitalares")
public class BillController {

    private final BillService billService;

    @GetMapping
    @Operation(summary = "Listar todas as faturas", description = "Retorna uma lista paginada de todas as contas geradas")
    @ApiResponse(responseCode = "200", description = "Lista recuperada com sucesso")
    public Page<Bill> getAllBills(
            @Parameter(description = "Número da página (inicia em 0)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Quantidade de faturas por página") @RequestParam(defaultValue = "2") int size) {
        return billService.getAllBills(page, size);
    }

    @PostMapping
    @Operation(summary = "Gerar nova fatura", description = "Cria uma nova cobrança vinculada a um paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Fatura gerada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de faturamento inválidos")
    })
    public Bill createBill(@RequestBody Bill bill) {
        return billService.createBill(bill);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar fatura por ID", description = "Retorna os detalhes de uma fatura específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fatura encontrada"),
            @ApiResponse(responseCode = "404", description = "Fatura não encontrada")
    })
    public Bill getBillById(@Parameter(description = "ID da fatura", example = "1") @PathVariable Long id) {
        return billService.getBillById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover fatura", description = "Exclui permanentemente o registro de uma fatura")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fatura removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Fatura não encontrada")
    })
    public void deleteBill(@Parameter(description = "ID da fatura a ser removida") @PathVariable Long id) {
        billService.deleteBill(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar fatura", description = "Modifica dados da fatura (útil para atualizar status de pagamento)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fatura atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Fatura não encontrada")
    })
    public Bill updateBill(
            @Parameter(description = "ID da fatura a ser atualizada") @PathVariable Long id,
            @RequestBody Bill bill) {
        return billService.updateBill(id, bill);
    }
}
