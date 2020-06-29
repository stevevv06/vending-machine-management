package com.avasquez.vendingadmin.service.api;

import com.avasquez.vendingadmin.service.dto.VendingMachineCashDTO;
import com.avasquez.vendingadmin.service.dto.VendingMachineDTO;
import com.avasquez.vendingadmin.service.dto.VendingMachineReportDTO;
import com.avasquez.vendingadmin.service.dto.VendingMachineTotalDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.avasquez.vendingadmin.domain.VendingMachine}.
 */
public interface VendingMachineService extends CrudService<VendingMachineDTO, Long>  {
    boolean checkUnlockCode(Long vendingMachineId, String unlockCode);
    Page<VendingMachineReportDTO> getReport(Pageable page);
}
