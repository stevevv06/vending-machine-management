package com.avasquez.vendingadmin.service.api;

import com.avasquez.vendingadmin.service.dto.VendingMachineItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.avasquez.vendingadmin.domain.VendingMachineItem}.
 */
public interface VendingMachineItemService extends CrudService<VendingMachineItemDTO, Long>  {
    Page<VendingMachineItemDTO> findAllByVendingMachineId(Long id, Pageable pageable);
}
