package com.avasquez.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.avasquez.domain.VendingMachineModel} entity.
 */
public class VendingMachineModelDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String name;

    private Boolean acceptsCoins;

    private Boolean acceptsBills;

    private Boolean acceptsCreditCard;

    
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

    public Boolean isAcceptsCoins() {
        return acceptsCoins;
    }

    public void setAcceptsCoins(Boolean acceptsCoins) {
        this.acceptsCoins = acceptsCoins;
    }

    public Boolean isAcceptsBills() {
        return acceptsBills;
    }

    public void setAcceptsBills(Boolean acceptsBills) {
        this.acceptsBills = acceptsBills;
    }

    public Boolean isAcceptsCreditCard() {
        return acceptsCreditCard;
    }

    public void setAcceptsCreditCard(Boolean acceptsCreditCard) {
        this.acceptsCreditCard = acceptsCreditCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VendingMachineModelDTO)) {
            return false;
        }

        return id != null && id.equals(((VendingMachineModelDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VendingMachineModelDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", acceptsCoins='" + isAcceptsCoins() + "'" +
            ", acceptsBills='" + isAcceptsBills() + "'" +
            ", acceptsCreditCard='" + isAcceptsCreditCard() + "'" +
            "}";
    }
}
