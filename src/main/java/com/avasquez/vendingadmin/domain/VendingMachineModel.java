package com.avasquez.vendingadmin.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A VendingMachineModel.
 */
@Entity
@Table(name = "vending_machine_model")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VendingMachineModel extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 150)
    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @NotNull
    @Column(name = "accepts_coins", nullable = false)
    private Boolean acceptsCoins;

    @NotNull
    @Column(name = "accepts_bills", nullable = false)
    private Boolean acceptsBills;

    @NotNull
    @Column(name = "accepts_credit_card", nullable = false)
    private Boolean acceptsCreditCard;

    @OneToMany(mappedBy = "vendingMachineModel", fetch = FetchType.LAZY)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<VendingMachine> vendingMachines = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isAcceptsCoins() {
        return acceptsCoins;
    }

    public void setAcceptsCoins(Boolean acceptsCoins) {
        this.acceptsCoins = acceptsCoins;
    }

    public Boolean isAcceptsBills() {
        return acceptsBills;
    }

    public void setAcceptsBills(Boolean acceptsBills) {
        this.acceptsBills = acceptsBills;
    }

    public Boolean isAcceptsCreditCard() {
        return acceptsCreditCard;
    }

    public void setAcceptsCreditCard(Boolean acceptsCreditCard) {
        this.acceptsCreditCard = acceptsCreditCard;
    }

    public List<VendingMachine> getVendingMachines() {
        return vendingMachines;
    }

    public void setVendingMachines(List<VendingMachine> vendingMachines) {
        this.vendingMachines = vendingMachines;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VendingMachineModel)) {
            return false;
        }
        return id != null && id.equals(((VendingMachineModel) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }


    @Override
    public String toString() {
        return "VendingMachineModel{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", acceptsCoins='" + isAcceptsCoins() + "'" +
            ", acceptsBills='" + isAcceptsBills() + "'" +
            ", acceptsCreditCard='" + isAcceptsCreditCard() + "'" +
            "}";
    }
}
