package com.avasquez.vendingadmin.service;

import com.avasquez.vendingadmin.service.dto.BillTypeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.avasquez.vendingadmin.domain.BillType}.
 */
public interface BillTypeService {

    /**
     * Save a billType.
     *
     * @param billTypeDTO the entity to save.
     * @return the persisted entity.
     */
    BillTypeDTO save(BillTypeDTO billTypeDTO);

    /**
     * Get all the billTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BillTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" billType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BillTypeDTO> findOne(Long id);

    /**
     * Delete the "id" billType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
