package com.avasquez.vendingadmin.repository;

import com.avasquez.vendingadmin.domain.CoinType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CoinType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CoinTypeRepository extends JpaRepository<CoinType, Long> {
}
