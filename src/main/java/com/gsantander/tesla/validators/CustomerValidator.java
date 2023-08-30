package com.gsantander.tesla.validators;

import com.gsantander.tesla.annotations.CustomerValidation;
import com.gsantander.tesla.model.TslCustomer;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

public class CustomerValidator implements ConstraintValidator<CustomerValidation, TslCustomer> {

    public boolean isValid(TslCustomer tslCustomer, ConstraintValidatorContext cxt) {
        cxt.disableDefaultConstraintViolation();
        switch (tslCustomer.getPerson()) {
            case NATURAL -> {
                if(StringUtils.isBlank(tslCustomer.getLastName())) {
                    cxt.buildConstraintViolationWithTemplate("{customer.lastName.isEmpty}")
                            .addNode("lastName").addConstraintViolation();
                    return false;
                }
                if(StringUtils.isBlank(tslCustomer.getFirstName())) {
                    cxt.buildConstraintViolationWithTemplate("{customer.firstName.isEmpty}")
                            .addNode("firstName").addConstraintViolation();
                    return false;
                }
                break;
            }
            case LEGAL -> {
                if(StringUtils.isBlank(tslCustomer.getDescription())) {
                    cxt.buildConstraintViolationWithTemplate("{customer.description.isEmpty}")
                            .addNode("description").addConstraintViolation();
                    return false;
                }
                break;
            }
        }
        return true;
    }

}