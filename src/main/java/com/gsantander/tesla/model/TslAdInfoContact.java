package com.gsantander.tesla.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

@Entity(name = "AdInfoContacts")
public class TslAdInfoContact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idAdInfoContact")
    @SequenceGenerator(name = "idAdInfoContact", sequenceName = "IDADINFOCONTACT", allocationSize = 1)
    private Integer idAdInfoContact;
    @ManyToOne
    @JoinColumn(name = "IdAdInfo")
    @JsonBackReference
    private TslAdInfo adInfo;
    @NotBlank(message = "{field.notBlank}")
    @Size(max = 50, message = "{field.size}")
    private String lastName;
    @NotBlank(message = "{field.notBlank}")
    @Size(max = 50, message = "{field.size}")
    private String firstName;
    @Size(max = 100, message = "{field.size}")
    private String phone = "";
    @Size(max = 100, message = "{field.size}")
    private String cellPhone = "";
    @Size(max = 100, message = "{field.size}")
    @Email(message = "{email.invalid}")
    private String email = "";

    // Getters & Setters

    public Integer getIdAdInfoContact() {
        return idAdInfoContact;
    }

    public void setIdAdInfoContact(Integer idAdInfoContact) {
        this.idAdInfoContact = idAdInfoContact;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Equals, HashCode, ToString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TslAdInfoContact that = (TslAdInfoContact) o;
        return Objects.equals(idAdInfoContact, that.idAdInfoContact) && Objects.equals(lastName, that.lastName) && Objects.equals(firstName, that.firstName) && Objects.equals(phone, that.phone) && Objects.equals(cellPhone, that.cellPhone) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAdInfoContact, lastName, firstName, phone, cellPhone, email);
    }

    @Override
    public String toString() {
        return "TslAdInfoContact{" +
                "idAdInfoContact=" + idAdInfoContact +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", phone='" + phone + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
