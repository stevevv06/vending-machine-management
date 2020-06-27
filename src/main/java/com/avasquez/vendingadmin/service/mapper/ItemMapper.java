package com.avasquez.service.mapper;


import com.avasquez.domain.*;
import com.avasquez.service.dto.ItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Item} and its DTO {@link ItemDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ItemMapper extends EntityMapper<ItemDTO, Item> {


    @Mapping(target = "vendingMachineItems", ignore = true)
    @Mapping(target = "vendingMachineTransactions", ignore = true)
    Item toEntity(ItemDTO itemDTO);

    default Item fromId(Long id) {
        if (id == null) {
            return null;
        }
        Item item = new Item();
        item.setId(id);
        return item;
    }
}
