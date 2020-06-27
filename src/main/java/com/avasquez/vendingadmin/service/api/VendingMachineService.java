package com.avasquez.vendingadmin.service;

import com.avasquez.vendingadmin.service.dto.VendingMachineDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.avasquez.vendingadmin.domain.VendingMachine}.
 */
public interface VendingMachineService {

    /**
     * Save a vendingMachine.
     *
     * @param vendingMachineDTO the entity to save.
     * @return the persisted entity.
     */
    VendingMachineDTO save(VendingMachineDTO vendingMachineDTO);

    /**
     * Get all the vendingMachines.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<VendingMachineDTO> findAll(Pageable pageable);


    /**
     * Get the "id" vendingMachine.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VendingMachineDTO> findOne(Long id);

    /**
     * Delete the "id" vendingMachine.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
