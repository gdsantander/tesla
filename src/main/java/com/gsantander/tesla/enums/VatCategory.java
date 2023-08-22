package com.gsantander.tesla.enums;

import com.gsantander.tesla.interfaces.IEnum;
import com.gsantander.tesla.tools.TslFunctions;

public enum VatCategory implements IEnum {

    NONE("-"),
    RESPONSIBLE("vatCategoryResponsible"),
    EXEMPT("vatCategoryExempt"),
    SIMPLIFIED("vatCategorySimplified"),
    ENDCONSUMER("vatCategoryEndConsumer");

    private final String description;

    private VatCategory(String description) {
        this.description = description;
    }

    public String toString() {
        return TslFunctions.getMessage(this.description);
    }

}