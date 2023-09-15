package com.gsantander.tesla.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gsantander.tesla.annotations.DateValidation;
import com.gsantander.tesla.tools.TslConstants;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity(name = "CBillsDueDates")
public class TslCBillDueDate implements Serializable {

    private static int keyCounter = TslConstants.KEY_COUNTER_HIGH_VALUE;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idCBillDueDate")
    @SequenceGenerator(name = "idCBillDueDate", sequenceName = "IDCBILLDUEDATE", allocationSize = 1)
    private Integer idCBillDueDate;
    @ManyToOne
    @JoinColumn(name = "IdCBill")
    @JsonBackReference
    private TslCBill cBill;
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern= TslConstants.PATTERN_TIME_FORMAT_JSON, timezone = TslConstants.TIME_ZONE)
    @DateValidation
    private Date dueDate = new Date();
    private BigDecimal amount = new BigDecimal(0);
    private boolean annulled;
    @Transient
    private final int key = TslCBillDueDate.getNextKey();

    // Getters & Setters

    public Integer getIdCBillDueDate() {
        return idCBillDueDate;
    }

    public void setIdCBillDueDate(Integer idCBillDueDate) {
        this.idCBillDueDate = idCBillDueDate;
    }

    public TslCBill getcBill() {
        return cBill;
    }

    public void setcBill(TslCBill cBill) {
        this.cBill = cBill;
    }

    public boolean isAnnulled() {
        return annulled;
    }

    public void setAnnulled(boolean annulled) {
        this.annulled = annulled;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    // Equals, HashCode, ToString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TslCBillDueDate that = (TslCBillDueDate) o;
        return Objects.equals(idCBillDueDate, that.idCBillDueDate) && Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCBillDueDate, key);
    }

    @Override
    public String toString() {
        return "TslCBillDueDate{" +
                "idCBillDueDate=" + idCBillDueDate +
                ", key='" + key +
                '}';
    }

    public static int getNextKey() {
        keyCounter--;
        return keyCounter;
    }

}
