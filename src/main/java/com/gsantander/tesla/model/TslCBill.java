package com.gsantander.tesla.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gsantander.tesla.annotations.CBillValidation;
import com.gsantander.tesla.annotations.DateValidation;
import com.gsantander.tesla.enums.DocumentCondition;
import com.gsantander.tesla.enums.Letter;
import com.gsantander.tesla.enums.OperationalCenter;
import com.gsantander.tesla.tools.TslConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "CBills")
@CBillValidation
public class TslCBill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idCBill")
    @SequenceGenerator(name = "idCBill", sequenceName = "IDCBILL", allocationSize = 1)
    private Integer idCBill;
    @NotNull(message = "{field.notNull}")
    @Positive(message = "{field.positive}")
    private Integer idCompany;
    @OneToOne()
    @JoinColumn(name = "IdSalesPoint")
    private TslSalesPoint salesPoint;
    @OneToOne()
    @JoinColumn(name = "IdDocument")
    private TslDocument document;
    private String letter = Letter.X.name();
    private Integer preNumber = 0;
    private Integer number = 0;
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern= TslConstants.PATTERN_TIME_FORMAT_JSON, timezone = TslConstants.TIME_ZONE)
    @DateValidation
    private Date creditDate = new Date();
    @OneToOne()
    @JoinColumn(name = "IdCustomer")
    private TslCustomer customer;
    @OneToOne()
    @JoinColumn(name = "IdCurrency")
    private TslCurrency currency;
    private BigDecimal exchangeRate = new BigDecimal(0);
    @Column(name = "TID_Condition")
    private DocumentCondition condition = DocumentCondition.CURRENTACCOUNT;
    private String comments = "";
    private String operationalCenter = OperationalCenter.NONE.getCode();
    private boolean annulled;
    @OneToMany(mappedBy = "cBill",
               orphanRemoval = true,
               cascade = {CascadeType.ALL})
    @OrderBy("idCBillItem ASC")
    @JsonManagedReference
    private Set<TslCBillItem> items = new HashSet<>();
    @OneToMany(mappedBy = "cBill",
               orphanRemoval = true,
               cascade = {CascadeType.ALL})
    @OrderBy("dueDate ASC")
    @JsonManagedReference
    private Set<TslCBillDueDate> dueDates = new HashSet<>();
    @OneToMany(mappedBy = "cBill",
            orphanRemoval = true,
            cascade = {CascadeType.ALL})
    @JsonManagedReference
    private Set<TslCBillReference> references = new HashSet<>();

    // Getters & Setters

    public Integer getIdCBill() {
        return idCBill;
    }

    public void setIdCBill(Integer idCBill) {
        this.idCBill = idCBill;
    }

    public Integer getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Integer idCompany) {
        this.idCompany = idCompany;
    }

    public TslSalesPoint getSalesPoint() {
        return salesPoint;
    }

    public void setSalesPoint(TslSalesPoint salesPoint) {
        this.salesPoint = salesPoint;
    }

    public TslDocument getDocument() {
        return document;
    }

    public void setDocument(TslDocument document) {
        this.document = document;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public Integer getPreNumber() {
        return preNumber;
    }

    public void setPreNumber(Integer preNumber) {
        this.preNumber = preNumber;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getCreditDate() {
        return creditDate;
    }

    public void setCreditDate(Date creditDate) {
        this.creditDate = creditDate;
    }

    public TslCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(TslCustomer customer) {
        this.customer = customer;
    }

    public TslCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(TslCurrency currency) {
        this.currency = currency;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public DocumentCondition getCondition() {
        return condition;
    }

    public void setCondition(DocumentCondition condition) {
        this.condition = condition;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean isAnnulled() {
        return annulled;
    }

    public void setAnnulled(boolean annulled) {
        this.annulled = annulled;
    }

    public Set<TslCBillItem> getItems() {
        return items;
    }

    public void setItems(Set<TslCBillItem> items) {
        this.items = items;
    }

    public String getOperationalCenter() {
        return operationalCenter;
    }

    public void setOperationalCenter(String operationalCenter) {
        this.operationalCenter = operationalCenter;
    }

    public Set<TslCBillDueDate> getDueDates() {
        return dueDates;
    }

    public void setDueDates(Set<TslCBillDueDate> dueDates) {
        this.dueDates = dueDates;
    }

    public Set<TslCBillReference> getReferences() {
        return references;
    }

    public void setReferences(Set<TslCBillReference> references) {
        this.references = references;
    }

    // Equals, HashCode, ToString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TslCBill that = (TslCBill) o;
        return Objects.equals(idCBill, that.idCBill) &&
               Objects.equals(salesPoint, that.salesPoint) &&
               Objects.equals(document, that.document) &&
               Objects.equals(letter, that.letter) &&
               Objects.equals(preNumber, that.preNumber) &&
               Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCBill, salesPoint, document, letter, preNumber, number);
    }

    @Override
    public String toString() {
        return "TslCBill{" +
                "idCBill=" + idCBill +
                ", salesPoint='" + salesPoint.getDescription() + '\'' +
                ", document='" + document.getDescription() + '\'' +
                ", letter='" + letter + '\'' +
                ", preNumber='" + preNumber + '\'' +
                ", number='" + number +
                '}';
    }

    // Methods

    public BigDecimal getAmount() {
        BigDecimal amount = new BigDecimal(0);
        for(TslCBillItem cBillItem:this.items) {
            amount = amount.add(cBillItem.getAmount());
        }
        return amount;
    }

}