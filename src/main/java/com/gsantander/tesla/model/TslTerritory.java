package com.gsantander.tesla.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gsantander.tesla.annotations.TaxIdValidation;
import com.gsantander.tesla.annotations.TerritoryValidation;
import com.gsantander.tesla.enums.Country;
import com.gsantander.tesla.interfaces.ITreeEntity;
import com.gsantander.tesla.tools.TslFunctions;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "Territories")
@TerritoryValidation
public class TslTerritory implements Serializable, Comparable<TslTerritory>, ITreeEntity {

    public enum TerritoryType {NONE, PROVINCE, CITY}

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idTerritory")
    @SequenceGenerator(name = "idTerritory", sequenceName = "IDTERRITORY", allocationSize = 1)
    private Integer idTerritory;
    @NotBlank(message = "{field.notBlank}")
    @Size(max = 100, message = "{field.size}")
    private String description;
    private Integer atPosition = 0;
    @Column(name = "TID_Country")
    private Country country = Country.NONE;
    @ManyToOne
    @JoinColumn(name = "IdTerritory_Parent")
    @JsonBackReference
    private TslTerritory parent;
    @OneToMany(mappedBy = "parent",
            orphanRemoval = true,
            cascade = {CascadeType.ALL})
    @OrderBy("description ASC")
    @JsonManagedReference
    private Set<TslTerritory> subTerritories = new HashSet<>();

    // Hooks

    @PrePersist
    @PreUpdate
    private void prePersistCountry(){
        switch (this.getType()) {
            case CITY -> {
                this.country = Country.NONE;
            }
        }
    }

    // Getters & Setters

    public Integer getIdTerritory() {
        return idTerritory;
    }

    public void setIdTerritory(Integer idTerritory) {
        this.idTerritory = idTerritory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAtPosition() {
        return atPosition;
    }

    public void setAtPosition(Integer atPosition) {
        this.atPosition = atPosition;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public TslTerritory getParent() {
        return parent;
    }

    public void setParent(TslTerritory parent) {
        this.parent = parent;
    }

    public Set<TslTerritory> getSubTerritories() {
        return subTerritories;
    }

    public void setSubTerritories(Set<TslTerritory> subTerritories) {
        this.subTerritories = subTerritories;
    }

    // Equals, HashCode, ToString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TslTerritory that = (TslTerritory) o;
        return Objects.equals(idTerritory, that.idTerritory) && Objects.equals(this.getHierarchyDescription(), that.getHierarchyDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTerritory, this.getHierarchyDescription());
    }

    @Override
    public String toString() {
        return "TslTerritory{" +
                "idTerritory=" + idTerritory +
                ", description='" + this.getHierarchyDescription() + '\'' +
                '}';
    }

    // Comparators

    @Override
    public int compareTo(TslTerritory o) {
        return new CompareToBuilder()
                .append(this.getHierarchyDescription(),o.getHierarchyDescription())
                .append(this.hashCode(),o.hashCode())
                .toComparison();
    }

    // Methods

    @JsonProperty("hierarchyDescription")
    public String getHierarchyDescription() {
        return TslFunctions.getHierarchyDescription(this);
    }

    public int getLevel() {
        return TslFunctions.getLevel(this);
    }

    public TerritoryType getType() {
        switch (this.getLevel()) {
            case 1: return TerritoryType.PROVINCE;
            case 2: return TerritoryType.CITY;
        }
        return TerritoryType.NONE;
    }

}