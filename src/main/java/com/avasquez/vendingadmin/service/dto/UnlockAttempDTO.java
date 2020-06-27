package com.avasquez.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link com.avasquez.domain.UnlockAttemp} entity.
 */
public class UnlockAttempDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private LocalDate unlockDate;


    private Long vendingMachineId;
    
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

    public Long getVendingMachineId() {
        return vendingMachineId;
    }

    public void setVendingMachineId(Long vendingMachineId) {
        this.vendingMachineId = vendingMachineId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UnlockAttempDTO)) {
            return false;
        }

        return id != null && id.equals(((UnlockAttempDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UnlockAttempDTO{" +
            "id=" + getId() +
            ", unlockDate='" + getUnlockDate() + "'" +
            ", vendingMachineId=" + getVendingMachineId() +
            "}";
    }
}
