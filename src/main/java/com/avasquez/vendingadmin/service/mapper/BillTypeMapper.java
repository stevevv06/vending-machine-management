package com.avasquez.vendingadmin.service.mapper;


import com.avasquez.vendingadmin.domain.BillType;
import com.avasquez.vendingadmin.service.dto.BillTypeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link BillType} and its DTO {@link BillTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BillTypeMapper extends EntityMapper<BillTypeDTO, BillType> {


    @Mapping(target = "vendingMachineCashes", ignore = true)
    BillType toEntity(BillTypeDTO dto);

    default BillType fromId(Long id) {
        if (id == null) {
            return null;
        }
        BillType billType = new BillType();
        billType.setId(id);
        return billType;
    }
}
