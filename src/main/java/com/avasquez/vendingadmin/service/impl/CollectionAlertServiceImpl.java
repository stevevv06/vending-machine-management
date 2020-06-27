package com.avasquez.service.impl;

import com.avasquez.service.CollectionAlertService;
import com.avasquez.domain.CollectionAlert;
import com.avasquez.repository.CollectionAlertRepository;
import com.avasquez.service.dto.CollectionAlertDTO;
import com.avasquez.service.mapper.CollectionAlertMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CollectionAlert}.
 */
@Service
@Transactional
public class CollectionAlertServiceImpl implements CollectionAlertService {

    private final Logger log = LoggerFactory.getLogger(CollectionAlertServiceImpl.class);

    private final CollectionAlertRepository collectionAlertRepository;

    private final CollectionAlertMapper collectionAlertMapper;

    public CollectionAlertServiceImpl(CollectionAlertRepository collectionAlertRepository, CollectionAlertMapper collectionAlertMapper) {
        this.collectionAlertRepository = collectionAlertRepository;
        this.collectionAlertMapper = collectionAlertMapper;
    }

    /**
     * Save a collectionAlert.
     *
     * @param collectionAlertDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CollectionAlertDTO save(CollectionAlertDTO collectionAlertDTO) {
        log.debug("Request to save CollectionAlert : {}", collectionAlertDTO);
        CollectionAlert collectionAlert = collectionAlertMapper.toEntity(collectionAlertDTO);
        collectionAlert = collectionAlertRepository.save(collectionAlert);
        return collectionAlertMapper.toDto(collectionAlert);
    }

    /**
     * Get all the collectionAlerts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CollectionAlertDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CollectionAlerts");
        return collectionAlertRepository.findAll(pageable)
            .map(collectionAlertMapper::toDto);
    }


    /**
     * Get one collectionAlert by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CollectionAlertDTO> findOne(Long id) {
        log.debug("Request to get CollectionAlert : {}", id);
        return collectionAlertRepository.findById(id)
            .map(collectionAlertMapper::toDto);
    }

    /**
     * Delete the collectionAlert by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CollectionAlert : {}", id);
        collectionAlertRepository.deleteById(id);
    }
}