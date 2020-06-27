package com.avasquez.vendingadmin.service;

import com.avasquez.vendingadmin.service.dto.VendingMachineItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.avasquez.vendingadmin.domain.VendingMachineItem}.
 */
public interface VendingMachineItemService {

    /**
     * Save a vendingMachineItem.
     *
     * @param vendingMachineItemDTO the entity to save.
     * @return the persisted entity.
     */
    VendingMachineItemDTO save(VendingMachineItemDTO vendingMachineItemDTO);

    /**
     * Get all the vendingMachineItems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<VendingMachineItemDTO> findAll(Pageable pageable);


    /**
     * Get the "id" vendingMachineItem.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VendingMachineItemDTO> findOne(Long id);

    /**
     * Delete the "id" vendingMachineItem.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
