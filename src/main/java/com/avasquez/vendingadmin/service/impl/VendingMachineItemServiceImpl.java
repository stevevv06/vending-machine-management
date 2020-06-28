package com.avasquez.vendingadmin.service.impl;

import com.avasquez.vendingadmin.domain.VendingMachineCash;
import com.avasquez.vendingadmin.domain.VendingMachineItem;
import com.avasquez.vendingadmin.repository.VendingMachineItemRepository;
import com.avasquez.vendingadmin.service.api.VendingMachineItemService;
import com.avasquez.vendingadmin.service.dto.VendingMachineCashDTO;
import com.avasquez.vendingadmin.service.dto.VendingMachineItemDTO;
import com.avasquez.vendingadmin.service.mapper.VendingMachineItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link VendingMachineItem}.
 */
@Service
@Transactional
public class VendingMachineItemServiceImpl implements VendingMachineItemService {

    private final Logger log = LoggerFactory.getLogger(VendingMachineItemServiceImpl.class);

    private final VendingMachineItemRepository vendingMachineItemRepository;

    private final VendingMachineItemMapper vendingMachineItemMapper;

    public VendingMachineItemServiceImpl(VendingMachineItemRepository vendingMachineItemRepository, VendingMachineItemMapper vendingMachineItemMapper) {
        this.vendingMachineItemRepository = vendingMachineItemRepository;
        this.vendingMachineItemMapper = vendingMachineItemMapper;
    }

    /**
     * Save a vendingMachineItem.
     *
     * @param vendingMachineItemDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public VendingMachineItemDTO save(VendingMachineItemDTO vendingMachineItemDTO) {
        log.debug("Request to save VendingMachineItem : {}", vendingMachineItemDTO);
        VendingMachineItem vendingMachineItem = vendingMachineItemMapper.toEntity(vendingMachineItemDTO);
        vendingMachineItem = vendingMachineItemRepository.save(vendingMachineItem);
        return vendingMachineItemMapper.toDto(vendingMachineItem);
    }

    /**
     * Save a collection of VendingMachineItem.
     *
     * @param dto the entity to save.
     * @return the persisted entity.
     */
    @Override
    public List<VendingMachineItemDTO> save(List<VendingMachineItemDTO> dto) {
        log.debug("Request to save VendingMachineItemDTO : {}", dto);
        List<VendingMachineItem> ent = vendingMachineItemMapper.toEntity(dto);
        ent = vendingMachineItemRepository.saveAll(ent);
        return vendingMachineItemMapper.toDto(ent);
    }

    /**
     * Get all the vendingMachineItems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<VendingMachineItemDTO> findAll(Pageable pageable) {
        log.debug("Request to get all VendingMachineItems");
        return vendingMachineItemRepository.findAll(pageable)
            .map(vendingMachineItemMapper::toDto);
    }


    /**
     * Get one vendingMachineItem by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<VendingMachineItemDTO> find(Long id) {
        log.debug("Request to get VendingMachineItem : {}", id);
        return vendingMachineItemRepository.findById(id)
            .map(vendingMachineItemMapper::toDto);
    }

    /**
     * Delete the vendingMachineItem by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete VendingMachineItem : {}", id);
        vendingMachineItemRepository.deleteById(id);
    }
}
