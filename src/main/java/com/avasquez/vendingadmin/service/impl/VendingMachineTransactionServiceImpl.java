package com.avasquez.service.impl;

import com.avasquez.service.VendingMachineTransactionService;
import com.avasquez.domain.VendingMachineTransaction;
import com.avasquez.repository.VendingMachineTransactionRepository;
import com.avasquez.service.dto.VendingMachineTransactionDTO;
import com.avasquez.service.mapper.VendingMachineTransactionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link VendingMachineTransaction}.
 */
@Service
@Transactional
public class VendingMachineTransactionServiceImpl implements VendingMachineTransactionService {

    private final Logger log = LoggerFactory.getLogger(VendingMachineTransactionServiceImpl.class);

    private final VendingMachineTransactionRepository vendingMachineTransactionRepository;

    private final VendingMachineTransactionMapper vendingMachineTransactionMapper;

    public VendingMachineTransactionServiceImpl(VendingMachineTransactionRepository vendingMachineTransactionRepository, VendingMachineTransactionMapper vendingMachineTransactionMapper) {
        this.vendingMachineTransactionRepository = vendingMachineTransactionRepository;
        this.vendingMachineTransactionMapper = vendingMachineTransactionMapper;
    }

    /**
     * Save a vendingMachineTransaction.
     *
     * @param vendingMachineTransactionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public VendingMachineTransactionDTO save(VendingMachineTransactionDTO vendingMachineTransactionDTO) {
        log.debug("Request to save VendingMachineTransaction : {}", vendingMachineTransactionDTO);
        VendingMachineTransaction vendingMachineTransaction = vendingMachineTransactionMapper.toEntity(vendingMachineTransactionDTO);
        vendingMachineTransaction = vendingMachineTransactionRepository.save(vendingMachineTransaction);
        return vendingMachineTransactionMapper.toDto(vendingMachineTransaction);
    }

    /**
     * Get all the vendingMachineTransactions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<VendingMachineTransactionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all VendingMachineTransactions");
        return vendingMachineTransactionRepository.findAll(pageable)
            .map(vendingMachineTransactionMapper::toDto);
    }


    /**
     * Get one vendingMachineTransaction by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<VendingMachineTransactionDTO> findOne(Long id) {
        log.debug("Request to get VendingMachineTransaction : {}", id);
        return vendingMachineTransactionRepository.findById(id)
            .map(vendingMachineTransactionMapper::toDto);
    }

    /**
     * Delete the vendingMachineTransaction by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete VendingMachineTransaction : {}", id);
        vendingMachineTransactionRepository.deleteById(id);
    }
}