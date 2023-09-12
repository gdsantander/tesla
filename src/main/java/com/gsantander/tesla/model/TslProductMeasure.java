package com.gsantander.tesla.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gsantander.tesla.annotations.DateValidation;
import com.gsantander.tesla.tools.TslConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity(name = "ProductsMeasures")
public class TslProductMeasure implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idProductMeasure")
    @SequenceGenerator(name = "idProductMeasure", sequenceName = "IDPRODUCTMEASURE", allocationSize = 1)
    private Integer idProductMeasure;
    @NotNull(message = "{field.notNull}")
    @Positive(message = "{field.positive}")
    private Integer idCompany;
    @NotBlank(message = "{field.notBlank}")
    @Size(max = 100, message = "{field.size}")
    private String description;
    @Size(max = 10, message = "{field.size}")
    private String code = "";

    // Getters & Setters

    public Integer getIdProductMeasure() {
        return idProductMeasure;
    }

    public void setIdProductMeasure(Integer idProductMeasure) {
        this.idProductMeasure = idProductMeasure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Integer idCompany) {
        this.idCompany = idCompany;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    // Equals, HashCode, ToString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TslProductMeasure that = (TslProductMeasure) o;
        return Objects.equals(idProductMeasure, that.idProductMeasure) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProductMeasure, description);
    }

    @Override
    public String toString() {
        return "TslProductMeasure{" +
                "idProductMeasure=" + idProductMeasure +
                ", description='" + description + '\'' +
                '}';
    }

}