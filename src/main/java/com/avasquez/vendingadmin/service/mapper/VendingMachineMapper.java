package com.avasquez.vendingadmin.service.mapper;


import com.avasquez.vendingadmin.domain.VendingMachine;
import com.avasquez.vendingadmin.service.dto.VendingMachineDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link VendingMachine} and its DTO {@link VendingMachineDTO}.
 */
@Mapper(componentModel = "spring", uses = {VendingMachineModelMapper.class})
public interface VendingMachineMapper extends EntityMapper<VendingMachineDTO, VendingMachine> {

    @Mapping(source = "vendingMachineModel.id", target = "vendingMachineModelId")
    @Mapping(source = "vendingMachineModel.name", target = "vendingMachineModelName")
    VendingMachineDTO toDto(VendingMachine vendingMachine);

    @Mapping(target = "vendingMachineItems", ignore = true)
    @Mapping(target = "vendingMachineCashes", ignore = true)
    @Mapping(target = "vendingMachineTransactions", ignore = true)
    @Mapping(target = "unlockAttemps", ignore = true)
    @Mapping(target = "collectionAlerts", ignore = true)
    @Mapping(source = "vendingMachineModelId", target = "vendingMachineModel")
    VendingMachine toEntity(VendingMachineDTO vendingMachineDTO);

    default VendingMachine fromId(Long id) {
        if (id == null) {
            return null;
        }
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.setId(id);
        return vendingMachine;
    }
}
