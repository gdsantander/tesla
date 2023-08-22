package com.gsantander.tesla.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "Profiles")
public class TslProfile implements Serializable, Comparable<TslProfile> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idProfile")
    @SequenceGenerator(name = "idProfile", sequenceName = "IDPROFILE", allocationSize = 1)
    private Integer idProfile;
    @NotNull(message = "{field.notNull}")
    @Positive(message = "{field.positive}")
    private Integer idCompany;
    @NotBlank(message = "{field.notBlank}")
    @Size(max = 100, message = "{field.size}")
    private String description;
    @ManyToMany(mappedBy = "profiles",
                fetch = FetchType.LAZY)
    @OrderBy("lastName ASC, firstName ASC")
    //@JsonBackReference
    @JsonIgnoreProperties("profiles")
    private Set<TslUser> users = new HashSet<>();

    // Getters & Setters

    public Integer getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(Integer idProfile) {
        this.idProfile = idProfile;
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

    public Set<TslUser> getUsers() {
        return users;
    }

    public void setUsers(Set<TslUser> users) {
        this.users = users;
    }

    // Equals, HashCode, ToString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TslProfile that = (TslProfile) o;
        return Objects.equals(idProfile, that.idProfile) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProfile, description);
    }

    @Override
    public String toString() {
        return "TslProfile{" +
                "idProfile=" + idProfile +
                ", description='" + description + '\'' +
                '}';
    }

    // Comparators

    @Override
    public int compareTo(TslProfile o) {
        return new CompareToBuilder()
                        .append(this.getDescription(),o.getDescription())
                        .append(this.hashCode(),o.hashCode())
                        .toComparison();
    }

}
