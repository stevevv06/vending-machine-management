package com.avasquez.vendingadmin.service;

import com.avasquez.vendingadmin.service.dto.VendingMachineModelDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.avasquez.vendingadmin.domain.VendingMachineModel}.
 */
public interface VendingMachineModelService {

    /**
     * Save a vendingMachineModel.
     *
     * @param vendingMachineModelDTO the entity to save.
     * @return the persisted entity.
     */
    VendingMachineModelDTO save(VendingMachineModelDTO vendingMachineModelDTO);

    /**
     * Get all the vendingMachineModels.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<VendingMachineModelDTO> findAll(Pageable pageable);


    /**
     * Get the "id" vendingMachineModel.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VendingMachineModelDTO> findOne(Long id);

    /**
     * Delete the "id" vendingMachineModel.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
