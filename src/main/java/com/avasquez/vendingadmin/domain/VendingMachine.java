package com.avasquez.vendingadmin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "vendingMachine", fetch = FetchType.LAZY)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<VendingMachineItem> vendingMachineItems = new ArrayList<>();

    @OneToMany(mappedBy = "vendingMachine", fetch = FetchType.LAZY)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<VendingMachineCash> vendingMachineCashes = new ArrayList<>();

    @OneToMany(mappedBy = "vendingMachine", fetch = FetchType.LAZY)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<VendingMachineTransaction> vendingMachineTransactions = new ArrayList<>();

    @OneToMany(mappedBy = "vendingMachine", fetch = FetchType.LAZY)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<UnlockAttemp> unlockAttemps = new ArrayList<>();

    @OneToMany(mappedBy = "vendingMachine", fetch = FetchType.LAZY)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<CollectionAlert> collectionAlerts = new ArrayList<>();

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

    public List<VendingMachineItem> getVendingMachineItems() {
        return vendingMachineItems;
    }

    public void setVendingMachineItems(List<VendingMachineItem> vendingMachineItems) {
        this.vendingMachineItems = vendingMachineItems;
    }

    public List<VendingMachineCash> getVendingMachineCashes() {
        return vendingMachineCashes;
    }

    public void setVendingMachineCashes(List<VendingMachineCash> vendingMachineCashes) {
        this.vendingMachineCashes = vendingMachineCashes;
    }

    public List<VendingMachineTransaction> getVendingMachineTransactions() {
        return vendingMachineTransactions;
    }

    public void setVendingMachineTransactions(List<VendingMachineTransaction> vendingMachineTransactions) {
        this.vendingMachineTransactions = vendingMachineTransactions;
    }

    public List<UnlockAttemp> getUnlockAttemps() {
        return unlockAttemps;
    }

    public void setUnlockAttemps(List<UnlockAttemp> unlockAttemps) {
        this.unlockAttemps = unlockAttemps;
    }

    public List<CollectionAlert> getCollectionAlerts() {
        return collectionAlerts;
    }

    public void setCollectionAlerts(List<CollectionAlert> collectionAlerts) {
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
