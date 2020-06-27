package com.avasquez.vendingadmin.service;

import com.avasquez.vendingadmin.service.dto.VendingMachineCashDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.avasquez.vendingadmin.domain.VendingMachineCash}.
 */
public interface VendingMachineCashService {

    /**
     * Save a vendingMachineCash.
     *
     * @param vendingMachineCashDTO the entity to save.
     * @return the persisted entity.
     */
    VendingMachineCashDTO save(VendingMachineCashDTO vendingMachineCashDTO);

    /**
     * Get all the vendingMachineCashes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<VendingMachineCashDTO> findAll(Pageable pageable);


    /**
     * Get the "id" vendingMachineCash.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VendingMachineCashDTO> findOne(Long id);

    /**
     * Delete the "id" vendingMachineCash.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
