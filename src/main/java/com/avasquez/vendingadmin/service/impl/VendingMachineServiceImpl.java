package com.avasquez.vendingadmin.service.impl;

import com.avasquez.vendingadmin.domain.UnlockAttemp;
import com.avasquez.vendingadmin.domain.VendingMachine;
import com.avasquez.vendingadmin.repository.VendingMachineRepository;
import com.avasquez.vendingadmin.service.api.*;
import com.avasquez.vendingadmin.service.dto.*;
import com.avasquez.vendingadmin.service.mapper.VendingMachineItemMapper;
import com.avasquez.vendingadmin.service.mapper.VendingMachineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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
    private VendingMachineCashService vendingMachineCashService;

    public VendingMachineServiceImpl(VendingMachineRepository vendingMachineRepository,
                                     VendingMachineMapper vendingMachineMapper) {
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
                VendingMachineDTO d = vendingMachineMapper.toDto(e);
                d.setUnlockCode(null);
                return d;
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
                VendingMachineDTO d = vendingMachineMapper.toDto(e);
                d.setUnlockCode(null);
                return d;
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

    @Override
    @Transactional(readOnly = true)
    public boolean checkUnlockCode(Long vendingMachineId, String unlockCode) {
        boolean ret = false;
        Optional<VendingMachine> opt = vendingMachineRepository.findById(vendingMachineId);
        if (opt.isPresent()) {
            VendingMachine vm = opt.get();
            if (vm.getUnlockCode().compareTo(unlockCode) == 0) {
                ret = true;
            }
        }
        return ret;
    }

    @Override
    public Page<VendingMachineReportDTO> getReport(Pageable page) {
        return vendingMachineRepository.findAll(page)
                .map(e -> {
                    VendingMachineDTO d = vendingMachineMapper.toDto(e);
                    VendingMachineReportDTO r = new VendingMachineReportDTO();
                    r.setId(d.getId());
                    r.setName(d.getName());
                    r.setStatusOnline(d.isStatusOnline());
                    r.setVendingMachineModelId(d.getVendingMachineModelId());
                    r.setVendingMachineModelName(d.getVendingMachineModelName());
                    r.setCollectionAlert(e.getCollectionAlerts() != null && e.getCollectionAlerts().size()>1);
                    Optional<VendingMachineTotalDTO> tot = vendingMachineCashService.getTotalCashByVendingMachineId(e.getId());
                    if(tot.isPresent()) {
                        r.setTotalCash(tot.get().getTotal());
                    } else {
                        r.setTotalCash(BigDecimal.ZERO);
                    }

                    return r;
                });
    }

    @Autowired
    public void setVendingMachineCashService(VendingMachineCashService vendingMachineCashService) {
        this.vendingMachineCashService = vendingMachineCashService;
    }
}
