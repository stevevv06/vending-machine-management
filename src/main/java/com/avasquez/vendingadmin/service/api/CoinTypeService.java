package com.avasquez.vendingadmin.service;

import com.avasquez.vendingadmin.service.dto.CoinTypeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.avasquez.vendingadmin.domain.CoinType}.
 */
public interface CoinTypeService {

    /**
     * Save a coinType.
     *
     * @param coinTypeDTO the entity to save.
     * @return the persisted entity.
     */
    CoinTypeDTO save(CoinTypeDTO coinTypeDTO);

    /**
     * Get all the coinTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CoinTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" coinType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CoinTypeDTO> findOne(Long id);

    /**
     * Delete the "id" coinType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
