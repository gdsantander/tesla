package com.gsantander.tesla.validators;

import com.gsantander.tesla.annotations.CBillValidation;
import com.gsantander.tesla.config.ApplicationConfig;
import com.gsantander.tesla.model.TslCBill;
import com.gsantander.tesla.model.TslCompany;
import com.gsantander.tesla.repositories.ICompanyRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class CBillValidator implements ConstraintValidator<CBillValidation, TslCBill> {

    private final ApplicationConfig applicationConfig;

    public CBillValidator(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    public boolean isValid(TslCBill tslCBill, ConstraintValidatorContext cxt) {
        cxt.disableDefaultConstraintViolation();
        TslCompany tslCompany = this.applicationConfig.getTslCompany(tslCBill.getIdCompany());
        if(!tslCompany.getCurrency().equals(tslCBill.getCurrency())) {
            if(tslCBill.getExchangeRate().equals(BigDecimal.ZERO)) {
                cxt.buildConstraintViolationWithTemplate("{cBill.exchangeRate.isZero}")
                        .addNode("exchangeRate").addConstraintViolation();
                return false;
            }
        }
        return true;
    }

}