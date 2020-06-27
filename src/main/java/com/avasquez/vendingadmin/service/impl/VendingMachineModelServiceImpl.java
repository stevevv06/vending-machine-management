package com.avasquez.vendingadmin.service.impl;

import com.avasquez.vendingadmin.domain.VendingMachineModel;
import com.avasquez.vendingadmin.repository.VendingMachineModelRepository;
import com.avasquez.vendingadmin.service.api.VendingMachineModelService;
import com.avasquez.vendingadmin.service.dto.VendingMachineModelDTO;
import com.avasquez.vendingadmin.service.mapper.VendingMachineModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link VendingMachineModel}.
 */
@Service
@Transactional
public class VendingMachineModelServiceImpl implements VendingMachineModelService {

    private final Logger log = LoggerFactory.getLogger(VendingMachineModelServiceImpl.class);

    private final VendingMachineModelRepository vendingMachineModelRepository;

    private final VendingMachineModelMapper vendingMachineModelMapper;

    public VendingMachineModelServiceImpl(VendingMachineModelRepository vendingMachineModelRepository, VendingMachineModelMapper vendingMachineModelMapper) {
        this.vendingMachineModelRepository = vendingMachineModelRepository;
        this.vendingMachineModelMapper = vendingMachineModelMapper;
    }

    /**
     * Save a vendingMachineModel.
     *
     * @param vendingMachineModelDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public VendingMachineModelDTO save(VendingMachineModelDTO vendingMachineModelDTO) {
        log.debug("Request to save VendingMachineModel : {}", vendingMachineModelDTO);
        VendingMachineModel vendingMachineModel = vendingMachineModelMapper.toEntity(vendingMachineModelDTO);
        vendingMachineModel = vendingMachineModelRepository.save(vendingMachineModel);
        return vendingMachineModelMapper.toDto(vendingMachineModel);
    }

    /**
     * Get all the vendingMachineModels.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<VendingMachineModelDTO> findAll(Pageable pageable) {
        log.debug("Request to get all VendingMachineModels");
        return vendingMachineModelRepository.findAll(pageable)
            .map(vendingMachineModelMapper::toDto);
    }


    /**
     * Get one vendingMachineModel by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<VendingMachineModelDTO> find(Long id) {
        log.debug("Request to get VendingMachineModel : {}", id);
        return vendingMachineModelRepository.findById(id)
            .map(vendingMachineModelMapper::toDto);
    }

    /**
     * Delete the vendingMachineModel by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete VendingMachineModel : {}", id);
        vendingMachineModelRepository.deleteById(id);
    }
}
