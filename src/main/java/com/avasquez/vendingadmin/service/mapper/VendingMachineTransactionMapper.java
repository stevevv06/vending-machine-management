package com.avasquez.service.mapper;


import com.avasquez.domain.*;
import com.avasquez.service.dto.VendingMachineTransactionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link VendingMachineTransaction} and its DTO {@link VendingMachineTransactionDTO}.
 */
@Mapper(componentModel = "spring", uses = {VendingMachineMapper.class, ItemMapper.class})
public interface VendingMachineTransactionMapper extends EntityMapper<VendingMachineTransactionDTO, VendingMachineTransaction> {

    @Mapping(source = "vendingMachine.id", target = "vendingMachineId")
    @Mapping(source = "item.id", target = "itemId")
    VendingMachineTransactionDTO toDto(VendingMachineTransaction vendingMachineTransaction);

    @Mapping(source = "vendingMachineId", target = "vendingMachine")
    @Mapping(source = "itemId", target = "item")
    VendingMachineTransaction toEntity(VendingMachineTransactionDTO vendingMachineTransactionDTO);

    default VendingMachineTransaction fromId(Long id) {
        if (id == null) {
            return null;
        }
        VendingMachineTransaction vendingMachineTransaction = new VendingMachineTransaction();
        vendingMachineTransaction.setId(id);
        return vendingMachineTransaction;
    }
}
