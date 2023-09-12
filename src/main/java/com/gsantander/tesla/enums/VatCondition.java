package com.gsantander.tesla.enums;

import com.gsantander.tesla.interfaces.IEnum;
import com.gsantander.tesla.tools.TslFunctions;

public enum VatCondition implements IEnum {

    TAXABLE("vatConditionTaxable",true),
    NONTAXABLE("vatConditionNonTaxable",false),
    EXEMPT("vatConditionExempt",false);

    private final String description;
    private final boolean taxable;

    private VatCondition(String description, boolean taxable) {
        this.description = description;
        this.taxable = taxable;
    }

    public boolean isTaxable() {
        return this.taxable;
    }

    public String toString() {
        return TslFunctions.getMessage(this.description);
    }

}