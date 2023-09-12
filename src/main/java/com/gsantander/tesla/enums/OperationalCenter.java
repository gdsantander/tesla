package com.gsantander.tesla.enums;

import com.gsantander.tesla.interfaces.IEnum;
import com.gsantander.tesla.tools.TslFunctions;

public enum OperationalCenter implements IEnum {

    NONE("-","NONE"),
    PRODUCTS("operationalCenterProducts","PRD"),
    AGREEMENTS("operationalCenterAgreements","AGR"),
    TASKS("operationalCenterTasks","TSK");

    private final String description;
    private final String code;

    private OperationalCenter(String description, String code) {
        this.description = description;
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public String toString() {
        return TslFunctions.getMessage(this.description);
    }

}