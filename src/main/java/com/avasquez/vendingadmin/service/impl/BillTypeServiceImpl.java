package com.avasquez.service.impl;

import com.avasquez.service.BillTypeService;
import com.avasquez.domain.BillType;
import com.avasquez.repository.BillTypeRepository;
import com.avasquez.service.dto.BillTypeDTO;
import com.avasquez.service.mapper.BillTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * @param billTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public BillTypeDTO save(BillTypeDTO billTypeDTO) {
        log.debug("Request to save BillType : {}", billTypeDTO);
        BillType billType = billTypeMapper.toEntity(billTypeDTO);
        billType = billTypeRepository.save(billType);
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
    public Optional<BillTypeDTO> findOne(Long id) {
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
