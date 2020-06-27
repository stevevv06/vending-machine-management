package com.avasquez.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.avasquez.domain.VendingMachine} entity.
 */
public class VendingMachineDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String name;

    private String unlockCode;

    private Boolean statusOnline;


    private Long vendingMachineModelId;
    
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

    public Long getVendingMachineModelId() {
        return vendingMachineModelId;
    }

    public void setVendingMachineModelId(Long vendingMachineModelId) {
        this.vendingMachineModelId = vendingMachineModelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VendingMachineDTO)) {
            return false;
        }

        return id != null && id.equals(((VendingMachineDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VendingMachineDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", unlockCode='" + getUnlockCode() + "'" +
            ", statusOnline='" + isStatusOnline() + "'" +
            ", vendingMachineModelId=" + getVendingMachineModelId() +
            "}";
    }
}
