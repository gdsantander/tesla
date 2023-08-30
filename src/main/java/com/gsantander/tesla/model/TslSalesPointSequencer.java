package com.gsantander.tesla.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;

@Entity(name = "SalesPointsSequencers")
public class TslSalesPointSequencer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idSalesPointSequencer")
    @SequenceGenerator(name = "idSalesPointSequencer", sequenceName = "IDSALESPOINTSEQUENCER", allocationSize = 1)
    private Integer idSalesPointSequencer;
    @ManyToOne
    @JoinColumn(name = "IdSalesPoint")
    @JsonBackReference
    private TslSalesPoint salesPoint;
    @OneToOne()
    @JoinColumn(name = "IdDocument")
    @NotNull(message = "{field.notNull}")
    private TslDocument document;
    @Column(name = "Number_A")
    private Integer numberA = 1;
    @Column(name = "Number_B")
    private Integer numberB = 1;
    @Column(name = "Number_C")
    private Integer numberC = 1;
    @Column(name = "Number_E")
    private Integer numberE = 1;
    @Column(name = "Number_M")
    private Integer numberM = 1;
    @Column(name = "Number_X")
    private Integer numberX = 1;

    // Getters & Setters

    public Integer getIdSalesPointSequencer() {
        return idSalesPointSequencer;
    }

    public void setIdSalesPointSequencer(Integer idSalesPointSequencer) {
        this.idSalesPointSequencer = idSalesPointSequencer;
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

    public Integer getNumberA() {
        return numberA;
    }

    public void setNumberA(Integer numberA) {
        this.numberA = numberA;
    }

    public Integer getNumberB() {
        return numberB;
    }

    public void setNumberB(Integer numberB) {
        this.numberB = numberB;
    }

    public Integer getNumberC() {
        return numberC;
    }

    public void setNumberC(Integer numberC) {
        this.numberC = numberC;
    }

    public Integer getNumberE() {
        return numberE;
    }

    public void setNumberE(Integer numberE) {
        this.numberE = numberE;
    }

    public Integer getNumberM() {
        return numberM;
    }

    public void setNumberM(Integer numberM) {
        this.numberM = numberM;
    }

    public Integer getNumberX() {
        return numberX;
    }

    public void setNumberX(Integer numberX) {
        this.numberX = numberX;
    }

    // Equals, HashCode, ToString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TslSalesPointSequencer that = (TslSalesPointSequencer) o;
        return Objects.equals(idSalesPointSequencer, that.idSalesPointSequencer) && Objects.equals(document, that.document);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSalesPointSequencer, document);
    }

    @Override
    public String toString() {
        return "TslSalesPointSequencer{" +
                "idSalesPointSequencer=" + idSalesPointSequencer +
                ", document='" + document.getDescription() +
                '}';
    }

}
