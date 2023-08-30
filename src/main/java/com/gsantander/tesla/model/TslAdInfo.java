package com.gsantander.tesla.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gsantander.tesla.annotations.DateValidation;
import com.gsantander.tesla.annotations.TaxIdValidation;
import com.gsantander.tesla.enums.*;
import com.gsantander.tesla.tools.TslConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "AdInfo")
@TaxIdValidation
public class TslAdInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idAdInfo")
    @SequenceGenerator(name = "idAdInfo", sequenceName = "IDADINFO", allocationSize = 1)
    private Integer idAdInfo;
    @Column(name = "TID_Type")
    private AdInfoType type = AdInfoType.ORGANIZATIONAL;
    @Email(message = "{email.invalid}")
    @Size(max = 100, message = "{field.size}")
    private String email = "";
    @Size(max = 100, message = "{field.size}")
    private String cellPhone = "";
    @Size(max = 100, message = "{field.size}")
    private String phone = "";
    @Size(max = 100, message = "{field.size}")
    private String street = "";
    @Column(name = "Street_Number")
    @Size(max = 10, message = "{field.size}")
    private String streetNumber = "";
    @Column(name = "Street_Floor")
    @Size(max = 10, message = "{field.size}")
    private String streetFloor = "";
    @Column(name = "Street_Department")
    @Size(max = 10, message = "{field.size}")
    private String streetDepartment = "";
    @Column(name = "Street_Between_1")
    @Size(max = 100, message = "{field.size}")
    private String streetBetween1 = "";
    @Column(name = "Street_Between_2")
    @Size(max = 100, message = "{field.size}")
    private String streetBetween2 = "";
    @Column(name = "Street_Comments")
    @Size(max = 100, message = "{field.size}")
    private String streetComments = "";
    @OneToOne
    @JoinColumn(name = "IdTerritory")
    private TslTerritory territory;
    @Column(name = "TID_TaxIdType")
    private TaxIdType taxIdType = TaxIdType.NONE;
    @Size(max = 30, message = "{field.size}")
    private String taxId = "";
    @Size(max = 100, message = "{field.size}")
    private String legalName = "";
    @Column(name = "TID_VatCategory")
    private VatCategory vatCategory = VatCategory.NONE;
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern= TslConstants.PATTERN_DATE_FORMAT, timezone = TslConstants.TIME_ZONE)
    @DateValidation
    private Date birthDate;
    @Column(name = "TID_Gender")
    private Gender gender = Gender.NONE;
    @Column(name = "TID_PersonalIdType")
    private PersonalIdType personalIdType = PersonalIdType.NONE;
    @Column(name = "TID_BirthPlace")
    private Country birthplace = Country.NONE;
    @Column(name = "TID_Nationality")
    private Country nationality = Country.NONE;
    @Range(min = 0, max = 99999999, message = "{field.range}")
    private Integer personalId = 0;
    private String comments = "";
    @OneToMany(mappedBy = "adInfo",
               orphanRemoval = true,
               cascade = {CascadeType.ALL})
    @OrderBy("lastName ASC, firstName ASC")
    @JsonManagedReference
    private Set<TslAdInfoContact> contacts = new HashSet<>();

    // Getters & Setters

    public AdInfoType getType() {
        return type;
    }

    public void setType(AdInfoType type) {
        this.type = type;
    }

    public Integer getIdAdInfo() {
        return idAdInfo;
    }

    public void setIdAdInfo(Integer idAdInfo) {
        this.idAdInfo = idAdInfo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetFloor() {
        return streetFloor;
    }

    public void setStreetFloor(String streetFloor) {
        this.streetFloor = streetFloor;
    }

    public String getStreetDepartment() {
        return streetDepartment;
    }

    public void setStreetDepartment(String streetDepartment) {
        this.streetDepartment = streetDepartment;
    }

    public String getStreetBetween1() {
        return streetBetween1;
    }

    public void setStreetBetween1(String streetBetween1) {
        this.streetBetween1 = streetBetween1;
    }

    public String getStreetBetween2() {
        return streetBetween2;
    }

    public void setStreetBetween2(String streetBetween2) {
        this.streetBetween2 = streetBetween2;
    }

    public String getStreetComments() {
        return streetComments;
    }

    public void setStreetComments(String streetComments) {
        this.streetComments = streetComments;
    }

    public TslTerritory getTerritory() {
        return territory;
    }

    public void setTerritory(TslTerritory territory) {
        this.territory = territory;
    }

    public TaxIdType getTaxIdType() {
        return taxIdType;
    }

    public void setTaxIdType(TaxIdType taxIdType) {
        this.taxIdType = taxIdType;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public VatCategory getVatCategory() {
        return vatCategory;
    }

    public void setVatCategory(VatCategory vatCategory) {
        this.vatCategory = vatCategory;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public PersonalIdType getPersonalIdType() {
        return personalIdType;
    }

    public void setPersonalIdType(PersonalIdType personalIdType) {
        this.personalIdType = personalIdType;
    }

    public Country getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(Country birthplace) {
        this.birthplace = birthplace;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public Integer getPersonalId() {
        return personalId;
    }

    public void setPersonalId(Integer personalId) {
        this.personalId = personalId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Set<TslAdInfoContact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<TslAdInfoContact> contacts) {
        this.contacts = contacts;
    }

    // Equals, HashCode, ToString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TslAdInfo tslAdInfo = (TslAdInfo) o;
        return Objects.equals(idAdInfo, tslAdInfo.idAdInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAdInfo);
    }

    @Override
    public String toString() {
        return "TslAdInfo{" +
                "idAdInfo=" + idAdInfo +
                '}';
    }

}