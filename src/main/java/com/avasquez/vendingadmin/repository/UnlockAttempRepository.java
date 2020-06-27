package com.avasquez.repository;

import com.avasquez.domain.UnlockAttemp;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UnlockAttemp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UnlockAttempRepository extends JpaRepository<UnlockAttemp, Long>, JpaSpecificationExecutor<UnlockAttemp> {
}
