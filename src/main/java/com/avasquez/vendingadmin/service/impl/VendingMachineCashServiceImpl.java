package com.avasquez.vendingadmin.service.impl;

import com.avasquez.vendingadmin.domain.VendingMachineCash;
import com.avasquez.vendingadmin.repository.VendingMachineCashRepository;
import com.avasquez.vendingadmin.service.api.VendingMachineCashService;
import com.avasquez.vendingadmin.service.dto.VendingMachineCashDTO;
import com.avasquez.vendingadmin.service.mapper.VendingMachineCashMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link VendingMachineCash}.
 */
@Service
@Transactional
public class VendingMachineCashServiceImpl implements VendingMachineCashService {

    private final Logger log = LoggerFactory.getLogger(VendingMachineCashServiceImpl.class);

    private final VendingMachineCashRepository vendingMachineCashRepository;

    private final VendingMachineCashMapper vendingMachineCashMapper;

    public VendingMachineCashServiceImpl(VendingMachineCashRepository vendingMachineCashRepository, VendingMachineCashMapper vendingMachineCashMapper) {
        this.vendingMachineCashRepository = vendingMachineCashRepository;
        this.vendingMachineCashMapper = vendingMachineCashMapper;
    }

    /**
     * Save a vendingMachineCash.
     *
     * @param vendingMachineCashDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public VendingMachineCashDTO save(VendingMachineCashDTO vendingMachineCashDTO) {
        log.debug("Request to save VendingMachineCash : {}", vendingMachineCashDTO);
        VendingMachineCash vendingMachineCash = vendingMachineCashMapper.toEntity(vendingMachineCashDTO);
        vendingMachineCash = vendingMachineCashRepository.save(vendingMachineCash);
        return vendingMachineCashMapper.toDto(vendingMachineCash);
    }

    /**
     * Get all the vendingMachineCashes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<VendingMachineCashDTO> findAll(Pageable pageable) {
        log.debug("Request to get all VendingMachineCashes");
        return vendingMachineCashRepository.findAll(pageable)
            .map(vendingMachineCashMapper::toDto);
    }


    /**
     * Get one vendingMachineCash by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<VendingMachineCashDTO> find(Long id) {
        log.debug("Request to get VendingMachineCash : {}", id);
        return vendingMachineCashRepository.findById(id)
            .map(vendingMachineCashMapper::toDto);
    }

    /**
     * Delete the vendingMachineCash by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete VendingMachineCash : {}", id);
        vendingMachineCashRepository.deleteById(id);
    }
}
