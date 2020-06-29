package com.avasquez.vendingadmin.repository;

import com.avasquez.vendingadmin.domain.UnlockAttemp;
import com.avasquez.vendingadmin.service.dto.UnlockAttempDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Spring Data  repository for the UnlockAttemp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UnlockAttempRepository extends JpaRepository<UnlockAttemp, Long>, JpaSpecificationExecutor<UnlockAttemp> {
    List<UnlockAttemp> findByVendingMachineIdAndUnlockDate(Long vendingMachineId, LocalDate unlockDate);
}
