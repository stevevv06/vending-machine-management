package com.avasquez.vendingadmin.service.impl;

import com.avasquez.vendingadmin.domain.CoinType;
import com.avasquez.vendingadmin.repository.CoinTypeRepository;
import com.avasquez.vendingadmin.service.api.CoinTypeService;
import com.avasquez.vendingadmin.service.dto.CoinTypeDTO;
import com.avasquez.vendingadmin.service.mapper.CoinTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link CoinType}.
 */
@Service
@Transactional
public class CoinTypeServiceImpl implements CoinTypeService {

    private final Logger log = LoggerFactory.getLogger(CoinTypeServiceImpl.class);

    private final CoinTypeRepository coinTypeRepository;

    private final CoinTypeMapper coinTypeMapper;

    public CoinTypeServiceImpl(CoinTypeRepository coinTypeRepository, CoinTypeMapper coinTypeMapper) {
        this.coinTypeRepository = coinTypeRepository;
        this.coinTypeMapper = coinTypeMapper;
    }

    /**
     * Save a coinType.
     *
     * @param coinTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CoinTypeDTO save(CoinTypeDTO coinTypeDTO) {
        log.debug("Request to save CoinType : {}", coinTypeDTO);
        CoinType coinType = coinTypeMapper.toEntity(coinTypeDTO);
        coinType = coinTypeRepository.save(coinType);
        return coinTypeMapper.toDto(coinType);
    }

    /**
     * Save a collection of coinType.
     *
     * @param dto the entity to save.
     * @return the persisted entity.
     */
    @Override
    public List<CoinTypeDTO> save(List<CoinTypeDTO> dto) {
        log.debug("Request to save CoinTypeDTO : {}", dto);
        List<CoinType> ent = coinTypeMapper.toEntity(dto);
        ent = coinTypeRepository.saveAll(ent);
        return coinTypeMapper.toDto(ent);
    }

    /**
     * Get all the coinTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CoinTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CoinTypes");
        return coinTypeRepository.findAll(pageable)
            .map(coinTypeMapper::toDto);
    }


    /**
     * Get one coinType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CoinTypeDTO> find(Long id) {
        log.debug("Request to get CoinType : {}", id);
        return coinTypeRepository.findById(id)
            .map(coinTypeMapper::toDto);
    }

    /**
     * Delete the coinType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CoinType : {}", id);
        coinTypeRepository.deleteById(id);
    }
}
