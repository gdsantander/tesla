package com.gsantander.tesla.enums;

import java.util.Locale;

import com.gsantander.tesla.interfaces.IEnum;
import com.gsantander.tesla.tools.TslFunctions;

public enum Language implements IEnum {

    SPANISH("spanish","es"),
    ENGLISH("english","en");

    private final String description;
    private final String code;

    private Language(String description, String code) {
        this.description = description;
        this.code = code;
    }

    public String toString() {
        return TslFunctions.getMessage(this.description);
    }

    public String getCode() {
        return this.code;
    }

    public Locale getLocale() {
        switch(this) {
            case SPANISH: {
                return new Locale("es","AR");
            }
            case ENGLISH: {
                return new Locale("en","US");
            }
        }
        return null;
    }

}