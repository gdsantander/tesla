package com.gsantander.tesla.enums;

import com.gsantander.tesla.interfaces.IEnum;
import com.gsantander.tesla.tools.TslFunctions;

public enum PersonType implements IEnum {

    NATURAL("personTypeNatural"),
    LEGAL("personTypeLegal");

    private final String description;

    private PersonType(String description) {
        this.description = description;
    }

    public String toString() {
        return TslFunctions.getMessage(this.description);
    }

}