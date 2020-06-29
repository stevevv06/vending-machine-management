package com.avasquez.vendingadmin.service.api;

import com.avasquez.vendingadmin.service.dto.CollectionAlertDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.avasquez.vendingadmin.domain.CollectionAlert}.
 */
public interface CollectionAlertService extends CrudService<CollectionAlertDTO, Long>  {
    void checkCashAndTriggerAlert(Long vendingMachineId);
    Optional<CollectionAlertDTO> find(Long vendingMachineId, LocalDate alertDate);

}
