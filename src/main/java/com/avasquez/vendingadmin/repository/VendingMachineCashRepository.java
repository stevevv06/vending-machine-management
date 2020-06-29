package com.avasquez.vendingadmin.repository;

import com.avasquez.vendingadmin.domain.VendingMachineCash;
import com.avasquez.vendingadmin.domain.VendingMachineItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Spring Data  repository for the VendingMachineCash entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VendingMachineCashRepository extends JpaRepository<VendingMachineCash, Long>, JpaSpecificationExecutor<VendingMachineCash> {
    Page<VendingMachineCash> findAllByVendingMachineId(Long vendingMachineId, Pageable pageable);

    @Query("SELECT SUM(c.coinQuantity * c.coinType.value) as total " +
            "FROM VendingMachineCash c " +
            "WHERE c.vendingMachine.id = :vendingMachineId AND c.coinType IS NOT NULL")
    Optional<BigDecimal> getTotalCoinByVendingMachineId(@Param("vendingMachineId") Long vendingMachineId);

    @Query("SELECT SUM(c.billQuantity * c.billType.value) as total " +
            "FROM VendingMachineCash c " +
            "WHERE c.vendingMachine.id = :vendingMachineId AND c.billType IS NOT NULL")
    Optional<BigDecimal> getTotalBillByVendingMachineId(@Param("vendingMachineId") Long vendingMachineId);

    Optional<VendingMachineCash> findByVendingMachineIdAndBillTypeId(Long vendingMachineId, Long billTypeID);
    Optional<VendingMachineCash> findByVendingMachineIdAndCoinTypeId(Long vendingMachineId, Long coinTypeId);
}
