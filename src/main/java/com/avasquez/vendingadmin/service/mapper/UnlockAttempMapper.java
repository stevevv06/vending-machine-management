package com.avasquez.vendingadmin.service.mapper;


import com.avasquez.vendingadmin.domain.*;
import com.avasquez.vendingadmin.service.dto.UnlockAttempDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UnlockAttemp} and its DTO {@link UnlockAttempDTO}.
 */
@Mapper(componentModel = "spring", uses = {VendingMachineMapper.class})
public interface UnlockAttempMapper extends EntityMapper<UnlockAttempDTO, UnlockAttemp> {

    @Mapping(source = "vendingMachine.id", target = "vendingMachineId")
    UnlockAttempDTO toDto(UnlockAttemp unlockAttemp);

    @Mapping(source = "vendingMachineId", target = "vendingMachine")
    UnlockAttemp toEntity(UnlockAttempDTO unlockAttempDTO);

    default UnlockAttemp fromId(Long id) {
        if (id == null) {
            return null;
        }
        UnlockAttemp unlockAttemp = new UnlockAttemp();
        unlockAttemp.setId(id);
        return unlockAttemp;
    }
}
