package com.avasquez.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.math.BigDecimal;
import com.avasquez.domain.enumeration.PaymentType;

/**
 * A DTO for the {@link com.avasquez.domain.VendingMachineTransaction} entity.
 */
public class VendingMachineTransactionDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private Integer itemQuantity;

    private BigDecimal paymentAmount;

    private BigDecimal cashInAmount;

    private BigDecimal cashChange;

    private LocalDate transactionDate;

    private PaymentType paymentType;


    private Long vendingMachineId;

    private Long itemId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public BigDecimal getCashInAmount() {
        return cashInAmount;
    }

    public void setCashInAmount(BigDecimal cashInAmount) {
        this.cashInAmount = cashInAmount;
    }

    public BigDecimal getCashChange() {
        return cashChange;
    }

    public void setCashChange(BigDecimal cashChange) {
        this.cashChange = cashChange;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Long getVendingMachineId() {
        return vendingMachineId;
    }

    public void setVendingMachineId(Long vendingMachineId) {
        this.vendingMachineId = vendingMachineId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VendingMachineTransactionDTO)) {
            return false;
        }

        return id != null && id.equals(((VendingMachineTransactionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VendingMachineTransactionDTO{" +
            "id=" + getId() +
            ", itemQuantity=" + getItemQuantity() +
            ", paymentAmount=" + getPaymentAmount() +
            ", cashInAmount=" + getCashInAmount() +
            ", cashChange=" + getCashChange() +
            ", transactionDate='" + getTransactionDate() + "'" +
            ", paymentType='" + getPaymentType() + "'" +
            ", vendingMachineId=" + getVendingMachineId() +
            ", itemId=" + getItemId() +
            "}";
    }
}
