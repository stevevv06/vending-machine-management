package com.avasquez.vendingadmin.service;

import com.avasquez.vendingadmin.service.dto.CollectionAlertDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.avasquez.vendingadmin.domain.CollectionAlert}.
 */
public interface CollectionAlertService {

    /**
     * Save a collectionAlert.
     *
     * @param collectionAlertDTO the entity to save.
     * @return the persisted entity.
     */
    CollectionAlertDTO save(CollectionAlertDTO collectionAlertDTO);

    /**
     * Get all the collectionAlerts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CollectionAlertDTO> findAll(Pageable pageable);


    /**
     * Get the "id" collectionAlert.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CollectionAlertDTO> findOne(Long id);

    /**
     * Delete the "id" collectionAlert.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
