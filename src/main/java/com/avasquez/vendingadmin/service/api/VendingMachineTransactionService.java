package com.avasquez.vendingadmin.service;

import com.avasquez.vendingadmin.service.dto.VendingMachineTransactionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.avasquez.vendingadmin.domain.VendingMachineTransaction}.
 */
public interface VendingMachineTransactionService {

    /**
     * Save a vendingMachineTransaction.
     *
     * @param vendingMachineTransactionDTO the entity to save.
     * @return the persisted entity.
     */
    VendingMachineTransactionDTO save(VendingMachineTransactionDTO vendingMachineTransactionDTO);

    /**
     * Get all the vendingMachineTransactions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<VendingMachineTransactionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" vendingMachineTransaction.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VendingMachineTransactionDTO> findOne(Long id);

    /**
     * Delete the "id" vendingMachineTransaction.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
