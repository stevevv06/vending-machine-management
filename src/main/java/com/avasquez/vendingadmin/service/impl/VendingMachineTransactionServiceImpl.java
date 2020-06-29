package com.avasquez.vendingadmin.service.impl;

import com.avasquez.vendingadmin.domain.*;
import com.avasquez.vendingadmin.domain.enumeration.PaymentType;
import com.avasquez.vendingadmin.repository.*;
import com.avasquez.vendingadmin.service.api.CollectionAlertService;
import com.avasquez.vendingadmin.service.api.VendingMachineCashService;
import com.avasquez.vendingadmin.service.api.VendingMachineTransactionService;
import com.avasquez.vendingadmin.service.dto.VendingMachineCashDTO;
import com.avasquez.vendingadmin.service.dto.VendingMachineTotalDTO;
import com.avasquez.vendingadmin.service.dto.VendingMachineTransactionDTO;
import com.avasquez.vendingadmin.service.dto.VendingMachineTransactionSaleDTO;
import com.avasquez.vendingadmin.service.mapper.VendingMachineTransactionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link VendingMachineTransaction}.
 */
@Service
@Transactional
public class VendingMachineTransactionServiceImpl implements VendingMachineTransactionService {

    private final Logger log = LoggerFactory.getLogger(VendingMachineTransactionServiceImpl.class);
    private final VendingMachineTransactionRepository vendingMachineTransactionRepository;
    private final VendingMachineTransactionMapper vendingMachineTransactionMapper;
    private final VendingMachineCashService vendingMachineCashService;
    private final CollectionAlertService collectionAlertService;
    private final VendingMachineRepository vendingMachineRepository;
    private final CoinTypeRepository coinTypeRepository;
    private final BillTypeRepository billTypeRepository;
    private final ItemRepository itemRepository;

    public VendingMachineTransactionServiceImpl(
            VendingMachineTransactionRepository vendingMachineTransactionRepository,
            VendingMachineTransactionMapper vendingMachineTransactionMapper,
            VendingMachineCashService vendingMachineCashService,
            CollectionAlertService collectionAlertService,
            VendingMachineRepository vendingMachineRepository,
            CoinTypeRepository coinTypeRepository,
            BillTypeRepository billTypeRepository,
            ItemRepository itemRepository
    ) {
        this.vendingMachineTransactionRepository = vendingMachineTransactionRepository;
        this.vendingMachineTransactionMapper = vendingMachineTransactionMapper;
        this.vendingMachineCashService = vendingMachineCashService;
        this.collectionAlertService = collectionAlertService;
        this.vendingMachineRepository = vendingMachineRepository;
        this.coinTypeRepository = coinTypeRepository;
        this.billTypeRepository = billTypeRepository;
        this.itemRepository = itemRepository;
    }

    /**
     * Save a vendingMachineTransaction.
     *
     * @param vendingMachineTransactionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public VendingMachineTransactionDTO save(VendingMachineTransactionDTO vendingMachineTransactionDTO) {
        log.debug("Request to save VendingMachineTransaction : {}", vendingMachineTransactionDTO);
        if(!validatePaymentMethod(vendingMachineTransactionDTO.getVendingMachineId(), vendingMachineTransactionDTO.getPaymentType())) {
            throw new RuntimeException("Payment Method Not Supported");
        }
        VendingMachineTransaction vendingMachineTransaction = vendingMachineTransactionMapper.toEntity(vendingMachineTransactionDTO);
        vendingMachineTransaction = vendingMachineTransactionRepository.save(vendingMachineTransaction);
        //Verify cash in machine and sends alert if apply
        collectionAlertService.checkCashAndTriggerAlert(vendingMachineTransactionDTO.getVendingMachineId());
        return vendingMachineTransactionMapper.toDto(vendingMachineTransaction);
    }

    /**
     * Save a collection of VendingMachineTransaction.
     *
     * @param dto the entity to save.
     * @return the persisted entity.
     */
    @Override
    public List<VendingMachineTransactionDTO> save(List<VendingMachineTransactionDTO> dto) {
        log.debug("Request to save VendingMachineTransactionDTO : {}", dto);
        dto.forEach(d -> {
            if(!validatePaymentMethod(d.getVendingMachineId(), d.getPaymentType())) {
                throw new RuntimeException("Payment Method Not Supported");
            }
        });
        List<VendingMachineTransaction> ent = vendingMachineTransactionMapper.toEntity(dto);
        ent = vendingMachineTransactionRepository.saveAll(ent);
        return vendingMachineTransactionMapper.toDto(ent);
    }

    /**
     * Get all the vendingMachineTransactions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<VendingMachineTransactionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all VendingMachineTransactions");
        return vendingMachineTransactionRepository.findAll(pageable)
            .map(vendingMachineTransactionMapper::toDto);
    }


    /**
     * Get one vendingMachineTransaction by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<VendingMachineTransactionDTO> find(Long id) {
        log.debug("Request to get VendingMachineTransaction : {}", id);
        return vendingMachineTransactionRepository.findById(id)
            .map(vendingMachineTransactionMapper::toDto);
    }

    /**
     * Delete the vendingMachineTransaction by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete VendingMachineTransaction : {}", id);
        vendingMachineTransactionRepository.deleteById(id);
    }

    @Override
    public VendingMachineTransactionDTO save(VendingMachineTransactionSaleDTO dto) {
        if(!validatePaymentMethod(dto.getVendingMachineId(), dto.getPaymentType())) {
            throw new RuntimeException("Payment Method Not Supported");
        }
        VendingMachineTransactionDTO d = new VendingMachineTransactionDTO();
        List<VendingMachineCashDTO> changeDetail = null;
        d.setItemQuantity(dto.getItemQuantity());
        d.setPaymentType(dto.getPaymentType());
        d.setVendingMachineId((dto.getVendingMachineId()));
        if(dto.getItemId() != null) {
            d.setItemId(dto.getItemId());
        } else if(dto.getItemCode() != null && dto.getItemId() == null) {
            Optional<Item> opti = itemRepository.findByCode(dto.getItemCode());
            if(opti.isPresent()) {
                d.setItemId(opti.get().getId());
            } else {
                throw new RuntimeException("Item Code not found: " + dto.getItemCode());
            }
        }

        d.setTransactionDate(LocalDate.now());
        if(dto.getPaymentType() == PaymentType.CREDIT_CARD) {
            d.setCashInAmount(BigDecimal.ZERO);
            d.setCashChange(BigDecimal.ZERO);
        } else {
            List<VendingMachineCashDTO> cashList = dto.getVendingMachineCashs();
            for(VendingMachineCashDTO c : cashList) {
                c.setVendingMachineId(dto.getVendingMachineId());
                if (c.getBillTypeValue() == null && c.getBillTypeId() != null) {
                    BillType bt = billTypeRepository.findById(c.getBillTypeId()).get();
                    c.setBillTypeValue(bt.getValue());

                }
                if (c.getCoinTypeValue() == null && c.getCoinTypeId() != null) {
                    CoinType ct = coinTypeRepository.findById(c.getCoinTypeId()).get();
                    c.setCoinTypeValue(ct.getValue());
                }
            }
            vendingMachineCashService.save(cashList);

            BigDecimal cashIn = dto.getVendingMachineCashs().stream()
                    .map(c -> c.getBillTypeId() != null
                                ? c.getBillTypeValue().multiply(new BigDecimal(c.getBillQuantity()))
                                : c.getCoinTypeValue().multiply(new BigDecimal(c.getCoinQuantity()))
                    ).reduce(BigDecimal.ZERO, BigDecimal::add);

            Item item = itemRepository.findById(d.getItemId()).get();
            d.setPaymentAmount(item.getPrice().multiply(new BigDecimal(d.getItemQuantity())));

            BigDecimal change = cashIn.subtract(d.getPaymentAmount());
            d.setCashInAmount(cashIn);
            d.setCashChange(change);

            if(change.compareTo(BigDecimal.ZERO) > 0) {
                changeDetail = vendingMachineCashService.dischargeChange(d.getVendingMachineId(), change);
            }
        }
        d = this.save(d);
        d.setChangeDetail(changeDetail);
        return d;
    }

    @Transactional(readOnly = true)
    private boolean validatePaymentMethod(Long vendingMachineId, PaymentType paymentType) {
        boolean valid = false;
        Optional<VendingMachine> vmopt = vendingMachineRepository.findById(vendingMachineId);
        if(vmopt.isPresent()) {
            VendingMachine vm = vmopt.get();
            if(paymentType == PaymentType.CASH && vm.getVendingMachineModel().isAcceptsBills() && vm.getVendingMachineModel().isAcceptsCoins()) {
                valid = true;
            }
            if (paymentType == PaymentType.CREDIT_CARD && vm.getVendingMachineModel().isAcceptsCreditCard()) {
                valid = true;
            }
        }
        return valid;
    }

    @Override
    public Optional<VendingMachineTotalDTO> getTotalProfitByVendingMachineId(Long vendingMachineId) {
        VendingMachineTotalDTO ret = new VendingMachineTotalDTO();
        ret.setId(vendingMachineId);
        Optional<BigDecimal> opt = vendingMachineTransactionRepository.getTotalProfitByVendingMachineId(vendingMachineId);
        if(opt.isPresent()) {
            VendingMachine vmd = vendingMachineRepository.findById(vendingMachineId).get();
            ret.setId(vmd.getId());
            ret.setName(vmd.getName());
            ret.setTotal(opt.get());
        } else {
            ret.setTotal(BigDecimal.ZERO);
        }
        return Optional.of(ret);
    }

    @Override
    public Optional<VendingMachineTotalDTO> getCountTransactions(Long vendingMachineId, LocalDate transactionDate) {
        VendingMachineTotalDTO ret = new VendingMachineTotalDTO();
        ret.setId(vendingMachineId);
        Optional<BigDecimal> opt = vendingMachineTransactionRepository.getCountByVendingMachineIdAndTransactionDate(vendingMachineId, transactionDate);
        if(opt.isPresent()) {
            VendingMachine vmd = vendingMachineRepository.findById(vendingMachineId).get();
            ret.setId(vmd.getId());
            ret.setName(vmd.getName());
            ret.setTotal(opt.get());
        } else {
            ret.setTotal(BigDecimal.ZERO);
        }
        return Optional.of(ret);
    }
}
