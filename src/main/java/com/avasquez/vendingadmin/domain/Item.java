package com.avasquez.vendingadmin.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A Item.
 */
@Entity
@Table(name = "item")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Item extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 150)
    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "price", precision = 21, scale = 2, nullable = false)
    private BigDecimal price;

    @OneToMany(mappedBy = "item")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<VendingMachineItem> vendingMachineItems = new HashSet<>();

    @OneToMany(mappedBy = "item")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<VendingMachineTransaction> vendingMachineTransactions = new HashSet<>();


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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<VendingMachineItem> getVendingMachineItems() {
        return vendingMachineItems;
    }

    public void setVendingMachineItems(Set<VendingMachineItem> vendingMachineItems) {
        this.vendingMachineItems = vendingMachineItems;
    }

    public Set<VendingMachineTransaction> getVendingMachineTransactions() {
        return vendingMachineTransactions;
    }

    public void setVendingMachineTransactions(Set<VendingMachineTransaction> vendingMachineTransactions) {
        this.vendingMachineTransactions = vendingMachineTransactions;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Item)) {
            return false;
        }
        return id != null && id.equals(((Item) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }


    @Override
    public String toString() {
        return "Item{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            ", price=" + getPrice() +
            "}";
    }
}
