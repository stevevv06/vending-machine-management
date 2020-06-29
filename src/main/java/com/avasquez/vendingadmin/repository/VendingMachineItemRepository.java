package com.avasquez.vendingadmin.repository;

import com.avasquez.vendingadmin.domain.VendingMachineItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the VendingMachineItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VendingMachineItemRepository extends JpaRepository<VendingMachineItem, Long>, JpaSpecificationExecutor<VendingMachineItem> {
    Page<VendingMachineItem> findAllByVendingMachineId(Long vendingMachineId, Pageable pageable);
}
