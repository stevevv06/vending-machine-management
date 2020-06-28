package com.avasquez.vendingadmin.service.api;

import com.avasquez.vendingadmin.service.dto.VendingMachineCashDTO;
import com.avasquez.vendingadmin.service.dto.VendingMachineItemDTO;
import com.avasquez.vendingadmin.service.dto.VendingMachineTotalDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.avasquez.vendingadmin.domain.VendingMachineCash}.
 */
public interface VendingMachineCashService extends CrudService<VendingMachineCashDTO, Long> {
    Page<VendingMachineCashDTO> findAllByVendingMachineId(Long id, Pageable pageable);
    Optional<VendingMachineTotalDTO> getTotalCashByVendingMachineId(Long vendingMachineId);
}
