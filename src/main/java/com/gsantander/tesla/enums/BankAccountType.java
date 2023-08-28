package com.gsantander.tesla.enums;

import com.gsantander.tesla.interfaces.IEnum;
import com.gsantander.tesla.tools.TslFunctions;

public enum BankAccountType implements IEnum {

    NONE("-"),
    SAVINGS("bankAccountSavings"),
    CHECKING("bankAccountChecking");

    private final String description;

    private BankAccountType(String description) {
        this.description = description;
    }

    public String toString() {
        return TslFunctions.getMessage(this.description);
    }

}