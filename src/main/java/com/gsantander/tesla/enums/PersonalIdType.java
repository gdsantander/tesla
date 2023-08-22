package com.gsantander.tesla.enums;

import com.gsantander.tesla.interfaces.IEnum;
import com.gsantander.tesla.tools.TslFunctions;

public enum PersonalIdType implements IEnum {

    NONE("-"),
    DNI("personalIdTypeDNI");

    private final String description;

    private PersonalIdType(String description) {
        this.description = description;
    }

    public String toString() {
        return TslFunctions.getMessage(this.description);
    }

}