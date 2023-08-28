package com.gsantander.tesla.model;

import com.gsantander.tesla.enums.PersonType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

@Entity(name = "Customers")
public class TslCustomer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idCustomer")
    @SequenceGenerator(name = "idCustomer", sequenceName = "IDCUSTOMER", allocationSize = 1)
    private Integer idCustomer;
    @NotNull(message = "{field.notNull}")
    @Positive(message = "{field.positive}")
    private Integer idCompany;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdAdInfo")
    private TslAdInfo adInfo;
    @Size(max = 50, message = "{field.size}")
    private String lastName = "";
    @Size(max = 50, message = "{field.size}")
    private String firstName = "";
    @Size(max = 100, message = "{field.size}")
    private String description = "";
    @Column(name = "TID_Person")
    private PersonType person = PersonType.LEGAL;

    // Getters & Setters

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Integer getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Integer idCompany) {
        this.idCompany = idCompany;
    }

    public TslAdInfo getAdInfo() {
        return adInfo;
    }

    public void setAdInfo(TslAdInfo adInfo) {
        this.adInfo = adInfo;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PersonType getPerson() {
        return person;
    }

    public void setPerson(PersonType person) {
        this.person = person;
    }

    // Methods

    public String getDescriptionBuilt() {
        switch (this.person) {
            case NATURAL -> {
                return this.lastName + ", " + this.firstName;
            }
            case LEGAL -> {
                return this.description;
            }
        }
        return "";
    }

    // Equals, HashCode, ToString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TslCustomer that = (TslCustomer) o;
        return Objects.equals(idCustomer, that.idCustomer) &&
               Objects.equals(this.getDescriptionBuilt(), that.getDescriptionBuilt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCustomer, this.getDescriptionBuilt());
    }

    @Override
    public String toString() {
        return "TslCustomer{" +
                "idCustomer=" + idCustomer +
                ", description='" + this.getDescriptionBuilt() +
                '}';
    }

}