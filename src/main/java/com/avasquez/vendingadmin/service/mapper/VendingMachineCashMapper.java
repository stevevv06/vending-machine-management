package com.avasquez.service.mapper;


import com.avasquez.domain.*;
import com.avasquez.service.dto.VendingMachineCashDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link VendingMachineCash} and its DTO {@link VendingMachineCashDTO}.
 */
@Mapper(componentModel = "spring", uses = {VendingMachineMapper.class, CoinTypeMapper.class, BillTypeMapper.class})
public interface VendingMachineCashMapper extends EntityMapper<VendingMachineCashDTO, VendingMachineCash> {

    @Mapping(source = "vendingMachine.id", target = "vendingMachineId")
    @Mapping(source = "coinType.id", target = "coinTypeId")
    @Mapping(source = "billType.id", target = "billTypeId")
    VendingMachineCashDTO toDto(VendingMachineCash vendingMachineCash);

    @Mapping(source = "vendingMachineId", target = "vendingMachine")
    @Mapping(source = "coinTypeId", target = "coinType")
    @Mapping(source = "billTypeId", target = "billType")
    VendingMachineCash toEntity(VendingMachineCashDTO vendingMachineCashDTO);

    default VendingMachineCash fromId(Long id) {
        if (id == null) {
            return null;
        }
        VendingMachineCash vendingMachineCash = new VendingMachineCash();
        vendingMachineCash.setId(id);
        return vendingMachineCash;
    }
}
