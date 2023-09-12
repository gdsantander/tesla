package com.gsantander.tesla.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

@Entity(name = "ProductsArticles")
public class TslProductArticle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idProductArticle")
    @SequenceGenerator(name = "idProductArticle", sequenceName = "IDPRODUCTARTICLE", allocationSize = 1)
    private Integer idProductArticle;
    @NotNull(message = "{field.notNull}")
    @Positive(message = "{field.positive}")
    private Integer idCompany;
    @NotBlank(message = "{field.notBlank}")
    @Size(max = 100, message = "{field.size}")
    private String description;
    @Size(max = 10, message = "{field.size}")
    private String code = "";

    // Getters & Setters

    public Integer getIdProductArticle() {
        return idProductArticle;
    }

    public void setIdProductArticle(Integer idProductArticle) {
        this.idProductArticle = idProductArticle;
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
        TslProductArticle that = (TslProductArticle) o;
        return Objects.equals(idProductArticle, that.idProductArticle) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProductArticle, description);
    }

    @Override
    public String toString() {
        return "TslProductArticle{" +
                "idProductArticle=" + idProductArticle +
                ", description='" + description + '\'' +
                '}';
    }

}