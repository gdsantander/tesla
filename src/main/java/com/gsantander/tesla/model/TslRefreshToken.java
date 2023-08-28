package com.gsantander.tesla.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity(name = "Tokens")
public class TslRefreshToken implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idToken")
    @SequenceGenerator(name = "idToken", sequenceName = "IDTOKEN", allocationSize = 1)
    private Integer idToken;
    @Temporal(TemporalType.DATE)
    private Date expirationDate;
    private String refreshToken;
    @OneToOne
    @JoinColumn(name = "IdUser")
    private TslUser user;
    private boolean systemAdmin;

    // Getters & Setters

    public Integer getIdToken() {
        return idToken;
    }

    public void setIdToken(Integer idToken) {
        this.idToken = idToken;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public TslUser getUser() {
        return user;
    }

    public void setUser(TslUser user) {
        this.user = user;
    }

    public boolean isSystemAdmin() {
        return systemAdmin;
    }

    public void setSystemAdmin(boolean systemAdmin) {
        this.systemAdmin = systemAdmin;
    }

    // Equals, HashCode, ToString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TslRefreshToken that = (TslRefreshToken) o;
        return Objects.equals(idToken, that.idToken) && Objects.equals(refreshToken, that.refreshToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idToken, refreshToken);
    }

    @Override
    public String toString() {
        return "TslRefreshToken{" +
                "idToken=" + idToken +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }

}
