package com.avasquez.vendingadmin.repository;

import com.avasquez.vendingadmin.domain.VendingMachineModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the VendingMachineModel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VendingMachineModelRepository extends JpaRepository<VendingMachineModel, Long> {
}
