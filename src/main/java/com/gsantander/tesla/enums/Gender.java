package com.gsantander.tesla.enums;

import com.gsantander.tesla.interfaces.IEnum;
import com.gsantander.tesla.tools.TslFunctions;

public enum Gender implements IEnum {

    NONE("-"),
    MALE("genderMale"),
    FEMALE("genderFemale");

    private final String description;

    private Gender(String description) {
        this.description = description;
    }

    public String toString() {
        return TslFunctions.getMessage(this.description);
    }

}