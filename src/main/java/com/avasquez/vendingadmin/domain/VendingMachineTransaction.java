package com.avasquez.vendingadmin.domain;

import com.avasquez.vendingadmin.domain.enumeration.PaymentType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A VendingMachineTransaction.
 */
@Entity
@Table(name = "vending_machine_transaction")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VendingMachineTransaction extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(value = 0)
    @Column(name = "item_quantity", nullable = false)
    private Integer itemQuantity;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "payment_amount", precision = 21, scale = 2, nullable = false)
    private BigDecimal paymentAmount;

    @DecimalMin(value = "0")
    @Column(name = "cash_in_amount", precision = 21, scale = 2)
    private BigDecimal cashInAmount;

    @DecimalMin(value = "0")
    @Column(name = "cash_change", precision = 21, scale = 2)
    private BigDecimal cashChange;

    @NotNull
    @Column(name = "transaction_date", nullable = false)
    private LocalDate transactionDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", nullable = false)
    private PaymentType paymentType;

    @ManyToOne
    @JsonIgnoreProperties(value = "vendingMachineTransactions", allowSetters = true)
    private VendingMachine vendingMachine;

    @ManyToOne
    @JsonIgnoreProperties(value = "vendingMachineTransactions", allowSetters = true)
    private Item item;


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

    public VendingMachine getVendingMachine() {
        return vendingMachine;
    }

    public void setVendingMachine(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VendingMachineTransaction)) {
            return false;
        }
        return id != null && id.equals(((VendingMachineTransaction) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }


    @Override
    public String toString() {
        return "VendingMachineTransaction{" +
            "id=" + getId() +
            ", itemQuantity=" + getItemQuantity() +
            ", paymentAmount=" + getPaymentAmount() +
            ", cashInAmount=" + getCashInAmount() +
            ", cashChange=" + getCashChange() +
            ", transactionDate='" + getTransactionDate() + "'" +
            ", paymentType='" + getPaymentType() + "'" +
            "}";
    }
}
