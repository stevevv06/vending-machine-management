package com.avasquez.vendingadmin.repository;

import com.avasquez.vendingadmin.domain.VendingMachineCash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the VendingMachineCash entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VendingMachineCashRepository extends JpaRepository<VendingMachineCash, Long>, JpaSpecificationExecutor<VendingMachineCash> {
}
