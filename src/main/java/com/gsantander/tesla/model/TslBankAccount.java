package com.gsantander.tesla.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gsantander.tesla.annotations.DateValidation;
import com.gsantander.tesla.enums.BankAccountType;
import com.gsantander.tesla.tools.TslConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity(name = "BankAccounts")
public class TslBankAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idBankAccount")
    @SequenceGenerator(name = "idBankAccount", sequenceName = "IDBANKACCOUNT", allocationSize = 1)
    private Integer idBankAccount;
    @NotNull(message = "{field.notNull}")
    @Positive(message = "{field.positive}")
    private Integer idCompany;
    @OneToOne()
    @JoinColumn(name = "IdBank")
    private TslBank bank;
    @Size(max = 100, message = "{field.size}")
    private String description = "";
    @Column(name = "TID_Type")
    private BankAccountType type = BankAccountType.NONE;
    @Column(name = "Account_Code")
    @Size(max = 30, message = "{field.size}")
    private String accountCode = "";
    @Column(name = "Account_Number")
    @Size(max = 30, message = "{field.size}")
    private String accountNumber = "";
    @OneToOne()
    @JoinColumn(name = "IdCurrency")
    private TslCurrency currency;
    @Size(max = 100, message = "{field.size}")
    private String alias = "";
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern= TslConstants.PATTERN_DATE_FORMAT, timezone = TslConstants.TIME_ZONE)
    @DateValidation
    private Date startDate;
    private BigDecimal startBalance = new BigDecimal(0);

    // Getters & Setters

    public TslBank getBank() {
        return bank;
    }

    public void setBank(TslBank bank) {
        this.bank = bank;
    }

    public Integer getIdBankAccount() {
        return idBankAccount;
    }

    public void setIdBankAccount(Integer idBankAccount) {
        this.idBankAccount = idBankAccount;
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

    public BankAccountType getType() {
        return type;
    }

    public void setType(BankAccountType type) {
        this.type = type;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public TslCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(TslCurrency currency) {
        this.currency = currency;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public BigDecimal getStartBalance() {
        return startBalance;
    }

    public void setStartBalance(BigDecimal startBalance) {
        this.startBalance = startBalance;
    }

    // Equals, HashCode, ToString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TslBankAccount that = (TslBankAccount) o;
        return Objects.equals(idBankAccount, that.idBankAccount) &&
               Objects.equals(bank, that.bank) &&
               Objects.equals(type, that.type) &&
               Objects.equals(currency, that.currency) &&
               Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBankAccount, bank, description);
    }

    @Override
    public String toString() {
        return "TslBankAccount{" +
                "idBankAccount=" + idBankAccount +
                ", bank='" + bank.getDescription() + '\'' +
                ", type='" + type.name() + '\'' +
                ", currency='" + currency.getDescription() + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}