package com.gsantander.tesla.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gsantander.tesla.annotations.DateValidation;
import com.gsantander.tesla.tools.TslConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity(name = "Currencies")
public class TslCurrency implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idCurrency")
    @SequenceGenerator(name = "idCurrency", sequenceName = "IDCURRENCY", allocationSize = 1)
    private Integer idCurrency;
    @NotBlank(message = "{field.notBlank}")
    @Size(max = 100, message = "{field.size}")
    private String description;
    @Column(name = "TaxAgency_Code")
    @Size(max = 10, message = "{field.size}")
    private String taxAgencyCode = "";
    @Column(name = "Symbol")
    @Size(max = 5, message = "{field.size}")
    private String symbol = "";
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern= TslConstants.PATTERN_DATE_FORMAT, timezone = TslConstants.TIME_ZONE)
    @DateValidation
    private Date startDate;
    private BigDecimal startBalance = new BigDecimal(0);

    // Getters & Setters

    public Integer getIdCurrency() {
        return idCurrency;
    }

    public void setIdCurrency(Integer idCurrency) {
        this.idCurrency = idCurrency;
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public BigDecimal getStartBalance() {
        return startBalance;
    }

    public void setStartBalance(BigDecimal startBalance) {
        this.startBalance = startBalance;
    }

    // Equals, HashCode, ToString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TslCurrency that = (TslCurrency) o;
        return Objects.equals(idCurrency, that.idCurrency) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCurrency, description);
    }

    @Override
    public String toString() {
        return "TslCurrency{" +
                "idCurrency=" + idCurrency +
                ", description='" + description + '\'' +
                '}';
    }

}