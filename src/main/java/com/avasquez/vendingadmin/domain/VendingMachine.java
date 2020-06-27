package com.avasquez.vendingadmin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A VendingMachine.
 */
@Entity
@Table(name = "vending_machine")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VendingMachine extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 150)
    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @NotNull
    @Size(max = 10)
    @Column(name = "unlock_code", length = 10, nullable = false)
    private String unlockCode;

    @NotNull
    @Column(name = "status_online", nullable = false)
    private Boolean statusOnline;

    @OneToMany(mappedBy = "vendingMachine")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<VendingMachineItem> vendingMachineItems = new HashSet<>();

    @OneToMany(mappedBy = "vendingMachine")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<VendingMachineCash> vendingMachineCashes = new HashSet<>();

    @OneToMany(mappedBy = "vendingMachine")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<VendingMachineTransaction> vendingMachineTransactions = new HashSet<>();

    @OneToMany(mappedBy = "vendingMachine")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<UnlockAttemp> unlockAttemps = new HashSet<>();

    @OneToMany(mappedBy = "vendingMachine")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<CollectionAlert> collectionAlerts = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "vendingMachines", allowSetters = true)
    private VendingMachineModel vendingMachineModel;


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

    public String getUnlockCode() {
        return unlockCode;
    }

    public void setUnlockCode(String unlockCode) {
        this.unlockCode = unlockCode;
    }

    public Boolean isStatusOnline() {
        return statusOnline;
    }

    public void setStatusOnline(Boolean statusOnline) {
        this.statusOnline = statusOnline;
    }

    public Set<VendingMachineItem> getVendingMachineItems() {
        return vendingMachineItems;
    }

    public void setVendingMachineItems(Set<VendingMachineItem> vendingMachineItems) {
        this.vendingMachineItems = vendingMachineItems;
    }

    public Set<VendingMachineCash> getVendingMachineCashes() {
        return vendingMachineCashes;
    }

    public void setVendingMachineCashes(Set<VendingMachineCash> vendingMachineCashes) {
        this.vendingMachineCashes = vendingMachineCashes;
    }

    public Set<VendingMachineTransaction> getVendingMachineTransactions() {
        return vendingMachineTransactions;
    }

    public void setVendingMachineTransactions(Set<VendingMachineTransaction> vendingMachineTransactions) {
        this.vendingMachineTransactions = vendingMachineTransactions;
    }

    public Set<UnlockAttemp> getUnlockAttemps() {
        return unlockAttemps;
    }

    public void setUnlockAttemps(Set<UnlockAttemp> unlockAttemps) {
        this.unlockAttemps = unlockAttemps;
    }

    public Set<CollectionAlert> getCollectionAlerts() {
        return collectionAlerts;
    }

    public void setCollectionAlerts(Set<CollectionAlert> collectionAlerts) {
        this.collectionAlerts = collectionAlerts;
    }

    public VendingMachineModel getVendingMachineModel() {
        return vendingMachineModel;
    }

    public void setVendingMachineModel(VendingMachineModel vendingMachineModel) {
        this.vendingMachineModel = vendingMachineModel;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VendingMachine)) {
            return false;
        }
        return id != null && id.equals(((VendingMachine) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }


    @Override
    public String toString() {
        return "VendingMachine{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", unlockCode='" + getUnlockCode() + "'" +
            ", statusOnline='" + isStatusOnline() + "'" +
            "}";
    }
}
