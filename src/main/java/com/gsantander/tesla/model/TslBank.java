package com.gsantander.tesla.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

@Entity(name = "Banks")
public class TslBank implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idBank")
    @SequenceGenerator(name = "idBank", sequenceName = "IDBANK", allocationSize = 1)
    private Integer idBank;
    @NotBlank(message = "{field.notBlank}")
    @Size(max = 100, message = "{field.size}")
    private String description;
    @Size(max = 10, message = "{field.size}")
    private String code = "";

    // Getters & Setters

    public Integer getIdBank() {
        return idBank;
    }

    public void setIdBank(Integer idBank) {
        this.idBank = idBank;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        TslBank that = (TslBank) o;
        return Objects.equals(idBank, that.idBank) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBank, description);
    }

    @Override
    public String toString() {
        return "TslBank{" +
                "idBank=" + idBank +
                ", description='" + description + '\'' +
                '}';
    }

}