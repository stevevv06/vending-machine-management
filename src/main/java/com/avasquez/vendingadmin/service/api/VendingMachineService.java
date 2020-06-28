package com.avasquez.vendingadmin.service.api;

import com.avasquez.vendingadmin.service.dto.ItemDTO;
import com.avasquez.vendingadmin.service.dto.VendingMachineDTO;
import com.avasquez.vendingadmin.service.dto.VendingMachineWithItemsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.avasquez.vendingadmin.domain.VendingMachine}.
 */
public interface VendingMachineService extends CrudService<VendingMachineDTO, Long>  {
    VendingMachineWithItemsDTO save(VendingMachineWithItemsDTO vendingMachineWithItemsDTO);
    Optional<VendingMachineWithItemsDTO> findWithItems(Long id);
}
