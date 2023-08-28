package com.gsantander.tesla.enums;

import com.gsantander.tesla.interfaces.IEnum;
import com.gsantander.tesla.tools.TslFunctions;

public enum CompanyStatus implements IEnum {

    NONE("-"),
    PRODUCTION("companyStatusProduction"),
    DEMO("companyStatusDemo"),
    OUTOFSERVICE("companyStatusOutOfService");

    private final String description;

    private CompanyStatus(String description) {
        this.description = description;
    }

    public String toString() {
        return TslFunctions.getMessage(this.description);
    }

}