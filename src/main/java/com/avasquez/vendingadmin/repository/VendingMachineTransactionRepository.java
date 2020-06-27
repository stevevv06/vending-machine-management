package com.avasquez.vendingadmin.repository;

import com.avasquez.vendingadmin.domain.VendingMachineTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the VendingMachineTransaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VendingMachineTransactionRepository extends JpaRepository<VendingMachineTransaction, Long>, JpaSpecificationExecutor<VendingMachineTransaction> {
}
