package com.avasquez.vendingadmin.service.impl;

import com.avasquez.vendingadmin.domain.BillType;
import com.avasquez.vendingadmin.repository.BillTypeRepository;
import com.avasquez.vendingadmin.service.api.BillTypeService;
import com.avasquez.vendingadmin.service.dto.BillTypeDTO;
import com.avasquez.vendingadmin.service.mapper.BillTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link BillType}.
 */
@Service
@Transactional
public class BillTypeServiceImpl implements BillTypeService {

    private final Logger log = LoggerFactory.getLogger(BillTypeServiceImpl.class);

    private final BillTypeRepository billTypeRepository;

    private final BillTypeMapper billTypeMapper;

    public BillTypeServiceImpl(BillTypeRepository billTypeRepository, BillTypeMapper billTypeMapper) {
        this.billTypeRepository = billTypeRepository;
        this.billTypeMapper = billTypeMapper;
    }

    /**
     * Save a billType.
     *
     * @param dto the entity to save.
     * @return the persisted entity.
     */
    @Override
    public BillTypeDTO save(BillTypeDTO dto) {
        log.debug("Request to save BillType : {}", dto);
        BillType billType = billTypeMapper.toEntity(dto);
        billType = billTypeRepository.save(billType);
        return billTypeMapper.toDto(billType);
    }

    /**
     * Save a collection of billType.
     *
     * @param dto the entity to save.
     * @return the persisted entity.
     */
    @Override
    public List<BillTypeDTO> save(List<BillTypeDTO> dto) {
        log.debug("Request to save BillType : {}", dto);
        List<BillType> billType = billTypeMapper.toEntity(dto);
        billType = billTypeRepository.saveAll(billType);
        return billTypeMapper.toDto(billType);
    }

    /**
     * Get all the billTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BillTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BillTypes");
        return billTypeRepository.findAll(pageable)
            .map(billTypeMapper::toDto);
    }


    /**
     * Get one billType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BillTypeDTO> find(Long id) {
        log.debug("Request to get BillType : {}", id);
        return billTypeRepository.findById(id)
            .map(billTypeMapper::toDto);
    }

    /**
     * Delete the billType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BillType : {}", id);
        billTypeRepository.deleteById(id);
    }
}
