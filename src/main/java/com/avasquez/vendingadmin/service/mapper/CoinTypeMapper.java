package com.avasquez.service.mapper;


import com.avasquez.domain.*;
import com.avasquez.service.dto.CoinTypeDTO;

import org.mapstruct.*;

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