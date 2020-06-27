package com.avasquez.vendingadmin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A UnlockAttemp.
 */
@Entity
@Table(name = "unlock_attemp")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UnlockAttemp extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "unlock_date", nullable = false)
    private LocalDate unlockDate;

    @ManyToOne
    @JsonIgnoreProperties(value = "unlockAttemps", allowSetters = true)
    private VendingMachine vendingMachine;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getUnlockDate() {
        return unlockDate;
    }

    public void setUnlockDate(LocalDate unlockDate) {
        this.unlockDate = unlockDate;
    }

    public VendingMachine getVendingMachine() {
        return vendingMachine;
    }

    public void setVendingMachine(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UnlockAttemp)) {
            return false;
        }
        return id != null && id.equals(((UnlockAttemp) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }


    @Override
    public String toString() {
        return "UnlockAttemp{" +
            "id=" + getId() +
            ", unlockDate='" + getUnlockDate() + "'" +
            "}";
    }
}
