package com.avasquez.vendingadmin.service.impl;

import com.avasquez.vendingadmin.domain.VendingMachine;
import com.avasquez.vendingadmin.repository.VendingMachineRepository;
import com.avasquez.vendingadmin.service.api.VendingMachineService;
import com.avasquez.vendingadmin.service.dto.VendingMachineDTO;
import com.avasquez.vendingadmin.service.mapper.VendingMachineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link VendingMachine}.
 */
@Service
@Transactional
public class VendingMachineServiceImpl implements VendingMachineService {

    private final Logger log = LoggerFactory.getLogger(VendingMachineServiceImpl.class);

    private final VendingMachineRepository vendingMachineRepository;

    private final VendingMachineMapper vendingMachineMapper;

    public VendingMachineServiceImpl(VendingMachineRepository vendingMachineRepository, VendingMachineMapper vendingMachineMapper) {
        this.vendingMachineRepository = vendingMachineRepository;
        this.vendingMachineMapper = vendingMachineMapper;
    }

    /**
     * Save a vendingMachine.
     *
     * @param vendingMachineDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public VendingMachineDTO save(VendingMachineDTO vendingMachineDTO) {
        log.debug("Request to save VendingMachine : {}", vendingMachineDTO);
        VendingMachine vendingMachine = vendingMachineMapper.toEntity(vendingMachineDTO);
        vendingMachine = vendingMachineRepository.save(vendingMachine);
        return vendingMachineMapper.toDto(vendingMachine);
    }

    /**
     * Get all the vendingMachines.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<VendingMachineDTO> findAll(Pageable pageable) {
        log.debug("Request to get all VendingMachines");
        return vendingMachineRepository.findAll(pageable)
            .map(vendingMachineMapper::toDto);
    }


    /**
     * Get one vendingMachine by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<VendingMachineDTO> find(Long id) {
        log.debug("Request to get VendingMachine : {}", id);
        return vendingMachineRepository.findById(id)
            .map(vendingMachineMapper::toDto);
    }

    /**
     * Delete the vendingMachine by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete VendingMachine : {}", id);
        vendingMachineRepository.deleteById(id);
    }
}
