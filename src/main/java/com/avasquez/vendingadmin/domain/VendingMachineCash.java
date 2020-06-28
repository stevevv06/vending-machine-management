package com.avasquez.vendingadmin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A VendingMachineCash.
 */
@Entity
@Table(name = "vending_machine_cash")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VendingMachineCash extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0)
    @Column(name = "coin_quantity")
    private Integer coinQuantity;

    @Min(value = 0)
    @Column(name = "bill_quantity")
    private Integer billQuantity;

    @ManyToOne
    @JsonIgnoreProperties(value = "vendingMachineCashes", allowSetters = true)
    private VendingMachine vendingMachine;

    @ManyToOne
    @JsonIgnoreProperties(value = "vendingMachineCashes", allowSetters = true)
    private CoinType coinType;

    @ManyToOne
    @JsonIgnoreProperties(value = "vendingMachineCashes", allowSetters = true)
    private BillType billType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCoinQuantity() {
        return coinQuantity;
    }

    public void setCoinQuantity(Integer coinQuantity) {
        this.coinQuantity = coinQuantity;
    }

    public Integer getBillQuantity() {
        return billQuantity;
    }

    public void setBillQuantity(Integer billQuantity) {
        this.billQuantity = billQuantity;
    }

    public VendingMachine getVendingMachine() {
        return vendingMachine;
    }

    public void setVendingMachine(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    public CoinType getCoinType() {
        return coinType;
    }

    public void setCoinType(CoinType coinType) {
        this.coinType = coinType;
    }

    public BillType getBillType() {
        return billType;
    }

    public void setBillType(BillType billType) {
        this.billType = billType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VendingMachineCash)) {
            return false;
        }
        return id != null && id.equals(((VendingMachineCash) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }


    @Override
    public String toString() {
        return "VendingMachineCash{" +
            "id=" + getId() +
            ", coinQuantity=" + getCoinQuantity() +
            ", billQuantity=" + getBillQuantity() +
            "}";
    }
}
