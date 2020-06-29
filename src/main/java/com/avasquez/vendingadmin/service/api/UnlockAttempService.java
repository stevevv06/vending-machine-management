package com.avasquez.vendingadmin.service.api;

import com.avasquez.vendingadmin.service.dto.UnlockAttempDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.avasquez.vendingadmin.domain.UnlockAttemp}.
 */
public interface UnlockAttempService extends CrudService<UnlockAttempDTO, Long> {
    List<UnlockAttempDTO> findByVendingMachineId(Long vendingMachineId);
}
