package com.gsantander.tesla.enums;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.gsantander.tesla.interfaces.IEnum;
import com.gsantander.tesla.tools.TslConstants;
import com.gsantander.tesla.tools.TslFunctions;

public enum UserPrivilege implements IEnum {

    ADMIN("userPrivilegesAdmin"),
    USER("userPrivilegesUser"),
    GUEST("userPrivilegesGuest");

    private final String description;

    private UserPrivilege(String description) {
        this.description = description;
    }

    public String toString() {
        return TslFunctions.getMessage(this.description);
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(TslConstants.AUTHORITY_READ));
        if(this==ADMIN||this==USER)
            authorities.add(new SimpleGrantedAuthority(TslConstants.AUTHORITY_WRITE));
        return authorities;
    }

}