package com.avasquez.repository;

import com.avasquez.domain.VendingMachine;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the VendingMachine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VendingMachineRepository extends JpaRepository<VendingMachine, Long>, JpaSpecificationExecutor<VendingMachine> {
}
