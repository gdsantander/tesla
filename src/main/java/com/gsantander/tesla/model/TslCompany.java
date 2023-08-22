package com.gsantander.tesla.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

@Entity(name = "Companies")
public class TslCompany implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idCompany")
    @SequenceGenerator(name = "idCompany", sequenceName = "IDCOMPANY", allocationSize = 1)
    private Integer idCompany;
    @NotBlank(message = "{field.notBlank}")
    @Size(max = 100, message = "{field.size}")
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdAdInfo")
    protected TslAdInfo adInfo;

    // Getters & Setters

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

    public TslAdInfo getAdInfo() {
        return adInfo;
    }

    public void setAdInfo(TslAdInfo adInfo) {
        this.adInfo = adInfo;
    }

    // Equals, HashCode, ToString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TslCompany that = (TslCompany) o;
        return Objects.equals(idCompany, that.idCompany) && Objects.equals(description, that.description) && Objects.equals(adInfo, that.adInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCompany, description, adInfo);
    }

    @Override
    public String toString() {
        return "TslCompany{" +
                "idCompany=" + idCompany +
                ", description='" + description + '\'' +
                '}';
    }

}
