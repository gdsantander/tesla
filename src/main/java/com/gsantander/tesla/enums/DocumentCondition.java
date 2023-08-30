package com.gsantander.tesla.enums;

import com.gsantander.tesla.interfaces.IEnum;
import com.gsantander.tesla.tools.TslFunctions;

public enum DocumentCondition implements IEnum {

    CASH("documentConditionCash"),
    CURRENTACCOUNT("documentConditionCurrentAccount");

    private final String description;

    private DocumentCondition(String description) {
        this.description = description;
    }

    public String toString() {
        return TslFunctions.getMessage(this.description);
    }

}