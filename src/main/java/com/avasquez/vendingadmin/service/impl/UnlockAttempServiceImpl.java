package com.avasquez.service.impl;

import com.avasquez.service.UnlockAttempService;
import com.avasquez.domain.UnlockAttemp;
import com.avasquez.repository.UnlockAttempRepository;
import com.avasquez.service.dto.UnlockAttempDTO;
import com.avasquez.service.mapper.UnlockAttempMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link UnlockAttemp}.
 */
@Service
@Transactional
public class UnlockAttempServiceImpl implements UnlockAttempService {

    private final Logger log = LoggerFactory.getLogger(UnlockAttempServiceImpl.class);

    private final UnlockAttempRepository unlockAttempRepository;

    private final UnlockAttempMapper unlockAttempMapper;

    public UnlockAttempServiceImpl(UnlockAttempRepository unlockAttempRepository, UnlockAttempMapper unlockAttempMapper) {
        this.unlockAttempRepository = unlockAttempRepository;
        this.unlockAttempMapper = unlockAttempMapper;
    }

    /**
     * Save a unlockAttemp.
     *
     * @param unlockAttempDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public UnlockAttempDTO save(UnlockAttempDTO unlockAttempDTO) {
        log.debug("Request to save UnlockAttemp : {}", unlockAttempDTO);
        UnlockAttemp unlockAttemp = unlockAttempMapper.toEntity(unlockAttempDTO);
        unlockAttemp = unlockAttempRepository.save(unlockAttemp);
        return unlockAttempMapper.toDto(unlockAttemp);
    }

    /**
     * Get all the unlockAttemps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UnlockAttempDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UnlockAttemps");
        return unlockAttempRepository.findAll(pageable)
            .map(unlockAttempMapper::toDto);
    }


    /**
     * Get one unlockAttemp by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UnlockAttempDTO> findOne(Long id) {
        log.debug("Request to get UnlockAttemp : {}", id);
        return unlockAttempRepository.findById(id)
            .map(unlockAttempMapper::toDto);
    }

    /**
     * Delete the unlockAttemp by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UnlockAttemp : {}", id);
        unlockAttempRepository.deleteById(id);
    }
}
