package com.avasquez.repository;

import com.avasquez.domain.VendingMachineModel;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the VendingMachineModel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VendingMachineModelRepository extends JpaRepository<VendingMachineModel, Long> {
}
