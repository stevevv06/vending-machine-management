package com.avasquez.vendingadmin.service.impl;

import com.avasquez.vendingadmin.domain.BillType;
import com.avasquez.vendingadmin.domain.CoinType;
import com.avasquez.vendingadmin.domain.VendingMachineCash;
import com.avasquez.vendingadmin.repository.BillTypeRepository;
import com.avasquez.vendingadmin.repository.CoinTypeRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private final CoinTypeRepository coinTypeRepository;
    private final BillTypeRepository billTypeRepository;

    public VendingMachineCashServiceImpl(
            VendingMachineCashRepository vendingMachineCashRepository,
            VendingMachineCashMapper vendingMachineCashMapper,
            VendingMachineService vendingMachineService,
            CoinTypeRepository coinTypeRepository,
            BillTypeRepository billTypeRepository) {
        this.vendingMachineCashRepository = vendingMachineCashRepository;
        this.vendingMachineCashMapper = vendingMachineCashMapper;
        this.vendingMachineService = vendingMachineService;
        this.coinTypeRepository =coinTypeRepository;
        this.billTypeRepository = billTypeRepository;
    }

    /**
     * Save a vendingMachineCash.
     *
     * @param dto the entity to save.
     * @return the persisted entity.
     */
    @Override
    public VendingMachineCashDTO save(VendingMachineCashDTO dto) {
        log.debug("Request to save VendingMachineCash : {}", dto);
        if(dto.getCoinTypeValue() != null && dto.getCoinTypeId() == null) {
            CoinType ct = coinTypeRepository.findByValue(dto.getCoinTypeValue()).get();
            dto.setCoinTypeId(ct.getId());
        }
        if(dto.getBillTypeValue() != null && dto.getBillTypeId() == null) {
            BillType ct = billTypeRepository.findByValue(dto.getBillTypeValue()).get();
            dto.setBillTypeId(ct.getId());
        }
        VendingMachineCash ent = null;
        if(dto.getCoinTypeId() != null) {
            Optional<VendingMachineCash> opt = vendingMachineCashRepository.findByVendingMachineIdAndCoinTypeId(
                    dto.getVendingMachineId(), dto.getCoinTypeId());
            if(opt.isPresent()) {
                ent = opt.get();
                ent.setCoinQuantity(ent.getCoinQuantity() + dto.getCoinQuantity());
            } else {
                 ent = vendingMachineCashMapper.toEntity(dto);
            }
        }
        if(dto.getBillTypeId() != null) {
            Optional<VendingMachineCash> opt = vendingMachineCashRepository.findByVendingMachineIdAndBillTypeId(
                    dto.getVendingMachineId(), dto.getBillTypeId());
            if(opt.isPresent()) {
                ent = opt.get();
                ent.setBillQuantity(ent.getBillQuantity() + dto.getBillQuantity());
            } else {
                ent = vendingMachineCashMapper.toEntity(dto);
            }
        }

        ent = vendingMachineCashRepository.save(ent);
        return vendingMachineCashMapper.toDto(ent);
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
        List<VendingMachineCashDTO> ret = dto.stream()
                .map(d -> this.save(d))
        .collect(Collectors.toList());
        return ret;
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

    @Override
    public List<VendingMachineCashDTO> dischargeChange(Long vendingMachineId, BigDecimal change) {
        List<VendingMachineCashDTO> ret = new ArrayList<>();
        List<VendingMachineCash> vmc = vendingMachineCashRepository.findAllByVendingMachineId(vendingMachineId, null).getContent();
        if(vmc != null) {
            vmc = vmc.stream()
                    .filter(v -> v.getCoinType() != null && v.getCoinType().getValue().compareTo(change) <= 0 && v.getCoinQuantity() > 0)
                    .sorted((v1, v2) -> v2.getCoinType().getValue().compareTo(v1.getCoinType().getValue()))
                    .collect(Collectors.toList());
            BigDecimal tot = BigDecimal.ZERO;
            for(VendingMachineCash c : vmc) {
                BigDecimal diff = change.subtract(tot);
                BigDecimal coinsRequired = diff.divideToIntegralValue(c.getCoinType().getValue());
                if(coinsRequired.compareTo(BigDecimal.ZERO) > 0) {
                    if (coinsRequired.compareTo(new BigDecimal(c.getCoinQuantity())) > 0) {
                        coinsRequired = new BigDecimal(c.getCoinQuantity());
                    }
                    VendingMachineCashDTO d = new VendingMachineCashDTO();
                    d.setVendingMachineId(vendingMachineId);
                    d.setCoinTypeId(c.getCoinType().getId());
                    d.setCoinTypeValue(c.getCoinType().getValue());
                    d.setCoinQuantity(coinsRequired.intValue());
                    ret.add(d);
                    tot = tot.add(c.getCoinType().getValue().multiply(coinsRequired));
                    if (tot.compareTo(change) == 0) break;
                }
            }
            if(tot.compareTo(change) != 0) {
                throw new RuntimeException("Not enough coins to change");
            } else {
                //discharge change
                ret.stream()
                        .map(d -> {
                            d.setCoinQuantity(d.getCoinQuantity()*-1);
                            return this.save(d);
                        })
                        .collect(Collectors.toList());
            }
        }
        return ret;
    }
}
