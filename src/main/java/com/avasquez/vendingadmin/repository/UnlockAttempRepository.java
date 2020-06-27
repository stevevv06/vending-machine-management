package com.avasquez.vendingadmin.repository;

import com.avasquez.vendingadmin.domain.UnlockAttemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UnlockAttemp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UnlockAttempRepository extends JpaRepository<UnlockAttemp, Long>, JpaSpecificationExecutor<UnlockAttemp> {
}
