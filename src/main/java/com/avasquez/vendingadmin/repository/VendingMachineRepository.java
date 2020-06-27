package com.avasquez.vendingadmin.repository;

import com.avasquez.vendingadmin.domain.VendingMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the VendingMachine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VendingMachineRepository extends JpaRepository<VendingMachine, Long>, JpaSpecificationExecutor<VendingMachine> {
}
