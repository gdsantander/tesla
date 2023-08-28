package com.gsantander.tesla.enums;

public enum TokenType {

    ACCESS_TOKEN, REFRESH_TOKEN;

    public int getExpirationInMinutes() {
        switch(this) {
            case ACCESS_TOKEN: return 1000;
            case REFRESH_TOKEN: return 1500;
        }
        return 0;
    }

}
