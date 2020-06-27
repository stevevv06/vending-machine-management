package com.avasquez.vendingadmin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A VendingMachineItem.
 */
@Entity
@Table(name = "vending_machine_item")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VendingMachineItem extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(value = 0)
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToOne
    @JsonIgnoreProperties(value = "vendingMachineItems", allowSetters = true)
    private VendingMachine vendingMachine;

    @ManyToOne
    @JsonIgnoreProperties(value = "vendingMachineItems", allowSetters = true)
    private Item item;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
        if (!(o instanceof VendingMachineItem)) {
            return false;
        }
        return id != null && id.equals(((VendingMachineItem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }


    @Override
    public String toString() {
        return "VendingMachineItem{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            "}";
    }
}
