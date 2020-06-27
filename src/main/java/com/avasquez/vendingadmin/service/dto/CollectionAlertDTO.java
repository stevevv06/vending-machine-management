package com.avasquez.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link com.avasquez.domain.CollectionAlert} entity.
 */
public class CollectionAlertDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private LocalDate alertDate;


    private Long vendingMachineId;
    
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
        if (!(o instanceof CollectionAlertDTO)) {
            return false;
        }

        return id != null && id.equals(((CollectionAlertDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CollectionAlertDTO{" +
            "id=" + getId() +
            ", alertDate='" + getAlertDate() + "'" +
            ", vendingMachineId=" + getVendingMachineId() +
            "}";
    }
}
