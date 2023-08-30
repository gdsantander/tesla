package com.gsantander.tesla.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gsantander.tesla.annotations.DateValidation;
import com.gsantander.tesla.enums.DocumentCondition;
import com.gsantander.tesla.enums.Letter;
import com.gsantander.tesla.tools.TslConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity(name = "CBills")
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
    private Letter letter = Letter.X;
    private Integer preNumber = 0;
    private Integer number = 0;
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern= TslConstants.PATTERN_DATE_FORMAT, timezone = TslConstants.TIME_ZONE)
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
    private boolean annulled;

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

    public Letter getLetter() {
        return letter;
    }

    public void setLetter(Letter letter) {
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
                ", letter='" + letter.name() + '\'' +
                ", preNumber='" + preNumber + '\'' +
                ", number='" + number +
                '}';
    }

}