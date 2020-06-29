package com.avasquez.vendingadmin.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.avasquez.vendingadmin.domain.VendingMachine} entity.
 */
public class VendingMachineReportDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 150)
    private String name;

    @NotNull
    private Boolean statusOnline;

    @NotNull
    private Long vendingMachineModelId;

    private String vendingMachineModelName;

    private BigDecimal totalCash;

    private Boolean collectionAlert;
    
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

    public Boolean isStatusOnline() {
        return statusOnline;
    }

    public void setStatusOnline(Boolean statusOnline) {
        this.statusOnline = statusOnline;
    }

    public Long getVendingMachineModelId() {
        return vendingMachineModelId;
    }

    public void setVendingMachineModelId(Long vendingMachineModelId) {
        this.vendingMachineModelId = vendingMachineModelId;
    }

    public String getVendingMachineModelName() {
        return vendingMachineModelName;
    }

    public void setVendingMachineModelName(String vendingMachineModelName) {
        this.vendingMachineModelName = vendingMachineModelName;
    }

    public BigDecimal getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(BigDecimal totalCash) {
        this.totalCash = totalCash;
    }

    public Boolean getCollectionAlert() {
        return collectionAlert;
    }

    public void setCollectionAlert(Boolean collectionAlert) {
        this.collectionAlert = collectionAlert;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VendingMachineReportDTO)) {
            return false;
        }

        return id != null && id.equals(((VendingMachineReportDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }


    @Override
    public String toString() {
        return "VendingMachineDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", statusOnline='" + isStatusOnline() + "'" +
            ", vendingMachineModelId=" + getVendingMachineModelId() +
            "}";
    }
}
