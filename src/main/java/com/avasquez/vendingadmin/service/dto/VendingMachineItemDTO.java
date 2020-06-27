package com.avasquez.vendingadmin.service.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link com.avasquez.vendingadmin.domain.VendingMachineItem} entity.
 */
public class VendingMachineItemDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    @Min(value = 0)
    private Integer quantity;

    @NotNull
    private Long vendingMachineId;

    @NotNull
    private Long itemId;
    
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

    public Long getVendingMachineId() {
        return vendingMachineId;
    }

    public void setVendingMachineId(Long vendingMachineId) {
        this.vendingMachineId = vendingMachineId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VendingMachineItemDTO)) {
            return false;
        }

        return id != null && id.equals(((VendingMachineItemDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VendingMachineItemDTO{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", vendingMachineId=" + getVendingMachineId() +
            ", itemId=" + getItemId() +
            "}";
    }
}
