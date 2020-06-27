package com.avasquez.vendingadmin.repository;

import com.avasquez.vendingadmin.domain.BillType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BillType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BillTypeRepository extends JpaRepository<BillType, Long> {
}
