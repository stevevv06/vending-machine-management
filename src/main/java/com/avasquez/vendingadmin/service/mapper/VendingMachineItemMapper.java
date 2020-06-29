package com.avasquez.vendingadmin.service.mapper;


import com.avasquez.vendingadmin.domain.VendingMachineItem;
import com.avasquez.vendingadmin.service.dto.VendingMachineItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link VendingMachineItem} and its DTO {@link VendingMachineItemDTO}.
 */
@Mapper(componentModel = "spring", uses = {VendingMachineMapper.class, ItemMapper.class})
public interface VendingMachineItemMapper extends EntityMapper<VendingMachineItemDTO, VendingMachineItem> {

    @Mapping(source = "vendingMachine.id", target = "vendingMachineId")
    @Mapping(source = "item.id", target = "itemId")
    @Mapping(source = "item.name", target = "itemName")
    @Mapping(source = "item.price", target = "itemPrice")
    VendingMachineItemDTO toDto(VendingMachineItem vendingMachineItem);

    @Mapping(source = "vendingMachineId", target = "vendingMachine")
    @Mapping(source = "itemId", target = "item")
    VendingMachineItem toEntity(VendingMachineItemDTO vendingMachineItemDTO);

    default VendingMachineItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        VendingMachineItem vendingMachineItem = new VendingMachineItem();
        vendingMachineItem.setId(id);
        return vendingMachineItem;
    }
}
