package com.avasquez.vendingadmin.service.api;

import com.avasquez.vendingadmin.service.dto.UnlockAttempDTO;

import java.util.List;

/**
 * Service Interface for managing {@link com.avasquez.vendingadmin.domain.UnlockAttemp}.
 */
public interface UnlockAttempService extends CrudService<UnlockAttempDTO, Long> {
    List<UnlockAttempDTO> findByVendingMachineId(Long vendingMachineId);
}
