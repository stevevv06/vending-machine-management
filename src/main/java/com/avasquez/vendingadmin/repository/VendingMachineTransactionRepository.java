package com.avasquez.vendingadmin.repository;

import com.avasquez.vendingadmin.domain.VendingMachineTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the VendingMachineTransaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VendingMachineTransactionRepository extends JpaRepository<VendingMachineTransaction, Long>, JpaSpecificationExecutor<VendingMachineTransaction> {
    List<VendingMachineTransaction> findAllByVendingMachineIdAndTransactionDate(Long vendingMachineId, LocalDate transactionDate);

    @Query("SELECT SUM(c.paymentAmount) as total " +
            "FROM VendingMachineTransaction c " +
            "WHERE c.vendingMachine.id = :vendingMachineId")
    Optional<BigDecimal> getTotalProfitByVendingMachineId(@Param("vendingMachineId") Long vendingMachineId);

    @Query("SELECT COUNT(c) as total " +
            "FROM VendingMachineTransaction c " +
            "WHERE c.vendingMachine.id = :vendingMachineId AND c.transactionDate = :transactionDate")
    Optional<BigDecimal> getCountByVendingMachineIdAndTransactionDate(
            @Param("vendingMachineId") Long vendingMachineId, @Param("transactionDate") LocalDate transactionDate);
}
