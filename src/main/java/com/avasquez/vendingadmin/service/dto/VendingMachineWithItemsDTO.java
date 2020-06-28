package com.avasquez.vendingadmin.service.dto;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineWithItemsDTO {
    private Long id;

    private String name;

    @NotNull
    private List<VendingMachineItemDTO> vendingMachineItems = new ArrayList<>();

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

    public List<VendingMachineItemDTO> getVendingMachineItems() {
        return vendingMachineItems;
    }

    public void setVendingMachineItems(List<VendingMachineItemDTO> items) {
        this.vendingMachineItems = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VendingMachineWithItemsDTO)) {
            return false;
        }

        return id != null && id.equals(((VendingMachineWithItemsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VendingMachineWithItemsDTO{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                "}";
    }
}
