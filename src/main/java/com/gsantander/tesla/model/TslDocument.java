package com.gsantander.tesla.model;

import com.gsantander.tesla.enums.Country;
import com.gsantander.tesla.enums.CurrentAccountEntryEffect;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

@Entity(name = "Documents")
public class TslDocument implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idDocument")
    @SequenceGenerator(name = "idDocument", sequenceName = "IDDOCUMENT", allocationSize = 1)
    private Integer idDocument;
    @Column(name = "TID_Country")
    private Country country = Country.NONE;
    @NotBlank(message = "{field.notBlank}")
    @Size(max = 100, message = "{field.size}")
    private String description;
    @Size(max = 10, message = "{field.size}")
    private String abbreviation = "";
    @Column(name = "TID_Effect")
    private CurrentAccountEntryEffect effect = CurrentAccountEntryEffect.NONE;
    private boolean fiscal;

    // Getters & Setters

    public Integer getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(Integer idDocument) {
        this.idDocument = idDocument;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public CurrentAccountEntryEffect getEffect() {
        return effect;
    }

    public void setEffect(CurrentAccountEntryEffect effect) {
        this.effect = effect;
    }

    public boolean isFiscal() {
        return fiscal;
    }

    public void setFiscal(boolean fiscal) {
        this.fiscal = fiscal;
    }

    // Equals, HashCode, ToString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TslDocument that = (TslDocument) o;
        return Objects.equals(idDocument, that.idDocument) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDocument, description);
    }

    @Override
    public String toString() {
        return "TslDocument{" +
                "idDocument=" + idDocument +
                ", description='" + description + '\'' +
                '}';
    }

}