package com.avasquez.repository;

import com.avasquez.domain.VendingMachineCash;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the VendingMachineCash entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VendingMachineCashRepository extends JpaRepository<VendingMachineCash, Long>, JpaSpecificationExecutor<VendingMachineCash> {
}
