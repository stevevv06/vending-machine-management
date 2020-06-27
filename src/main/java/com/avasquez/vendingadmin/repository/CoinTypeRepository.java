package com.avasquez.repository;

import com.avasquez.domain.CoinType;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CoinType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CoinTypeRepository extends JpaRepository<CoinType, Long> {
}
