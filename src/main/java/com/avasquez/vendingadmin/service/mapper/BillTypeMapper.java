package com.avasquez.vendingadmin.service.mapper;


import com.avasquez.vendingadmin.domain.*;
import com.avasquez.vendingadmin.service.dto.BillTypeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BillType} and its DTO {@link BillTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BillTypeMapper extends EntityMapper<BillTypeDTO, BillType> {


    @Mapping(target = "vendingMachineCashes", ignore = true)
    BillType toEntity(BillTypeDTO billTypeDTO);

    default BillType fromId(Long id) {
        if (id == null) {
            return null;
        }
        BillType billType = new BillType();
        billType.setId(id);
        return billType;
    }
}
