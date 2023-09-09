package com.gsantander.tesla.validators;

import com.gsantander.tesla.annotations.DateValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.joda.time.DateTime;

import java.time.LocalDate;
import java.util.Date;

public class DateValidator implements ConstraintValidator<DateValidation, Date>
{
    public boolean isValid(Date date, ConstraintValidatorContext cxt) {
        if(date!=null) {
            DateTime dtDate = new DateTime(date);
            if(dtDate.getYear()<1900)
                return false;
            if(dtDate.getYear()>2200)
                return false;
        }
        return true;
    }

}