package com.avasquez.repository;

import com.avasquez.domain.VendingMachineTransaction;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the VendingMachineTransaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VendingMachineTransactionRepository extends JpaRepository<VendingMachineTransaction, Long>, JpaSpecificationExecutor<VendingMachineTransaction> {
}
