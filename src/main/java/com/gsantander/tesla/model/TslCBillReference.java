package com.gsantander.tesla.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gsantander.tesla.tools.TslConstants;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "CBillsReferences")
public class TslCBillReference implements Serializable {

    private static int keyCounter = TslConstants.KEY_COUNTER_HIGH_VALUE;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idCBillReference")
    @SequenceGenerator(name = "idCBillReference", sequenceName = "IDCBILLREFERENCE", allocationSize = 1)
    private Integer idCBillReference;
    @ManyToOne
    @JoinColumn(name = "IdCBill")
    @JsonBackReference
    private TslCBill cBill;
    @OneToOne()
    @JoinColumn(name = "IdCBillDueDate")
    private TslCBillDueDate cBillDueDate;
    private BigDecimal amount = new BigDecimal(0);
    private boolean annulled;
    @Transient
    private final int key = TslCBillReference.getNextKey();

    // Getters & Setters

    public Integer getIdCBillReference() {
        return idCBillReference;
    }

    public void setIdCBillReference(Integer idCBillReference) {
        this.idCBillReference = idCBillReference;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TslCBillDueDate getcBillDueDate() {
        return cBillDueDate;
    }

    public void setcBillDueDate(TslCBillDueDate cBillDueDate) {
        this.cBillDueDate = cBillDueDate;
    }

    // Equals, HashCode, ToString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TslCBillReference that = (TslCBillReference) o;
        return Objects.equals(idCBillReference, that.idCBillReference) && Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCBillReference, key);
    }

    @Override
    public String toString() {
        return "TslCBillReference{" +
                "idCBillReference=" + idCBillReference +
                ", key='" + key +
                '}';
    }

    public static int getNextKey() {
        keyCounter--;
        return keyCounter;
    }

}
