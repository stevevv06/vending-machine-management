package com.avasquez.vendingadmin.service.impl;

import com.avasquez.vendingadmin.domain.CollectionAlert;
import com.avasquez.vendingadmin.domain.VendingMachine_;
import com.avasquez.vendingadmin.repository.CollectionAlertRepository;
import com.avasquez.vendingadmin.repository.VendingMachineCashRepository;
import com.avasquez.vendingadmin.service.api.CollectionAlertService;
import com.avasquez.vendingadmin.service.api.VendingMachineCashService;
import com.avasquez.vendingadmin.service.dto.CollectionAlertDTO;
import com.avasquez.vendingadmin.service.dto.VendingMachineTotalDTO;
import com.avasquez.vendingadmin.service.mapper.CollectionAlertMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link CollectionAlert}.
 */
@Service
@Transactional
public class CollectionAlertServiceImpl implements CollectionAlertService {

    private final Logger log = LoggerFactory.getLogger(CollectionAlertServiceImpl.class);
    private final CollectionAlertRepository collectionAlertRepository;
    private final CollectionAlertMapper collectionAlertMapper;
    private VendingMachineCashService vendingMachineCashService;

    public CollectionAlertServiceImpl(
            CollectionAlertRepository collectionAlertRepository,
            CollectionAlertMapper collectionAlertMapper) {
        this.collectionAlertRepository = collectionAlertRepository;
        this.collectionAlertMapper = collectionAlertMapper;
    }

    /**
     * Save a collectionAlert.
     *
     * @param collectionAlertDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CollectionAlertDTO save(CollectionAlertDTO collectionAlertDTO) {
        log.debug("Request to save CollectionAlert : {}", collectionAlertDTO);
        CollectionAlert collectionAlert = collectionAlertMapper.toEntity(collectionAlertDTO);
        collectionAlert = collectionAlertRepository.save(collectionAlert);
        return collectionAlertMapper.toDto(collectionAlert);
    }

    /**
     * Save a collection of coinType.
     *
     * @param dto the entity to save.
     * @return the persisted entity.
     */
    @Override
    public List<CollectionAlertDTO> save(List<CollectionAlertDTO> dto) {
        log.debug("Request to save CollectionAlertDTO : {}", dto);
        List<CollectionAlert> ent = collectionAlertMapper.toEntity(dto);
        ent = collectionAlertRepository.saveAll(ent);
        return collectionAlertMapper.toDto(ent);
    }

    /**
     * Get all the collectionAlerts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CollectionAlertDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CollectionAlerts");
        return collectionAlertRepository.findAll(pageable)
            .map(collectionAlertMapper::toDto);
    }


    /**
     * Get one collectionAlert by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CollectionAlertDTO> find(Long id) {
        log.debug("Request to get CollectionAlert : {}", id);
        return collectionAlertRepository.findById(id)
            .map(collectionAlertMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public Optional<CollectionAlertDTO> find(Long vendingMachineId, LocalDate alertDate) {
        log.debug("Request to get CollectionAlert : {} {}", vendingMachineId, alertDate);
        return collectionAlertRepository.findByVendingMachineIdAndAlertDate(
                vendingMachineId, alertDate)
                .map(collectionAlertMapper::toDto);
    }

    /**
     * Delete the collectionAlert by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CollectionAlert : {}", id);
        collectionAlertRepository.deleteById(id);
    }

    @Override
    public void checkCashAndTriggerAlert(Long vendingMachineId) {
        Optional<VendingMachineTotalDTO> totOpt = vendingMachineCashService.getTotalCashByVendingMachineId(vendingMachineId);
        if(totOpt.isPresent()) {
            BigDecimal tot = totOpt.get().getTotal();
            if(tot.compareTo(new BigDecimal("100")) >= 0) {
                CollectionAlertDTO alert = new CollectionAlertDTO();
                alert.setAlertDate(LocalDate.now());
                alert.setVendingMachineId(vendingMachineId);
                Optional<CollectionAlertDTO> existing = find(alert.getVendingMachineId(), alert.getAlertDate());
                if(!existing.isPresent())
                    this.save(alert);
            }
        }
    }

    @Autowired
    public void setVendingMachineCashService(VendingMachineCashService vendingMachineCashService) {
        this.vendingMachineCashService = vendingMachineCashService;
    }

}
