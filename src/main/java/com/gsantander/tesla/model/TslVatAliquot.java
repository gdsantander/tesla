package com.gsantander.tesla.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "VatAliquots")
public class TslVatAliquot implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idVatAliquot")
    @SequenceGenerator(name = "idVatAliquot", sequenceName = "IDVATALIQUOT", allocationSize = 1)
    private Integer idVatAliquot;
    @NotBlank(message = "{field.notBlank}")
    @Size(max = 100, message = "{field.size}")
    private String description;
    @Column(name = "TaxAgency_Code")
    @Size(max = 10, message = "{field.size}")
    private String taxAgencyCode = "";
    private BigDecimal aliquot = new BigDecimal(0);

    // Getters & Setters

    public Integer getIdVatAliquot() {
        return idVatAliquot;
    }

    public void setIdVatAliquot(Integer idVatAliquot) {
        this.idVatAliquot = idVatAliquot;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaxAgencyCode() {
        return taxAgencyCode;
    }

    public void setTaxAgencyCode(String taxAgencyCode) {
        this.taxAgencyCode = taxAgencyCode;
    }

    public BigDecimal getAliquot() {
        return aliquot;
    }

    public void setAliquot(BigDecimal aliquot) {
        this.aliquot = aliquot;
    }

    // Equals, HashCode, ToString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TslVatAliquot that = (TslVatAliquot) o;
        return Objects.equals(idVatAliquot, that.idVatAliquot) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVatAliquot, description);
    }

    @Override
    public String toString() {
        return "TslVatAliquot{" +
                "idVatAliquot=" + idVatAliquot +
                ", description='" + description + '\'' +
                '}';
    }

}