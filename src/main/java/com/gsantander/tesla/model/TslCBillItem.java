package com.gsantander.tesla.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gsantander.tesla.annotations.CBillItemValidation;
import com.gsantander.tesla.annotations.CBillValidation;
import com.gsantander.tesla.enums.VatCondition;
import com.gsantander.tesla.tools.TslConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "CBillsItems")
@CBillItemValidation
public class TslCBillItem implements Serializable {

    private static int keyCounter = TslConstants.KEY_COUNTER_HIGH_VALUE;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idCBillItem")
    @SequenceGenerator(name = "idCBillItem", sequenceName = "IDCBILLITEM", allocationSize = 1)
    private Integer idCBillItem;
    @ManyToOne
    @JoinColumn(name = "IdCBill")
    @JsonBackReference
    private TslCBill cBill;
    @OneToOne()
    @JoinColumn(name = "IdProduct")
    private TslProduct product;
    private BigDecimal unitPrice = new BigDecimal(0);
    @Min(value = 1, message = "{field.min}")
    private BigDecimal quantity = new BigDecimal(1);
    private String comments = "";
    @Column(name = "TID_VatCondition")
    private VatCondition vatCondition = VatCondition.TAXABLE;
    @OneToOne()
    @JoinColumn(name = "IdVatAliquot")
    private TslVatAliquot vatAliquot;
    @Column(name = "Taxes_VatAliquot")
    private BigDecimal taxesVatAliquot = new BigDecimal(0);
    @Column(name = "Taxes_Vat")
    private BigDecimal taxesVat = new BigDecimal(0);
    private boolean annulled;
    @Transient
    private final int key = TslCBillItem.getNextKey();

    // Getters & Setters

    public Integer getIdCBillItem() {
        return idCBillItem;
    }

    public void setIdCBillItem(Integer idCBillItem) {
        this.idCBillItem = idCBillItem;
    }

    public TslCBill getcBill() {
        return cBill;
    }

    public void setcBill(TslCBill cBill) {
        this.cBill = cBill;
    }

    public TslProduct getProduct() {
        return product;
    }

    public void setProduct(TslProduct product) {
        this.product = product;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public VatCondition getVatCondition() {
        return vatCondition;
    }

    public void setVatCondition(VatCondition vatCondition) {
        this.vatCondition = vatCondition;
        if(!this.vatCondition.isTaxable()) {
            this.setVatAliquot(null);
        }
    }

    public TslVatAliquot getVatAliquot() {
        return vatAliquot;
    }

    public void setVatAliquot(TslVatAliquot vatAliquot) {
        this.vatAliquot = vatAliquot;
        this.setTaxesVatAliquot(new BigDecimal(0));
        if(this.vatAliquot!=null) {
            this.setTaxesVatAliquot(this.vatAliquot.getAliquot());
        }
    }

    public boolean isAnnulled() {
        return annulled;
    }

    public void setAnnulled(boolean annulled) {
        this.annulled = annulled;
    }

    public BigDecimal getTaxesVatAliquot() {
        return taxesVatAliquot;
    }

    public void setTaxesVatAliquot(BigDecimal taxesVatAliquot) {
        this.taxesVatAliquot = taxesVatAliquot;
        this.setTaxesVat(new BigDecimal(0));
        if(!this.taxesVatAliquot.equals(BigDecimal.ZERO)) {
            BigDecimal vat = this.getSubtotal().multiply(this.taxesVatAliquot).divide(new BigDecimal(100));
            this.setTaxesVat(vat);
        }
    }

    public BigDecimal getTaxesVat() {
        return taxesVat;
    }

    public void setTaxesVat(BigDecimal taxesVat) {
        this.taxesVat = taxesVat;
    }

    // Equals, HashCode, ToString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TslCBillItem that = (TslCBillItem) o;
        return Objects.equals(idCBillItem, that.idCBillItem) && Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCBillItem, key);
    }

    @Override
    public String toString() {
        return "TslCBillItem{" +
                "idCBillItem=" + idCBillItem +
                ", key='" + key +
                '}';
    }

    public static int getNextKey() {
        keyCounter--;
        return keyCounter;
    }

    // Methods

    public BigDecimal getSubtotal() {
        return this.unitPrice.multiply(this.quantity);
    }

    public BigDecimal getAmount() {
        return this.getSubtotal().add(this.taxesVat);
    }

}
