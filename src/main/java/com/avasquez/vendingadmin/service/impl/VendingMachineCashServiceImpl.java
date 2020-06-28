package com.avasquez.vendingadmin.service.impl;

import com.avasquez.vendingadmin.domain.UnlockAttemp;
import com.avasquez.vendingadmin.domain.VendingMachineCash;
import com.avasquez.vendingadmin.repository.VendingMachineCashRepository;
import com.avasquez.vendingadmin.service.api.VendingMachineCashService;
import com.avasquez.vendingadmin.service.api.VendingMachineService;
import com.avasquez.vendingadmin.service.dto.*;
import com.avasquez.vendingadmin.service.mapper.VendingMachineCashMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
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
    private final VendingMachineService vendingMachineService;

    public VendingMachineCashServiceImpl(
            VendingMachineCashRepository vendingMachineCashRepository,
            VendingMachineCashMapper vendingMachineCashMapper,
            VendingMachineService vendingMachineService) {
        this.vendingMachineCashRepository = vendingMachineCashRepository;
        this.vendingMachineCashMapper = vendingMachineCashMapper;
        this.vendingMachineService = vendingMachineService;
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
     * Save a collection of VendingMachineCash.
     *
     * @param dto the entity to save.
     * @return the persisted entity.
     */
    @Override
    public List<VendingMachineCashDTO> save(List<VendingMachineCashDTO> dto) {
        log.debug("Request to save VendingMachineCashDTO : {}", dto);
        List<VendingMachineCash> ent = vendingMachineCashMapper.toEntity(dto);
        ent = vendingMachineCashRepository.saveAll(ent);
        return vendingMachineCashMapper.toDto(ent);
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

    @Override
    @Transactional(readOnly = true)
    public Page<VendingMachineCashDTO> findAllByVendingMachineId(Long id, Pageable pageable) {
        return vendingMachineCashRepository.findAllByVendingMachineId(id, pageable)
                .map(vendingMachineCashMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VendingMachineTotalDTO> getTotalCashByVendingMachineId(Long vendingMachineId) {
        Optional<VendingMachineTotalDTO> ret = Optional.empty();
        Optional<BigDecimal> tc = vendingMachineCashRepository.getTotalCoinByVendingMachineId(vendingMachineId);
        Optional<BigDecimal> tb = vendingMachineCashRepository.getTotalBillByVendingMachineId(vendingMachineId);
        BigDecimal tot = BigDecimal.ZERO;
        if(tc.isPresent() || tb.isPresent()) {
            VendingMachineTotalDTO d = new VendingMachineTotalDTO();
            VendingMachineDTO vmd = vendingMachineService.find(vendingMachineId).get();
            d.setId(vmd.getId());
            d.setName(vmd.getName());
            if(tc.isPresent()) tot = tot.add(tc.get());
            if(tb.isPresent()) tot = tot.add(tb.get());
            d.setTotal(tot);
            ret = Optional.of(d);
        }
        return ret;
    }
}
