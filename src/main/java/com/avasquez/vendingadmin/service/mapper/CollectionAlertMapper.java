package com.avasquez.vendingadmin.service.mapper;


import com.avasquez.vendingadmin.domain.*;
import com.avasquez.vendingadmin.service.dto.CollectionAlertDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CollectionAlert} and its DTO {@link CollectionAlertDTO}.
 */
@Mapper(componentModel = "spring", uses = {VendingMachineMapper.class})
public interface CollectionAlertMapper extends EntityMapper<CollectionAlertDTO, CollectionAlert> {

    @Mapping(source = "vendingMachine.id", target = "vendingMachineId")
    CollectionAlertDTO toDto(CollectionAlert collectionAlert);

    @Mapping(source = "vendingMachineId", target = "vendingMachine")
    CollectionAlert toEntity(CollectionAlertDTO collectionAlertDTO);

    default CollectionAlert fromId(Long id) {
        if (id == null) {
            return null;
        }
        CollectionAlert collectionAlert = new CollectionAlert();
        collectionAlert.setId(id);
        return collectionAlert;
    }
}
