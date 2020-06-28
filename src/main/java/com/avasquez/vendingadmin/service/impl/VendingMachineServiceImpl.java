package com.avasquez.vendingadmin.service.impl;

import com.avasquez.vendingadmin.domain.Item;
import com.avasquez.vendingadmin.domain.VendingMachine;
import com.avasquez.vendingadmin.domain.VendingMachineModel;
import com.avasquez.vendingadmin.repository.VendingMachineRepository;
import com.avasquez.vendingadmin.service.api.VendingMachineItemService;
import com.avasquez.vendingadmin.service.api.VendingMachineService;
import com.avasquez.vendingadmin.service.dto.VendingMachineDTO;
import com.avasquez.vendingadmin.service.dto.VendingMachineItemDTO;
import com.avasquez.vendingadmin.service.dto.VendingMachineModelDTO;
import com.avasquez.vendingadmin.service.dto.VendingMachineWithItemsDTO;
import com.avasquez.vendingadmin.service.mapper.VendingMachineItemMapper;
import com.avasquez.vendingadmin.service.mapper.VendingMachineMapper;
import com.avasquez.vendingadmin.service.mapper.VendingMachineWithItemsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service Implementation for managing {@link VendingMachine}.
 */
@Service
@Transactional
public class VendingMachineServiceImpl implements VendingMachineService {

    private final Logger log = LoggerFactory.getLogger(VendingMachineServiceImpl.class);

    private final VendingMachineRepository vendingMachineRepository;
    private final VendingMachineItemService vendingMachineItemService;

    private final VendingMachineMapper vendingMachineMapper;
    private final VendingMachineWithItemsMapper vendingMachineWithItemsMapper;
    private final VendingMachineItemMapper vendingMachineItemMapper;

    public VendingMachineServiceImpl(VendingMachineRepository vendingMachineRepository,
                                     VendingMachineItemService vendingMachineItemService,
                                     VendingMachineMapper vendingMachineMapper,
                                     VendingMachineWithItemsMapper vendingMachineWithItemsMapper,
                                     VendingMachineItemMapper vendingMachineItemMapper) {
        this.vendingMachineRepository = vendingMachineRepository;
        this.vendingMachineItemService = vendingMachineItemService;
        this.vendingMachineMapper = vendingMachineMapper;
        this.vendingMachineWithItemsMapper = vendingMachineWithItemsMapper;
        this.vendingMachineItemMapper = vendingMachineItemMapper;
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
     * Save a collection of vending machines.
     *
     * @param dtos the entity to save.
     * @return the persisted entity.
     */
    @Override
    public List<VendingMachineDTO> save(List<VendingMachineDTO> dtos){
        log.debug("Request to save VendingMachine");
        List<VendingMachine> vms = vendingMachineMapper.toEntity(dtos);
        vms = vendingMachineRepository.saveAll(vms);
        return vendingMachineMapper.toDto(vms);
    }

    /**
     * Save a vendingMachine with items.
     *
     * @param dto the entity to save.
     * @return the persisted entity.
     */
    @Override
    public VendingMachineWithItemsDTO save(VendingMachineWithItemsDTO dto) {
        log.debug("Request to save VendingMachine : {}", dto);
        Optional<VendingMachine> opt = vendingMachineRepository.findById(dto.getId());
        if(opt.isPresent()) {
            List<VendingMachineItemDTO> itemsDTO = vendingMachineItemService.save(dto.getVendingMachineItems());

            VendingMachine vendingMachine = opt.get();
            List items = vendingMachineItemMapper.toEntity(itemsDTO);
            vendingMachine.setVendingMachineItems(items);
            vendingMachine = vendingMachineRepository.save(vendingMachine);
            dto = vendingMachineWithItemsMapper.toDto(vendingMachine);
        } else {
            throw new RuntimeException("VendingMachine not found");
        }
        return dto;
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
            .map(e -> {
                e.setUnlockCode(null);
                return vendingMachineMapper.toDto(e);
            });
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
            .map(e -> {
                e.setUnlockCode(null);
                return vendingMachineMapper.toDto(e);
            });
    }

    /**
     * Get one vendingMachine with items by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<VendingMachineWithItemsDTO> findWithItems(Long id) {
        log.debug("Request to get VendingMachine : {}", id);
        return vendingMachineRepository.findById(id)
                .map(e -> {
                    e.setUnlockCode(null);
                    return vendingMachineWithItemsMapper.toDto(e);
                });
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
