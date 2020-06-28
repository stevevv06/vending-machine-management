package com.avasquez.vendingadmin.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * A BillType.
 */
@Entity
@Table(name = "bill_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BillType extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 150)
    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "value", precision = 21, scale = 2, nullable = false)
    private BigDecimal value;

    @OneToMany(mappedBy = "billType", fetch = FetchType.LAZY)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<VendingMachineCash> vendingMachineCashes = new ArrayList<>();


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

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public List<VendingMachineCash> getVendingMachineCashes() {
        return vendingMachineCashes;
    }

    public void setVendingMachineCashes(List<VendingMachineCash> vendingMachineCashes) {
        this.vendingMachineCashes = vendingMachineCashes;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BillType)) {
            return false;
        }
        return id != null && id.equals(((BillType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }


    @Override
    public String toString() {
        return "BillType{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", value=" + getValue() +
            "}";
    }
}
