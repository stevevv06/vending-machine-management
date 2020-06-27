package com.avasquez.vendingadmin.service.mapper;


import com.avasquez.vendingadmin.domain.*;
import com.avasquez.vendingadmin.service.dto.VendingMachineModelDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link VendingMachineModel} and its DTO {@link VendingMachineModelDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface VendingMachineModelMapper extends EntityMapper<VendingMachineModelDTO, VendingMachineModel> {


    @Mapping(target = "vendingMachines", ignore = true)
    VendingMachineModel toEntity(VendingMachineModelDTO vendingMachineModelDTO);

    default VendingMachineModel fromId(Long id) {
        if (id == null) {
            return null;
        }
        VendingMachineModel vendingMachineModel = new VendingMachineModel();
        vendingMachineModel.setId(id);
        return vendingMachineModel;
    }
}
