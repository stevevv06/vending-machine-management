package com.avasquez.vendingadmin.service.mapper;


import com.avasquez.vendingadmin.domain.CoinType;
import com.avasquez.vendingadmin.service.dto.CoinTypeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link CoinType} and its DTO {@link CoinTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CoinTypeMapper extends EntityMapper<CoinTypeDTO, CoinType> {


    @Mapping(target = "vendingMachineCashes", ignore = true)
    CoinType toEntity(CoinTypeDTO coinTypeDTO);

    default CoinType fromId(Long id) {
        if (id == null) {
            return null;
        }
        CoinType coinType = new CoinType();
        coinType.setId(id);
        return coinType;
    }
}
