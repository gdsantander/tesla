package com.gsantander.tesla.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gsantander.tesla.enums.UserPrivilege;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity(name = "Users")
public class TslUser implements UserDetails, Comparable<TslUser> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idUser")
    @SequenceGenerator(name = "idUser", sequenceName = "IDUSER", allocationSize = 1)
    private Integer idUser;
    @NotNull(message = "{field.notNull}")
    @Positive(message = "{field.positive}")
    private Integer idCompany;
    @NotBlank(message = "{field.notBlank}")
    @Size(max = 50, message = "{field.size}")
    private String lastName;
    @NotBlank(message = "{field.notBlank}")
    @Size(max = 50, message = "{field.size}")
    private String firstName;
    @Column(name = "Credentials_UserName")
    @Size(max = 50, message = "{field.size}")
    @NotBlank(message = "{field.notBlank}")
    private String credentialsUserName;
    @Column(name = "Credentials_Password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String credentialsPassword;
    @Email(message = "{email.invalid}")
    @NotBlank(message = "{field.notBlank}")
    @Size(max = 100, message = "{field.size}")
    private String email;
    @Column(name = "TID_Privileges")
    private UserPrivilege privileges = UserPrivilege.USER;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "UsersLinks",
               joinColumns = {@JoinColumn(name = "IdUser")},
               inverseJoinColumns = {@JoinColumn(name = "IdProfile")})
    @OrderBy("description ASC")
    //@JsonManagedReference
    @JsonIgnoreProperties("users")
    private Set<TslProfile> profiles = new HashSet<>();

    // Getters & Setters

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Integer idCompany) {
        this.idCompany = idCompany;
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

    public String getCredentialsUserName() {
        return credentialsUserName;
    }

    public void setCredentialsUserName(String credentialsUserName) {
        this.credentialsUserName = credentialsUserName;
    }

    public String getCredentialsPassword() {
        return credentialsPassword;
    }

    public void setCredentialsPassword(String credentialsPassword) {
        this.credentialsPassword = credentialsPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserPrivilege getPrivileges() {
        return privileges;
    }

    public void setPrivileges(UserPrivilege privileges) {
        this.privileges = privileges;
    }

    public Set<TslProfile> getProfiles() {
        return profiles;
    }

    public void setProfiles(Set<TslProfile> profiles) {
        this.profiles = profiles;
    }

    // Equals, HashCode, ToString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TslUser tslUser = (TslUser) o;
        return Objects.equals(idUser, tslUser.idUser) && Objects.equals(lastName, tslUser.lastName) && Objects.equals(firstName, tslUser.firstName) && Objects.equals(email, tslUser.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, lastName, firstName, email);
    }

    @Override
    public String toString() {
        return "TslUser{" +
                "idUser=" + idUser +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    // Comparators

    @Override
    public int compareTo(TslUser o) {
        return new CompareToBuilder()
                .append(this.getLastName(),o.getLastName())
                .append(this.getFirstName(),o.getFirstName())
                .append(this.hashCode(),o.hashCode())
                .toComparison();
    }

    // Methods

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = this.privileges.getAuthorities();
        return authorities;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return this.credentialsPassword;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return this.credentialsUserName;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

}
