package com.avasquez.repository;

import com.avasquez.domain.VendingMachineItem;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the VendingMachineItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VendingMachineItemRepository extends JpaRepository<VendingMachineItem, Long>, JpaSpecificationExecutor<VendingMachineItem> {
}
