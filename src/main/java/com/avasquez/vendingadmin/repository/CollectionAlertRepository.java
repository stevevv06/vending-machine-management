package com.avasquez.repository;

import com.avasquez.domain.CollectionAlert;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CollectionAlert entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CollectionAlertRepository extends JpaRepository<CollectionAlert, Long>, JpaSpecificationExecutor<CollectionAlert> {
}
