package com.avasquez.vendingadmin.service.mapper;


import com.avasquez.vendingadmin.domain.VendingMachine;
import com.avasquez.vendingadmin.service.dto.VendingMachineDTO;
import com.avasquez.vendingadmin.service.dto.VendingMachineWithItemsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link VendingMachine} and its DTO {@link VendingMachineDTO}.
 */
@Mapper(componentModel = "spring", uses = {VendingMachineModelMapper.class, VendingMachineItemMapper.class})
public interface VendingMachineWithItemsMapper extends EntityMapper<VendingMachineWithItemsDTO, VendingMachine> {

    @Mapping(source = "vendingMachineItems", target = "vendingMachineItems")
    VendingMachineWithItemsDTO toDto(VendingMachine vendingMachine);

    @Mapping(source = "vendingMachineItems", target = "vendingMachineItems")
    @Mapping(target = "vendingMachineCashes", ignore = true)
    @Mapping(target = "vendingMachineTransactions", ignore = true)
    @Mapping(target = "unlockAttemps", ignore = true)
    @Mapping(target = "collectionAlerts", ignore = true)
    VendingMachine toEntity(VendingMachineWithItemsDTO vendingMachineDTO);

    default VendingMachine fromId(Long id) {
        if (id == null) {
            return null;
        }
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.setId(id);
        return vendingMachine;
    }
}
