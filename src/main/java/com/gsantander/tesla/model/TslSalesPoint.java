package com.gsantander.tesla.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "SalesPoints")
public class TslSalesPoint implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idSalesPoint")
    @SequenceGenerator(name = "idSalesPoint", sequenceName = "IDSALESPOINT", allocationSize = 1)
    private Integer idSalesPoint;
    @NotNull(message = "{field.notNull}")
    @Positive(message = "{field.positive}")
    private Integer idCompany;
    @NotBlank(message = "{field.notBlank}")
    @Size(max = 100, message = "{field.size}")
    private String description;
    private Integer preNumber;
    private boolean active = true;
    private boolean electronicBills;
    @OneToMany(mappedBy = "salesPoint",
               orphanRemoval = true,
               cascade = {CascadeType.ALL})
    //@OrderBy("document.description ASC")
    @JsonManagedReference
    private Set<TslSalesPointSequencer> sequencers = new HashSet<>();

    // Getters & Setters

    public Set<TslSalesPointSequencer> getSequencers() {
        return sequencers;
    }

    public void setSequencers(Set<TslSalesPointSequencer> sequencers) {
        this.sequencers = sequencers;
    }

    public Integer getIdSalesPoint() {
        return idSalesPoint;
    }

    public void setIdSalesPoint(Integer idSalesPoint) {
        this.idSalesPoint = idSalesPoint;
    }

    public Integer getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Integer idCompany) {
        this.idCompany = idCompany;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPreNumber() {
        return preNumber;
    }

    public void setPreNumber(Integer preNumber) {
        this.preNumber = preNumber;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isElectronicBills() {
        return electronicBills;
    }

    public void setElectronicBills(boolean electronicBills) {
        this.electronicBills = electronicBills;
    }

    // Equals, HashCode, ToString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TslSalesPoint that = (TslSalesPoint) o;
        return Objects.equals(idSalesPoint, that.idSalesPoint) &&
               Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSalesPoint, description);
    }

    @Override
    public String toString() {
        return "TslSalesPoint{" +
                "idSalesPoint=" + idSalesPoint +
                ", description='" + description +
                '}';
    }

}