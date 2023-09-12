package com.gsantander.tesla.model;

import com.gsantander.tesla.annotations.CustomerValidation;
import com.gsantander.tesla.annotations.ProductValidation;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

@Entity(name = "Products")
@ProductValidation
public class TslProduct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idProduct")
    @SequenceGenerator(name = "idProduct", sequenceName = "IDPRODUCT", allocationSize = 1)
    private Integer idProduct;
    @NotNull(message = "{field.notNull}")
    @Positive(message = "{field.positive}")
    private Integer idCompany;
    @OneToOne()
    @JoinColumn(name = "IdProductFamily")
    private TslProductFamily family;
    @OneToOne()
    @JoinColumn(name = "IdProductArticle")
    private TslProductArticle article;
    @OneToOne()
    @JoinColumn(name = "IdProductMeasure")
    private TslProductMeasure measure;
    @Size(max = 100, message = "{field.size}")
    private String description = "";
    @Size(max = 20, message = "{field.size}")
    private String code = "";

    // Getters & Setters

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
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

    public TslProductFamily getFamily() {
        return family;
    }

    public void setFamily(TslProductFamily family) {
        this.family = family;
    }

    public TslProductArticle getArticle() {
        return article;
    }

    public void setArticle(TslProductArticle article) {
        this.article = article;
    }

    public TslProductMeasure getMeasure() {
        return measure;
    }

    public void setMeasure(TslProductMeasure measure) {
        this.measure = measure;
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
        TslProduct that = (TslProduct) o;
        return Objects.equals(idProduct, that.idProduct) &&
               Objects.equals(family, that.family) &&
               Objects.equals(article, that.article) &&
               Objects.equals(measure, that.measure) &&
               Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduct, family, article, measure, description);
    }

    @Override
    public String toString() {
        return "TslProduct{" +
                "idProduct=" + idProduct +
                ", family='" + family.getDescription() + '\'' +
                ", article='" + article.getDescription() + '\'' +
                ", measure='" + measure.getDescription() + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    // Methods

    public String getCodeBuilt() {
        String codeBuilt = "";
        if(this.family!=null) {
            if(StringUtils.isNotBlank(codeBuilt))
                codeBuilt += "-";
            codeBuilt += this.family.getCode();
        }
        if(this.article!=null) {
            if(StringUtils.isNotBlank(codeBuilt))
                codeBuilt += "-";
            codeBuilt += this.article.getCode();
        }
        if(this.measure!=null) {
            if(StringUtils.isNotBlank(codeBuilt))
                codeBuilt += "-";
            codeBuilt += this.measure.getCode();
        }
        if(StringUtils.isNotBlank(this.code)) {
            if(StringUtils.isNotBlank(codeBuilt))
                codeBuilt += "-";
            codeBuilt += this.code;
        }
        return codeBuilt;
    }

    public String getDescriptionBuilt() {
        String descriptionBuild = "";
        if(this.family!=null) {
            if(StringUtils.isNotBlank(descriptionBuild))
                descriptionBuild += " | ";
            descriptionBuild += this.family.getDescription().trim();
        }
        if(this.article!=null) {
            if(StringUtils.isNotBlank(descriptionBuild))
                descriptionBuild += " | ";
            descriptionBuild += this.article.getDescription().trim();
        }
        if(this.measure!=null) {
            if(StringUtils.isNotBlank(descriptionBuild))
                descriptionBuild += " | ";
            descriptionBuild += this.measure.getDescription().trim();
        }
        if(StringUtils.isNotBlank(this.description)) {
            if(StringUtils.isNotBlank(descriptionBuild))
                descriptionBuild += " | ";
            descriptionBuild += this.description.trim();
        }
        return descriptionBuild;
    }

    public boolean usingParts() {
        if(this.family!=null)
            return true;
        if(this.article!=null)
            return true;
        if(this.measure!=null)
            return true;
        return false;
    }

}