package com.avasquez.vendingadmin.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link com.avasquez.vendingadmin.domain.CollectionAlert} entity.
 */
public class CollectionAlertDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate alertDate;

    @NotNull
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


    @Override
    public String toString() {
        return "CollectionAlertDTO{" +
            "id=" + getId() +
            ", alertDate='" + getAlertDate() + "'" +
            ", vendingMachineId=" + getVendingMachineId() +
            "}";
    }
}
