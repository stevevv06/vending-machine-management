package com.avasquez.vendingadmin.service.mapper;


import com.avasquez.vendingadmin.domain.*;
import com.avasquez.vendingadmin.service.dto.VendingMachineCashDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link VendingMachineCash} and its DTO {@link VendingMachineCashDTO}.
 */
@Mapper(componentModel = "spring", uses = {VendingMachineMapper.class, CoinTypeMapper.class, BillTypeMapper.class})
public interface VendingMachineCashMapper extends EntityMapper<VendingMachineCashDTO, VendingMachineCash> {

    @Mapping(source = "vendingMachine.id", target = "vendingMachineId")
    @Mapping(source = "coinType.id", target = "coinTypeId")
    @Mapping(source = "coinType.name", target = "coinTypeName")
    @Mapping(source = "coinType.value", target = "coinTypeValue")
    @Mapping(source = "billType.id", target = "billTypeId")
    @Mapping(source = "billType.name", target = "billTypeName")
    @Mapping(source = "billType.value", target = "billTypeValue")
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
