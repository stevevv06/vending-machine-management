package com.avasquez.vendingadmin.service.impl;

import com.avasquez.vendingadmin.domain.VendingMachine;
import com.avasquez.vendingadmin.domain.VendingMachineTransaction;
import com.avasquez.vendingadmin.repository.VendingMachineTransactionRepository;
import com.avasquez.vendingadmin.service.api.VendingMachineTransactionService;
import com.avasquez.vendingadmin.service.dto.VendingMachineDTO;
import com.avasquez.vendingadmin.service.dto.VendingMachineTransactionDTO;
import com.avasquez.vendingadmin.service.mapper.VendingMachineTransactionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
     * Save a collection of VendingMachineTransaction.
     *
     * @param dto the entity to save.
     * @return the persisted entity.
     */
    @Override
    public List<VendingMachineTransactionDTO> save(List<VendingMachineTransactionDTO> dto) {
        log.debug("Request to save VendingMachineTransactionDTO : {}", dto);
        List<VendingMachineTransaction> ent = vendingMachineTransactionMapper.toEntity(dto);
        ent = vendingMachineTransactionRepository.saveAll(ent);
        return vendingMachineTransactionMapper.toDto(ent);
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
    public Optional<VendingMachineTransactionDTO> find(Long id) {
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
