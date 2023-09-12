package com.gsantander.tesla.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

@Entity(name = "ProductsFamilies")
public class TslProductFamily implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idProductFamily")
    @SequenceGenerator(name = "idProductFamily", sequenceName = "IDPRODUCTFAMILY", allocationSize = 1)
    private Integer idProductFamily;
    @NotNull(message = "{field.notNull}")
    @Positive(message = "{field.positive}")
    private Integer idCompany;
    @NotBlank(message = "{field.notBlank}")
    @Size(max = 100, message = "{field.size}")
    private String description;
    @Size(max = 10, message = "{field.size}")
    private String code = "";

    // Getters & Setters

    public Integer getIdProductFamily() {
        return idProductFamily;
    }

    public void setIdProductFamily(Integer idProductFamily) {
        this.idProductFamily = idProductFamily;
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
        TslProductFamily that = (TslProductFamily) o;
        return Objects.equals(idProductFamily, that.idProductFamily) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProductFamily, description);
    }

    @Override
    public String toString() {
        return "TslProductFamily{" +
                "idProductFamily=" + idProductFamily +
                ", description='" + description + '\'' +
                '}';
    }

}