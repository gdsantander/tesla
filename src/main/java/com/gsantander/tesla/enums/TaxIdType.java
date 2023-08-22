package com.gsantander.tesla.enums;

import com.gsantander.tesla.interfaces.IEnum;
import com.gsantander.tesla.tools.TslFunctions;

public enum TaxIdType implements IEnum {

    NONE("-"),
    CUIT("taxIdTypeCUIT");

    private final String description;

    private TaxIdType(String description) {
        this.description = description;
    }

    public String toString() {
        return TslFunctions.getMessage(this.description);
    }

}