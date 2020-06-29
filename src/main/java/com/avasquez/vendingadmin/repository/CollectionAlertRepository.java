package com.avasquez.vendingadmin.repository;

import com.avasquez.vendingadmin.domain.CollectionAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Spring Data  repository for the CollectionAlert entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CollectionAlertRepository extends JpaRepository<CollectionAlert, Long>, JpaSpecificationExecutor<CollectionAlert> {
    Optional<CollectionAlert> findByVendingMachineIdAndAlertDate(Long vendingMachineId, LocalDate alertDate);
}
