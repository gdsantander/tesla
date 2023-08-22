package com.gsantander.tesla.validators;

import com.gsantander.tesla.annotations.DateValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class DateValidator implements ConstraintValidator<DateValidation, LocalDate>
{
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext cxt) {
        if(localDate!=null) {
            if(localDate.getYear()<1900)
                return false;
            if(localDate.getYear()>2200)
                return false;
        }
        return true;
    }

}