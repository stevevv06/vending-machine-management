package com.avasquez.vendingadmin.service;

import com.avasquez.vendingadmin.service.dto.UnlockAttempDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.avasquez.vendingadmin.domain.UnlockAttemp}.
 */
public interface UnlockAttempService {

    /**
     * Save a unlockAttemp.
     *
     * @param unlockAttempDTO the entity to save.
     * @return the persisted entity.
     */
    UnlockAttempDTO save(UnlockAttempDTO unlockAttempDTO);

    /**
     * Get all the unlockAttemps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UnlockAttempDTO> findAll(Pageable pageable);


    /**
     * Get the "id" unlockAttemp.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UnlockAttempDTO> findOne(Long id);

    /**
     * Delete the "id" unlockAttemp.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
