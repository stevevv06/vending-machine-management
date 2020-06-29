package com.avasquez.vendingadmin.service.api;

import com.avasquez.vendingadmin.service.dto.VendingMachineTotalDTO;
import com.avasquez.vendingadmin.service.dto.VendingMachineTransactionDTO;
import com.avasquez.vendingadmin.service.dto.VendingMachineTransactionSaleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.avasquez.vendingadmin.domain.VendingMachineTransaction}.
 */
public interface VendingMachineTransactionService extends CrudService<VendingMachineTransactionDTO, Long>  {
    VendingMachineTransactionDTO save(VendingMachineTransactionSaleDTO dto);
    Optional<VendingMachineTotalDTO> getTotalProfitByVendingMachineId(Long vendingMachineId);
    Optional<VendingMachineTotalDTO> getCountTransactions(Long vendingMachineId, LocalDate transactionDate);
}
