package com.gsantander.tesla.validators;

import com.gsantander.tesla.annotations.CBillValidation;
import com.gsantander.tesla.model.TslCBill;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CBillValidator implements ConstraintValidator<CBillValidation, TslCBill> {

    public boolean isValid(TslCBill tslCBill, ConstraintValidatorContext cxt) {
        return true;
    }

}