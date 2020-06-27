package com.avasquez.vendingadmin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A CollectionAlert.
 */
@Entity
@Table(name = "collection_alert")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CollectionAlert extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "alert_date", nullable = false)
    private LocalDate alertDate;

    @ManyToOne
    @JsonIgnoreProperties(value = "collectionAlerts", allowSetters = true)
    private VendingMachine vendingMachine;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAlertDate() {
        return alertDate;
    }

    public void setAlertDate(LocalDate alertDate) {
        this.alertDate = alertDate;
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
        if (!(o instanceof CollectionAlert)) {
            return false;
        }
        return id != null && id.equals(((CollectionAlert) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }


    @Override
    public String toString() {
        return "CollectionAlert{" +
            "id=" + getId() +
            ", alertDate='" + getAlertDate() + "'" +
            "}";
    }
}
